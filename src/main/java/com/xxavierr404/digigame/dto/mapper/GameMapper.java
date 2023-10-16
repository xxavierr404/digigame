package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.Game;
import com.xxavierr404.digigame.dto.GameDto;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {
    GameDto toDto(Game game);
    Game fromDto(GameDto gameDto);
}
