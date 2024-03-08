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

import java.util.function.Function;

import com.svenruppert.functional.model.Result;

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
