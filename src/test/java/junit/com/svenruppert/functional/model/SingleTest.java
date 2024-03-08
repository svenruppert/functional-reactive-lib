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
package junit.com.svenruppert.functional.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.svenruppert.functional.model.Single;

public class SingleTest {

  @Test
  public void testEquals() {
    final Single single = new Single(0);

    assertFalse(single.equals(null));
    assertTrue(single.equals(single));
    assertTrue(single.equals(new Single(0)));
  }

  @Test
  public void testHashCode() {
    final Single single = new Single(0);

    assertEquals(31, single.hashCode());
  }

  @Test
  public void testNext() {
    final Single single = Single.next(0);

    assertNotNull(single);
    assertEquals(0, single.getT1());
  }

  @Test
  public void testToString() {
    final Single single = new Single(0);

    assertEquals("Single{t1=0}", single.toString());
  }
}
