package com.secondbrain.ai.SecondBrain.controller;


import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentUploadRequest;
import com.secondbrain.ai.SecondBrain.entity.User;
import com.secondbrain.ai.SecondBrain.repository.UserRepository;
import com.secondbrain.ai.SecondBrain.service.PdfDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class PdfDocumentController {
     private final  PdfDocumentService pdfDocumentService;
     @PostMapping(value="/uploadDocument/{userId}",consumes ="multipart/form-data")
    public ResponseEntity<PdfDocumentResponse> uploadDocument(@RequestParam("file") MultipartFile file, @PathVariable Long userId)
    {
        PdfDocumentResponse response = pdfDocumentService.uploadDocument(file,userId);
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
