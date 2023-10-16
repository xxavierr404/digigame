package com.xxavierr404.digigame.dto;

import lombok.Data;

@Data
public class IssueReportDto {
    private Long gameId;
    private Long postId;
    private Long userId;
    private String reportText;
}
