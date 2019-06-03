package com.example.reactor.notifier.config;

import com.example.reactor.notifier.notice.NoticeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@EnableWebFlux
public class WebConfiguration implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> noticeRoutes(NoticeHandler handler) {
        return RouterFunctions
                .route(GET("/notices/{id}"), handler::findById)
                .andRoute(GET("/notices"), handler::findAll)
                .andRoute(POST("/notices"), handler::save);
    }

}
