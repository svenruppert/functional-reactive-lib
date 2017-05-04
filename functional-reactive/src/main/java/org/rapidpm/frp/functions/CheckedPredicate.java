package org.rapidpm.frp.functions;

import java.util.function.Predicate;

/**
 *  if an exception is thrown, the predicate will give back false;
 *
 */
public interface CheckedPredicate<T> extends Predicate<T> {
  @Override
  default boolean test(T t) {
    try {
      return testWithExceptions(t);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  boolean testWithExceptions(T t);
}
