package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalChatService chatService;

    @BeforeEach
    public void setup() {
        // Set up mock behavior for the service
        when(chatService.getChatCompletion()).thenReturn("Test response");
    }

    @Test
    public void testGetChatCompletion() throws Exception {
        // Perform GET request and verify the response
        mockMvc.perform(get("/api/chat/completion"))
                .andExpect(status().isOk())
                .andExpect(content().string("Test response"));
    }

    @Test
    public void testGetChatCompletion_ServiceReturnsDifferentResponse() throws Exception {
        // Modify the mock to return a different response
        when(chatService.getChatCompletion()).thenReturn("Another test response");

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/chat/completion"))
                .andExpect(status().isOk())
                .andExpect(content().string("Another test response"));
    }

    @Test
    public void testGetChatCompletion_ServiceThrowsException() throws Exception {
        // Modify the mock to throw an exception
        when(chatService.getChatCompletion()).thenThrow(new RuntimeException("Service exception"));

        // Verify that the exception is thrown
        assertThrows(RuntimeException.class, () -> {
            chatService.getChatCompletion();
        });
    }
}
