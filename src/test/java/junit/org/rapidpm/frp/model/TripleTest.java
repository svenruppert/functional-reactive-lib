package junit.org.rapidpm.frp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Triple;

public class TripleTest {

  @Test
  public void testEquals() {
    final Triple triple = new Triple(0, 1, 2);

    assertTrue(triple.equals(triple));
    assertFalse(triple.equals(null));
    assertFalse(triple.equals(new Triple(null, 1, 2)));
    assertFalse(triple.equals(new Triple(0, null, 2)));
  }

  @Test
  public void testEqualsT1() {
    final Triple triple = new Triple(null, 1, 2);

    assertTrue(triple.equals(new Triple(null, 1, 2)));
    assertFalse(triple.equals(new Triple(0, 1, 2)));
  }

  @Test
  public void testEqualsT2() {
    final Triple triple = new Triple(0, null, 2);

    assertTrue(triple.equals(new Triple(0, null, 2)));
    assertFalse(triple.equals(new Triple(0, 1, 2)));
  }

  @Test
  public void testEqualsT3() {
    final Triple triple = new Triple(0, 1, null);

    assertTrue(triple.equals(new Triple(0, 1, null)));
    assertFalse(triple.equals(new Triple(0, 1, 2)));
  }

  @Test
  public void testHashCode() {
    final Triple triple = new Triple(0, 1, 2);

    assertEquals(33, triple.hashCode());
  }

  @Test
  public void testHashCodeT1() {
    final Triple triple = new Triple(null, 1, 2);

    assertEquals(33, triple.hashCode());
  }

  @Test
  public void testHashCodeT2() {
    final Triple triple = new Triple(0, null, 2);

    assertEquals(2, triple.hashCode());
  }

  @Test
  public void testHashCodeT3() {
    final Triple triple = new Triple(0, 1, null);

    assertEquals(31, triple.hashCode());
  }

  @Test
  public void testNext() {
    final Triple triple = Triple.next(0, 1, 2);

    assertNotNull(triple);
    assertEquals(0, triple.getT1());
    assertEquals(1, triple.getT2());
    assertEquals(2, triple.getT3());
  }

  @Test
  public void testToString() {
    final Triple triple = new Triple(0, 1, 2);

    assertEquals("Triple{t1=0, t2=1, t3=2}", triple.toString());
  }
}
