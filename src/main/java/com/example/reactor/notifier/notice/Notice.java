package com.example.reactor.notifier.notice;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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
