package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.dto.ChatDto;
import org.mapstruct.Mapper;

@Mapper
public interface ChatMapper {
    ChatDto toDto(Chat chat);
    Chat fromDto(ChatDto dto);
}
