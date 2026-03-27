package com.secondbrain.ai.SecondBrain.service;

import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentUploadRequest;

import java.util.List;

public interface PdfDocumentService {
    PdfDocumentResponse uploadDocument(PdfDocumentUploadRequest request, Long userId);

    PdfDocumentResponse findById(Long id);

    List<PdfDocumentResponse> findAllByUserId(Long userId);

     void delete(Long id);
}
