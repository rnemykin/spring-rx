package com.example.reactor.notifier.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface EntityRepository<T> extends ReactiveCrudRepository<T, Serializable> {
}
