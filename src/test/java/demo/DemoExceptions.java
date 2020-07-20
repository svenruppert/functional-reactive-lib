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
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;

import java.util.Optional;
import java.util.stream.Stream;

public class DemoExceptions {

  @Test
  void demo01() {
    try {
      int ups = Integer.parseInt("ups");
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    Stream.of("1", "2", "3", "4", "ups")
          .map(Integer::parseInt)
          .count();

    Stream.of("1", "2", "3", "4", "ups")
          .map(s -> {
            try {
              return Integer.parseInt(s);
            } catch (NumberFormatException e) {
              e.printStackTrace();
              return -1; //oh no
            }
          })
          .count();

    Stream.of("1", "2", "3", "4", "ups")
          .map(s -> {
            try {
              return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
              e.printStackTrace();
              return Optional.<Integer>empty(); //oh no
            }
          })
          .filter(Optional::isPresent)
//          .flatMap(Optional::stream)
          .count();


    Stream.of("1", "2", "3", "4", "ups")
          .map(new CheckedFunction<String, Integer>() {
            @Override
            public Integer applyWithException(String s) throws Exception {
              return Integer.parseInt(s);
            }
          })
          .count();

    Stream.of("1", "2", "3", "4", "ups")
          .map((CheckedFunction<String, Integer>) Integer::parseInt)
          .flatMap(Result::stream)
          .count();



  }
}
