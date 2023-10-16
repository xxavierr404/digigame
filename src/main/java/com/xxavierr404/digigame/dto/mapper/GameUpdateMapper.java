package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.GameUpdate;
import com.xxavierr404.digigame.dto.GameUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameUpdateMapper {
    @Mapping(source = "game.id", target = "gameId")
    GameUpdateDto toDto(GameUpdate gameUpdate);
}
