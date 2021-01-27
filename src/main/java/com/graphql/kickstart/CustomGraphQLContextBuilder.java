package com.graphql.kickstart;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import com.graphql.kickstart.domain.Author;
import com.graphql.kickstart.repository.AuthorRepository;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    public static final String AUTHOR_DATA_LOADER = "AUTHOR_DATA_LOADER";

    private AuthorRepository authorRepository;

    public CustomGraphQLContextBuilder(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public GraphQLContext build() {
        return new DefaultGraphQLContext();
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(buildDataLoaderRegistry())
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .with(buildDataLoaderRegistry())
                .build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
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
