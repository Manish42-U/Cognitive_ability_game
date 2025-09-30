package com.cognitiveabilitygame.controller;

// TestController.java

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognitiveabilitygame.dto.GameScoreDTO;
import com.cognitiveabilitygame.entity.GameScore;
import com.cognitiveabilitygame.entity.TestSession;
import com.cognitiveabilitygame.service.TestService;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @Autowired
    private TestService testService;
    
    @PostMapping("/start/{userId}")
    public ResponseEntity<TestSession> startTest(@PathVariable Long userId) {
        try {
            TestSession testSession = testService.startNewTest(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(testSession);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/score")
    public ResponseEntity<GameScore> submitGameScore(@Valid @RequestBody GameScoreDTO gameScoreDTO) {
        try {
            GameScore gameScore = testService.saveGameScore(gameScoreDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(gameScore);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/complete/{testSessionId}")
    public ResponseEntity<TestSession> completeTest(@PathVariable Long testSessionId) {
        try {
            TestSession completedSession = testService.completeTest(testSessionId);
            return ResponseEntity.ok(completedSession);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TestSession>> getUserTestHistory(@PathVariable Long userId) {
        try {
            List<TestSession> history = testService.getUserTestHistory(userId);
            return ResponseEntity.ok(history);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/scores/{testSessionId}")
    public ResponseEntity<List<GameScore>> getTestSessionScores(@PathVariable Long testSessionId) {
        try {
            List<GameScore> scores = testService.getTestSessionScores(testSessionId);
            return ResponseEntity.ok(scores);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
