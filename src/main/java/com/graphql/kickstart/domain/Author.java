package com.graphql.kickstart.domain;

public class Author {

    private int id;
    private String name;

    public Author(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
