package org.rapidpm.frp;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 */
public interface StreamFunctions {

  static <T> Function<Predicate<T>, Function<Stream<T>, Stream<T>>> streamFilter() {
    return (filter) -> (Function<Stream<T>, Stream<T>>) inputStream -> inputStream.filter(filter);
  }
}
