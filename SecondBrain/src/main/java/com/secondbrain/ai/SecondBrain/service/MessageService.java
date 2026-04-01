package com.secondbrain.ai.SecondBrain.service;

import com.secondbrain.ai.SecondBrain.dto.message.MessageRequest;
import com.secondbrain.ai.SecondBrain.dto.message.MessageResponse;

import java.util.List;

public interface MessageService {

     MessageResponse askQuestion(MessageRequest request);

     MessageResponse findById(Long id);

     List<MessageResponse> findAllByChatSessionId(Long chatSessionId);

     MessageResponse delete(Long id);


}
