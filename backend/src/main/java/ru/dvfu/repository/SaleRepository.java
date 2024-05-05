package ru.dvfu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Sale;
import ru.dvfu.enumeration.Region;
import ru.dvfu.model.ChartData;

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

    @Query("SELECT new ru.dvfu.model.ChartData( " +
            "          s.releasedGame.year, " +
            "          s.releasedGame.game.publisher.name, " +
            "          CASE WHEN (:aggregator1 = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator2 = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator3 = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:skipPublishersFilter = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:skipPlatformsFilter = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:skipGenresFilter = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:skipYearsFilter = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:skipRegionsFilter = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.game.publisher.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsGroupByPublisher(
            String aggregator1,
            String aggregator2,
            String aggregator3,
            Boolean skipPublishersFilter,
            Boolean skipPlatformsFilter,
            Boolean skipGenresFilter,
            Boolean skipYearsFilter,
            Boolean skipRegionsFilter,
            Collection<String> publishers,
            Collection<String> platforms,
            Collection<String> genres,
            Collection<Integer> years,
            Collection<Region> regions,
            Pageable pageable
    );


    @Query("SELECT new ru.dvfu.model.ChartData( " +
            "          s.releasedGame.year, " +
            "          s.releasedGame.platform.name, " +
            "          CASE WHEN (:aggregator1 = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator2 = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator3 = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:skipPublishersFilter = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:skipPlatformsFilter = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:skipGenresFilter = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:skipYearsFilter = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:skipRegionsFilter = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.platform.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsGroupByPlatform(
            String aggregator1,
            String aggregator2,
            String aggregator3,
            Boolean skipPublishersFilter,
            Boolean skipPlatformsFilter,
            Boolean skipGenresFilter,
            Boolean skipYearsFilter,
            Boolean skipRegionsFilter,
            Collection<String> publishers,
            Collection<String> platforms,
            Collection<String> genres,
            Collection<Integer> years,
            Collection<Region> regions,
            Pageable pageable
    );

    @Query("SELECT new ru.dvfu.model.ChartData( " +
            "          s.releasedGame.year, " +
            "          s.releasedGame.game.genre.name, " +
            "          CASE WHEN (:aggregator1 = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator2 = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator3 = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:skipPublishersFilter = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:skipPlatformsFilter = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:skipGenresFilter = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:skipYearsFilter = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:skipRegionsFilter = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.game.genre.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsGroupByGenre(
            String aggregator1,
            String aggregator2,
            String aggregator3,
            Boolean skipPublishersFilter,
            Boolean skipPlatformsFilter,
            Boolean skipGenresFilter,
            Boolean skipYearsFilter,
            Boolean skipRegionsFilter,
            Collection<String> publishers,
            Collection<String> platforms,
            Collection<String> genres,
            Collection<Integer> years,
            Collection<Region> regions,
            Pageable pageable
    );

}
