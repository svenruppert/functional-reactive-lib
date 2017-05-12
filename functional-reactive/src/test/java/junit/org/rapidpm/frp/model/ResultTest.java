package junit.org.rapidpm.frp.model;

import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class ResultTest {

  @Test
  public void test001() throws Exception {
    Result<String> hello = Result.success("Hello");
    Result<String> world = hello.thenCombine("World", (s, s2) -> Result.success(s + " - " + s2));
    Assert.assertEquals("Hello - World", world.get());
  }


  @Test
  public void test002() throws Exception {
    Result<String> hello = Result.success("Hello");
    CompletableFuture<Result<String>> world = hello
        .thenCombineAsync("World", (s, s2) -> Result.success(s + " - " + s2));

    Result<String> result = world.join();
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isPresent());
    Assert.assertEquals("Hello - World", result.get());
  }
}
