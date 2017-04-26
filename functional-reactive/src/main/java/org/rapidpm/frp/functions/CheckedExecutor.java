package org.rapidpm.frp.functions;

import org.rapidpm.frp.model.Result;

/**
 * Created by svenruppert on 25.04.17.
 */
@FunctionalInterface
public interface CheckedExecutor extends CheckedConsumer<Void> {

  default Result<Void> execute() throws Exception {
    return apply(null);
  }

  Void applyWithException(Void v) throws Exception;
}