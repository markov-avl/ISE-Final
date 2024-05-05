package ru.dvfu.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.dvfu.enumeration.Region;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Filter {

    private final List<String> publishers;

    private final List<String> platforms;

    private final List<String> genres;

    private final List<Integer> years;

    private final List<Region> regions;

    public static Filter of(List<String> publishers,
                            List<String> platforms,
                            List<String> genres,
                            List<String> years,
                            List<String> regions) {
        return new Filter(
                Collections.unmodifiableList(publishers),
                Collections.unmodifiableList(platforms),
                Collections.unmodifiableList(genres),
                years.stream()
                        .map(Integer::parseInt)
                        .toList(),
                regions.stream()
                        .map(value -> value.toUpperCase(Locale.US))
                        .map(Region::valueOf)
                        .toList()
        );
    }

}
