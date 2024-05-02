package ru.dvfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
