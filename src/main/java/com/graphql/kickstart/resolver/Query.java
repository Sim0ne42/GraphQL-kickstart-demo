package com.graphql.kickstart.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.repository.AuthorRepository;
import com.graphql.kickstart.repository.BookRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    public List<Author> authors() {
        return authorRepository.findAll();
    }
}
