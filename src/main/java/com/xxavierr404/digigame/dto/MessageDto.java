package com.xxavierr404.digigame.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long chatId;
    private Long userId;
    private String text;
}
