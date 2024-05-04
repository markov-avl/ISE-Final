package ru.dvfu.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import ru.dvfu.dto.params.MultiSortParamsDto;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.exception.FailedToSortException;

import java.util.Optional;

@UtilityClass
public class SortUtil {

    public final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

    public final String DEFAULT_SORT_BY = "id";

    public Sort.Direction getDirection(@Nullable Sort.Direction direction) {
        return direction == null ? DEFAULT_DIRECTION : direction;
    }

    public String getSortBy(@Nullable String sortBy) {
        return sortBy == null ? DEFAULT_SORT_BY : sortBy;
    }

    public Sort request(@Nullable Sort.Direction direction, @Nullable String sortBy) {
        return Sort.by(getDirection(direction), getSortBy(sortBy));
    }

    public Sort request(SortParamsDto sortParamsDto) {
        return request(sortParamsDto.getSortDirection(), sortParamsDto.getSortBy());
    }

    public Sort request(MultiSortParamsDto multiSortParamsDto) {
        try {
            return multiSortParamsDto.getSort().stream()
                    .map(sort -> sort.split("-"))
                    .map(split -> Sort.by(Sort.Direction.fromString(split[0]), split[1]))
                    .reduce(Sort::and)
                    .orElse(Sort.by(DEFAULT_DIRECTION, DEFAULT_SORT_BY));
        } catch (Exception e) {
            throw new FailedToSortException("Не удалось распарсить сортировку: " + e.getMessage());
        }
    }

}
