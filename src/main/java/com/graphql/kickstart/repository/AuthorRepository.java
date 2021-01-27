package com.graphql.kickstart.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;

@Component
public class AuthorRepository {

    private List<Author> authors = List.of(
            new Author(1, "Douglas Adams"),
            new Author(2, "Stephen King"),
            new Author(3, "Franz Kafka"),
            new Author(4, "Edgar Allan Poe")
    );

    public Author findById(int authorId) {
        return authors.stream()
                .filter(author -> author.getId() == authorId)
                .findFirst()
                .orElse(null);
    }

    public List<Author> findAll() {
        return authors;
    }
}
