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
import com.svenruppert.functional.functions.CheckedSupplier;
import com.svenruppert.functional.model.Result;

/**
 *
 */
public class CheckedSupplierTest {

  @Test
  public void test001() throws Exception {
    Result<String> result = ((CheckedSupplier<String>) () -> "Hello").get();
    assertNotNull(result);
    assertTrue(result.isPresent());
    assertEquals("Hello", result.get());
  }

  @Test
  public void test002() throws Exception {
    Result<String> result = ((CheckedSupplier<String>) () -> {
      throw new RuntimeException("Hello");
    })
        .get();
    assertNotNull(result);
    assertTrue(result.isAbsent());
  }
}
