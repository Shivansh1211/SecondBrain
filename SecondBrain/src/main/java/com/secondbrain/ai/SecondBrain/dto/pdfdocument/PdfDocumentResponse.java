package com.secondbrain.ai.SecondBrain.dto.pdfdocument;

import lombok.Data;

@Data
public class PdfDocumentResponse {

       private Long id;
       private String fileName;
       private String filePath;
       private Long userId;
}
