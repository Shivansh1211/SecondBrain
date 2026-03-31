package com.secondbrain.ai.SecondBrain.dto.chatsession;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatSessionRequest {
  @NotNull(message="user_ID cannot be null")
    private Long userId;

  @NotNull(message="document_ID cannot be null")
    private Long pdfDocumentId;
}
