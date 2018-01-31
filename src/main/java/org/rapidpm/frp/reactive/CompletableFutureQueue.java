package org.rapidpm.frp.reactive;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * <p>CompletableFutureQueue class.</p>
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public class CompletableFutureQueue<T, R> {

  private Function<T, CompletableFuture<R>> resultFunction;

  private CompletableFutureQueue(Function<T, CompletableFuture<R>> resultFunction) {
    this.resultFunction = resultFunction;
  }

  /**
   * <p>define.</p>
   *
   * @param transformation a {@link java.util.function.Function} object.
   * @param <T> a T object.
   * @param <R> a R object.
   * @return a {@link org.rapidpm.frp.reactive.CompletableFutureQueue} object.
   */
  public static <T, R> CompletableFutureQueue<T, R> define(Function<T, R> transformation) {
    return new CompletableFutureQueue<>(t -> CompletableFuture.completedFuture(transformation.apply(t)));
  }

  /**
   * <p>thenCombineAsync.</p>
   *
   * @param nextTransformation a {@link java.util.function.Function} object.
   * @param <N> a N object.
   * @return a {@link org.rapidpm.frp.reactive.CompletableFutureQueue} object.
   */
  public <N> CompletableFutureQueue<T, N> thenCombineAsync(Function<R, N> nextTransformation) {
    final Function<T, CompletableFuture<N>> f = this.resultFunction
        .andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> nextTransformation.apply(v))));
    return new CompletableFutureQueue<>(f);
  }

  /**
   * <p>resultFunction.</p>
   *
   * @return a {@link java.util.function.Function} object.
   */
  public Function<T, CompletableFuture<R>> resultFunction() {
    return this.resultFunction;
  }
}
