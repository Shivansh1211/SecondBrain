package com.secondbrain.ai.SecondBrain.dto.pdfdocument;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PdfDocumentUploadRequest {
    @NotBlank(message = "File name cannot be empty")
    private String fileName;


}
