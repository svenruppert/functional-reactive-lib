package org.rapidpm.frp.model.serial;

import java.io.Serializable;

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
 */
public class Quad<T1 extends Serializable, T2 extends Serializable, T3 extends Serializable, T4 extends Serializable> {

  private T1 t1;
  private T2 t2;
  private T3 t3;
  private T4 t4;

  public Quad(final T1 t1 , final T2 t2 , final T3 t3 , final T4 t4) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
  }

  public T4 getT4() {
    return t4;
  }

  public T3 getT3() {
    return t3;
  }

  public T1 getT1() {
    return t1;
  }

  public T2 getT2() {
    return t2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (! (o instanceof Quad)) return false;

    Quad<?, ?, ?, ?> quad = (Quad<?, ?, ?, ?>) o;

    if (t1 != null ? ! t1.equals(quad.t1) : quad.t1 != null) return false;
    if (t2 != null ? ! t2.equals(quad.t2) : quad.t2 != null) return false;
    if (t3 != null ? ! t3.equals(quad.t3) : quad.t3 != null) return false;
    return t4 != null ? t4.equals(quad.t4) : quad.t4 == null;
  }

  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;
    result = 31 * result + (t2 != null ? t2.hashCode() : 0);
    result = 31 * result + (t3 != null ? t3.hashCode() : 0);
    result = 31 * result + (t4 != null ? t4.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Quad{" +
        "t1=" + t1 +
        ", t2=" + t2 +
        ", t3=" + t3 +
        ", t4=" + t4 +
        '}';
  }

  public static <T1 extends Serializable,
      T2 extends Serializable,
      T3 extends Serializable,
      T4 extends Serializable> Quad<T1, T2, T3, T4> next(final T1 t1 , final T2 t2 , final T3 t3 , final T4 t4) {
    return new Quad<>(t1 , t2 , t3 , t4);
  }
}
