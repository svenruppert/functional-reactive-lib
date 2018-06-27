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
package junit.org.rapidpm.frp.functions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 26.04.17.
 */
public class CheckedExecutorTest {


  @Test
  public void test001() throws Exception {

    final CheckedExecutor e = () -> { /* do magic here */};
    final Result<Void> result = e.execute();
    assertNotNull(result);

    assertFalse(result.isPresent());
    assertTrue(result.isAbsent());

    assertTrue(result instanceof Result.Success);

  }

  @Test
  public void test002() throws Exception {

    final CheckedExecutor e = () -> {
      throw new RuntimeException("noop");
    };
    final Result<Void> result = e.execute();
    assertNotNull(result);

    assertFalse(result.isPresent());
    assertTrue(result.isAbsent());

    assertTrue(result instanceof Result.Failure);

  }
}
