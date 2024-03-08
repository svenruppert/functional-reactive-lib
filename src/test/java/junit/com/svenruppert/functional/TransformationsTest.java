/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package junit.com.svenruppert.functional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.functional.Transformations;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TransformationsTest {


  @Test
  public void test001() {

    String helloWorld = Transformations
        .<String, String, String>curryBiFunction()
        .apply((s1, s2) -> s1 + " " + s2)
        .apply("Hello").apply("World");
    assertEquals("Hello World", helloWorld);
  }


  @Test
  public void test002() {
    String helloWorld = Transformations
        .<String, String, String>unCurryBiFunction()
        .apply(inputA -> inputB -> inputA + " " + inputB)
        .apply("Hello", "World");
    assertEquals("Hello World", helloWorld);
  }

  @Test
  public void test003() {

    String helloWorld = Transformations
        .<String, String, String, String>curryTriFunction()
        .apply((s1, s2, s3) -> s1 + " " + s2 + " " + s3)
        .apply("Hello").apply("World").apply("!");
    assertEquals("Hello World !", helloWorld);
  }


  @Test
  public void test004(){
    String helloWorld = Transformations
        .<String, String, String, String>unCurryTriFunction()
        .apply(inputA -> inputB -> inputC -> inputA + " " + inputB + " " + inputC)
        .apply("Hello", "World", "!");
    assertEquals("Hello World !", helloWorld);
  }

  @Test
  public void test005(){
    String helloWorld = Transformations
        .<String, String, String, String>unCurryCheckedTriFunction()
        .apply(inputA -> inputB -> inputC -> inputA + " " + inputB + " " + inputC)
        .apply("Hello", "World", "!")
        .get();
    assertEquals("Hello World !", helloWorld);
  }

  @Test
  public void test006()  {
    Transformations
        .<String, String, String, String>unCurryCheckedTriFunction()
        .apply(inputA -> inputB -> inputC -> {
          throw new RuntimeException("");
        })
        .apply("Hello", "World", "!")
        .ifPresent(e -> Assertions.fail("should be false"));
  }


  @Test
  public void test007() {

    String helloWorld = Transformations
        .<String, String, String, String>curryCheckedTriFunction()
        .apply((s1, s2, s3) -> s1 + " " + s2 + " " + s3)
        .apply("Hello").apply("World").apply("!")
        .get();
    assertEquals("Hello World !", helloWorld);
  }

  @Test
  public void test008(){
    Transformations
        .<String, String, String, String>curryCheckedTriFunction()
        .apply((s1, s2, s3) -> {throw new RuntimeException("");})
        .apply("Hello").apply("World").apply("!")
        .ifPresent(e -> Assertions.fail("should be false"));
  }

  @Test
  public void test009() {
    String helloWorld = Transformations
        .<String, String, String>curryCheckedBiFunction()
        .apply((s1, s2) -> s1 + " " + s2)
        .apply("Hello").apply("World")
        .get();
    assertEquals("Hello World", helloWorld);
  }

  @Test
  public void test010() {
    Transformations
        .<String, String, String>curryCheckedBiFunction()
        .apply((s1, s2) -> {throw new RuntimeException("");})
        .apply("Hello").apply("World")
        .ifPresent(e -> Assertions.fail("should be false"));
  }

  @Test
  public void test011() {
    String helloWorld = Transformations
        .<String, String, String>unCurryCheckedBiFunction()
        .apply(inputA -> inputB -> inputA + " " + inputB)
        .apply("Hello", "World")
        .get();
    assertEquals("Hello World", helloWorld);
  }

  @Test
  public void test012() {
    Transformations
        .<String, String, String>unCurryCheckedBiFunction()
        .apply(inputA -> inputB -> {throw new RuntimeException("");})
        .apply("Hello", "World")
        .ifPresent(e -> Assertions.fail("should be false"));
  }

}
