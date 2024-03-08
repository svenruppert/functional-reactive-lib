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
package junit.com.svenruppert.functional.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.svenruppert.functional.functions.CheckedBiFunction;
import com.svenruppert.functional.model.Result;

/**
 *
 */
public class CheckedBiFunctionTest {

  @Test
  public void test001() {

    final Result<String> result = ((CheckedBiFunction<String, String, String>) (s1 , s2) -> "ok")
        .apply("Hello" , "World");

    assertNotNull(result);
    assertTrue(result.isPresent());
    assertEquals("ok" , result.get());

  }

  @Test
  public void test002() throws Exception {

    final Result<String> result = ((CheckedBiFunction<String, String, String>) (s1 , s2) -> {
      throw new RuntimeException("noop");
    })
        .apply("Hello" , "World");

    assertNotNull(result);
    assertTrue(result.isAbsent());

  }
}
