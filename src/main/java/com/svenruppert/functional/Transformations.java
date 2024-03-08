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
package com.svenruppert.functional;

import com.svenruppert.functional.functions.CheckedBiFunction;
import com.svenruppert.functional.functions.CheckedFunction;
import com.svenruppert.functional.functions.CheckedTriFunction;
import com.svenruppert.functional.functions.TriFunction;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by svenruppert on 24.04.17.
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public interface Transformations {


  /**
   * <p>not.</p>
   *
   * @return a {@link java.util.function.Function} object.
   */
  static Function<Boolean, Boolean> not() {
    return (input) -> !input;
  }


  /**
   * <p>higherCompose.</p>
   *
   * @param <T> a T object.
   * @param <U> a U object.
   * @param <V> a V object.
   * @return a {@link java.util.function.Function} object.
   */
  static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
    return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));
  }


  /**
   * <p>enumToStream.</p>
   *
   * @param <T> a T object.
   * @return a {@link java.util.function.Function} object.
   */
  static <T> Function<Enumeration<T>, Stream<T>> enumToStream() {
    return (e) -> StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<T>() {
      public T next() {
        return e.nextElement();
      }

      public boolean hasNext() {
        return e.hasMoreElements();
      }
    }, Spliterator.ORDERED), false);
  }

  static <T> Function<Iterator<T>, Stream<T>> iteratorToStream() {
    return (e) -> StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<T>() {
      public T next() {
        return e.next();
      }

      public boolean hasNext() {
        return e.hasNext();
      }
    }, Spliterator.ORDERED), false);
  }

  /**
   * <p>curryBiFunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, R> Function<BiFunction<A, B, R>, Function<A, Function<B, R>>> curryBiFunction() {
    return (func) -> a -> b -> func.apply(a, b);
  }

  /**
   * <p>curryCheckedBiFunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, R> Function<CheckedBiFunction<A, B, R>, Function<A, CheckedFunction<B, R>>> curryCheckedBiFunction() {
    return (func) -> a -> b -> func.applyWithException(a, b);
  }

  /**
   * <p>unCurryBifunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, R> Function<Function<A, Function<B, R>>, BiFunction<A, B, R>> unCurryBiFunction() {
    return (func) -> (a, b) -> func.apply(a)
                                   .apply(b);
  }

  /**
   * <p>unCurryCheckedBifunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, R> Function<Function<A, CheckedFunction<B, R>>, CheckedBiFunction<A, B, R>> unCurryCheckedBiFunction() {
    return (func) -> (a, b) -> func.apply(a)
                                   .applyWithException(b);
  }

  /**
   * <p>curryTriFunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <C> a C object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, C, R> Function<TriFunction<A, B, C, R>, Function<A, Function<B, Function<C, R>>>> curryTriFunction() {
    return (func) -> a -> b -> c -> func.apply(a, b, c);
  }

  /**
   * <p>curryCheckedTriFunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <C> a C object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, C, R> Function<CheckedTriFunction<A, B, C, R>, Function<A, Function<B, CheckedFunction<C, R>>>> curryCheckedTriFunction() {
    return (func) -> a -> b -> c -> func.applyWithException(a, b, c);
  }

  /**
   * <p>unCurryTrifunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <C> a C object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, C, R> Function<Function<A, Function<B, Function<C, R>>>, TriFunction<A, B, C, R>> unCurryTriFunction() {
    return (func) -> (a, b, c) -> func.apply(a)
                                      .apply(b)
                                      .apply(c);
  }


  /**
   * <p>unCurryTrifunction.</p>
   *
   * @param <A> a A object.
   * @param <B> a B object.
   * @param <C> a C object.
   * @param <R> a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <A, B, C, R> Function<Function<A, Function<B, CheckedFunction<C, R>>>, CheckedTriFunction<A, B, C, R>> unCurryCheckedTriFunction() {
    return (func) -> (a, b, c) -> func.apply(a)
                                      .apply(b)
                                      .applyWithException(c);
  }


  //Function Casts

  /**
   * <p>not.</p>
   *
   * @param p   a {@link java.util.function.Predicate} object.
   * @param <T> a T object.
   * @return a {@link java.util.function.Predicate} object.
   */
  static <T> Predicate<T> not(Predicate<T> p) {
    return t -> !p.test(t);
  }

  /**
   * <p>asPredicate.</p>
   *
   * @param predicate a {@link java.util.function.Predicate} object.
   * @param <T>       a T object.
   * @return a {@link java.util.function.Predicate} object.
   */
  static <T> Predicate<T> asPredicate(Predicate<T> predicate) {
    return predicate;
  }

  /**
   * <p>asConsumer.</p>
   *
   * @param consumer a {@link java.util.function.Consumer} object.
   * @param <T>      a T object.
   * @return a {@link java.util.function.Consumer} object.
   */
  static <T> Consumer<T> asConsumer(Consumer<T> consumer) {
    return consumer;
  }

  /**
   * <p>asSupplier.</p>
   *
   * @param supplier a {@link java.util.function.Supplier} object.
   * @param <T>      a T object.
   * @return a {@link java.util.function.Supplier} object.
   */
  static <T> Supplier<T> asSupplier(Supplier<T> supplier) {
    return supplier;
  }

  /**
   * <p>asFunc.</p>
   *
   * @param function a {@link java.util.function.Function} object.
   * @param <T>      a T object.
   * @param <R>      a R object.
   * @return a {@link java.util.function.Function} object.
   */
  static <T, R> Function<T, R> asFunc(Function<T, R> function) {
    return function;
  }


  static <T, R> CheckedFunction<T, R> asCheckedFunc(Function<T, R> f) {
    return f::apply;
  }


}
