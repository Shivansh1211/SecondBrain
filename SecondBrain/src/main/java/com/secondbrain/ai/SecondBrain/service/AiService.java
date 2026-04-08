package com.secondbrain.ai.SecondBrain.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getAnswer(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    public String summarizeDocument(String extractedText) {
        String prompt = """
                You are a helpful assistant. Summarize the following document clearly and concisely.
                Focus on key points, main ideas, and important details.
                
                Document:
                """ + extractedText;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}