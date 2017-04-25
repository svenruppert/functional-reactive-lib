package junit.org.rapidpm.frp;

import static org.junit.Assert.*;
import static org.rapidpm.frp.StringFunctions.notEmpty;
import static org.rapidpm.frp.StringFunctions.notStartsWith;

import org.junit.Assert;
import org.junit.Test;
import org.rapidpm.frp.StringFunctions;

/**
 * Created by svenruppert on 25.04.17.
 */
public class StringFunctionsTest {


  @Test
  public void notStartsWith001() throws Exception {
    Assert.assertFalse(notStartsWith().apply("AAA", "A"));
  }

  @Test
  public void notStartsWith002() throws Exception {
    Assert.assertFalse(notStartsWith().apply("AAA", "AAA"));
  }

  @Test
  public void notStartsWith003() throws Exception {
    Assert.assertTrue(notStartsWith().apply("AAA", "AAAA"));
  }

  @Test
  public void notStartsWith004() throws Exception {
    Assert.assertTrue(notStartsWith().apply("AAA", "B"));
  }

  @Test
  public void notStartsWith005() throws Exception {
    Assert.assertFalse(notStartsWith().apply("AAA", ""));
  }

  @Test
  public void notStartsWith006() throws Exception {
    Assert.assertFalse(notStartsWith().apply(" AAA", ""));
  }

  @Test
  public void notStartsWith007() throws Exception {
    Assert.assertFalse(notStartsWith().apply(" AAA", " "));
  }

  @Test
  public void notEmpty001() throws Exception {
    Assert.assertFalse(notEmpty().test(""));
  }

  @Test
  public void notEmpty002() throws Exception {
    Assert.assertTrue(notEmpty().test(" "));
  }

  @Test
  public void notEmpty003() throws Exception {
    Assert.assertTrue(notEmpty().test("A"));
  }




}