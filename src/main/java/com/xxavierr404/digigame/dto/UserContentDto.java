package com.xxavierr404.digigame.dto;

import com.xxavierr404.digigame.domain.ContentType;
import lombok.Data;

@Data
public class UserContentDto {
    private Long userId;
    private ContentType contentType;
    private String contentUrl;
}
