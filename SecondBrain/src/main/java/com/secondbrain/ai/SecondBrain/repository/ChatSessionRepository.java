package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession,Long> {
}
