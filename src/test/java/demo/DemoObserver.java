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

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static java.lang.System.out;

public class DemoObserver {


  @Test
  void test001() {

    class Observer<KEY, VALUE> {
      private ConcurrentHashMap<KEY, Consumer<VALUE>> listener
          = new ConcurrentHashMap<>();

      public void register(KEY key,
                           Consumer<VALUE> consumer) {
        listener.put(key, consumer);
      }

      public void remove(KEY key) {
        listener.remove(key);
      }

      public void senEvent(VALUE value) {
        listener.values()
                .forEach(c -> c.accept(value));
      }
    }


    Observer<String, Integer> observer = new Observer<>();

    observer.register("key1", out::println);
    observer.senEvent(2);
    observer.remove("key1");
    observer.senEvent(2);


  }
}
