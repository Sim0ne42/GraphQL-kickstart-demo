package com.graphql.kickstart.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.domain.Book;
import com.graphql.kickstart.repository.AuthorRepository;
import com.graphql.kickstart.repository.BookRepository;

@Component
public class DataLoaderRegistryFactory {

    public static final String AUTHOR_DATA_LOADER = "AUTHOR_DATA_LOADER";
    public static final String BOOK_DATA_LOADER = "BOOK_DATA_LOADER";

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DataLoaderRegistryFactory(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public DataLoaderRegistry create() {
        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(AUTHOR_DATA_LOADER, createAuthorDataLoader());
        registry.register(BOOK_DATA_LOADER, createBookDataLoader());
        return registry;
    }

    private DataLoader<Integer, Author> createAuthorDataLoader() {
        return DataLoader.newMappedDataLoader(ids -> CompletableFuture.supplyAsync(() -> ids.stream()
                        .map(authorRepository::findById)
                        .collect(Collectors.toMap(Author::getId, Function.identity()))
                )
        );
    }

    private DataLoader<Integer, List<Book>> createBookDataLoader() {
        return DataLoader.newMappedDataLoader(ids -> CompletableFuture.supplyAsync(() -> {
                    Map<Integer, List<Book>> authorIdToBooksMap = new HashMap<>();
                    ids.forEach(id -> authorIdToBooksMap.put(id, bookRepository.findByAuthorId(id)));
                    return authorIdToBooksMap;
                })
        );
    }
}
