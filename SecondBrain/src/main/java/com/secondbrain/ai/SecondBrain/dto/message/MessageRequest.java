package com.secondbrain.ai.SecondBrain.dto.message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {
    @NotNull(message = "ChatSessionId Required")
    private Long chatSessionId;

    @NotBlank(message="Ask a question?")
    @Size(min=1,max=4000,message = "Question size is not acceptable")
    private String question;

}
