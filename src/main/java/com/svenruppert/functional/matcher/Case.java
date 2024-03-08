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
package com.svenruppert.functional.matcher;

import java.util.function.Supplier;
import java.util.stream.Stream;

import com.svenruppert.functional.model.Pair;
import com.svenruppert.functional.model.Result;

public class Case<T> extends Pair<Supplier<Boolean>, Supplier<Result<T>>> {

  /**
   * <p>Constructor for Case.</p>
   *
   * @param booleanSupplier a {@link java.util.function.Supplier} object.
   * @param resultSupplier a {@link java.util.function.Supplier} object.
   */
  public Case(final Supplier<Boolean> booleanSupplier , final Supplier<Result<T>> resultSupplier) {
    super(booleanSupplier , resultSupplier);
  }

  /**
   * <p>matchCase.</p>
   *
   * @param condition a {@link java.util.function.Supplier} object.
   * @param value a {@link java.util.function.Supplier} object.
   * @param <T> a T object.
   * @return a {@link com.svenruppert.functional.matcher.Case} object.
   */
  public static <T> Case<T> matchCase(Supplier<Boolean> condition ,
                                      Supplier<Result<T>> value) {
    return new Case<>(condition , value);
  }

  /**
   * <p>matchCase.</p>
   *
   * @param value a {@link java.util.function.Supplier} object.
   * @param <T> a T object.
   * @return a {@link com.svenruppert.functional.matcher.Case.DefaultCase} object.
   */
  public static <T> DefaultCase<T> matchCase(Supplier<Result<T>> value) {
    return new DefaultCase<>(() -> true , value);
  }

  /**
   * <p>isMatching.</p>
   *
   * @return a boolean.
   */
  private boolean isMatching() {
    return getT1().get();
  }

  /**
   * <p>result.</p>
   *
   * @return a {@link com.svenruppert.functional.model.Result} object.
   */
  public Result<T> result() {
    return getT2().get();
  }

  /**
   * <p>match.</p>
   *
   * @param defaultCase a {@link com.svenruppert.functional.matcher.Case.DefaultCase} object.
   * @param matchers a {@link com.svenruppert.functional.matcher.Case} object.
   * @param <T> a T object.
   * @return a {@link com.svenruppert.functional.model.Result} object.
   */
  @SafeVarargs
  public static <T> Result<T> match(DefaultCase<T> defaultCase , Case<T>... matchers) {
    return Stream
        .of(matchers)
        .filter(Case::isMatching)
        .map(Case::result)
        .findFirst()
        .orElseGet(defaultCase::result);
  }

  public static class DefaultCase<T> extends Case<T> {
    public DefaultCase(final Supplier<Boolean> booleanSupplier , final Supplier<Result<T>> resultSupplier) {
      super(booleanSupplier , resultSupplier);
    }
  }
}
