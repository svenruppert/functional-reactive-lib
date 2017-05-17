package junit.org.rapidpm.frp.functions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
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
    Assert.assertFalse(p.test(""));
  }

  @Test
  public void test002() throws Exception {
    CheckedPredicate<String> p = s -> {
      throw new IOException("foo");
    };
    Assert.assertFalse(p.test(""));
  }

  @Test
  public void test003() throws Exception {
    CheckedPredicate<String> p = s -> s.equals("foo");
    Assert.assertFalse(p.test(""));
    Assert.assertTrue(p.test("foo"));
  }


}
