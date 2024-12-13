/*
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
