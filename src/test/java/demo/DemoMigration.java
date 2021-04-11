package demo;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class DemoMigration {


  @Test
  void test001() {


    Function<Integer, Integer> plus2 = (i)-> i + 2;
    Function<Integer, Integer> plus5 = (i)-> i + 5;
    Function<Integer, Integer> plus10 = (i)-> i + 10;

    Function<Integer, Function<Integer, Integer>>
        adder = (con) -> (i) -> i+con;

    Function<Integer, Integer> plusTwo
        = adder.apply(2);



  }
}
