package com.graphql.kickstart.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Book;

@Component
public class BookRepository {

    private final List<Book> books = List.of(
            new Book(1, "Guida galattica per gli autostoppisti", 1),
            new Book(2, "Ristorante al termine dell'Universo", 1),
            new Book(3, "IT", 2),
            new Book(4, "La metamorfosi", 3)
    );

    public List<Book> findAll() {
        return books;
    }

    public Book findById(final int bookId) {
        return books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElse(null);
    }

    public Book findByName(final String bookName) {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst()
                .orElse(null);
    }
}
