package com.cognitiveabilitygame.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "test_sessions")
public class TestSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "total_score")
    private Integer totalScore = 0;
    
    @Enumerated(EnumType.STRING)
    private TestStatus status = TestStatus.STARTED;
    
    @OneToMany(mappedBy = "testSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameScore> gameScores;
    
    @PrePersist
    protected void onCreate() {
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
    }
    
    public enum TestStatus {
        STARTED, IN_PROGRESS, COMPLETED, ABANDONED
    }
    
    // Constructors
    public TestSession() {}
    
    public TestSession(User user) {
        this.user = user;
        this.startTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    
    public TestStatus getStatus() { return status; }
    public void setStatus(TestStatus status) { this.status = status; }
    
    public List<GameScore> getGameScores() { return gameScores; }
    public void setGameScores(List<GameScore> gameScores) { this.gameScores = gameScores; }
}

