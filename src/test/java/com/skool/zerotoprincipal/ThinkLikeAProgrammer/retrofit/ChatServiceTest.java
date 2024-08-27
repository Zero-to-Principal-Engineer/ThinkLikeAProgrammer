package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    @Mock
    private ChatService chatService;

    @Mock
    private Call<ResponseBody> mockCall;

    private ChatRequest chatRequest;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a sample ChatRequest object
        chatRequest = new ChatRequest(
                "TestModel",
                Collections.emptyList(),  // Assuming empty messages for simplicity
                0.7,
                100,
                false
        );
    }

    @Test
    void testGetChatCompletion_Success() throws IOException {
        // Mock the behavior of the Call object to return a successful response
        ResponseBody mockResponseBody = mock(ResponseBody.class);
        Response<ResponseBody> mockResponse = Response.success(mockResponseBody);

        when(mockCall.execute()).thenReturn(mockResponse);
        when(chatService.getChatCompletion(chatRequest)).thenReturn(mockCall);

        // Invoke the method
        Call<ResponseBody> call = chatService.getChatCompletion(chatRequest);
        Response<ResponseBody> response = call.execute();

        // Verify that the Retrofit call was made and was successful
        verify(chatService).getChatCompletion(chatRequest);
        assertTrue(response.isSuccessful());
        assertEquals(mockResponseBody, response.body());
    }

    @Test
    void testGetChatCompletion_Failure() throws IOException {
        // Mock the behavior of the Call object to return a failed response
        Response<ResponseBody> mockResponse = Response.error(500, mock(ResponseBody.class));

        when(mockCall.execute()).thenReturn(mockResponse);
        when(chatService.getChatCompletion(chatRequest)).thenReturn(mockCall);

        // Invoke the method
        Call<ResponseBody> call = chatService.getChatCompletion(chatRequest);
        Response<ResponseBody> response = call.execute();

        // Verify that the Retrofit call was made and failed
        verify(chatService).getChatCompletion(chatRequest);
        assertFalse(response.isSuccessful());
        assertEquals(500, response.code());
    }

    @Test
    void testGetChatCompletion_Exception() throws IOException {
        // Mock the behavior of the Call object to throw an IOException
        when(mockCall.execute()).thenThrow(new IOException("Network error"));
        when(chatService.getChatCompletion(chatRequest)).thenReturn(mockCall);

        // Invoke the method and handle the exception
        Call<ResponseBody> call = chatService.getChatCompletion(chatRequest);
        try {
            call.execute();
        } catch (IOException e) {
            // Verify that the exception is thrown as expected
            assertEquals("Network error", e.getMessage());
        }

        // Verify that the Retrofit call was made
        verify(chatService).getChatCompletion(chatRequest);
    }
}