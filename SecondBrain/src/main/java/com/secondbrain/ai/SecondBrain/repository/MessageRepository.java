package com.secondbrain.ai.SecondBrain.repository;
import com.secondbrain.ai.SecondBrain.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByChatSessionId(Long chatSessionId);
}
