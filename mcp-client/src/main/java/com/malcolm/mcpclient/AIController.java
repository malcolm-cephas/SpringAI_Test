package com.malcolm.mcpclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    private final AIAssistantService aiAssistantService;

    public AIController(AIAssistantService aiAssistantService) {
        this.aiAssistantService = aiAssistantService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        try {
            String model = request.getModel() != null ? request.getModel() : "gemini";
            String response = aiAssistantService.chat(request.getMessage(), model);
            return ResponseEntity.ok(new ChatResponse(response, "success", model));
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            if (e.getCause() != null) {
                errorMessage += " | Details: " + e.getCause().getMessage();
            }
            return ResponseEntity.internalServerError()
                    .body(new ChatResponse("Error: " + errorMessage, "error", "unknown"));
        }
    }

    @GetMapping("/chat")
    public String chatHelp() {
        return "This is the AI Chat endpoint! To use it, please send a **POST** request with a JSON body. <br><br>" +
                "Example: <code>{ \"message\": \"Hello\", \"model\": \"gemini\" }</code>";
    }
}
