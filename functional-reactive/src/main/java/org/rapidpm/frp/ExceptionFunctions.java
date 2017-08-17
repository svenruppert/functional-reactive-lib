package org.rapidpm.frp;

import java.util.Objects;
import java.util.function.Function;

/**
 *
 */
public interface ExceptionFunctions {

  static Function<Exception, String> message(){
   return (e) -> {
     Objects.requireNonNull(e, "Exception instance was null.");
     final String message = e.getMessage();
     return (message != null ? message : e.getClass().getSimpleName());
   };
  }


}
