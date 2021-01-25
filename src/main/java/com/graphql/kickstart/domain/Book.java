package com.graphql.kickstart.domain;

public class Book {

    private int id;
    private String name;
    private int authorId;

    public Book(final int id, final String name, final int authorId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAuthorId() {
        return authorId;
    }
}
