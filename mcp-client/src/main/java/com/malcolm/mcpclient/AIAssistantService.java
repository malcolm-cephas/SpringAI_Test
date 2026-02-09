package com.malcolm.mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIAssistantService {
    private final ChatClient openAiChatClient;
    // private final ChatClient ollamaChatClient;
    // private final ChatClient geminiChatClient;
    private final List<ToolCallback> mcpToolCallbacks;

    public AIAssistantService(
            @Qualifier("openAiChatClient") ChatClient openAiChatClient,
            // @Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
            // @Qualifier("geminiChatClient") ChatClient geminiChatClient,
            List<ToolCallback> mcpToolCallbacks) {
        this.openAiChatClient = openAiChatClient;
        // this.ollamaChatClient = ollamaChatClient;
        // this.geminiChatClient = geminiChatClient;
        this.mcpToolCallbacks = mcpToolCallbacks;
    }

    public String chat(String userMessage, String model) {
        String systemPrompt = """
                You are a helpful AI assistant with access to data analysis tools.
                Use the available tools to provide accurate and helpful responses.
                Always explain what data you're accessing and why.
                """;

        // ChatClient client;
        // if ("ollama".equalsIgnoreCase(model)) {
        // client = ollamaChatClient;
        // } else if ("gemini".equalsIgnoreCase(model)) {
        // client = geminiChatClient;
        // } else {
        // client = openAiChatClient;
        // }
        ChatClient client = openAiChatClient;

        return client.prompt()
                .system(systemPrompt)
                .user(userMessage)
                .toolCallbacks(mcpToolCallbacks)
                .call()
                .content();
    }
}
