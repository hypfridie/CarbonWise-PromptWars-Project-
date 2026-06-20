package com.carbonwise.app.ui.chat;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbonwise.app.databinding.ActivityAiChatBinding;
import com.carbonwise.app.ui.chat.adapter.ChatAdapter;
import com.carbonwise.app.viewmodel.AiChatViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AiChatActivity extends AppCompatActivity {

    private ActivityAiChatBinding binding;
    private AiChatViewModel viewModel;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAiChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AiChatViewModel.class);

        setupToolbar();
        setupRecyclerView();
        setupInput();
        observeViewModel();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        chatAdapter = new ChatAdapter();
        binding.recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerChat.setAdapter(chatAdapter);
    }

    private void setupInput() {
        binding.editMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                return true;
            }
            return false;
        });

        binding.buttonSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = binding.editMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            binding.editMessage.setText("");
            viewModel.sendMessage(message);
        }
    }

    private void observeViewModel() {
        viewModel.getMessages().observe(this, messages -> {
            chatAdapter.submitList(messages);
            if (!messages.isEmpty()) {
                binding.recyclerChat.smoothScrollToPosition(messages.size() - 1);
            }
        });

        viewModel.getIsLoading().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.buttonSend.setEnabled(!isLoading);
        });

        viewModel.getError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
