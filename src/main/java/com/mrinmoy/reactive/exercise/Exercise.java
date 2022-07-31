package com.mrinmoy.reactive.exercise;

public class Exercise {

    public static void main(String[] args) {

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here

        StreamSources.intNumbersStream().forEach(i -> System.out.println(i));

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here

        System.out.println("  ----   ");

        StreamSources.intNumbersStream().filter(number -> number < 5)
                .forEach(i -> System.out.println(i));


        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here

        System.out.println("  ----   ");
        StreamSources.intNumbersStream().filter(number -> number > 5)
                .skip(1)
                .limit(2)
                .forEach(number -> System.out.println(number));

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        System.out.println("  ----   ");

        Integer value = StreamSources.intNumbersStream().filter(number -> number > 5)
                .findFirst()
                .orElse(-1);

        System.out.println(value);


        // Print first names of all users in userStream
        // TODO: Write code here
        System.out.println("  ----   ");

        StreamSources.userStream().forEach(user -> System.out.println(user.getFirstName()));

        //another way
        System.out.println("  ----   ");
        StreamSources.userStream()
                .map(user -> user.getFirstName())  // map converts stream of one instance to another instance
                .forEach(userName -> System.out.println(userName));


        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here

        System.out.println("  ----   ");
        StreamSources.intNumbersStream()
                .flatMap((id -> StreamSources.userStream().filter(user -> user.getUserId() == id)))
                .map(user -> user.getFirstName())
                .forEach(userName -> System.out.println(userName));


        //another way
        StreamSources.userStream()
                .filter(u -> StreamSources.intNumbersStream().anyMatch(i -> i == u.getUserId()))
                .forEach(userName -> System.out.println(userName));

    }

}
