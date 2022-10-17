package com.mrinmoy.reactive.services;

import com.mrinmoy.reactive.domain.Book;
import com.mrinmoy.reactive.domain.Review;
import com.mrinmoy.reactive.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private final BookInfoServices bookInfoServices;
    private final ReviewService reviewService;

    public BookService(BookInfoServices bookInfoServices, ReviewService reviewService) {
        this.bookInfoServices = bookInfoServices;
        this.reviewService = reviewService;
    }


    public Flux<Book> getBooks() {

        var allBooks = bookInfoServices.getBookInfo();
        return allBooks
                .flatMap(bookInfo -> {
                   Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                   return reviews
                           .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occured while fetching Books");
                })
                .log();

    }

    public Flux<Book> getBooksRetry() {

        var allBooks = bookInfoServices.getBookInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occured while fetching Books");
                })
                .retry(3)
                .log();

    }

    public Flux<Book> getBooksRetryWhen() {

        var retrySpec = Retry.backoff(
                3,
                Duration.ofMillis(3000)
        ).filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                Exceptions.propagate(retrySignal.failure())));

        var allBooks = bookInfoServices.getBookInfo();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occured while fetching Books");
                })
                .retryWhen(retrySpec)
                .log();

    }

    public Mono<Book> getBookById(Long bookId) {
        var book = bookInfoServices.getBookById(bookId);
        var review = reviewService.getReviews(bookId)
                .collectList();

        return book.zipWith(review, (b, r) -> new Book(b, r));
    }


}
