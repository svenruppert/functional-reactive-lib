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
import com.svenruppert.functional.model.Quad;

public class QuadTest {

  @Test
  public void testEquals() {
    final Quad quad = new Quad(0, 1, 2, 3);

    assertTrue(quad.equals(quad));
    assertFalse(quad.equals(null));
    assertFalse(quad.equals(new Quad(null, 1, 2, 3)));
    assertFalse(quad.equals(new Quad(0, null, 2, 3)));
    assertFalse(quad.equals(new Quad(0, 1, null, 3)));
  }

  @Test
  public void testEqualsT1() {
    final Quad quad = new Quad(null, 1, 2, 3);

    assertTrue(quad.equals(new Quad(null, 1, 2, 3)));
    assertFalse(quad.equals(new Quad(0, 1, 2, 3)));
  }

  @Test
  public void testEqualsT2() {
    final Quad quad = new Quad(0, null, 2, 3);

    assertTrue(quad.equals(new Quad(0, null, 2, 3)));
    assertFalse(quad.equals(new Quad(0, 1, 2, 3)));
  }

  @Test
  public void testEqualsT3() {
    final Quad quad = new Quad(0, 1, null, 3);

    assertTrue(quad.equals(new Quad(0, 1, null, 3)));
    assertFalse(quad.equals(new Quad(0, 1, 2, 3)));
  }

  @Test
  public void testEqualsT4() {
    final Quad quad = new Quad(0, 1, 2, null);

    assertTrue(quad.equals(new Quad(0, 1, 2, null)));
    assertFalse(quad.equals(new Quad(0, 1, 2, 3)));
  }

  @Test
  public void testHashCode() {
    final Quad quad = new Quad(0, 1, 2, 3);

    assertEquals(1026, quad.hashCode());
  }

  @Test
  public void testHashCodeT1() {
    final Quad quad = new Quad(null, 1, 2, 3);

    assertEquals(1026, quad.hashCode());
  }

  @Test
  public void testHashCodeT2() {
    final Quad quad = new Quad(0, null, 2, 3);

    assertEquals(65, quad.hashCode());
  }

  @Test
  public void testHashCodeT3() {
    final Quad quad = new Quad(0, 1, null, 3);

    assertEquals(964, quad.hashCode());
  }

  @Test
  public void testHashCodeT4() {
    final Quad quad = new Quad(0, 1, 2, null);

    assertEquals(1023, quad.hashCode());
  }


  @Test
  public void testNext() {
    final Quad quad = Quad.next(0, 1, 2, 3);

    assertNotNull(quad);
    assertEquals(0, quad.getT1());
    assertEquals(1, quad.getT2());
    assertEquals(2, quad.getT3());
    assertEquals(3, quad.getT4());
  }

  @Test
  public void testToString() {
    final Quad quad = new Quad(0, 1, 2, 3);

    assertEquals("Quad{t1=0, t2=1, t3=2, t4=3}", quad.toString());
  }
}
