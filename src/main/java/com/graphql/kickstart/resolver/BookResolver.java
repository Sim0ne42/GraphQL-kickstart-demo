package com.graphql.kickstart.resolver;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.factory.DataLoaderRegistryFactory;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    public CompletableFuture<Author> author(Book book, DataFetchingEnvironment environment) {
        DataLoader dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.AUTHOR_DATA_LOADER);
        return dataLoader.load(book.getAuthorId());
    }
}
