package demo;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Result;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DemoResult {


  @Test
  void demoA() {

    Result.fromOptional(Optional.empty());
    Result.success("ok")
          .toOptional();

    Result<Long>    nullable = Result.ofNullable(null);
    Result<String>  success  = Result.success("String");
    Result<Integer> ooops    = Result.failure("Ooops");


    Result<String> a = Result.success("A");
    a.isPresent();
    a.isAbsent();

    a.ifPresent(String::toUpperCase);
    a.ifFailed(f -> {});

    a.ifPresentOrElse(String::toLowerCase, () -> {});
    a.ifPresentOrElse(String::toLowerCase, (failed) -> {});

    a.ifPresentOrElseAsync(String::toLowerCase, () -> {});
    a.ifPresentOrElseAsync(String::toLowerCase, (failed) -> {});

    BiFunction<String, String, Result<String>> fkt = (x, y) -> Result.ofNullable(x + y);
    Result<String>                             r   = a.thenCombine("B", fkt);

    CompletableFuture<Result<String>> rAsync = a.thenCombineAsync("B", fkt);


    Function<Integer, Integer> f1 = (a1) -> a1 * -1;
    Function<Integer, Integer> f2 = (a2) -> a2 * 10;


    BiFunction<
        Function<Integer, Integer>,
        Function<Integer, Integer>,
        Function<Integer, Integer>> rF
        = Function::andThen;

//    var transform = rF.andThen(Result::ofNullable);

//    Result.success(f1)
//          .thenCombineAsync(f2, transform)
//          .join()
//          .map(f -> f.apply(10))
//          .get();


  }
}
