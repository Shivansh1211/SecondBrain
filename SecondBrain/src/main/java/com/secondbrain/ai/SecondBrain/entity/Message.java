package com.secondbrain.ai.SecondBrain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {
    @ManyToOne
    @JoinColumn(name= "chat_session_id", nullable = false)
    private ChatSession chatSession;
    @Column(nullable = false)
    private String question;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String answer;
}
