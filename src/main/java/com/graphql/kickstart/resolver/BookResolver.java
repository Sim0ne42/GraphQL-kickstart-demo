package com.graphql.kickstart.resolver;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.repository.AuthorRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author author(Book book) {
        return authorRepository.findById(book.getAuthorId());
    }
}
