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
