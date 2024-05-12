package ru.dvfu.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.exception.FailedToSortException;

import java.util.Collection;
import java.util.Map;

@UtilityClass
public class SortUtil {

    public final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

    public final String DEFAULT_SORT_BY = "id";

    public Sort.Direction getDirection(@Nullable Sort.Direction direction) {
        return direction == null ? DEFAULT_DIRECTION : direction;
    }

    public String getSortBy(@Nullable String field) {
        return field == null ? DEFAULT_SORT_BY : field;
    }

    public Sort request(@Nullable Sort.Direction direction, @Nullable String field) {
        return Sort.by(getDirection(direction), getSortBy(field));
    }

    public Sort request(SortParamsDto sortParamsDto) {
        try {
            return sortParamsDto.getSort().stream()
                    .map(sort -> sort.split("-"))
                    .map(split -> Sort.by(Sort.Direction.fromString(split[0]), split[1]))
                    .reduce(Sort::and)
                    .orElse(Sort.by(DEFAULT_DIRECTION, DEFAULT_SORT_BY));
        } catch (Exception e) {
            throw new FailedToSortException("Не удалось распарсить сортировку: " + e.getMessage());
        }
    }

    public Pageable correctSorting(PageRequest page, Map<String, String> mappings, Collection<String> by) {
        Sort.Order[] correctOrders = page.getSort().stream()
                .filter(order -> by.contains(order.getProperty().toLowerCase()))
                .map(order -> order.withProperty(mappings.get(order.getProperty())))
                .toArray(Sort.Order[]::new);
        Sort correctSort = Sort.by(correctOrders);

        return page.withSort(correctSort);
    }

}
