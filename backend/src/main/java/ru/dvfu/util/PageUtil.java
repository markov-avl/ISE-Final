package ru.dvfu.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import ru.dvfu.dto.params.PageParamsDto;

@UtilityClass
public class PageUtil {

    public final int MIN_PAGE = 0;

    public final int MIN_SIZE = 1;

    public final int MAX_SIZE = 1000;

    public final String PAGE_ERROR = "Номер страницы не может быть меньше " + MIN_PAGE;

    public final String SIZE_ERROR = "На странице не может быть меньше " + MIN_SIZE + " элемента";

    public Integer getPage(@Nullable Integer page) {
        return page == null ? MIN_PAGE : Math.max(page, MIN_PAGE);
    }

    public Integer getSize(@Nullable Integer size) {
        return size == null ? MAX_SIZE : Math.max(Math.min(size, MAX_SIZE), MIN_SIZE);
    }

    public PageRequest request(@Nullable Integer page, @Nullable Integer size) {
        return PageRequest.of(getPage(page), getSize(size));
    }

    public PageRequest request(PageParamsDto pageParamsDto) {
        return request(pageParamsDto.getPage() - 1, pageParamsDto.getSize());
    }

}
