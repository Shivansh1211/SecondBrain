package com.secondbrain.ai.SecondBrain.service;

import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionRequest;
import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionResponse;

import java.util.List;


public interface ChatSessionService {

     ChatSessionResponse createChatSession(ChatSessionRequest request);

     ChatSessionResponse findById(Long id);

     List<ChatSessionResponse> findAllByUserIdAndPdfDocumentId(Long pdfDocumentId, Long userId);

     List<ChatSessionResponse> findAllByUserId(Long userId);

     void delete(Long id);

}
