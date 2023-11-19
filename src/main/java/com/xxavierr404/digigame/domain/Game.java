package com.xxavierr404.digigame.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String gameName;

    @OneToMany(mappedBy = "game")
    private List<GameUpdate> updates;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    @OneToMany(mappedBy = "game")
    private List<IssueReport> issueReports;

    @OneToMany(mappedBy = "game")
    private List<UserContent> userContents;
}

