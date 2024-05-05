package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.ReleasedGame;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.repository.ReleasedGameRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReleasedGameService {

    private final ReleasedGameRepository releasedGameRepository;

    public ReleasedGame getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<ReleasedGame> findById(Long id) {
        return releasedGameRepository.findById(id);
    }

    @Cacheable
    public Page<Integer> getYears(Pageable page) {
        try {
            return releasedGameRepository.findDistinctYearsNotNull(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

}
