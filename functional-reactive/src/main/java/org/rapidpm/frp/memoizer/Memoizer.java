package org.rapidpm.frp.memoizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.rapidpm.frp.Transformations;
import org.rapidpm.frp.functions.TriFunction;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created by RapidPM - Team on 10.12.16.
 */

public class Memoizer<T, U> {
  private final Map<T, U> memoizationCache = new ConcurrentHashMap<>();

  private Supplier<T> doMemoize(final Supplier<T> function) {
    return new Supplier<T>() {
      private T value;

      @Override
      public T get() {
        if (value == null) value = function.get();
        return value;
      }
    };
  }


  private Function<T, U> doMemoize(final Function<T, U> function) {
    return input -> memoizationCache.computeIfAbsent(input, function);
  }

  public static <T> Supplier<T> memoize(final Supplier<T> function) {
    return new Memoizer<T, T>().doMemoize(function);
  }

  public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
    return new Memoizer<T, U>().doMemoize(function);
  }


  public static <T1, T2, R> BiFunction<T1, T2, R> memoize(final BiFunction<T1, T2, R> biFunc) {
    final Function<T1, Function<T2, R>> transformed = Memoizer.memoize(x -> Memoizer.memoize(y -> biFunc.apply(x, y)));
    return Transformations
        .<T1, T2, R>unCurryBifunction()
        .apply(transformed);
  }

//  public static <T1, T2, R> BiFunction<T1, T2, R> memoize(final BiFunction<T1, T2, R> biFunc) {
//    final Function<T1, Function<T2, R>> transformed = Memoizer.memoize(x -> Memoizer.memoize(y -> biFunc.apply(x, y)));
//    return (x, y) -> transformed.apply(x).apply(y);
//  }

  public static <T1, T2, T3, R> TriFunction<T1, T2, T3, R> memoize(final TriFunction<T1, T2, T3, R> threeFunc) {
    final Function<T1, Function<T2, Function<T3, R>>> transformed
        = Memoizer.memoize(x -> Memoizer.memoize(y -> Memoizer.memoize(z -> threeFunc.apply(x, y, z))));
    return Transformations
        .<T1, T2, T3, R>unCurryTrifunction()
        .apply(transformed);
//    return (x, y, z) -> transformed.apply(x).apply(y).apply(z);
  }

}
