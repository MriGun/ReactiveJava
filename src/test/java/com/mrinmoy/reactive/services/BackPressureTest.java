package com.mrinmoy.reactive.services;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure() {

        var number = Flux.range(1, 100).log();
        //number.subscribe(integer -> System.out.println("integer = " + integer));

        number.subscribe(new BaseSubscriber<Integer>() {

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    cancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });

    }


    @Test
    public void testBackPressureDrop() {

        var number = Flux.range(1, 100).log();
        //number.subscribe(integer -> System.out.println("integer = " + integer));

        number
                .onBackpressureDrop(integer -> {
                    System.out.println("Dropped values = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    hookOnCancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });

    }


    @Test
    public void testBackPressureBuffer() {

        var number = Flux.range(1, 100).log();
        //number.subscribe(integer -> System.out.println("integer = " + integer));

        number
                .onBackpressureBuffer(10, integer -> {
                    System.out.println("Buffered values = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });

    }

    @Test
    public void testBackPressureError() {

        var number = Flux.range(1, 100).log();
        //number.subscribe(integer -> System.out.println("integer = " + integer));

        number
                .onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("Errorrrrrrrrrrrrrr");
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });

    }
}
