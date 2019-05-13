package junit.org.rapidpm.frp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Quint;

public class QuintTest {

  @Test
  public void testEquals() {
    final Quint quint = new Quint(0, 1, 2, 3, 4);

    assertTrue(quint.equals(quint));
    assertFalse(quint.equals(null));
    assertFalse(quint.equals(new Quint(null, 1, 2, 3, 4)));
    assertFalse(quint.equals(new Quint(0, null, 2, 3, 4)));
    assertFalse(quint.equals(new Quint(0, 1, null, 3, 4)));
    assertFalse(quint.equals(new Quint(0, 1, 2, null, 4)));
  }

  @Test
  public void testEqualsT1() {
    final Quint quint = new Quint(null, 1, 2, 3, 4);

    assertTrue(quint.equals(new Quint(null, 1, 2, 3, 4)));
    assertFalse(quint.equals(new Quint(0, 1, 2, 3, 4)));
  }

  @Test
  public void testEqualsT2() {
    final Quint quint = new Quint(0, null, 2, 3, 4);

    assertTrue(quint.equals(new Quint(0, null, 2, 3, 4)));
    assertFalse(quint.equals(new Quint(0, 1, 2, 3, 4)));
  }

  @Test
  public void testEqualsT3() {
    final Quint quint = new Quint(0, 1, null, 3, 4);

    assertTrue(quint.equals(new Quint(0, 1, null, 3, 4)));
    assertFalse(quint.equals(new Quint(0, 1, 2, 3, 4)));
  }

  @Test
  public void testEqualsT4() {
    final Quint quint = new Quint(0, 1, 2, null, 4);

    assertTrue(quint.equals(new Quint(0, 1, 2, null, 4)));
    assertFalse(quint.equals(new Quint(0, 1, 2, 3, 4)));
  }

  @Test
  public void testEqualsT5() {
    final Quint quint = new Quint(0, 1, 2, 3, null);

    assertTrue(quint.equals(new Quint(0, 1, 2, 3, null)));
    assertFalse(quint.equals(new Quint(0, 1, 2, 3, 4)));
  }

  @Test
  public void testHashCode() {
    final Quint quint = new Quint(0, 1, 2, 3, 4);

    assertEquals(31810, quint.hashCode());
  }

  @Test
  public void testHashCodeT1() {
    final Quint quint = new Quint(null, 1, 2, 3, 4);

    assertEquals(31810, quint.hashCode());
  }

  @Test
  public void testHashCodeT2() {
    final Quint quint = new Quint(0, null, 2, 3, 4);

    assertEquals(2019, quint.hashCode());
  }

  @Test
  public void testHashCodeT3() {
    final Quint quint = new Quint(0, 1, null, 3, 4);

    assertEquals(29888, quint.hashCode());
  }

  @Test
  public void testHashCodeT4() {
    final Quint quint = new Quint(0, 1, 2, null, 4);

    assertEquals(31717, quint.hashCode());
  }

  @Test
  public void testHashCodeT5() throws Exception {
    final Quint quint = new Quint(0, 1, 2, 3, null);

    assertEquals(31806, quint.hashCode());
  }

  @Test
  public void testNext() {
    final Quint quint = Quint.next(0, 1, 2, 3, 4);

    assertNotNull(quint);
    assertEquals(0, quint.getT1());
    assertEquals(1, quint.getT2());
    assertEquals(2, quint.getT3());
    assertEquals(3, quint.getT4());
    assertEquals(4, quint.getT5());
  }

  @Test
  public void testToString() {
    final Quint quint = new Quint(0, 1, 2, 3, 4);

    assertEquals("Quint{t1=0, t2=1, t3=2, t4=3, t5=4}", quint.toString());
  }
}
