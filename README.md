[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.rapidpm/rapidpm-functional-reactive/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.rapidpm/rapidpm-functional-reactive)


![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=functional-reactive_functional-reactive-lib&metric=security_rating)
![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=functional-reactive_functional-reactive-lib&metric=sqale_rating)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a0b7d530374d400fa9a79d270cf95c1a)](https://www.codacy.com/app/sven-ruppert/functional-reactive-lib?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=functional-reactive/functional-reactive-lib&amp;utm_campaign=Badge_Grade)


# Functional Reactive Lib with Core Java
This is a nano lib, based on a few classes that will give you 
a handful of functional elements that you can use in your core Java project.
If you want to know more about it, have a look at my Youtube Series about this topic.

* (EN): [https://youtu.be/jMP9r5_bi5c](https://youtu.be/jMP9r5_bi5c)
* (DE): [https://youtu.be/S5ysVvritIg](https://youtu.be/S5ysVvritIg)

## Please, subscribe my **Youtube channel**
* Youtube: [DE] - [bit.ly/Youtube-Sven](https://bit.ly/Youtube-Sven)
* Youtube: [EN] - [bit.ly/Outdoor-Nerd](https://bit.ly/Outdoor-Nerd)

### and/or register for my Newsletter: 
This newsletter gives you access to the slides of my videos, 
and you are the first to be informed about my new videos, 
articles and blog posts.
Besides, you will always receive the latest information about 
where and when I will soon be giving online lectures, meetups and 
free webinars. So that you are among the first 
that will have the change to register and to grab your spot. 

Newsletter: [https://mailings.sven-ruppert.de](https://mailings.sven-ruppert.de)


## JDK8 or JDK9 and above
I compiled this with the Open JDK8.
Previous versions at maven central are build ith JDK10.
I went away from the JDK10, because most projects I know, are using JDK8 in production right know.
But, if you want to use this with JDK9/10/11/... you can use it.. it is working perfect!
Only if you start using the JMS, it could be helpful to add a **module-info.java**
Add the following lines, switch inside the pom.xml to the JDK version you prefer,
start a **mvn clean install** and be happy ;-)

```java
module rapidpm.functional.reactive {
 exports org.rapidpm.frp;
 exports com.svenruppert.functional.functions;
 exports com.svenruppert.functional.matcher;
 exports com.svenruppert.functional.memoizer;
 exports com.svenruppert.functional.model;
 exports com.svenruppert.functional.model.serial;
 exports com.svenruppert.functional.reactive;
}
```


# Release Notes
This Lib is working with JDK 8 up to 21
## 02.00.04-SRU
fixed jdk in jitpack yml

## 02.00.03-SRU
updated parent, and maven minimum version to 3.9.6

## 02.00.02-SRU
Switched groupid to 02.00.02-SRU - jitpack custom domain name is not working properly
updated to parent version 05.00.03-SRU

## 02.00.00-SRU
Changing to new namespace. The package will be under 
com.svenruppert:functional-reactive now


## 01.01.00-RPM-SNAPSHOT
This release we will increase test coverage and documentation.
The basic structures and functions are in production used since longer time.
Adding Apache Lic header to all files

## 01.00.07-RPM

* Transformations
* static <T> Function<Iterator<T>, Stream<T>> iteratorToStream() 
* <V, R> Result<R> thenCombineFlat(V value, BiFunction<T, V, R> func)
* started Youtube Series about this lib.
* removed JitCI from production pipeline

## 01.00.03-RPM
* added JitCI for deployment as well
* version updates

## 01.00.02-RPM
*  static <T, R> CheckedFunction<T, R> asCheckedFunc(Function<T, R> f)
* Converting.convertToString
* Converting.convertToInteger
* Converting.convertToDouble
* SystemProperties Util Functions
* Single<T>
   
## 01.00.01-RPM
* BUGFIX in case, the result was calculated two times.

## 01.00.00-RPM
* defined it as final, as there was no big change since a long time using it in a bunch of projects
* JDK8 is used to create the jar
* module-info.java is available inside the folder **_data**

## 00.07.06-RPM
* parent pom update - version updates

## 00.07.05-RPM
* parent pom update - version updates
* added docker based deploy scripts

## 00.07.04-RPM
* switched to new version numbers format
  To make search/replace easier, I started with a new version format.
  00.07.04-RPM ( -SNAPSHOT). The x.y.z is used in the same way, as before, but added RPM
  and leading zeros to make this format different from others.
  With this it is less possible to mix/change version numbers from 
  other dependencies. A **0.7.4** could be used from different 
  dependencies. ;-) 

## 0.7.3
* dependency updates
* added ```CompletableFutureQueue<T, N> thenCombineAsyncFromArray(Function<R, N>[] nextTransformations)```

## 0.7.2
* bug fix for **modules-info.java**

## 0.7.1 - DONT USED THIS VERSION !! broken module-info.java
* activated JIGSAW
* updated to rapidpm-dependencies 4.0.2

## 0.7.0 && 0.7.0-JDK8-SNAPSHOT
* started with JDK10/11 support
* using JDK11 for development and JDK10 for deployment

## 0.6.2
* latest JDK8 only version! - switching to JDK10/11 -- JDK8 model

## 0.6.1
* Added docker scripts for cross JDK compiles
* updates parent versions
* deactivated nexus mirror for drone
* deactivated orig IBM JDK 8/9 Docker images

## 0.6.0
This release is a maintenance release only, no new functionality 
* switched to new dependencies version 0.6.3
    * this includes the lic header plugin
    * version updates
    * minimized profiles
    * removed indirect dependency to old nexus
* updated all file headers

## 0.5.3
* added ```CheckedTriFunction<T1, T2, T3, R> extends TriFunction<T1, T2, T3, Result<R>>```
* typo unCurryBifunction -> unCurryBiFunction
* typo unCurryTrifunction -> unCurryTriFunction
* curry / un-curry Checked Functions ```Transformations```
    * curryCheckedBiFunction() - ```Function<CheckedBiFunction<A, B, R>, Function<A, CheckedFunction<B, R>>>```
    * unCurryCheckedBiFunction() - ```Function<Function<A, CheckedFunction<B, R>>, CheckedBiFunction<A, B, R>>```
    * curryCheckedTriFunction() - ```Function<
            CheckedTriFunction<A, B, C, R>,
            Function<A, Function<B, CheckedFunction<C, R>>>>```
    * unCurryCheckedTriFunction() - ```Function<
            Function<A, Function<B, CheckedFunction<C, R>>>,
            CheckedTriFunction<A, B, C, R>>``` 
    
## 0.5.2
* extend Result<T> with a fluent API
    * void ifFailed -> Result<T> ifFailed
    * void ifPresent -> Result<T> ifPresent
    * void ifAbsent -> Result<T> ifAbsent
    
## 0.5.1
* renamed Tripel (german) to Triple (en)
* pitest is working with junit5 now

## 0.5.0
* Result added ```void ifFailed(Consumer<String> failed);```
* switched to jUnit5
* updated parent pom to 3.5.7

## 0.1.0
* Result added ```<U> Result<U> asFailure()```
* Result added ```<U> Result<U> flatMap(Function<T, Result<U>> mapper)```
* ExceptionFunctions.message() extended exception message with Exception Classname
* added CompletableFutureQueue

## 0.0.6
* ExceptionFunctions added ```Function<Exception, String> message()```
* ExceptionFunctions added ```Function<Exception, Stream<StackTraceElement>> toStackTraceStream()```
* added Sext and Sept data-classes

## 0.0.5
* added model.serial pkg with Data classes only for ```extends Serializable``` types
* Transformations
    + curry / unCurry - BiFunction / TriFunction
* StreamFunctions added ```<T> Function<Predicate<T>, Function<Stream<T>, Stream<T>>> streamFilter()```
* Result.ofNullable
* Result renamed ```bind(Consumer<T> success, Consumer<String> failure)``` 
        to 
        ```ifPresentOrElse(Consumer<T> success, Consumer<String> failure)```
* Result added JDK9 signature ```ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)```
* Result added JDK9 signature ```Stream<T> stream()```
* Result added ```Result<T> or(Supplier<? extends Result<? extends T>> supplier)```
* Result added ```void ifAbsent(Runnable action)```
* Result added ```<U> Result<U> map(Function<? super T, ? extends U> mapper)```


## 0.0.4
* added Result.thenCombine
* added Result.thenCombineAsync
* added CheckedBiFunction

## 0.0.3
* added CheckedPredicate

## 0.0.2
* basic Datastructures like Pair, Triple, Quad
* added fromOptional and toOptional to the class Result
* added CheckedFunction , CheckedConsumer and CheckedSupplier
* extracted TriFunction from Memoizer to pkg functions
* added QuadFunction
* added StringFunctions
* added Transformations 
* added StringFunctions
* ported Strman-java to functional style