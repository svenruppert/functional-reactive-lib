package org.rapidpm.frp.functions;

/**
 * Created by svenruppert on 25.04.17.
 */
@FunctionalInterface
public interface CheckedExecutor extends CheckedConsumer<Void> {

  void execute() throws Exception;

  default Void applyWithException(Void v) throws Exception {
    execute();
    return null;
  }
}