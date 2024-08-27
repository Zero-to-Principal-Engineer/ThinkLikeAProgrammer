package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatRequestTest {

    @Test
    void getModel() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, false);
        assertEquals("TestModel", chatRequest.getModel());
    }

    @Test
    void setModel() {
        ChatRequest chatRequest = new ChatRequest(null, null, 0.7, 100, false);
        chatRequest.setModel("NewModel");
        assertEquals("NewModel", chatRequest.getModel());
    }

    @Test
    void getMessages() {
        Message message1 = new Message("user", "Hello");
        Message message2 = new Message("system", "Hi there!");
        List<Message> messages = Arrays.asList(message1, message2);
        ChatRequest chatRequest = new ChatRequest("TestModel", messages, 0.7, 100, false);
        assertEquals(messages, chatRequest.getMessages());
    }

    @Test
    void setMessages() {
        Message message1 = new Message("user", "Hello");
        Message message2 = new Message("system", "Hi there!");
        List<Message> messages = Arrays.asList(message1, message2);
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, false);
        chatRequest.setMessages(messages);
        assertEquals(messages, chatRequest.getMessages());
    }

    @Test
    void getTemperature() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, false);
        assertEquals(0.7, chatRequest.getTemperature());
    }

    @Test
    void setTemperature() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.0, 100, false);
        chatRequest.setTemperature(0.8);
        assertEquals(0.8, chatRequest.getTemperature());
    }

    @Test
    void getMaxTokens() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, false);
        assertEquals(100, chatRequest.getMaxTokens());
    }

    @Test
    void setMaxTokens() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 50, false);
        chatRequest.setMaxTokens(200);
        assertEquals(200, chatRequest.getMaxTokens());
    }

    @Test
    void isStream() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, true);
        assertTrue(chatRequest.isStream());
    }

    @Test
    void setStream() {
        ChatRequest chatRequest = new ChatRequest("TestModel", null, 0.7, 100, false);
        chatRequest.setStream(true);
        assertTrue(chatRequest.isStream());
    }

    @Test
    void testToString() {
        Message message1 = new Message("user", "Hello");
        Message message2 = new Message("system", "Hi there!");
        List<Message> messages = Arrays.asList(message1, message2);
        ChatRequest chatRequest = new ChatRequest("TestModel", messages, 0.7, 100, false);

        String expectedString = "ChatRequest{model='TestModel', messages=" + messages + ", temperature=0.7, maxTokens=100, stream=false}";
        assertEquals(expectedString, chatRequest.toString());
    }
}