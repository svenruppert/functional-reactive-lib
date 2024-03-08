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
