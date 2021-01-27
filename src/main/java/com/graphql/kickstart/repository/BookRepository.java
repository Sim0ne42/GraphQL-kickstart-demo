package com.graphql.kickstart.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Book;

@Component
public class BookRepository {

    private final List<Book> books = List.of(
            new Book(1, "The Hitchhiker's Guide to the Galaxy", 1),
            new Book(2, "Dirk Gently's Holistic Detective Agency", 1),
            new Book(3, "IT", 2),
            new Book(4, "The Metamorphosis", 3)
    );

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int bookId) {
        return books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElse(null);
    }

    public Book findByName(String bookName) {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst()
                .orElse(null);
    }

    public List<Book> findByAuthorId(int authorId) {
        return books.stream()
                .filter(book -> book.getAuthorId() == authorId)
                .collect(Collectors.toList());
    }
}
