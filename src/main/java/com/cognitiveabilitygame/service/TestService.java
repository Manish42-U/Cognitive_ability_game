package com.cognitiveabilitygame.service;

// TestService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognitiveabilitygame.dto.GameScoreDTO;
import com.cognitiveabilitygame.entity.Game;
import com.cognitiveabilitygame.entity.GameScore;
import com.cognitiveabilitygame.entity.TestSession;
import com.cognitiveabilitygame.entity.User;
import com.cognitiveabilitygame.repository.GameRepository;
import com.cognitiveabilitygame.repository.GameScoreRepository;
import com.cognitiveabilitygame.repository.TestSessionRepository;
import com.cognitiveabilitygame.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TestService {
    
    @Autowired
    private TestSessionRepository testSessionRepository;
    
    @Autowired
    private GameScoreRepository gameScoreRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    public TestSession startNewTest(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        TestSession testSession = new TestSession(user);
        testSession.setStatus(TestSession.TestStatus.STARTED);
        
        return testSessionRepository.save(testSession);
    }
    
    public GameScore saveGameScore(GameScoreDTO gameScoreDTO) {
        TestSession testSession = testSessionRepository.findById(gameScoreDTO.getTestSessionId())
                .orElseThrow(() -> new RuntimeException("Test session not found"));
        
        Game game = gameRepository.findById(gameScoreDTO.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));
        
        GameScore gameScore = new GameScore(testSession, game);
        gameScore.setMaxLevelReached(gameScoreDTO.getMaxLevelReached());
        gameScore.setTotalScore(gameScoreDTO.getTotalScore());
        gameScore.setTimeSpentSeconds(gameScoreDTO.getTimeSpentSeconds());
        gameScore.setCorrectAnswers(gameScoreDTO.getCorrectAnswers());
        gameScore.setTotalAttempts(gameScoreDTO.getTotalAttempts());
        
        GameScore savedScore = gameScoreRepository.save(gameScore);
        
        // Update test session status and total score
        testSession.setStatus(TestSession.TestStatus.IN_PROGRESS);
        testSession.setTotalScore(testSession.getTotalScore() + savedScore.getTotalScore());
        testSessionRepository.save(testSession);
        
        return savedScore;
    }
    
    public TestSession completeTest(Long testSessionId) {
        TestSession testSession = testSessionRepository.findById(testSessionId)
                .orElseThrow(() -> new RuntimeException("Test session not found"));
        
        testSession.setStatus(TestSession.TestStatus.COMPLETED);
        testSession.setEndTime(LocalDateTime.now());
        
        return testSessionRepository.save(testSession);
    }
    
    public List<TestSession> getUserTestHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return testSessionRepository.findByUserOrderByStartTimeDesc(user);
    }
    
    public List<GameScore> getTestSessionScores(Long testSessionId) {
        TestSession testSession = testSessionRepository.findById(testSessionId)
                .orElseThrow(() -> new RuntimeException("Test session not found"));
        
        return gameScoreRepository.findByTestSessionOrderByCompletionTime(testSession);
    }
}
