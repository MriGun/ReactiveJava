package com.mrinmoy.reactive.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

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
}
