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
