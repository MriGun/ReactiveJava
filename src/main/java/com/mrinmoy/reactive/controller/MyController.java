package com.mrinmoy.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
//@RequestMapping("/employees")
public class MyController {

    @GetMapping("/demo")
    public Mono<String> getMessage() {
        return computeMessage();
    }

    @GetMapping("/concat")
    public Mono<String> getMessageConcat() {
        return computeMessageWithDelay().zipWith(computeMessageFromDB())
                .map(value -> {
                    return value.getT1() + value.getT2();    // here is t is touple
                });
    }

    private Mono<String> computeMessage() {
        return Mono.just("Hello!");
    }

    private Mono<String> computeMessageWithDelay() {
        return Mono.just("Hello!").delayElement(Duration.ofSeconds(5));
    }

    private Mono<String> computeMessageFromDB() {
        return Mono.just("Mrinmoy!").delayElement(Duration.ofSeconds(5));
    }


}
