package com.mrinmoy.reactive.services;

import com.mrinmoy.reactive.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(Long bookId) {
        var reviewList = List.of(
                new Review(Long.valueOf(1),bookId,9.1,"Good Book"),
                new Review(Long.valueOf(2),bookId,8.6,"Worth Reading")
        );
        return Flux.fromIterable(reviewList);
    }
}
