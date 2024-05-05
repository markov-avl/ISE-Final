package ru.dvfu.util;

import lombok.experimental.UtilityClass;
import ru.dvfu.dto.params.MultiFilterParamsDto;
import ru.dvfu.model.Filter;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class FilterUtil {

    public final List<String> DEFAULT_VALUES = Collections.emptyList();

    public Filter request(MultiFilterParamsDto multiFilterParamsDto) {
        return Filter.of(
                multiFilterParamsDto.getPublishers(),
                multiFilterParamsDto.getPlatforms(),
                multiFilterParamsDto.getGenres(),
                multiFilterParamsDto.getYears(),
                multiFilterParamsDto.getRegions()
        );
    }

}
