package com.secondbrain.ai.SecondBrain.controller;


import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentUploadRequest;
import com.secondbrain.ai.SecondBrain.service.PdfDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class PdfDocumentController {
     private final  PdfDocumentService pdfDocumentService;
     @PostMapping("/uploadDocument/{id}")
    public ResponseEntity<PdfDocumentResponse> uploadDocument(@Valid @RequestBody PdfDocumentUploadRequest request,@PathVariable Long id)
    {
        PdfDocumentResponse response = pdfDocumentService.uploadDocument(request,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PdfDocumentResponse> findById(@PathVariable Long id)
    {
        PdfDocumentResponse response = pdfDocumentService.findById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PdfDocumentResponse>> findAllByUserId(@PathVariable Long userId)
    {
         List<PdfDocumentResponse> response = pdfDocumentService.findAllByUserId(userId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        pdfDocumentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
