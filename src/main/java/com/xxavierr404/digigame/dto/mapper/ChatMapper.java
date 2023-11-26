package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.dto.ChatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChatMapper {
    ChatDto toDto(Chat chat);
    @Mapping(source = "chatName", target = "chatName")
    Chat fromDto(ChatDto dto);
}
