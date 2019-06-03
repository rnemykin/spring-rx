package com.example.reactor.notifier.notice;

import com.example.reactor.notifier.common.AbstractEntityHandler;
import org.springframework.stereotype.Component;

@Component
public class NoticeHandler extends AbstractEntityHandler<Notice, NoticeRepository> {
    public NoticeHandler() {
        super(Notice.class);
    }
}
