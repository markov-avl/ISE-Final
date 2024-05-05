package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Genre;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.repository.GenreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Cacheable
    public Page<Genre> getAll(Pageable page) {
        try {
            return genreRepository.findAll(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

}
