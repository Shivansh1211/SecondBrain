package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession,Long> {
}
