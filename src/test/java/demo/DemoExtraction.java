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

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class DemoExtraction {


  @Test
  void test001() {
    List<String> names = asList("Hugo",
                                "Willy",
                                "Lotta",
                                "Maria");
    names.stream()
         .filter(v -> v.contains("L"))
         .forEach(System.out::println);
    names.stream()
         .filter(v -> v.contains("H"))
         .forEach(System.out::println);
  }

  @Test
  void test002() {
    List<String> names = asList("Hugo",
                                "Willy",
                                "Lotta",
                                "Maria");
    Consumer<String> println = System.out::println;
    String           l       = "L";//final
    names.stream()
         .filter(v -> v.contains(l))
         .forEach(println);

    String h = "H"; //final
    names.stream()
         .filter(v -> v.contains(h))
         .forEach(println);
  }

  @Test
  void test003() {
    List<String> names = asList("Hugo",
                                "Willy",
                                "Lotta",
                                "Maria");
    Consumer<String> println = System.out::println;

    String l = "L";//final
    String h = "H"; //final

    Function<String, Predicate<String>> f
        = input -> s -> s.contains(input);

    names.stream()
         .filter(f.apply(l))
         .forEach(println);

    names.stream()
         .filter(f.apply(h))
         .forEach(println);


    BiFunction<List<String>, String, Void>
        f2 = (list, input) -> {
          list.stream()
                .filter(f.apply(input))
                .forEach(println);
          return null;
        };

    f2.apply(names, l);
    f2.apply(names, h);

  }
}
