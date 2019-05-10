package junit.org.rapidpm.frp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Quad;

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
