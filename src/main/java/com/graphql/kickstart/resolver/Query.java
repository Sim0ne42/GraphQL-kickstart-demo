package com.graphql.kickstart.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.repository.BookRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

    private BookRepository bookRepository;

    public Query(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> books() {
        return bookRepository.findAll();
    }

    public Book bookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    public Book bookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}
