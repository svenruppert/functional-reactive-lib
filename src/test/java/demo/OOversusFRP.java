package demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Consumer;

public class OOversusFRP {


  @Test
  void demo001() {

    final Optional<String> o = Optional.of("HelloWorld");
    final Optional<String> upperCase = o.map(String::toUpperCase);

    o.ifPresent(System.out::println);
    o.ifPresent(new Consumer<String>() {
      @Override
      public void accept(String x) {System.out.println(x);}
    });

    o.orElseThrow(() -> new RuntimeException("Uuuuuppss"));


  }
}
