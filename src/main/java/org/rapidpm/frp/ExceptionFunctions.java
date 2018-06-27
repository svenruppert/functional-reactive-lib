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
package org.rapidpm.frp;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <p>ExceptionFunctions interface.</p>
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public interface ExceptionFunctions {

  /**
   * <p>message.</p>
   *
   * @return a {@link java.util.function.Function} object.
   */
  static Function<Exception, String> message() {
    return (e) -> {
      Objects.requireNonNull(e , "Exception instance was null.");
      final String message = e.getMessage();
      final String simpleName = e.getClass().getSimpleName();
      return (message != null ? simpleName + " - " + message : simpleName + " - no message");
    };
  }

  /**
   * <p>toStackTraceStream.</p>
   *
   * @return a {@link java.util.function.Function} object.
   */
  static Function<Exception, Stream<StackTraceElement>> toStackTraceStream() {
    return (e) -> {
      final StackTraceElement[] stackTrace = e.getStackTrace();
      return (stackTrace != null)
          ? Arrays.stream(stackTrace)
          : Stream.empty();
    };
  }
}
