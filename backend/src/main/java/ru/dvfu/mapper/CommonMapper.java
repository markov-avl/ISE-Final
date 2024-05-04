package ru.dvfu.mapper;

import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.dvfu.dto.PageDto;
import ru.dvfu.entity.BaseEntity;

import java.util.List;

/**
 * Mapping convention: Entity -> DTO
 *
 * @param <E> Database Entity
 * @param <D> General Entity DTO
 */
public interface CommonMapper<E extends BaseEntity, D> {

    D toDto(E entity);

    List<D> toDtos(List<E> entities);

    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "data", source = "content")
    PageDto<D> toPageDto(Page<E> page);

}
