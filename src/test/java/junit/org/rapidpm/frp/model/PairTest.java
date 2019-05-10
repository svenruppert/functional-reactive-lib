package junit.org.rapidpm.frp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Pair;

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
