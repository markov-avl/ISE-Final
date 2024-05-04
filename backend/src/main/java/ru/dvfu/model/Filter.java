package ru.dvfu.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Filter {

    private final List<String> publishers;

    private final List<String> platforms;

    private final List<String> genres;

    private final List<Integer> years;

    public static Filter of(List<String> publishers, List<String> platforms, List<String> genres, List<String> years) {
        return new Filter(
                Collections.unmodifiableList(publishers),
                Collections.unmodifiableList(platforms),
                Collections.unmodifiableList(genres),
                years.stream()
                        .map(Integer::parseInt)
                        .toList()
        );
    }

}
