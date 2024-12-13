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

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.functional.memoizer.Memoizer;

import java.util.function.Function;

public class DemoMemoizingLegacy {

  @Test
  void test001() {

    class Legacy {
      public String doWork(Integer input) {
        return input.toString()
               + "-"
               + System.nanoTime();
      }
    }

    Legacy legacy = new Legacy();
    Function<Integer, String> f = legacy::doWork;
    Function<Integer, String> fMemo = Memoizer.memoize(f);
    String a1 = fMemo.apply(1);
    Assertions.assertEquals(fMemo.apply(1), a1);

    String aLegacy = f.apply(1);
    Assertions.assertNotEquals(f.apply(1), aLegacy);

  }
}
