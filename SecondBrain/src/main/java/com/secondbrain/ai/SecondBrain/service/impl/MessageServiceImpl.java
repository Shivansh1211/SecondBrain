package com.secondbrain.ai.SecondBrain.service.impl;

import com.secondbrain.ai.SecondBrain.dto.message.MessageRequest;
import com.secondbrain.ai.SecondBrain.dto.message.MessageResponse;
import com.secondbrain.ai.SecondBrain.entity.ChatSession;
import com.secondbrain.ai.SecondBrain.entity.Message;
import com.secondbrain.ai.SecondBrain.exception.ResourceNotFoundException;
import com.secondbrain.ai.SecondBrain.repository.ChatSessionRepository;
import com.secondbrain.ai.SecondBrain.repository.MessageRepository;
import com.secondbrain.ai.SecondBrain.service.AiService;
import com.secondbrain.ai.SecondBrain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AiService aiService;

    @Override
    public MessageResponse askQuestion(MessageRequest request) {
       ChatSession chatSession= chatSessionRepository.findById(request.getChatSessionId())
               .orElseThrow(()-> new RuntimeException("Session not found"));
        Message message= new Message();
        message.setChatSession(chatSession);
        message.setQuestion(request.getQuestion());
        String answer = aiService.getAnswer(request.getQuestion());
        message.setAnswer(answer);

        Message saved=messageRepository.save(message);
        return mapToResponse(saved);

    }

    @Override
    public MessageResponse findById(Long id) {
        Message message= messageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Message not found"));
             return mapToResponse(message);

    }

    @Override
    public List<MessageResponse> findAllByChatSessionId(Long chatSessionId) {
         return messageRepository.findAllByChatSessionId(chatSessionId)
                 .stream()
                 .map(this::mapToResponse)
                 .toList();

    }

    @Override
    public MessageResponse delete(Long id) {
        messageRepository.deleteById(id);

        return null;
    }
    private MessageResponse mapToResponse(Message message)
    {
        MessageResponse  response= new MessageResponse();
        response.setId(message.getId());
        response.setChatSessionId(message.getChatSession().getId());
        response.setQuestion(message.getQuestion());
        response.setAnswer(message.getAnswer());
        response.setCreatedAt(message.getCreatedAt());

        return response;
    }
}
