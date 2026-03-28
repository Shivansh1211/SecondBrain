package com.secondbrain.ai.SecondBrain.dto.chatsession;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatSessionResponse {
    private Long id;
    private Long userId;
    private Long pdfDocumentId;

    private LocalDateTime createdAt;
}
