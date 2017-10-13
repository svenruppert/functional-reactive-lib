package org.rapidpm.frp;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 */
public interface ExceptionFunctions {

  static Function<Exception, String> message() {
    return (e) -> {
      Objects.requireNonNull(e , "Exception instance was null.");
      final String message = e.getMessage();
      final String simpleName = e.getClass().getSimpleName();
      return (message != null ? simpleName + " - " + message : simpleName + " - no message");
    };
  }

  static Function<Exception, Stream<StackTraceElement>> toStackTraceStream() {
    return (e) -> {
      final StackTraceElement[] stackTrace = e.getStackTrace();
      return (stackTrace != null)
          ? Arrays.stream(stackTrace)
          : Stream.empty();
    };
  }
}
