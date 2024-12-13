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
import com.svenruppert.functional.matcher.Case;
import com.svenruppert.functional.model.Result;

import static com.svenruppert.functional.matcher.Case.matchCase;

public class DemoCase {


  @Test
  void test001() {

    String value = "A";
    Result<String> result
        = Case.match(
            matchCase(() -> Result.failure("nothing fit")),
            matchCase(() -> value.contains("A"),
                      () -> Result.success("Got A")),
            matchCase(() -> value.contains("B"),
                      () -> Result.success("Got B"))
                    );


  }
}
