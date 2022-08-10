package com.mrinmoy.reactive.exercise;

import java.io.IOException;

public class Exercise2 {

    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
       ReactiveSources.intNumbersFlux()
               .subscribe(
                       e -> System.out.println(e),
                       err -> System.out.println(err.getMessage()),
                       () -> System.out.println("Complete"));
       // we are basically saying whenever reactive stream emit something we want to run a piece of code
        //We are not saying go fetch all the element and run for each.
        //We are saying keep finding it.



        // Print all users in the ReactiveSources.userFlux stream
        ReactiveSources.userFlux().subscribe(user -> System.out.println(user));

        System.out.println("Press a key to end");
        System.in.read();
    }

}
