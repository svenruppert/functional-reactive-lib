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
package org.rapidpm.frp.model;

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
public class Triple<T1, T2, T3> {
  private T1 t1;
  private T2 t2;
  private T3 t3;

  /**
   * <p>Constructor for Triple.</p>
   *
   * @param t1 a T1 object.
   * @param t2 a T2 object.
   * @param t3 a T3 object.
   */
  public Triple(final T1 t1, final T2 t2, final T3 t3) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
  }

  /**
   * <p>Getter for the field <code>t3</code>.</p>
   *
   * @return a T3 object.
   */
  public T3 getT3() {
    return t3;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (! (o instanceof Triple)) return false;

    Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;

    if (t1 != null ? ! t1.equals(triple.t1) : triple.t1 != null) return false;
    if (t2 != null ? ! t2.equals(triple.t2) : triple.t2 != null) return false;
    return t3 != null ? t3.equals(triple.t3) : triple.t3 == null;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;
    result = 31 * result + (t2 != null ? t2.hashCode() : 0);
    result = 31 * result + (t3 != null ? t3.hashCode() : 0);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Triple{" +
        "t1=" + t1 +
        ", t2=" + t2 +
        ", t3=" + t3 +
        '}';
  }

  /**
   * <p>next.</p>
   *
   * @param t1 a T1 object.
   * @param t2 a T2 object.
   * @param t3 a T3 object.
   * @param <T1> a T1 object.
   * @param <T2> a T2 object.
   * @param <T3> a T3 object.
   * @return a {@link Triple} object.
   */
  public static <T1, T2, T3> Triple<T1, T2, T3> next(final T1 t1, final T2 t2, final T3 t3) {
    return new Triple<>(t1, t2, t3);
  }
}
