package com.example.reactor.notifier;

import com.example.reactor.notifier.notice.Notice;
import com.example.reactor.notifier.notice.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class NotifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifierApplication.class, args);
    }

    @Autowired
    private NoticeRepository noticeRepository;

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
