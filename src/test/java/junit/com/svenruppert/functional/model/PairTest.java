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
import com.svenruppert.functional.model.Pair;

public class PairTest {

  @Test
  public void testEquals() {
    final Pair pair = new Pair(0, 1);

    assertFalse(pair.equals(null));
    assertFalse(pair.equals(new Pair(null, 1)));
    assertFalse(pair.equals(new Pair(0, null)));
    assertTrue(pair.equals(pair));
    assertTrue(pair.equals(new Pair(0, 1)));
  }

  @Test
  public void testHashCode() {
    final Pair pair = new Pair(0, 1);

    assertEquals(962, pair.hashCode());
  }

  @Test
  public void testNext() {
    final Pair pair = Pair.next(0, 1);

    assertNotNull(pair);
    assertEquals(0, pair.getT1());
    assertEquals(1, pair.getT2());
  }

  @Test
  public void testToString() {
    final Pair pair = new Pair(0, 1);

    assertEquals("Pair{t1=0, t2=1}", pair.toString());
  }
}
