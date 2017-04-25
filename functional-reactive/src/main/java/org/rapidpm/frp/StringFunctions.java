package org.rapidpm.frp;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Created by svenruppert on 25.04.17.
 */
public interface StringFunctions {

  static BiFunction<String, String, Boolean> notStartsWith() {
    return (s, prefix) -> ! s.startsWith(prefix);
  }

  static Predicate<String> notEmpty() {
    return (str) -> ! str.isEmpty();
  }

}
