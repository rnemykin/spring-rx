package com.example.reactor.notifier.notice;

import com.example.reactor.notifier.notice.Notice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface NoticeRepository extends ReactiveCrudRepository<Notice, Long> {


}
