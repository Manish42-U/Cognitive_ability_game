package com.cognitiveabilitygame.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognitiveabilitygame.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByActiveTrue();
    
    @Query(value = "SELECT * FROM games WHERE active = true ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<Game> findRandomActiveGames();
    
    Game findByType(String type);
}

