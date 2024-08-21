package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import com.google.gson.annotations.SerializedName;

class Message {
    @SerializedName("role")
    private String role;

    @SerializedName("content")
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters and setters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

