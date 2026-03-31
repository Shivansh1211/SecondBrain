package com.secondbrain.ai.SecondBrain.dto.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponse {


    private Long chatSessionId;
    private Long id;

    private String question;
    private String answer;
    private LocalDateTime createdAt;
}
