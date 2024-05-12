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
            "WHERE (:#{#publishers.isEmpty()} = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:#{#platforms.isEmpty()} = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:#{#genres.isEmpty()} = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:#{#years.isEmpty()} = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:#{#regions.isEmpty()} = TRUE OR s.region IN :regions)")
    Page<Sale> findByPublishersAndPlatformsAndGenresAndYearsAndRegions(Collection<String> publishers,
                                                                       Collection<String> platforms,
                                                                       Collection<String> genres,
                                                                       Collection<Integer> years,
                                                                       Collection<Region> regions,
                                                                       Pageable pageable);

    @Query("SELECT new ru.dvfu.model.ChartData( " +
            "          s.releasedGame.year, " +
            "          s.releasedGame.game.publisher.name, " +
            "          CASE WHEN (:aggregator = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:#{#publishers.isEmpty()} = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:#{#platforms.isEmpty()} = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:#{#genres.isEmpty()} = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:#{#years.isEmpty()} = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:#{#regions.isEmpty()} = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.game.publisher.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByPublisher(
            String aggregator,
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
            "          CASE WHEN (:aggregator = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:#{#publishers.isEmpty()} = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:#{#platforms.isEmpty()} = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:#{#genres.isEmpty()} = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:#{#years.isEmpty()} = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:#{#regions.isEmpty()} = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.platform.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByPlatform(
            String aggregator,
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
            "          CASE WHEN (:aggregator = 'SUM') THEN SUM(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MIN') THEN MIN(s.numberOfSales) " +
            "               WHEN (:aggregator = 'MAX') THEN MAX(s.numberOfSales) " +
            "               ELSE CAST(AVG(s.numberOfSales) AS DOUBLE) " +
            "          END " +
            "      ) " +
            "FROM Sale s " +
            "WHERE (:#{#publishers.isEmpty()} = TRUE OR s.releasedGame.game.publisher.name IN :publishers) " +
            "  AND (:#{#platforms.isEmpty()} = TRUE OR s.releasedGame.platform.name IN :platforms) " +
            "  AND (:#{#genres.isEmpty()} = TRUE OR s.releasedGame.game.genre.name IN :genres) " +
            "  AND (:#{#years.isEmpty()} = TRUE OR s.releasedGame.year IN :years) " +
            "  AND (:#{#regions.isEmpty()} = TRUE OR s.region IN :regions) " +
            "GROUP BY s.releasedGame.year, s.releasedGame.game.genre.name ")
    Page<ChartData> findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByGenre(
            String aggregator,
            Collection<String> publishers,
            Collection<String> platforms,
            Collection<String> genres,
            Collection<Integer> years,
            Collection<Region> regions,
            Pageable pageable
    );

}
