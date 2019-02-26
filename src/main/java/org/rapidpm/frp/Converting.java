package org.rapidpm.frp;

import java.util.function.Function;

import org.rapidpm.frp.model.Result;

public interface Converting {


  static <T>
  Function<T, Result<String>> convertToString(Function<T, String> func) {
    return Transformations.asCheckedFunc(func);
  }

  static <T>
  Function<T, Result<String>> convertToString() {
    return convertToString(String::valueOf);
  }

  static <T>
  Function<T, Result<Boolean>> convertToBoolean(Function<T, Boolean> func) {
    return Transformations.asCheckedFunc(func);
  }

  static <T>
  Function<T, Result<Boolean>> convertToBoolean() {
    return Converting
        .<T>convertToString()
        .andThen(s -> s.map(Boolean::parseBoolean));
  }

  static <T>
  Function<T, Result<Integer>> convertToInteger(Function<T, Integer> func) {
    return Transformations.asCheckedFunc(func);
  }

  static <T>
  Function<T, Result<Integer>> convertToInteger() {
    return Converting
        .<T>convertToString()
        .andThen(s -> s.map(Integer::parseInt));
  }

  static <T>
  Function<T, Result<Double>> convertToDouble(Function<T, Double> func) {
    return Transformations.asCheckedFunc(func);
  }

  static <T>
  Function<T, Result<Double>> convertToDouble() {
    return Converting
        .<T>convertToString()
        .andThen(s -> s.map(Double::parseDouble));
  }




}
