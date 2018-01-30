package junit.org.rapidpm.frp.model;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class ResultTest {

  @Test
  public void test001() throws Exception {
    Result<String> hello = Result.success("Hello");
    Result<String> world = hello.thenCombine("World", (s, s2) -> Result.success(s + " - " + s2));
    assertEquals("Hello - World", world.get());
  }


  @Test
  public void test002() throws Exception {
    Result<String> hello = Result.success("Hello");
    CompletableFuture<Result<String>> world = hello
        .thenCombineAsync("World", (s, s2) -> Result.success(s + " - " + s2));

    Result<String> result = world.join();
    assertNotNull(result);
    assertTrue(result.isPresent());
    assertEquals("Hello - World", result.get());
  }


  @Test
  public void test003() throws Exception {
    final Result<Integer> asFailure = Result.success("Hello").asFailure();
    assertTrue(asFailure.isAbsent());
//    asFailure.ifAbsent(Assert::fail);
    asFailure.ifPresent(v -> fail("not good"));
  }

  @Test
  public void test004() throws Exception {
    final Result<Integer> asFailure = Result.failure("Hello").asFailure();
    assertTrue(asFailure.isAbsent());
    asFailure.ifPresent(v -> fail("not good"));
  }
}
