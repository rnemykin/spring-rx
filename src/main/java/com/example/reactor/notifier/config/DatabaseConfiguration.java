package com.example.reactor.notifier.config;

import com.example.reactor.notifier.notice.NoticeRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
public class DatabaseConfiguration {
    @Bean
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("127.0.0.1")
                        .database("notice")
                        .username("postgres")
                        .password("postgres")
                        .build());
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    public R2dbcRepositoryFactory repositoryFactory(DatabaseClient client) {
        RelationalMappingContext ctx = new RelationalMappingContext();
        ctx.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client, ctx);
    }


    @Bean
    public NoticeRepository noticeRepository(R2dbcRepositoryFactory repositoryFactory) {
        return repositoryFactory.getRepository(NoticeRepository.class);
    }
}
