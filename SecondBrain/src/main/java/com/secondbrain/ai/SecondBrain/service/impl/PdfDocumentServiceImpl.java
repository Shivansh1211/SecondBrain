package com.secondbrain.ai.SecondBrain.service.impl;

import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentUploadRequest;
import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import com.secondbrain.ai.SecondBrain.entity.User;
import com.secondbrain.ai.SecondBrain.exception.ResourceNotFoundException;
import com.secondbrain.ai.SecondBrain.repository.PdfDocumentRepository;
import com.secondbrain.ai.SecondBrain.repository.UserRepository;
import com.secondbrain.ai.SecondBrain.service.PdfDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfDocumentServiceImpl implements PdfDocumentService {

    private final PdfDocumentRepository pdfDocumentRepository;
    private final UserRepository userRepository;
    @Override
    public PdfDocumentResponse uploadDocument(PdfDocumentUploadRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        PdfDocument document= new PdfDocument();
        document.setFileName(request.getFileName());
        document.setFilePath("uploads/" + request.getFileName());
        document.setUser(user);

        PdfDocument saved = pdfDocumentRepository.save(document);
        return mapToResponse(saved);
    }

    @Override
    public PdfDocumentResponse findById(Long id) {
        PdfDocument pdfDocument =  pdfDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));
        return mapToResponse(pdfDocument);
    }

    @Override
    public List<PdfDocumentResponse> findAllByUserId(Long userId) {
        return pdfDocumentRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        pdfDocumentRepository.deleteById(id);
    }
    private PdfDocumentResponse mapToResponse(PdfDocument document)
    {
        PdfDocumentResponse response = new PdfDocumentResponse();
        response.setId(document.getId());
        response.setFileName(document.getFileName());
        response.setFilePath(document.getFilePath());
        response.setUserId(document.getUser().getId());
        return response;
    }
}
