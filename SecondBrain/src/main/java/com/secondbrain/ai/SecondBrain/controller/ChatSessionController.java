package com.secondbrain.ai.SecondBrain.controller;

import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionRequest;
import com.secondbrain.ai.SecondBrain.dto.chatsession.ChatSessionResponse;
import com.secondbrain.ai.SecondBrain.service.ChatSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/session")
public class ChatSessionController {
    private final ChatSessionService chatSessionService;

    @PostMapping("/createSession")
     public ResponseEntity<ChatSessionResponse> createChatSession(@Valid @RequestBody ChatSessionRequest request)
    {
        ChatSessionResponse response = chatSessionService.createChatSession(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChatSessionResponse> findById(@PathVariable Long id)
    {
        ChatSessionResponse response = chatSessionService.findById(id);
         return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}/document/{pdfDocumentId}")
    public ResponseEntity<List<ChatSessionResponse>> findAllByUserIdAndPdfDocumentId(@PathVariable Long userId, @PathVariable Long pdfDocumentId)
    {
        List<ChatSessionResponse> response = chatSessionService.findAllByUserIdAndPdfDocumentId(userId,pdfDocumentId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatSessionResponse>> findAllByUserId(@PathVariable Long userId)
    {
        List<ChatSessionResponse> response = chatSessionService.findAllByUserId(userId);
        return  ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
       chatSessionService.delete(id);
       return ResponseEntity.noContent().build();
    }


}
