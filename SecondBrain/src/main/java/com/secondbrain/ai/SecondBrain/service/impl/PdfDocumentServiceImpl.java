package com.secondbrain.ai.SecondBrain.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.secondbrain.ai.SecondBrain.dto.pdfdocument.PdfDocumentResponse;
import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import com.secondbrain.ai.SecondBrain.entity.User;
import com.secondbrain.ai.SecondBrain.exception.InvalidFileException;
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
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("Please select a file to upload.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
            throw new InvalidFileException("Only PDF files are supported. Please upload a valid PDF document.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        String extractedText;
        try (PDDocument pdf = Loader.loadPDF(file.getBytes())) {
            extractedText = new PDFTextStripper().getText(pdf);
        } catch (IOException e) {
            throw new InvalidFileException("Failed to read PDF document: " + e.getMessage());
        }

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

        String summary = aiService.summarizeDocument(extractedText);

        PdfDocument document = new PdfDocument();
        document.setFileName(originalFilename);
        document.setFilePath(fileUrl);
        document.setExtractedText(extractedText);
        document.setSummary(summary);
        document.setUser(user);

        PdfDocument saved = pdfDocumentRepository.save(document);
        return mapToResponse(saved);
    }

    @Override
    public PdfDocumentResponse findById(Long id) {
        PdfDocument pdfDocument = pdfDocumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
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
        if (!pdfDocumentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with id: " + id);
        }
        pdfDocumentRepository.deleteById(id);
    }

    private PdfDocumentResponse mapToResponse(PdfDocument document) {
        PdfDocumentResponse response = new PdfDocumentResponse();
        response.setId(document.getId());
        response.setFileName(document.getFileName());
        response.setFilePath(document.getFilePath());
        response.setUserId(document.getUser().getId());
        response.setSummary(document.getSummary());
        return response;
    }
}
