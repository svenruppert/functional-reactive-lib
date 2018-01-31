package junit.org.rapidpm.frp.functions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.functions.CheckedPredicate;

/**
 *
 */
public class CheckedPredicateTest {

  @Test
  public void test001() throws Exception {
    CheckedPredicate<String> p = s -> {
      throw new RuntimeException("foo");
    };
    assertFalse(p.test(""));
  }

  @Test
  public void test002() throws Exception {
    CheckedPredicate<String> p = s -> {
      throw new IOException("foo");
    };
    assertFalse(p.test(""));
  }

  @Test
  public void test003() throws Exception {
    CheckedPredicate<String> p = s -> s.equals("foo");
    assertFalse(p.test(""));
    assertTrue(p.test("foo"));
  }


}
