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
import com.svenruppert.functional.model.Sext;

public class SextTest {

  @Test
  public void testEquals() {
    final Sext sext = new Sext(0, 1, 2, 3, 4, 5);

    assertTrue(sext.equals(sext));
    assertFalse(sext.equals(null));
    assertFalse(sext.equals(new Sext(null, 1, 2, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, null, 2, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, null, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, null, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, null, 5)));
  }

  @Test
  public void testEqualsT1() {
    final Sext sext = new Sext(null, 1, 2, 3, 4, 5);

    assertTrue(sext.equals(new Sext(null, 1, 2, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testEqualsT2() {
    final Sext sext = new Sext(0, null, 2, 3, 4, 5);

    assertTrue(sext.equals(new Sext(0, null, 2, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testEqualsT3() {
    final Sext sext = new Sext(0, 1, null, 3, 4, 5);

    assertTrue(sext.equals(new Sext(0, 1, null, 3, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testEqualsT4() {
    final Sext sext = new Sext(0, 1, 2, null, 4, 5);

    assertTrue(sext.equals(new Sext(0, 1, 2, null, 4, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testEqualsT5() {
    final Sext sext = new Sext(0, 1, 2, 3, null, 5);

    assertTrue(sext.equals(new Sext(0, 1, 2, 3, null, 5)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testEqualsT6() {
    final Sext sext = new Sext(0, 1, 2, 3, 4, null);

    assertTrue(sext.equals(new Sext(0, 1, 2, 3, 4, null)));
    assertFalse(sext.equals(new Sext(0, 1, 2, 3, 4, 5)));
  }

  @Test
  public void testHashCode() {
    final Sext sext = new Sext(0, 1, 2, 3, 4, 5);

    assertEquals(986115, sext.hashCode());
  }

  @Test
  public void testHashCodeT1() {
    final Sext sext = new Sext(null, 1, 2, 3, 4, 5);

    assertEquals(986115, sext.hashCode());
  }

  @Test
  public void testHashCodeT2() {
    final Sext sext = new Sext(0, null, 2, 3, 4, 5);

    assertEquals(62594, sext.hashCode());
  }

  @Test
  public void testHashCodeT3() {
    final Sext sext = new Sext(0, 1, null, 3, 4, 5);

    assertEquals(926533, sext.hashCode());
  }

  @Test
  public void testHashCodeT4(){
    final Sext sext = new Sext(0, 1, 2, null, 4, 5);

    assertEquals(983232, sext.hashCode());
  }

  @Test
  public void testHashCodeT5() {
    final Sext sext = new Sext(0, 1, 2, 3, null, 5);

    assertEquals(985991, sext.hashCode());
  }

  @Test
  public void testHashCodeT6() {
    final Sext sext = new Sext(0, 1, 2, 3, 4, null);

    assertEquals(986110, sext.hashCode());
  }

  @Test
  public void testNext() {
    final Sext sext = Sext.next(0, 1, 2, 3, 4, 5);

    assertNotNull(sext);
    assertEquals(0, sext.getT1());
    assertEquals(1, sext.getT2());
    assertEquals(2, sext.getT3());
    assertEquals(3, sext.getT4());
    assertEquals(4, sext.getT5());
    assertEquals(5, sext.getT6());
  }

  @Test
  public void testToString() {
    final Sext sext = new Sext(0, 1, 2, 3, 4, 5);

    assertEquals("Sext{t1=0, t2=1, t3=2, t4=3, t5=4, t6=5}", sext.toString());
  }
}
