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
 * <p>Sext class.</p>
 *
 * @author svenruppert
 * @version $Id: $Id
 */
public class Sext<T1, T2, T3, T4, T5, T6> {

  private T1 t1;
  private T2 t2;
  private T3 t3;
  private T4 t4;
  private T5 t5;
  private T6 t6;

  /**
   * <p>Constructor for Sext.</p>
   *
   * @param t1 a T1 object.
   * @param t2 a T2 object.
   * @param t3 a T3 object.
   * @param t4 a T4 object.
   * @param t5 a T5 object.
   * @param t6 a T6 object.
   */
  public Sext(final T1 t1 , final T2 t2 , final T3 t3 ,
              final T4 t4 , final T5 t5 , final T6 t6) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
  }

  /**
   * <p>Getter for the field <code>t6</code>.</p>
   *
   * @return a T6 object.
   */
  public T6 getT6() {
    return t6;
  }

  /**
   * <p>Getter for the field <code>t5</code>.</p>
   *
   * @return a T5 object.
   */
  public T5 getT5() {
    return t5;
  }

  /**
   * <p>Getter for the field <code>t4</code>.</p>
   *
   * @return a T4 object.
   */
  public T4 getT4() {
    return t4;
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
    if (! (o instanceof Sext)) return false;

    Sext<?, ?, ?, ?, ?, ?> sext = (Sext<?, ?, ?, ?, ?, ?>) o;

    if (t1 != null ? ! t1.equals(sext.t1) : sext.t1 != null) return false;
    if (t2 != null ? ! t2.equals(sext.t2) : sext.t2 != null) return false;
    if (t3 != null ? ! t3.equals(sext.t3) : sext.t3 != null) return false;
    if (t4 != null ? ! t4.equals(sext.t4) : sext.t4 != null) return false;
    if (t5 != null ? ! t5.equals(sext.t5) : sext.t5 != null) return false;
    return t6 != null ? t6.equals(sext.t6) : sext.t6 == null;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;
    result = 31 * result + (t2 != null ? t2.hashCode() : 0);
    result = 31 * result + (t3 != null ? t3.hashCode() : 0);
    result = 31 * result + (t4 != null ? t4.hashCode() : 0);
    result = 31 * result + (t5 != null ? t5.hashCode() : 0);
    result = 31 * result + (t6 != null ? t6.hashCode() : 0);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Quint{" +
        "t1=" + t1 +
        ", t2=" + t2 +
        ", t3=" + t3 +
        ", t4=" + t4 +
        ", t5=" + t5 +
        '}';
  }

  /**
   * <p>next.</p>
   *
   * @param t1 a T1 object.
   * @param t2 a T2 object.
   * @param t3 a T3 object.
   * @param t4 a T4 object.
   * @param t5 a T5 object.
   * @param t6 a T6 object.
   * @param <T1> a T1 object.
   * @param <T2> a T2 object.
   * @param <T3> a T3 object.
   * @param <T4> a T4 object.
   * @param <T5> a T5 object.
   * @param <T6> a T6 object.
   * @return a {@link org.rapidpm.frp.model.Sext} object.
   */
  public static <T1, T2, T3, T4, T5, T6> Sext<T1, T2, T3, T4, T5, T6> next(final T1 t1 , final T2 t2 , final T3 t3 ,
                                                                           final T4 t4 , final T5 t5, final T6 t6) {
    return new Sext<>(t1 , t2 , t3 , t4 , t5, t6);
  }


}
