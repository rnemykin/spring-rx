package com.example.reactor.notifier.notice;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
//@Table("notice2")
public class Notice {
    public enum Type {
        CONSOLE
    }


    public Notice(@NotNull String name) {
        this.name = name;
        this.type = Type.CONSOLE;
    }

    @Id
    Long id;

    Type type;

    @NotNull
    String name;

//    @CreatedDate
//    LocalDateTime createDate;
//
//    @LastModifiedDate
//    LocalDateTime modificationDate;

}
