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
