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
//with JDK9++
//    private String doHiddenWork() {
//      return "useless";
//    }
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
