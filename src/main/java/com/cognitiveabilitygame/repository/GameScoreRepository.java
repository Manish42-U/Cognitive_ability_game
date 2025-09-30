package com.cognitiveabilitygame.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognitiveabilitygame.entity.GameScore;
import com.cognitiveabilitygame.entity.TestSession;

import java.util.List;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
    List<GameScore> findByTestSession(TestSession testSession);
    List<GameScore> findByTestSessionOrderByCompletionTime(TestSession testSession);
}
