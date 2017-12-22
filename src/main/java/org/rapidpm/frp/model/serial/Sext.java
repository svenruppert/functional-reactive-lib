package org.rapidpm.frp.model.serial;

import java.io.Serializable;

/**
 *
 */
public class Sext<T1 extends Serializable, T2 extends Serializable, T3 extends Serializable,
    T4 extends Serializable, T5 extends Serializable, T6 extends Serializable> {

  private T1 t1;
  private T2 t2;
  private T3 t3;
  private T4 t4;
  private T5 t5;
  private T6 t6;

  public Sext(final T1 t1 , final T2 t2 , final T3 t3 ,
              final T4 t4 , final T5 t5 , final T6 t6) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
  }

  public T6 getT6() {
    return t6;
  }

  public T5 getT5() {
    return t5;
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
    if (! (o instanceof Sext)) return false;

    Sext<?, ?, ?, ?, ?, ?> sext = (Sext<?, ?, ?, ?, ?, ?>) o;

    if (t1 != null ? ! t1.equals(sext.t1) : sext.t1 != null) return false;
    if (t2 != null ? ! t2.equals(sext.t2) : sext.t2 != null) return false;
    if (t3 != null ? ! t3.equals(sext.t3) : sext.t3 != null) return false;
    if (t4 != null ? ! t4.equals(sext.t4) : sext.t4 != null) return false;
    if (t5 != null ? ! t5.equals(sext.t5) : sext.t5 != null) return false;
    return t6 != null ? t6.equals(sext.t6) : sext.t6 == null;
  }

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

  public static <T1 extends Serializable, T2 extends Serializable, T3 extends Serializable,
      T4 extends Serializable, T5 extends Serializable, T6 extends Serializable> Sext<T1, T2, T3, T4, T5, T6> next(final T1 t1 , final T2 t2 , final T3 t3 ,
                                                                                                                   final T4 t4 , final T5 t5 , final T6 t6) {
    return new Sext<>(t1 , t2 , t3 , t4 , t5 , t6);
  }


}
