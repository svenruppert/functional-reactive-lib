package junit.org.rapidpm.frp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rapidpm.frp.Converting;

public class ConvertingTest {

  @Test
  @DisplayName("convertToString")
  void test001() {
    final String result = Converting
        .<Integer>convertToString()
        .apply(1)
        .get();
    Assertions.assertEquals("1" , result);
  }

  @Test
  @DisplayName("convertToString(f)")
  void test002() {

    final String result = Converting
        .<Integer>convertToString((i) -> "x" + 1)
        .apply(1)
        .get();
    Assertions.assertEquals("x1" , result);
  }

  @Test
  @DisplayName("convertToString(f) - failed")
  void test003() {
    Converting
        .<Integer>convertToString((i) -> {
          throw new RuntimeException("failed");
        })
        .apply(1)
    .ifPresentOrElse((s) -> Assertions.fail(),
                     (failed) -> Assertions.assertEquals("RuntimeException - failed", failed));
  }



}
