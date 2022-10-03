package com.mrinmoy.reactive.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxMonoServicesTest {

    FluxMonoServices fluxMonoServices = new FluxMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitsMono = fluxMonoServices.fruitMono();
        StepVerifier.create(fruitsMono)
                .expectNext("Malta")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsMap = fluxMonoServices.fruitsFluxMap();
        StepVerifier.create(fruitsMap)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFluxFilter = fluxMonoServices.fruitsFluxFilter(5).log();
        StepVerifier.create(fruitsFluxFilter)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFluxFilter = fluxMonoServices.fruitsFluxFilterMap(5).log();
        StepVerifier.create(fruitsFluxFilter)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void testFruitsFluxFilterMap() {
        var fruitsMap = fluxMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitsMap)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsMap = fluxMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsMap)
                .expectNextCount(17)
                .verifyComplete();
    }



    @Test
    void testFruitMonoFlatMap() {
        var fruitsMap = fluxMonoServices.fruitMonoFlatMap();
        StepVerifier.create(fruitsMap)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap(){
    var fruitsMap = fluxMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsMap)
            .expectNextCount(17)
                .verifyComplete();
    }
}