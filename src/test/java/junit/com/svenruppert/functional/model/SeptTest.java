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
import com.svenruppert.functional.model.Sept;

public class SeptTest {

  @Test
  public void testEquals() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, 5, 6);

    assertTrue(sept.equals(sept));
    assertFalse(sept.equals(null));
    assertFalse(sept.equals(new Sept(null, 1, 2, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, null, 2, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, null, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, null, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, null, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, null, 6)));
  }

  @Test
  public void testEqualsT1() {
    final Sept sept = new Sept(null, 1, 2, 3, 4, 5, 6);

    assertTrue(sept.equals(new Sept(null, 1, 2, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT2() {
    final Sept sept = new Sept(0, null, 2, 3, 4, 5, 6);

    assertTrue(sept.equals(new Sept(0, null, 2, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT3() {
    final Sept sept = new Sept(0, 1, null, 3, 4, 5, 6);

    assertTrue(sept.equals(new Sept(0, 1, null, 3, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT4() {
    final Sept sept = new Sept(0, 1, 2, null, 4, 5, 6);

    assertTrue(sept.equals(new Sept(0, 1, 2, null, 4, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT5() {
    final Sept sept = new Sept(0, 1, 2, 3, null, 5, 6);

    assertTrue(sept.equals(new Sept(0, 1, 2, 3, null, 5, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT6() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, null, 6);

    assertTrue(sept.equals(new Sept(0, 1, 2, 3, 4, null, 6)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testEqualsT7() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, 5, null);

    assertTrue(sept.equals(new Sept(0, 1, 2, 3, 4, 5, null)));
    assertFalse(sept.equals(new Sept(0, 1, 2, 3, 4, 5, 6)));
  }

  @Test
  public void testHashCode() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, 5, 6);

    assertEquals(30569571, sept.hashCode());
  }

  @Test
  public void testHashCodeT1() {
    final Sept sept = new Sept(null, 1, 2, 3, 4, 5, 6);

    assertEquals(30569571, sept.hashCode());
  }

  @Test
  public void testHashCodeT2() {
    final Sept sept = new Sept(0, null, 2, 3, 4, 5, 6);

    assertEquals(1940420, sept.hashCode());
  }

  @Test
  public void testHashCodeT3() {
    final Sept sept = new Sept(0, 1, null, 3, 4, 5, 6);

    assertEquals(28722529, sept.hashCode());
  }

  @Test
  public void testHashCodeT4() {
    final Sept sept = new Sept(0, 1, 2, null, 4, 5, 6);

    assertEquals(30480198, sept.hashCode());
  }

  @Test
  public void testHashCodeT5() {
    final Sept sept = new Sept(0, 1, 2, 3, null, 5, 6);

    assertEquals(30565727, sept.hashCode());
  }

  @Test
  public void testHashCodeT6() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, null, 6);

    assertEquals(30569416, sept.hashCode());
  }

  @Test
  public void testHashCodeT7() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, 5, null);

    assertEquals(30569565, sept.hashCode());
  }

  @Test
  public void testNext() {
    final Sept sept = Sept.next(0, 1, 2, 3, 4, 5, 6);

    assertNotNull(sept);
    assertEquals(0, sept.getT1());
    assertEquals(1, sept.getT2());
    assertEquals(2, sept.getT3());
    assertEquals(3, sept.getT4());
    assertEquals(4, sept.getT5());
    assertEquals(5, sept.getT6());
    assertEquals(6, sept.getT7());
  }

  @Test
  public void testToString() {
    final Sept sept = new Sept(0, 1, 2, 3, 4, 5, 6);

    assertEquals("Sept{t1=0, t2=1, t3=2, t4=3, t5=4, t6=5, t7=6}", sept.toString());
  }
}
