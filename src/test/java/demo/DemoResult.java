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
package demo;

import org.junit.jupiter.api.Test;
import com.svenruppert.functional.model.Result;

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
