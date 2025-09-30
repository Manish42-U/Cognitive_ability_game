package com.cognitiveabilitygame.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_scores")
public class GameScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_session_id", nullable = false)
    private TestSession testSession;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    
    @Column(name = "max_level_reached")
    private Integer maxLevelReached = 1;
    
    @Column(name = "total_score")
    private Integer totalScore = 0;
    
    @Column(name = "time_spent_seconds")
    private Integer timeSpentSeconds = 0;
    
    @Column(name = "correct_answers")
    private Integer correctAnswers = 0;
    
    @Column(name = "total_attempts")
    private Integer totalAttempts = 0;
    
    @Column(name = "completion_time")
    private LocalDateTime completionTime;
    
    @PrePersist
    protected void onCreate() {
        if (completionTime == null) {
            completionTime = LocalDateTime.now();
        }
    }
    
    // Constructors
    public GameScore() {}
    
    public GameScore(TestSession testSession, Game game) {
        this.testSession = testSession;
        this.game = game;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public TestSession getTestSession() { return testSession; }
    public void setTestSession(TestSession testSession) { this.testSession = testSession; }
    
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
    
    public Integer getMaxLevelReached() { return maxLevelReached; }
    public void setMaxLevelReached(Integer maxLevelReached) { this.maxLevelReached = maxLevelReached; }
    
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    
    public Integer getTimeSpentSeconds() { return timeSpentSeconds; }
    public void setTimeSpentSeconds(Integer timeSpentSeconds) { this.timeSpentSeconds = timeSpentSeconds; }
    
    public Integer getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Integer correctAnswers) { this.correctAnswers = correctAnswers; }
    
    public Integer getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(Integer totalAttempts) { this.totalAttempts = totalAttempts; }
    
    public LocalDateTime getCompletionTime() { return completionTime; }
    public void setCompletionTime(LocalDateTime completionTime) { this.completionTime = completionTime; }
    
    // Helper method to calculate accuracy
    public double getAccuracy() {
        if (totalAttempts == 0) return 0.0;
        return (double) correctAnswers / totalAttempts * 100;
    }
}
