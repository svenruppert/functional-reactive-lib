package junit.org.rapidpm.frp;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.Transformations;

/**
 *
 */
public class TransformationsTest {


  @Test
  public void test001() throws Exception {

    String helloWorld = Transformations
        .<String, String, String>curryBiFunction()
        .apply((s1 , s2) -> s1 + " " + s2)
        .apply("Hello").apply("World");
    Assert.assertEquals("Hello World" , helloWorld);
  }


  @Test
  public void test002() throws Exception {
    String helloWorld = Transformations
        .<String, String, String>unCurryBifunction()
        .apply(inputA -> inputB -> inputA + " " + inputB)
        .apply("Hello" , "World");
    Assert.assertEquals("Hello World" , helloWorld);
  }

  @Test
  public void test003() throws Exception {

    String helloWorld = Transformations
        .<String, String, String, String>curryTriFunction()
        .apply((s1 , s2 , s3) -> s1 + " " + s2 + " " + s3)
        .apply("Hello").apply("World").apply("!");
    Assert.assertEquals("Hello World !" , helloWorld);
  }


  @Test
  public void test004() throws Exception {
    String helloWorld = Transformations
        .<String, String, String, String>unCurryTrifunction()
        .apply(inputA -> inputB -> inputC -> inputA + " " + inputB + " " + inputC)
        .apply("Hello" , "World" , "!");
    Assert.assertEquals("Hello World !" , helloWorld);
  }

}
