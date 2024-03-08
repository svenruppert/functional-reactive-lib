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

import static java.lang.System.getProperty;
import static com.svenruppert.functional.Converting.convertToBoolean;
import static com.svenruppert.functional.Converting.convertToDouble;
import static com.svenruppert.functional.Converting.convertToInteger;
import static com.svenruppert.functional.model.Result.ofNullable;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.svenruppert.functional.model.Result;

public interface SystemProperties {

  static BiFunction<Class, String, String> qualifiedParameter() {
    return (clazz , unqualifiedName) -> clazz.getName() + "." + unqualifiedName;
  }

  static BiFunction<Class, String, Boolean> hasSystemProperty() {
    return qualifiedParameter().andThen(key -> getProperty(key) != null);
  }

  static Function<String, Boolean> hasSystemProperty(Class qualifier) {
    return (key) -> hasSystemProperty().apply(qualifier , key);
  }


  static BiFunction<Class, String, Result<String>> systemProperty() {
    return qualifiedParameter().andThen(key -> ofNullable(getProperty(key)));
  }

  static BiFunction<Class, String, Result<String>> systemProperty(String defaultValue) {
    return qualifiedParameter().andThen(key -> ofNullable(getProperty(key , defaultValue)));
  }


  static Function<String, Result<String>> systemProperty(Class qualifier) {
    return (key) -> qualifiedParameter()
        .andThen(k -> ofNullable(getProperty(k)))
        .apply(qualifier , key);
  }

  static Function<String, Result<String>> systemProperty(Class qualifier , String defaultValue) {
    return (key) -> qualifiedParameter()
        .andThen(k -> ofNullable(getProperty(k , defaultValue)))
        .apply(qualifier , key);
  }


  static Function<String, Result<Boolean>> systemPropertyBoolean(Class qualifier) {
    return (key) -> systemProperty(qualifier)
        .apply(key)
        .flatMap(convertToBoolean());
  }

  static Function<String, Result<Boolean>> systemPropertyBoolean(Class qualifier , String defaultValue) {
    return (key) -> systemProperty(qualifier, defaultValue)
        .apply(key)
        .flatMap(convertToBoolean());
  }

  static Function<String, Result<Integer>> systemPropertyInt(Class qualifier) {
    return (key) -> systemProperty(qualifier)
        .apply(key)
        .flatMap(convertToInteger());
  }

  static Function<String, Result<Integer>> systemPropertyInt(Class qualifier , String defaultValue) {
    return (key) -> systemProperty(qualifier, defaultValue)
        .apply(key)
        .flatMap(convertToInteger());
  }

  static Function<String, Result<Double>> systemPropertyDouble(Class qualifier) {
    return (key) -> systemProperty(qualifier)
        .apply(key)
        .flatMap(convertToDouble());
  }

  static Function<String, Result<Double>> systemPropertyDouble(Class qualifier , String defaultValue) {
    return (key) -> systemProperty(qualifier, defaultValue)
        .apply(key)
        .flatMap(convertToDouble());
  }

}
