package org.rapidpm.frp;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by svenruppert on 24.04.17.
 */
public interface Transformations {


  public static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
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
