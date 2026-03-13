package com.secondbrain.ai.SecondBrain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Document extends BaseEntity{
    private String fileName;

    private String filePath;

    @ManyToOne
    private User user;

}
