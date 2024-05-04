package ru.dvfu.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import ru.dvfu.dto.params.SortParamsDto;

import java.util.Optional;

@UtilityClass
public class SortUtil {

    public final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

    public final String DEFAULT_SORT_BY = "id";

    public Sort.Direction getDirection(@Nullable Boolean ascending) {
        return Optional.ofNullable(ascending)
                .map(a -> a ? Sort.Direction.ASC : Sort.Direction.DESC)
                .orElse(DEFAULT_DIRECTION);
    }

    public Sort.Direction getDirection(@Nullable Sort.Direction direction) {
        return direction == null ? DEFAULT_DIRECTION : direction;
    }

    public String getSortBy(@Nullable String sortBy) {
        return sortBy == null ? DEFAULT_SORT_BY : sortBy;
    }

    public Sort request(@Nullable Boolean ascending, @Nullable String sortBy) {
        return Sort.by(getDirection(ascending), getSortBy(sortBy));
    }

    public Sort request(@Nullable Sort.Direction direction, @Nullable String sortBy) {
        return Sort.by(getDirection(direction), getSortBy(sortBy));
    }

    public Sort request(SortParamsDto sortParamsDto) {
        return request(sortParamsDto.getSortDirection(), sortParamsDto.getSortBy());
    }

}
