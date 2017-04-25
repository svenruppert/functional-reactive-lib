package org.rapidpm.frp.functions;

import java.util.function.Function;

import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, Result<R>> {
  @Override
  Result<R> apply(T t);
}