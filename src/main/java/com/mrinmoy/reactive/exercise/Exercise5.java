package com.mrinmoy.reactive.exercise;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {
     public static void main(String[] args) throws IOException {
         // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

         // Subscribe to a flux using the error and completion hooks
         // TODO: Write code here

         // Subscribe to a flux using an implementation of BaseSubscriber
         // TODO: Write code here

         /*Disposable subscribe = ReactiveSources
                 .intNumbersFlux()
                 .subscribe(
                         number -> System.out.println(number),
                         err -> System.out.println(err),
                         () -> System.out.println("Completed")
                 );*/

         ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

         //subscribe.dispose();

         System.out.println("Press a key to end");
         System.in.read();
    }
}

class MySubscriber<T> extends BaseSubscriber<T> {


    public void hookOnSubscribe(Subscription subscription) {
         System.out.println("Subscribe happened!");
         request(1);
    }

    public void hookOnNext(T value) {
         System.out.println(value.toString() + " received");
         request(1);
    }


}
