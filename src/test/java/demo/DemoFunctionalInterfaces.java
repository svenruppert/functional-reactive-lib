package demo;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;

public class DemoFunctionalInterfaces {
  private static String doWorkStatic() {return null;}

  @FunctionalInterface
  interface InterfaceA {
    String doWork();
  }

  @FunctionalInterface
  interface InterfaceB {
    String doWork();

    default String doMoreWork() {
      return "useless";
    }
  }

  @FunctionalInterface
  interface InterfaceC {
    String doWork();

    default String doMoreWork() {
      return "useless";
    }

    private String doHiddenWork() {
      return "useless";
    }
  }


  interface A {
    static void doStaticWork() {out.println("interface A");}
    default void doWork() {out.println("default A");}
  }

  interface B extends A {
    default void doWork() {out.println("default B");}
  }

  interface C {
    default void doWork() {out.println("default C");}
  }

  public static class ClassAB implements A , B { }
  public static class ClassAC implements A , C {
    @Override
    public void doWork() {
      out.println("default ClassAC");
    }
  }


  @Test
  void demoA() {
    new ClassAB().doWork(); //"default B"
    //ClassAB.doStaticWork()

    A.doStaticWork();

    new ClassAC().doWork(); //"default ClassAC"

  }


  public static class DemoClass{
    public void consumeInterfaceA(InterfaceA a){ }
  }

  @Test
  void demoB() {
    DemoClass demoClass = new DemoClass();

    demoClass.consumeInterfaceA(new InterfaceA() {
      @Override
      public String doWork() {
        return null;
      }
    });

    demoClass.consumeInterfaceA(() -> { return null;});
    demoClass.consumeInterfaceA(() -> null);
    //be careful !!! inheritance ??
    demoClass.consumeInterfaceA(DemoFunctionalInterfaces::doWorkStatic);



  }
}
