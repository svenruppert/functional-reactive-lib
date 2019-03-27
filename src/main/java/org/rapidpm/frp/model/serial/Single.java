package org.rapidpm.frp.model.serial;

import java.io.Serializable;
import java.util.Objects;

public class Single<T1 extends Serializable> {
  private T1 t1;

  /**
   * <p>Constructor for Pair.</p>
   *
   * @param t1 a T1 object.
   */
  public Single(final T1 t1) {
    this.t1 = t1;
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
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Single{" +
           "t1=" + t1 +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (! (o instanceof Single)) return false;
    Single<?> single = (Single<?>) o;
    return Objects.equals(t1 , single.t1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1);
  }

  /**
   * <p>next.</p>
   *
   * @param a    a T1 object.
   * @param <T1> a T1 object.
   * @return a {@link org.rapidpm.frp.model.serial.Single} object.
   */
  public static <T1 extends Serializable > Single<T1> next(T1 a) {
    return new Single<>(a);
  }
}
