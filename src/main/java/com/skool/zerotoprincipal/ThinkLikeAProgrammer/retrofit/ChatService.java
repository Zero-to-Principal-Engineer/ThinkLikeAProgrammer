package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatService {
    @Headers("Content-Type: application/json")
    @POST("/v1/chat/completions")
    Call<ResponseBody> getChatCompletion(@Body ChatRequest request);
}
