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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class DemoCompletableFuture {


  @Test
  void test001() {
    ExecutorService exServ = Executors
        .newFixedThreadPool(2);

    Supplier<String> dataSource = () -> "Hello World";
    CompletableFuture<String> cf = supplyAsync(dataSource, exServ);
    String result = cf.join();
    System.out.println(result);
    exServ.shutdown();
  }

  @Test
  void test002() {
    ExecutorService exServ = Executors
        .newFixedThreadPool(2);

    Supplier<String> dataSource = () -> "Hello World";
    supplyAsync(dataSource, exServ)
        .thenAcceptAsync(System.out::println, exServ)
        .join();
    exServ.shutdown();
  }

  @Test
  void test003() {
    ExecutorService exServ = Executors
        .newFixedThreadPool(2);

    CompletableFuture<String> cfA = supplyAsync(() -> "A", exServ);
    CompletableFuture<String> cfB = supplyAsync(() -> "B", exServ);
    CompletableFuture<String> cfC = supplyAsync(() -> "C", exServ);

    cfA.thenCombineAsync(cfB, (a, b) -> a + b)
       .thenCombineAsync(cfC, (ab, c) -> ab + c)
       .thenAcceptAsync(System.out::println, exServ)
       .join();
    exServ.shutdown();
  }

}
