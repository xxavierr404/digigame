package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Chat chat;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String text;

    @NotNull
    private LocalDateTime sentTime;
}
