package com.secondbrain.ai.SecondBrain.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getAnswer(String documentContext, String question) {
        String prompt = """
                You are SecondBrain, an intelligent assistant. Answer the user's question based strictly on the provided document context below.
                If the document does not contain relevant information to answer the question, state clearly that the document does not mention it.
                
                Document Context:
                %s
                
                User Question:
                %s
                """.formatted(
                documentContext != null ? documentContext : "No document text available.",
                question
        );

        return chatClient.prompt()
                .user(prompt)
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