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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import com.svenruppert.functional.functions.CheckedPredicate;

/**
 *
 */
public class CheckedPredicateTest {

  @Test
  public void test001() {
    CheckedPredicate<String> p = s -> {
      throw new RuntimeException("foo");
    };
    assertFalse(p.test(""));
  }

  @Test
  public void test002()  {
    CheckedPredicate<String> p = s -> {
      throw new IOException("foo");
    };
    assertFalse(p.test(""));
  }

  @Test
  public void test003() {
    CheckedPredicate<String> p = s -> s.equals("foo");
    assertFalse(p.test(""));
    assertTrue(p.test("foo"));
  }


}
