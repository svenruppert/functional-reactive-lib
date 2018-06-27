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
package junit.org.rapidpm.frp;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.rapidpm.frp.StreamFunctions;

/**
 *
 */
public class StreamFunctionsTest {

  @Test
  void test001() {

    final Set<Integer> set = StreamFunctions.<Integer>streamFilter()
        .apply(integer -> integer.equals(5))
        .apply(Stream.of(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9))
        .collect(toSet());


    assertEquals(1 , set.size());
    set.forEach(i -> assertEquals(Long.valueOf(i) , Long.valueOf(5)));
  }
}
