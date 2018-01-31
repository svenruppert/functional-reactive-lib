package org.rapidpm.frp.functions;

import static org.rapidpm.frp.ExceptionFunctions.message;

import java.util.function.Function;

import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 *
 * @author svenruppert
 * @version $Id: $Id
 */
@FunctionalInterface
public interface CheckedExecutor extends Function<Void, Result<Void>> {

  default Result<Void> execute() {
    return apply(null);
  }


  @Override
  default Result<Void> apply(Void t) {
    try {
      applyWithException();
      return Result.success(null);
    } catch (Exception e) {
      return Result.failure(message().apply(e));
    }
  }

  void applyWithException() throws Exception;

}
