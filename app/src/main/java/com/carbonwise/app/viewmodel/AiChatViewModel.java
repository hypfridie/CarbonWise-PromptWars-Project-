package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.remote.AiChatApiService;
import com.carbonwise.app.data.remote.model.AiChatRequest;
import com.carbonwise.app.data.remote.model.AiChatResponse;
import com.carbonwise.app.data.repository.CarbonRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.CarbonEntry;
import com.carbonwise.app.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class AiChatViewModel extends ViewModel {

    private final AiChatApiService apiService;
    private final CarbonRepository carbonRepository;
    private final UserRepository userRepository;
    
    private final MutableLiveData<List<ChatMessage>> messages = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private UserProfile userProfile;
    private List<CarbonEntry> recentEntries = new ArrayList<>();
    private final androidx.lifecycle.Observer<UserProfile> profileObserver = profile -> this.userProfile = profile;
    private final androidx.lifecycle.Observer<List<CarbonEntry>> entriesObserver = entries -> {
        if (entries != null) {
            this.recentEntries = entries.subList(0, Math.min(entries.size(), 20));
        }
    };

    @Inject
    public AiChatViewModel(AiChatApiService apiService, CarbonRepository carbonRepository, UserRepository userRepository) {
        this.apiService = apiService;
        this.carbonRepository = carbonRepository;
        this.userRepository = userRepository;
        loadContextData();
    }

    private void loadContextData() {
        userRepository.getFirstProfile().observeForever(profileObserver);
        carbonRepository.getAllEntries().observeForever(entriesObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        userRepository.getFirstProfile().removeObserver(profileObserver);
        carbonRepository.getAllEntries().removeObserver(entriesObserver);
    }

    public LiveData<List<ChatMessage>> getMessages() {
        return messages;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void sendMessage(String userMessage) {
        List<ChatMessage> currentMessages = messages.getValue();
        List<ChatMessage> updated = currentMessages != null ? new ArrayList<>(currentMessages) : new ArrayList<>();

        updated.add(new ChatMessage(userMessage, true));
        messages.setValue(updated);
        isLoading.setValue(true);

        String contextPrompt = constructContextPrompt(userMessage);
        AiChatRequest request = new AiChatRequest(contextPrompt);
        apiService.sendMessage(request).enqueue(new Callback<AiChatResponse>() {
            @Override
            public void onResponse(Call<AiChatResponse> call, Response<AiChatResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    String reply = response.body().getText();
                    List<ChatMessage> listWithReply = messages.getValue();
                    List<ChatMessage> finalUpdate = listWithReply != null ? new ArrayList<>(listWithReply) : new ArrayList<>();
                    finalUpdate.add(new ChatMessage(reply, false));
                    messages.setValue(finalUpdate);
                } else {
                    error.setValue("Failed to get response. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<AiChatResponse> call, Throwable t) {
                isLoading.setValue(false);
                error.setValue("Network error: " + t.getMessage());
            }
        });
    }

    private String constructContextPrompt(String userMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are CarbonWise AI, a helpful sustainability assistant. ");
        
        if (userProfile != null) {
            sb.append("The user's name is ").append(userProfile.getName()).append(". ");
            sb.append("Their goal is to reduce carbon emissions by ").append(userProfile.getMonthlyCarbonGoal()).append("kg per month. ");
        }

        if (!recentEntries.isEmpty()) {
            sb.append("The user's recent activities are: ");
            for (CarbonEntry entry : recentEntries) {
                sb.append(entry.getCategory()).append(" (").append(entry.getNotes()).append("), ");
            }
            sb.append(". ");
        }

        sb.append("Answer the following user question/message helpfully and concisely: ");
        sb.append(userMessage);
        
        return sb.toString();
    }

    public static class ChatMessage {
        public final String message;
        public final boolean isUser;

        public ChatMessage(String message, boolean isUser) {
            this.message = message;
            this.isUser = isUser;
        }
    }
}
