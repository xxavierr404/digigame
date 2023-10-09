package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @OneToOne
    @NotNull
    private Game game;

    @NotNull
    private LocalDateTime purchaseTime;
}
