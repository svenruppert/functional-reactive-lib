package junit.org.rapidpm.frp.matcher;

import static org.junit.Assert.assertEquals;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class CaseTest {


  @Test
  void test000() {
    String value = "OK";

    match(
        matchCase(() -> Result.success(value))
    )
        .ifPresentOrElse(
            s -> assertEquals(value , s),
            s -> Assert.fail()
            );
  }
  @Test
  void test001() {
    final String error_message = "error message";
    String value = null;

    match(
        matchCase(() -> Result.success(value)) ,
        matchCase(() -> value == null , () -> Result.failure(error_message))
    )
        .ifPresentOrElse(
            s -> Assert.fail() ,
            s -> assertEquals(error_message , s)
        );
  }

  @Test
  void test002() {
    final String error_message = "error message";
    String value = "OK";

    match(
        matchCase(() -> Result.success(value)) ,
        matchCase(() -> value == null , () -> Result.failure(error_message))
    )
        .ifPresentOrElse(
            s -> assertEquals(value , s) ,
            s -> Assert.fail()
        );
  }

  @Test
  void test003() {
    final String error_message = "error message";


    IntStream
        .range(0 , 10)
        .boxed()
        .forEach(value -> match(
            matchCase(() -> Result.success(value)) ,
            matchCase(() -> value == null , () -> Result.failure(error_message)) ,
            matchCase(() -> value == 1 , () -> Result.success(100)) ,
            matchCase(() -> value == 2 , () -> Result.success(200))
        )
            .ifPresentOrElse(
                s -> {
                  if (value == 1) assertEquals(Long.valueOf(100) , Long.valueOf(s));
                  else if (value == 2) assertEquals(Long.valueOf(200) , Long.valueOf(s));
                  else assertEquals(Long.valueOf(value) , Long.valueOf(s));
                } ,
                s -> Assert.fail()
            ));


  }


}
