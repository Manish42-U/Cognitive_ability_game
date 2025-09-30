package com.cognitiveabilitygame.dto;


public class GameScoreDTO {
    private Long testSessionId;
    private Long gameId;
    private Integer maxLevelReached;
    private Integer totalScore;
    private Integer timeSpentSeconds;
    private Integer correctAnswers;
    private Integer totalAttempts;
    
    // Constructors
    public GameScoreDTO() {}
    
    // Getters and Setters
    public Long getTestSessionId() { return testSessionId; }
    public void setTestSessionId(Long testSessionId) { this.testSessionId = testSessionId; }
    
    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }
    
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
}

