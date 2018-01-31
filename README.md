# Release Notes

This Lib is working with
 * Java 8
 * Java 9
 * Java 10 b41

## 0.5.1


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