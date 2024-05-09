package ru.dvfu.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class EnumCaseInsensitiveConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumClass;

    @Override
    public T convert(String from) {
        return T.valueOf(enumClass, from.toUpperCase());
    }

}
