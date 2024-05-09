package ru.dvfu.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.dvfu.converter.EnumCaseInsensitiveConverter;
import ru.dvfu.enumeration.Aggregator;
import ru.dvfu.enumeration.GroupBy;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
    }

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        registry.addConverter(String.class, Aggregator.class, new EnumCaseInsensitiveConverter<>(Aggregator.class));
        registry.addConverter(String.class, GroupBy.class, new EnumCaseInsensitiveConverter<>(GroupBy.class));
        registry.addConverter(String.class, Sort.Direction.class, new EnumCaseInsensitiveConverter<>(Sort.Direction.class));
    }

}