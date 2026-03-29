package com.secondbrain.ai.SecondBrain.repository;

import com.secondbrain.ai.SecondBrain.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession,Long> {
    List<ChatSession> findAllByUserId(Long userId);
    List<ChatSession> findAllByUserIdAndPdfDocumentId(Long userId, Long PdfDocumentId);
}
