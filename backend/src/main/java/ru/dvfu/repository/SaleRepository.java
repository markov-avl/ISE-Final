package ru.dvfu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Sale;
import ru.dvfu.enumeration.Region;

import java.util.Collection;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s " +
            "FROM Sale s " +
            "WHERE (:skipPublishersFilter = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:skipPlatformsFilter = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:skipGenresFilter = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:skipYearsFilter = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:skipRegionsFilter = TRUE OR s.region IN :regions)")
    Page<Sale> findByPublishersAndPlatformsAndGenresAndYears(Boolean skipPublishersFilter,
                                                             Boolean skipPlatformsFilter,
                                                             Boolean skipGenresFilter,
                                                             Boolean skipYearsFilter,
                                                             Boolean skipRegionsFilter,
                                                             Collection<String> publishers,
                                                             Collection<String> platforms,
                                                             Collection<String> genres,
                                                             Collection<Integer> years,
                                                             Collection<Region> regions,
                                                             Pageable pageable);

}
