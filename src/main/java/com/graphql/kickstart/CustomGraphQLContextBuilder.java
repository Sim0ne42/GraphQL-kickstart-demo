package com.graphql.kickstart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

import org.springframework.stereotype.Component;

import com.graphql.kickstart.factory.DataLoaderRegistryFactory;

import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private DataLoaderRegistryFactory dataLoaderRegistryFactory;

    public CustomGraphQLContextBuilder(DataLoaderRegistryFactory dataLoaderRegistryFactory) {
        this.dataLoaderRegistryFactory = dataLoaderRegistryFactory;
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
                .with(dataLoaderRegistryFactory.create())
                .build();
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .with(dataLoaderRegistryFactory.create())
                .build();
    }
}
