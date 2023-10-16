package com.xxavierr404.digigame.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long gameId;
    private Long userId;
    private String reviewText;
}
