package com.mrinmoy.reactive.exercise;

public class Exercise4 {

   public static void main(String[] args) {

       // Use ReactiveSources.intNumberMono()

       // Print the value from intNumberMono when it emits

       ReactiveSources.intNumberMono().subscribe(
               number -> System.out.println(number),
               err -> System.out.println(err.getMessage()),
               () -> System.out.println("Complete")
       );

       Integer number = ReactiveSources.intNumberMono().block();

       System.out.println("Hello" + number);

       // TODO: Write code here

       // Get the value from the Mono into an integer variable
    }
}
