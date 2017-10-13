package org.rapidpm.frp.reactive;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 *
 */
public class CompletableFutureQueue <T, R> {

  private Function<T, CompletableFuture<R>> resultFunction;

  private CompletableFutureQueue(Function<T, CompletableFuture<R>> resultFunction) {
    this.resultFunction = resultFunction;
  }

  public static <T, R> CompletableFutureQueue<T, R> define(Function<T, R> transformation) {
    return new CompletableFutureQueue<>(t -> CompletableFuture.completedFuture(transformation.apply(t)));
  }

  public <N> CompletableFutureQueue<T, N> thenCombineAsync(Function<R, N> nextTransformation) {
    final Function<T, CompletableFuture<N>> f = this.resultFunction
        .andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> nextTransformation.apply(v))));
    return new CompletableFutureQueue<>(f);
  }

  public Function<T, CompletableFuture<R>> resultFunction() {
    return this.resultFunction;
  }
}