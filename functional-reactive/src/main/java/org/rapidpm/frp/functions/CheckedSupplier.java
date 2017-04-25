package org.rapidpm.frp.functions;

import java.util.function.Supplier;

import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
@FunctionalInterface
public interface CheckedSupplier<T> extends Supplier<Result<T>> {

  @Override
  default Result<T> get() {
    try {
      return Result.success(getWithException());
    } catch (Exception e) {
      return Result.failure(e.getMessage());
    }
  }

  T getWithException() throws Exception;

  default T getOrElse(Supplier<T> supplier) {
    return get().getOrElse(supplier);
  }
}
