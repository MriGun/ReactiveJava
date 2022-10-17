package com.mrinmoy.reactive.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxMonoServices {
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number);
    }

    public Flux<String> fruitsFluxFilterMap(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofSeconds(3))
                .log();
    }


    //flatMapMany will convert Mono of string to Flux of String
    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }



    //Concat map will preserve the order of the element
    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofSeconds(3))
                .log();
    }


    public Mono<String> fruitMono() {
        return Mono.just("Malta");
    }

    public static void main(String[] args) {

        FluxMonoServices fluxMonoServices = new FluxMonoServices();
        fluxMonoServices.fruitsFlux()
                .subscribe(s -> System.out.println("Fruit is = " + s));

        fluxMonoServices.fruitMono()
                .subscribe(s -> {
                    System.out.println("Mono Fruit is = " + s);
                });
    }


    //Transform Operation. instead of using lamda filter we can use our custom made filter.
    Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .log();
                //.filter(s -> s.length() > number);
    }


    //If we want to pass a default data if no data available
    Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
        //.filter(s -> s.length() > number);
    }


    //If we want to switch new dataset then we will use switchIfEmpty
    Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Milk", "Butter"))
                .log();
        //.filter(s -> s.length() > number);
    }

    //concat will concat two flux or mono. It will happen setp by step.
    // First first flux will be completed using subscription. Then 2nd one. After that it will be concated.
    public Flux<String> fruitsFluxConcat() {

        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Lemon", "Latuce");

        return Flux.concat(fruits, veggies);
    }


    //same as concat
    public Flux<String> fruitsFluxConcatWith() {

        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Lemon", "Latuce");

        return fruits.concatWith(veggies);
    }


    //concat with Mono
    public Flux<String> fruitsMonoConcatWith() {

        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Lemon");

        return fruits.concatWith(veggies);
    }


    //Merge concat things asynchously. where concat does this sequentially
    public Flux<String> fruitsFluxMerge() {

        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Lemon", "Latuce")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits, veggies);
    }

    public Flux<String> fruitsFluxMergeWith() {

        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Lemon", "Latuce")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsFluxMergeWithSequential() {

        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Lemon", "Latuce")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits, veggies);
    }


    //zip enables us to perform opertaion on two flux or mono type and return a single flux or mono
    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Lemon", "Latuce");
        return Flux.zip(fruits, veggies, (first, second) -> first +second).log();
    }

    public Flux<String> fruitsFluxZipWth() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Lemon", "Latuce");
        return fruits.zipWith(veggies, (first, second) -> first +second).log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Lemon", "Latuce");
        var moreVeggies = Flux.just("Potato", "Tomato");
        return Flux.zip(fruits, veggies, moreVeggies)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
    }

    public Mono<String> fruitsMonoZipWth() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Lemon");
        return fruits.zipWith(veggies, (first, second) -> first +second).log();
    }

    //DoOn operator is side effect operator
    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription =" + subscription);
                })
                .doOnComplete(() -> System.out.println("Completed"))
                .log();
    }


    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Apple", "Mango")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occured!")
                ))
                .onErrorReturn("Orange");
    }

    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equals("Mango")) {
                        throw new RuntimeException("Exception Occured!");
                    }
                    return s.toUpperCase();
                })
                .onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equals("Mango")) {
                        throw new RuntimeException("Exception Occured!");
                    }
                    return s.toUpperCase();
                })
                .checkpoint("Error checkpoint")
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From onError Map");
                });
    }

    public Flux<String> fruitsFluxOnError() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equals("Mango")) {
                        throw new RuntimeException("Exception Occured!");
                    }
                    return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);
                });
    }

}
