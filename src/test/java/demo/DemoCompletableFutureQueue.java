package demo;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.reactive.CompletableFutureQueue;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class DemoCompletableFutureQueue {


  @Test
  void test001() {
    Function<Integer, CompletableFuture<Integer>> f = CompletableFutureQueue
        .<Integer, Integer>define((a) -> a + 10)
        .thenCombineAsync((b) -> b + 20)
        .thenCombineAsync((c) -> c + 30)
        .resultFunction();

    f.apply(1)
     .thenAcceptAsync(System.out::println)
     .join();
  }

  @Test
  void test002() {
    CompletableFutureQueue
        .<Integer, Integer>define((a) -> a + 10)
        .thenCombineAsync((b) -> b + 20)
        .thenCombineAsync((c) -> c + 30)
        .resultFunction()
        .apply(1)
        .thenAcceptAsync(System.out::println)
        .join();
  }
}
