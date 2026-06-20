package com.carbonwise.app.data.remote;

import com.carbonwise.app.data.remote.model.AiChatRequest;
import com.carbonwise.app.data.remote.model.AiChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AiChatApiService {

    @POST("v1beta/models/gemini-1.5-flash:generateContent")
    Call<AiChatResponse> sendMessage(@Body AiChatRequest request);
}
