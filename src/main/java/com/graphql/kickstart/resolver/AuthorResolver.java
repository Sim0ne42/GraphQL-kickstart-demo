package com.graphql.kickstart.resolver;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.factory.DataLoaderRegistryFactory;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {

    public CompletableFuture<List<Book>> books(Author author, DataFetchingEnvironment environment) {
        DataLoader dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.BOOK_DATA_LOADER);
        return dataLoader.load(author.getId());
    }
}
