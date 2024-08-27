package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getRole() {
        // Arrange
        Message message = new Message("user", "This is a content.");

        // Act
        String role = message.getRole();

        // Assert
        assertEquals("user", role);
    }

    @Test
    void setRole() {
        // Arrange
        Message message = new Message("user", "This is a content.");

        // Act
        message.setRole("system");

        // Assert
        assertEquals("system", message.getRole());
    }

    @Test
    void getContent() {
        // Arrange
        Message message = new Message("user", "This is a content.");

        // Act
        String content = message.getContent();

        // Assert
        assertEquals("This is a content.", content);
    }

    @Test
    void setContent() {
        // Arrange
        Message message = new Message("user", "This is a content.");

        // Act
        message.setContent("New content");

        // Assert
        assertEquals("New content", message.getContent());
    }

    @Test
    void testToString() {
        // Arrange
        Message message = new Message("user", "This is a content.");

        // Act
        String result = message.toString();

        // Assert
        String expected = "Message{role='user', content='This is a content.'}";
        assertEquals(expected, result);
    }
}