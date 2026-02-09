package com.malcolm.mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    @Bean
    @Qualifier("openAiChatClient")
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("You are a helpful AI assistant.")
                .build();
    }

    // @Bean
    // @Qualifier("ollamaChatClient")
    // public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
    // return ChatClient.builder(ollamaChatModel)
    // .defaultSystem("You are a helpful AI assistant.")
    // .build();
    // }

    // @Bean
    // @Qualifier("geminiChatClient")
    // public ChatClient geminiChatClient(GoogleGenAiChatModel geminiChatModel) {
    // return ChatClient.builder(geminiChatModel)
    // .defaultSystem("You are a helpful AI assistant.")
    // .build();
    // }
}
