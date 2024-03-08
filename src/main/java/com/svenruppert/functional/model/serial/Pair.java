/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.svenruppert.functional.model.serial;

import java.io.Serializable;
import java.util.Objects;

/**
 * Copyright (C) 2010 RapidPM
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
 * Created by RapidPM - Team on 10.12.16.
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public class Pair<T1 extends Serializable, T2 extends Serializable> implements Serializable {
  private T1 t1;
  private T2 t2;

  /**
   * <p>Constructor for Pair.</p>
   *
   * @param t1 a T1 object.
   * @param t2 a T2 object.
   */
  public Pair(final T1 t1, final T2 t2) {
    this.t1 = t1;
    this.t2 = t2;
  }

  /**
   * <p>Getter for the field <code>t1</code>.</p>
   *
   * @return a T1 object.
   */
  public T1 getT1() {
    return t1;
  }

  /**
   * <p>Getter for the field <code>t2</code>.</p>
   *
   * @return a T2 object.
   */
  public T2 getT2() {
    return t2;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Pair{" +
        "t1=" + t1 +
        ", t2=" + t2 +
        '}';
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (! (o instanceof Pair)) return false;
    final Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(t1, pair.t1) &&
        Objects.equals(t2, pair.t2);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(t1, t2);
  }

  /**
   * <p>next.</p>
   *
   * @param a a T1 object.
   * @param b a T2 object.
   * @param <T1> a T1 object.
   * @param <T2> a T2 object.
   * @return a {@link com.svenruppert.functional.model.serial.Pair} object.
   */
  public static <T1 extends Serializable, T2 extends Serializable> Pair<T1, T2> next(T1 a, T2 b) {
    return new Pair<>(a, b);
  }
}
