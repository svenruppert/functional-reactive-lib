package demo;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static java.lang.System.out;

public class DemoRegistration {

  interface Registration {
    void remove();
  }

  @Test
  void test001() {

    class Observer<KEY, VALUE> {
      private Set<Consumer<VALUE>> listener
          = ConcurrentHashMap.newKeySet();

      public Registration register(Consumer<VALUE> consumer) {
        listener.add(consumer);
        return () -> listener.remove(consumer);
      }

      public void senEvent(VALUE value) {
        listener.forEach(c -> c.accept(value));
      }
    }


    Observer<String, Integer> observer = new Observer<>();

    Registration reg = observer.register(out::println);
    observer.senEvent(2);
    reg.remove();
    observer.senEvent(2);


  }
}
