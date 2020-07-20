package demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DemoOptional {

  @Test
  void demoOptionalA() {
    Optional.of("String");
    Optional.ofNullable(null);
    Optional.empty();

    Optional<Integer> ofOne = Optional.of(1);
    ofOne.or(() -> Optional.of(2)); //JDK9
    ofOne.orElse(2);
    ofOne.orElseThrow(); //JDK10
    ofOne.stream(); //JDK9
    ofOne.map(i -> i + 2);
    ofOne.filter(i -> i == 2);

    ofOne.ifPresentOrElse( //JDK9
        i -> {},
        ()->{});


  }
}
