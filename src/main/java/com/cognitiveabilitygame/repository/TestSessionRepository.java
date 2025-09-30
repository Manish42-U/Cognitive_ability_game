package com.cognitiveabilitygame.repository;

// TestSessionRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognitiveabilitygame.entity.TestSession;
import com.cognitiveabilitygame.entity.User;

import java.util.List;

@Repository
public interface TestSessionRepository extends JpaRepository<TestSession, Long> {
    List<TestSession> findByUserOrderByStartTimeDesc(User user);
    List<TestSession> findByStatus(TestSession.TestStatus status);
}

