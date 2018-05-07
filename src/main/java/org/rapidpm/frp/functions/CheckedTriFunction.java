package org.rapidpm.frp.functions;

import org.rapidpm.frp.model.Result;

import static org.rapidpm.frp.ExceptionFunctions.message;

public interface CheckedTriFunction<T1, T2, T3, R> extends TriFunction<T1, T2, T3, Result<R>> {
  @Override
  default Result<R> apply(T1 t1, T2 t2, T3 t3) {
    try {
      return Result.success(applyWithException(t1, t2, t3));
    } catch (Exception e) {
      return Result.failure(message().apply(e));
    }
  }

  R applyWithException(T1 t1, T2 t2, T3 t3) throws Exception;
}
