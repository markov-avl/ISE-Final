package ru.dvfu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.SaleDto;
import ru.dvfu.dto.SaleExtendedDto;
import ru.dvfu.entity.Sale;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SaleMapper implements CommonMapper<Sale, SaleDto> {

    @Mapping(source = "releasedGame.id", target = "releasedGameId")
    public abstract SaleDto toDto(Sale entity);

    @Mapping(source = "releasedGame.game.name", target = "name")
    @Mapping(source = "releasedGame.year", target = "year")
    @Mapping(source = "releasedGame.platform.name", target = "platform")
    @Mapping(source = "releasedGame.game.genre.name", target = "genre")
    @Mapping(source = "releasedGame.game.publisher.name", target = "publisher")
    public abstract SaleExtendedDto toExtendedDto(Sale entity);

    public abstract List<SaleExtendedDto> toExtendedDtos(List<Sale> entities);

    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "data", source = "content")
    public abstract PageDto<SaleExtendedDto> toExtendedPageDto(Page<Sale> page);

}
