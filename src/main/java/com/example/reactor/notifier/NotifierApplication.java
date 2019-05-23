package com.example.reactor.notifier;

import com.example.reactor.notifier.notice.Notice;
import com.example.reactor.notifier.notice.NoticeRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class NotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifierApplication.class, args);
    }

    @Autowired
    NoticeRepository noticeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        noticeRepository.deleteAll()
                .thenMany(
                        Flux.just("hi", "whats up", "bb")
                                .map(Notice::new)
                                .flatMap(noticeRepository::save)
                ).thenMany(noticeRepository.findAll())
                .subscribe(s -> log.info(s.toString()), e -> log.error("", e), () -> log.info("finished"));
    }



}
