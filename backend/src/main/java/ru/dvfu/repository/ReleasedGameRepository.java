package ru.dvfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.ReleasedGame;

@Repository
public interface ReleasedGameRepository extends JpaRepository<ReleasedGame, Long> {
}
