package org.rapidpm.frp.functions;

import java.util.function.Function;

import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
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
      return Result.failure(e.getMessage());
    }
  }

  void applyWithException() throws Exception;

}