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
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <p>StreamFunctions interface.</p>
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public interface StreamFunctions {

  /**
   * <p>streamFilter.</p>
   *
   * @param <T> a T object.
   * @return a {@link java.util.function.Function} object.
   */
  static <T> Function<Predicate<T>, Function<Stream<T>, Stream<T>>> streamFilter() {
    return (filter) -> (Function<Stream<T>, Stream<T>>) inputStream -> inputStream.filter(filter);
  }
}
