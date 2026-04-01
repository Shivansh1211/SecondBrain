package com.secondbrain.ai.SecondBrain.controller;

import com.secondbrain.ai.SecondBrain.dto.message.MessageRequest;
import com.secondbrain.ai.SecondBrain.dto.message.MessageResponse;
import com.secondbrain.ai.SecondBrain.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/askQuestion")
    public ResponseEntity<MessageResponse> askQuestion(@Valid @RequestBody MessageRequest request)
    {
        MessageResponse response = messageService.askQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id)
    {
        MessageResponse response = messageService.findById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/session/{chatSessionId}")
    public ResponseEntity<List<MessageResponse>> findAllByChatSessionId(@PathVariable Long chatSessionId)
    {
        List<MessageResponse> response= messageService.findAllByChatSessionId(chatSessionId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
         messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
