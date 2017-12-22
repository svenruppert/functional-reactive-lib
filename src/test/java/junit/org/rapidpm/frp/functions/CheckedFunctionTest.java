package junit.org.rapidpm.frp.functions;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class CheckedFunctionTest {

  @Test
  public void test001() throws Exception {

    final Result<String> result = ((CheckedFunction<String, String>) s -> "ok")
        .apply("Hello World");

    Assert.assertNotNull(result);
    Assert.assertTrue(result.isPresent());
    Assert.assertEquals("ok", result.get());

  }

  @Test
  public void test002() throws Exception {

    final Result<String> result = ((CheckedFunction<String, String>) s -> {
      throw new RuntimeException("noop");
    })
        .apply("Hello World");

    Assert.assertNotNull(result);
    Assert.assertTrue(result.isAbsent());

  }


}
