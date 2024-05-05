package ru.dvfu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.ReleasedGameDto;
import ru.dvfu.dto.SaleExtendedDto;
import ru.dvfu.entity.ReleasedGame;
import ru.dvfu.entity.Sale;

@Mapper(componentModel = "spring")
public abstract class ReleasedGameMapper implements CommonMapper<ReleasedGame, ReleasedGameDto> {

    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "platform.id", target = "platformId")
    public abstract ReleasedGameDto toDto(ReleasedGame entity);

    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "data", source = "content")
    public abstract PageDto<Integer> toYearsPageDto(Page<Integer> page);

}
