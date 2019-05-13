package junit.org.rapidpm.frp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Single;

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
