package com.secondbrain.ai.SecondBrain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Message extends BaseEntity {
    @ManyToOne
    private ChatSession chatSession;

    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;
}
