package com.graphql.kickstart.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.repository.BookRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {

    private BookRepository bookRepository;

    public AuthorResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> books(Author author) {
        return bookRepository.findByAuthorId(author.getId());
    }
}
