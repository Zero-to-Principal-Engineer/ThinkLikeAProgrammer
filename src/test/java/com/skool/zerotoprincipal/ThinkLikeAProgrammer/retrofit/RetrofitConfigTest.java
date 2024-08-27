package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = RetrofitConfig.class)
class RetrofitConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Mock
    private OkHttpClient mockOkHttpClient;

    @InjectMocks
    private RetrofitConfig retrofitConfig;

    @Test
    void testOkHttpClientBeanCreation() {
        OkHttpClient okHttpClient = applicationContext.getBean(OkHttpClient.class);
        assertNotNull(okHttpClient, "OkHttpClient bean should be created by the configuration");

        // Verify the timeout values (120 seconds) as configured in the bean
        assertEquals(120, okHttpClient.connectTimeoutMillis() / 1000);
        assertEquals(120, okHttpClient.readTimeoutMillis() / 1000);
        assertEquals(120, okHttpClient.writeTimeoutMillis() / 1000);
    }

    @Test
    void testRetrofitBeanCreation() {
        Retrofit retrofit = applicationContext.getBean(Retrofit.class);
        assertNotNull(retrofit, "Retrofit bean should be created by the configuration");

        // Ensure that the correct base URL is set
        assertEquals("http://localhost:1234/", retrofit.baseUrl().toString());

        // Ensure GsonConverterFactory is set
        assertNotNull(retrofit.converterFactories().stream()
                .filter(factory -> factory instanceof GsonConverterFactory)
                .findFirst()
                .orElse(null), "Retrofit should use GsonConverterFactory");
    }

    @Test
    void testChatServiceBeanCreation() {
        ChatService chatService = applicationContext.getBean(ChatService.class);
        assertNotNull(chatService, "ChatService bean should be created by the configuration");
    }
}
