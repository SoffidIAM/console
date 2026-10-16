package com.soffid.iam.api;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String role; // "user" o "model" (Gemini usa "model" en lugar de "assistant")
    private String text;

    public ChatMessage() {}

    public ChatMessage(String role, String text) {
        this.role = role;
        this.text = text;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}