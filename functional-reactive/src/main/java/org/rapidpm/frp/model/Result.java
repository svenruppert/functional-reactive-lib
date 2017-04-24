package org.rapidpm.frp.model;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Copyright (C) 2017 RapidPM - Sven Ruppert
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created by Sven Ruppert - RapidPM - Team on 16.03.17.
 */
public interface Result<T> {
  void bind(Consumer<T> success, Consumer<String> failure);

  static <T> Result<T> failure(String errorMessage) {
    return new Result.Failure<>(errorMessage);
  }

  static <T> Result<T> success(T value) {
    return new Result.Success<>(value);
  }

  T get();

  T getOrElse(Supplier<T> supplier);

  Boolean isPresent();

  void ifPresent(Consumer<T> consumer);

  abstract class AbstractResult<T> implements Result<T> {
    protected final T value;

    public AbstractResult(T value) {
      this.value = value;
    }

    @Override
    public void ifPresent(Consumer<T> consumer) {
      Objects.requireNonNull(consumer);
      if (value != null) consumer.accept(value);
    }

    public Boolean isPresent() {
      return (value != null) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean isAbsent() {
      return (value == null) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public T get() {
      return value;
    }

    @Override
    public T getOrElse(Supplier<T> supplier) {
      Objects.requireNonNull(supplier);
      return (value != null) ? value : supplier.get();
    }

  }

  class Success<T> extends AbstractResult<T> {

    public Success(T value) {
      super(value);
    }

    @Override
    public void bind(final Consumer<T> success, final Consumer<String> failure) {
      success.accept(value);
    }

  }

  class Failure<T> extends AbstractResult<T> {

    private final String errorMessage;

    public Failure(final String errorMessage) {
      super(null);
      this.errorMessage = errorMessage;
    }

    @Override
    public void bind(final Consumer<T> success, final Consumer<String> failure) {
      failure.accept(errorMessage);
    }
  }
}
