package com.example.reactor.notifier.common;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface EntityHandler {
    Mono<ServerResponse> findById(ServerRequest request);
    Mono<ServerResponse> save(ServerRequest request);
    Mono<ServerResponse> findAll(ServerRequest request);
}
