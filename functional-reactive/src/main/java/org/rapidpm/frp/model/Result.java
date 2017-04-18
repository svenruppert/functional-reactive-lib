package org.rapidpm.frp.model;

import java.util.function.Consumer;

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

  class Success<T> implements Result<T> {

    private final T value;

    public Success(final T value) {
      this.value = value;
    }

    @Override
    public void bind(final Consumer<T> success, final Consumer<String> failure) {
      success.accept(value);
    }
  }

  class Failure<T> implements Result<T> {

    private final String errorMessage;

    public Failure(final String errorMessage) {
      this.errorMessage = errorMessage;
    }

    @Override
    public void bind(final Consumer<T> success, final Consumer<String> failure) {
      failure.accept(errorMessage);
    }
  }
}
