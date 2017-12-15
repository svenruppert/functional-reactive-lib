package junit.org.rapidpm.frp.functions;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.functions.CheckedSupplier;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class CheckedSupplierTest {

  @Test
  public void test001() throws Exception {
    Result<String> result = ((CheckedSupplier<String>) () -> "Hello").get();
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isPresent());
    Assert.assertEquals("Hello", result.get());
  }

  @Test
  public void test002() throws Exception {
    Result<String> result = ((CheckedSupplier<String>) () -> {
      throw new RuntimeException("Hello");
    })
        .get();
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isAbsent());
  }
}
