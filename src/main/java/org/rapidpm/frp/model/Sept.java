package org.rapidpm.frp.model;

/**
 *
 */
public class Sept<T1, T2, T3, T4, T5, T6, T7> {

  private T1 t1;
  private T2 t2;
  private T3 t3;
  private T4 t4;
  private T5 t5;
  private T6 t6;
  private T7 t7;

  public Sept(T1 t1 , T2 t2 , T3 t3 , T4 t4 , T5 t5 , T6 t6 , T7 t7) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
    this.t7 = t7;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (! (o instanceof Sept)) return false;

    Sept<?, ?, ?, ?, ?, ?, ?> sept = (Sept<?, ?, ?, ?, ?, ?, ?>) o;

    if (t1 != null ? ! t1.equals(sept.t1) : sept.t1 != null) return false;
    if (t2 != null ? ! t2.equals(sept.t2) : sept.t2 != null) return false;
    if (t3 != null ? ! t3.equals(sept.t3) : sept.t3 != null) return false;
    if (t4 != null ? ! t4.equals(sept.t4) : sept.t4 != null) return false;
    if (t5 != null ? ! t5.equals(sept.t5) : sept.t5 != null) return false;
    if (t6 != null ? ! t6.equals(sept.t6) : sept.t6 != null) return false;
    return t7 != null ? t7.equals(sept.t7) : sept.t7 == null;
  }

  @Override
  public int hashCode() {
    int result = t1 != null ? t1.hashCode() : 0;
    result = 31 * result + (t2 != null ? t2.hashCode() : 0);
    result = 31 * result + (t3 != null ? t3.hashCode() : 0);
    result = 31 * result + (t4 != null ? t4.hashCode() : 0);
    result = 31 * result + (t5 != null ? t5.hashCode() : 0);
    result = 31 * result + (t6 != null ? t6.hashCode() : 0);
    result = 31 * result + (t7 != null ? t7.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Sept{" +
        "t1=" + t1 +
        ", t2=" + t2 +
        ", t3=" + t3 +
        ", t4=" + t4 +
        ", t5=" + t5 +
        ", t6=" + t6 +
        ", t7=" + t7 +
        '}';
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Sept<T1, T2, T3, T4, T5, T6, T7> next(final T1 t1 , final T2 t2 , final T3 t3 ,
                                                                                   final T4 t4 , final T5 t5 , final T6 t6 , final T7 t7) {
    return new Sept<>(t1 , t2 , t3 , t4 , t5 , t6 , t7);
  }


  public T1 getT1() {
    return t1;
  }

  public T2 getT2() {
    return t2;
  }

  public T3 getT3() {
    return t3;
  }

  public T4 getT4() {
    return t4;
  }

  public T5 getT5() {
    return t5;
  }

  public T6 getT6() {
    return t6;
  }

  public T7 getT7() {
    return t7;
  }
}
