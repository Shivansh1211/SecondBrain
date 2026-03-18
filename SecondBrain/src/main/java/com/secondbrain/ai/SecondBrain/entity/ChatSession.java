package com.secondbrain.ai.SecondBrain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class ChatSession extends BaseEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Document document;

}
