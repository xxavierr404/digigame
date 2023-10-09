package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class GameUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long updateId;

    @ManyToOne
    @NotNull
    private Game game;

    @NotNull
    private String updateUrl;
}
