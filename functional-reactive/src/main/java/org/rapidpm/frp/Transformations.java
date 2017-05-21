package org.rapidpm.frp;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.rapidpm.frp.functions.TriFunction;

/**
 * Created by svenruppert on 24.04.17.
 */
public interface Transformations {


  static Function<Boolean, Boolean> not() {
    return (input) -> ! input;
  }


  static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
    return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));
  }

  static <T> Function<Enumeration<T>, Stream<T>> enumToStream() {
    return (e) ->
        StreamSupport
            .stream(Spliterators.spliteratorUnknownSize(new Iterator<T>() {
              public T next() {
                return e.nextElement();
              }

              public boolean hasNext() {
                return e.hasMoreElements();
              }
            }, Spliterator.ORDERED), false);
  }


  static <A, B, R> Function<BiFunction<A, B, R>, Function<A, Function<B, R>>> curryBiFunction() {
    return (func) -> a -> b -> func.apply(a, b);
  }

  static <A, B, R> Function<Function<A, Function<B, R>>, BiFunction<A, B, R>> unCurryBifunction() {
    return (func) -> (a, b) -> func.apply(a).apply(b);
  }

  static <A, B, C, R> Function<
      TriFunction<A, B, C, R>,
      Function<A, Function<B, Function<C, R>>>> curryTriFunction() {
    return (func) -> a -> b -> c -> func.apply(a, b, c);
  }

  static <A, B, C, R> Function<
      Function<A, Function<B, Function<C, R>>>,
      TriFunction<A, B, C, R>> unCurryTrifunction() {
    return (func) -> (a, b, c) -> func.apply(a).apply(b).apply(c);
  }




  //Function Casts
  static <T> Predicate<T> not(Predicate<T> p) {
    return t -> ! p.test(t);
  }

  static <T> Predicate<T> asPredicate(Predicate<T> predicate) {
    return predicate;
  }

  static <T> Consumer<T> asConsumer(Consumer<T> consumer) {
    return consumer;
  }

  static <T> Supplier<T> asSupplier(Supplier<T> supplier) {
    return supplier;
  }

  static <T, R> Function<T, R> asFunc(Function<T, R> function) {
    return function;
  }

}
