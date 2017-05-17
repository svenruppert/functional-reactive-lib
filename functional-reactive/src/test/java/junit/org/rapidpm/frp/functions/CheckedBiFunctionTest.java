package junit.org.rapidpm.frp.functions;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.functions.CheckedBiFunction;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class CheckedBiFunctionTest {

  @Test
  public void test001() throws Exception {

    final Result<String> result = ((CheckedBiFunction<String, String, String>) (s1, s2) -> "ok")
        .apply("Hello", "World");

    Assert.assertNotNull(result);
    Assert.assertTrue(result.isPresent());
    Assert.assertEquals("ok", result.get());

  }

  @Test
  public void test002() throws Exception {

    final Result<String> result = ((CheckedBiFunction<String, String, String>) (s1, s2) -> {
      throw new RuntimeException("noop");
    })
        .apply("Hello", "World");

    Assert.assertNotNull(result);
    Assert.assertTrue(result.isAbsent());

  }
}
