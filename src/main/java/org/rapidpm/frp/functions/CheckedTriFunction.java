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
package org.rapidpm.frp.functions;

import org.rapidpm.frp.model.Result;

import static org.rapidpm.frp.ExceptionFunctions.message;

public interface CheckedTriFunction<T1, T2, T3, R> extends TriFunction<T1, T2, T3, Result<R>> {
  @Override
  default Result<R> apply(T1 t1, T2 t2, T3 t3) {
    try {
      return Result.success(applyWithException(t1, t2, t3));
    } catch (Exception e) {
      return Result.failure(message().apply(e));
    }
  }

  R applyWithException(T1 t1, T2 t2, T3 t3) throws Exception;
}
