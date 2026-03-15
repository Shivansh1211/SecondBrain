package com.secondbrain.ai.SecondBrain.repository;
import com.secondbrain.ai.SecondBrain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
