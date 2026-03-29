package com.secondbrain.ai.SecondBrain.service.impl;

import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionRequest;
import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionResponse;
import com.secondbrain.ai.SecondBrain.entity.ChatSession;
import com.secondbrain.ai.SecondBrain.entity.PdfDocument;
import com.secondbrain.ai.SecondBrain.entity.User;
import com.secondbrain.ai.SecondBrain.repository.ChatSessionRepository;
import com.secondbrain.ai.SecondBrain.repository.PdfDocumentRepository;
import com.secondbrain.ai.SecondBrain.repository.UserRepository;
import com.secondbrain.ai.SecondBrain.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatSessionServiceImpl implements ChatSessionService {

    private final PdfDocumentRepository pdfDocumentRepository;
    private final UserRepository userRepository;
    private final ChatSessionRepository chatSessionRepository;

    @Override
    public ChatSessionResponse createChatSession(ChatSessionRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not found"));
        PdfDocument pdfDocument = pdfDocumentRepository.findById(request.getPdfDocumentId())
                .orElseThrow(() -> new RuntimeException("PdfDocument not found"));
        ChatSession chatSession = new ChatSession();
        chatSession.setUser(user);
        chatSession.setPdfDocument(pdfDocument);
        ChatSession saved = chatSessionRepository.save(chatSession);
        return mapToResponse(saved);
    }

    @Override
    public ChatSessionResponse findById(Long id) {
        ChatSession chatSession = chatSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        return mapToResponse(chatSession);
    }

    @Override
    public List<ChatSessionResponse> findAllByUserIdAndPdfDocumentId(Long userId, Long pdfDocumentId) {
        return chatSessionRepository.findAllByUserIdAndPdfDocumentId(userId, pdfDocumentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ChatSessionResponse> findAllByUserId(Long userId) {
        return chatSessionRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        chatSessionRepository.deleteById(id);
    }

    private ChatSessionResponse mapToResponse(ChatSession session) {
        ChatSessionResponse response = new ChatSessionResponse();
        response.setId(session.getId());
        response.setUserId(session.getUser().getId());
        response.setPdfDocumentId(session.getPdfDocument().getId());
        response.setCreatedAt(session.getCreatedAt());
        return response;
    }