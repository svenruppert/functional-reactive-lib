[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.rapidpm/rapidpm-functional-reactive/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.rapidpm/rapidpm-functional-reactive)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a0b7d530374d400fa9a79d270cf95c1a)](https://www.codacy.com/app/sven-ruppert/functional-reactive-lib?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=functional-reactive/functional-reactive-lib&amp;utm_campaign=Badge_Grade)
[![](http://drone.rapidpm.org/api/badges/RapidPM/functional-reactive-lib/status.svg?branch=develop)](http://drone.rapidpm.org/api/badges/RapidPM/functional-reactive-lib/status.svg?branch=develop)


# Release Notes

This Lib is working with
 * Java 08 (open/oracle/ibm/zulu/graalvm/shenandoah)
 * Java 09 (open/oracle/ibm/zulu/shenandoah)
 * Java 10 (open/oracle/shenandoah)
 * Java 11 (open/oracle)
 
With the beginning of the version 0.7.0
the lib will be shipped as a JDK10/11 version and 
a JDK8 version (marked with -jdk8 in the version tag)

I am not sure how long I will support JDK8 parallel to the JDK11 version.
As long, as there are not big changes, this should be available.


 
## 0.8.0-SNAPSHOT
This release we will increase test coverage and documentation.
The basic structures and functions are in production used since longer time.
Adding Apache Lic header to all files

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