package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LocalChatServiceTest {

    @Mock
    private ChatService chatService;

    @Mock
    private Call<ResponseBody> mockCall;

    @InjectMocks
    private LocalChatService localChatService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Inject mocks into LocalChatService
        localChatService = new LocalChatService(chatService);
    }

    @Test
    void testGetChatCompletion_Success() throws IOException {
        // Mock the behavior of the Call object to return a successful response
        ResponseBody mockResponseBody = mock(ResponseBody.class);
        Response<ResponseBody> mockResponse = Response.success(mockResponseBody);

        when(mockResponseBody.string()).thenReturn("This is a test response");
        when(mockCall.execute()).thenReturn(mockResponse);
        when(chatService.getChatCompletion(any(ChatRequest.class))).thenReturn(mockCall);

        // Invoke the method
        String result = localChatService.getChatCompletion();

        // Verify that the Retrofit call was made and was successful
        verify(chatService).getChatCompletion(any(ChatRequest.class));
        assertEquals("This is a test response", result);
    }

    @Test
    void testGetChatCompletion_Failure() throws IOException {
        // Mock the behavior of the Call object to return a failed response
        Response<ResponseBody> mockResponse = Response.error(500, mock(ResponseBody.class));

        when(mockCall.execute()).thenReturn(mockResponse);
        when(chatService.getChatCompletion(any(ChatRequest.class))).thenReturn(mockCall);

        // Invoke the method and expect a RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            localChatService.getChatCompletion();
        });

        // Verify that the Retrofit call was made and failed
        verify(chatService).getChatCompletion(any(ChatRequest.class));
        assertEquals("Request failed with status code: 500", exception.getMessage());
    }

    @Test
    void testGetChatCompletion_Exception() throws IOException {
        // Mock the behavior of the Call object to throw an IOException
        when(mockCall.execute()).thenThrow(new IOException("Network error"));
        when(chatService.getChatCompletion(any(ChatRequest.class))).thenReturn(mockCall);

        // Invoke the method and expect a RuntimeException due to IOException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            localChatService.getChatCompletion();
        });

        // Verify that the Retrofit call was made and that the exception is thrown
        verify(chatService).getChatCompletion(any(ChatRequest.class));
        assertEquals("Error executing request", exception.getMessage());
    }
}