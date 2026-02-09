package com.malcolm.mcpclient;

public class ChatResponse {
    private String response;
    private String status;
    private String model;

    public ChatResponse(String response, String status, String model) {
        this.response = response;
        this.status = status;
        this.model = model;
    }

    public String getResponse() {
        return response;
    }

    public String getStatus() {
        return status;
    }

    public String getModel() {
        return model;
    }
}
