package com.mrinmoy.reactive.services;

import com.mrinmoy.reactive.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoServices {
    public Flux<BookInfo> getBookInfo() {
        var books = List.of(
                new BookInfo(Long.valueOf(1),"Book One","Author One","12121212"),
                new BookInfo(Long.valueOf(2),"Book Two","Author Two","42342343"),
                new BookInfo(Long.valueOf(3),"Book Three","Author Three","23425444")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(Long bookId) {
        var book = new BookInfo(Long.valueOf(1),"Book One","Author One","12121212");
        return Mono.just(book);
    }
}
