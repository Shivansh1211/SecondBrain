package com.secondbrain.ai.SecondBrain.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentUploadRequest;
import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import com.secondbrain.ai.SecondBrain.entity.User;
import com.secondbrain.ai.SecondBrain.exception.ResourceNotFoundException;
import com.secondbrain.ai.SecondBrain.repository.PdfDocumentRepository;
import com.secondbrain.ai.SecondBrain.repository.UserRepository;
import com.secondbrain.ai.SecondBrain.service.AiService;
import com.secondbrain.ai.SecondBrain.service.PdfDocumentService;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfDocumentServiceImpl implements PdfDocumentService {

    private final PdfDocumentRepository pdfDocumentRepository;
    private final UserRepository userRepository;
    private final Cloudinary cloudinary;
    private final AiService aiService;

    @Override
    public PdfDocumentResponse uploadDocument(MultipartFile file, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        // Step 1: Extract text from PDF
        String extractedText;
        try {
            PDDocument pdf = Loader.loadPDF(file.getBytes());
            extractedText = new PDFTextStripper().getText(pdf);
            pdf.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read PDF: " + e.getMessage());
        }

        // Step 2: Upload to Cloudinary
        String fileUrl;
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "raw")
            );
            fileUrl = uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary upload failed: " + e.getMessage());
        }

        // Step 3: Summarize with AI
        String summary = aiService.summarizeDocument(extractedText);

        // Step 4: Save to DB
        PdfDocument document = new PdfDocument();
        document.setFileName(file.getOriginalFilename());
        document.setFilePath(fileUrl);
        document.setSummary(summary);
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
        response.setSummary(document.getSummary());
        return response;
    }
}
