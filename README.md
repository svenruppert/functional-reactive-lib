# Release Notes

## 0.0.5
* Transformations
    + curry / unCurry - BiFunction / TriFunction
* Result.ofNullable
* Result renamed ```bind(Consumer<T> success, Consumer<String> failure)``` 
        to 
        ```ifPresentOrElse(Consumer<T> success, Consumer<String> failure)```
* Result added JDK9 signature ```ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)```
* Result added JDK9 signature ```Stream<T> stream()```
* Result added ```Result<T> or(Supplier<? extends Result<? extends T>> supplier)```
* Result added ```void ifAbsent(Runnable action)```

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