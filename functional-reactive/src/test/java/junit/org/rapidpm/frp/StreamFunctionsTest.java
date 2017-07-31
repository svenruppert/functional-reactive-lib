package junit.org.rapidpm.frp;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.Assert;
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


    Assert.assertEquals(1 , set.size());
    set.forEach(i -> Assert.assertEquals(Long.valueOf(i) , Long.valueOf(5)));
  }
}
