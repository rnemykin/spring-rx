package com.example.reactor.notifier.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public abstract class AbstractEntityHandler<T, R extends ReactiveCrudRepository<T, Serializable>> implements EntityHandler {
    private static final String ID_PARAM_NAME = "id";
    private Class<T> entityClass;

    @Autowired
    protected R repository;

    public AbstractEntityHandler(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public Mono<ServerResponse> findById(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findById(request.pathVariable(ID_PARAM_NAME)), entityClass);
    }

    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.findAll(), entityClass);
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(entityClass)
                .flatMap(repository::save)
                .flatMap(saved -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(saved)));
    }
}
