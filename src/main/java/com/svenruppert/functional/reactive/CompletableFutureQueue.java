/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.svenruppert.functional.reactive;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
   * @param <T>            a T object.
   * @param <R>            a R object.
   * @return a {@link com.svenruppert.functional.reactive.CompletableFutureQueue} object.
   */
  public static <T, R> CompletableFutureQueue<T, R> define(Function<T, R> transformation) {
    return new CompletableFutureQueue<>(t -> CompletableFuture.completedFuture(transformation.apply(t)));
  }

  /**
   * <p>thenCombineAsync.</p>
   *
   * @param nextTransformation a {@link java.util.function.Function} object.
   * @param <N>                a N object.
   * @return a {@link com.svenruppert.functional.reactive.CompletableFutureQueue} object.
   */
  public <N> CompletableFutureQueue<T, N> thenCombineAsync(Function<R, N> nextTransformation) {
    final Function<T, CompletableFuture<N>> f = this.resultFunction
        .andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> nextTransformation.apply(v))));
    return new CompletableFutureQueue<>(f);
  }


//TODO : how to combine a list of CF ?

  public <N> CompletableFutureQueue<T, N> thenCombineAsyncFromArray(Function<R, N>[] nextTransformations) {
    CompletableFutureQueue cfq = this;
    for (Function<R, N> nextTransformation : nextTransformations) {
      cfq = cfq.thenCombineAsync(nextTransformation);
    }
    return cfq;
  }

//  public <N> CompletableFutureQueue<T, N> thenCombineAsync(Collection<Function<R, N>> nextTransformations) {
//
//    nextTransformations
//        .forEach(nextTransformation -> {
//      this.resultFunction = this.resultFunction
//          .andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> nextTransformation.apply(v))));
//    });
//
//
//    return new CompletableFutureQueue<>(this.resultFunction);
//  }
//
//
//
//
//  public <N> CompletableFutureQueue<T, N> thenCombineAsync(Function<R, N> firstTransformation, Function<R, N>... nextTransformations) {
//    final Function<T, CompletableFuture<N>> f = this.resultFunction
//        .andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> firstTransformation.apply(v))));
//
//    if (nextTransformations != null) {
//      Arrays
//          .stream(nextTransformations)
//          .map(nf -> this.resultFunction.andThen(before -> before.thenComposeAsync(v -> supplyAsync(() -> nf.apply(v)))))
//          .forEach(nF -> { /** don something **/ });
//
//    }
//    return new CompletableFutureQueue<>(f);
//  }

  /**
   * <p>resultFunction.</p>
   *
   * @return a {@link java.util.function.Function} object.
   */
  public Function<T, CompletableFuture<R>> resultFunction() {
    return this.resultFunction;
  }
}
