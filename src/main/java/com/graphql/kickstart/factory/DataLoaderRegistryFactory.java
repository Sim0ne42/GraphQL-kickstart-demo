package com.graphql.kickstart.factory;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.repository.AuthorRepository;

@Component
public class DataLoaderRegistryFactory {

    public static final String AUTHOR_DATA_LOADER = "AUTHOR_DATA_LOADER";

    private AuthorRepository authorRepository;

    public DataLoaderRegistryFactory(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public DataLoaderRegistry create() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(AUTHOR_DATA_LOADER, createAuthorDataLoader());
        return registry;
    }

    private DataLoader<Integer, Author> createAuthorDataLoader() {
        return DataLoader.newMappedDataLoader(ids -> CompletableFuture.supplyAsync(() -> ids.stream()
                        .map(authorRepository::findById)
                        .collect(Collectors.toMap(Author::getId, Function.identity()))
                )
        );
    }
}
