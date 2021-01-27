package com.graphql.kickstart.resolver;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.CustomGraphQLContextBuilder;
import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    public CompletableFuture<Author> author(Book book, DataFetchingEnvironment environment) {
        DataLoader dataLoader = environment.getDataLoader(CustomGraphQLContextBuilder.AUTHOR_DATA_LOADER);
        return dataLoader.load(book.getAuthorId());
    }
}
