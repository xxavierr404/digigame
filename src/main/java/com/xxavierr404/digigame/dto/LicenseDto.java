package com.xxavierr404.digigame.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LicenseDto {
    private Long userId;
    private Long gameId;
    private LocalDateTime purchaseTime;
}
