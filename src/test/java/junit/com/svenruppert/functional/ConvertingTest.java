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
package junit.com.svenruppert.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.svenruppert.functional.Converting;

public class ConvertingTest {

  @Test
  @DisplayName("convertToString")
  void test001() {
    final String result = Converting
        .<Integer>convertToString()
        .apply(1)
        .get();
    Assertions.assertEquals("1" , result);
  }

  @Test
  @DisplayName("convertToString(f)")
  void test002() {

    final String result = Converting
        .<Integer>convertToString((i) -> "x" + 1)
        .apply(1)
        .get();
    Assertions.assertEquals("x1" , result);
  }

  @Test
  @DisplayName("convertToString(f) - failed")
  void test003() {
    Converting
        .<Integer>convertToString((i) -> {
          throw new RuntimeException("failed");
        })
        .apply(1)
    .ifPresentOrElse((s) -> Assertions.fail(),
                     (failed) -> Assertions.assertEquals("RuntimeException - failed", failed));
  }



}
