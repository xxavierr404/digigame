package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @OneToOne(mappedBy = "chatId")
    @NotNull
    private Chat chat;

    @OneToOne(mappedBy = "userId")
    @NotNull
    private User user;

    @NotNull
    private String text;
}
