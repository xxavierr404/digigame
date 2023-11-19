package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class IssueReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Game game;

    @ManyToOne
    private UserContent post;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String reportText;
}
