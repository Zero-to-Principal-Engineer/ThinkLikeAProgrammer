package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)   // Increase connection timeout
                .readTimeout(120, TimeUnit.SECONDS)      // Increase read timeout
                .writeTimeout(120, TimeUnit.SECONDS)     // Increase write timeout
                .build();
    }

    @Bean
    public Retrofit retrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("http://localhost:1234")
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Bean
    public ChatService chatService(Retrofit retrofit) {
        return retrofit.create(ChatService.class);
    }
}
