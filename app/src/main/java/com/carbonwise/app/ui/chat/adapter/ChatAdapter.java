package com.carbonwise.app.ui.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.databinding.ItemChatMessageUserBinding;
import com.carbonwise.app.databinding.ItemChatMessageAiBinding;
import com.carbonwise.app.viewmodel.AiChatViewModel;

public class ChatAdapter extends ListAdapter<AiChatViewModel.ChatMessage, RecyclerView.ViewHolder> {

    private static final int TYPE_USER = 1;
    private static final int TYPE_AI = 2;

    public ChatAdapter() {
        super(new DiffUtil.ItemCallback<AiChatViewModel.ChatMessage>() {
            @Override
            public boolean areItemsTheSame(@NonNull AiChatViewModel.ChatMessage oldItem,
                                           @NonNull AiChatViewModel.ChatMessage newItem) {
                return oldItem.message.equals(newItem.message) && oldItem.isUser == newItem.isUser;
            }

            @Override
            public boolean areContentsTheSame(@NonNull AiChatViewModel.ChatMessage oldItem,
                                              @NonNull AiChatViewModel.ChatMessage newItem) {
                return oldItem.message.equals(newItem.message);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).isUser ? TYPE_USER : TYPE_AI;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_USER) {
            return new UserViewHolder(ItemChatMessageUserBinding.inflate(inflater, parent, false));
        } else {
            return new AiViewHolder(ItemChatMessageAiBinding.inflate(inflater, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AiChatViewModel.ChatMessage message = getItem(position);
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind(message);
        } else if (holder instanceof AiViewHolder) {
            ((AiViewHolder) holder).bind(message);
        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatMessageUserBinding binding;

        UserViewHolder(ItemChatMessageUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AiChatViewModel.ChatMessage message) {
            binding.textMessage.setText(message.message);
        }
    }

    static class AiViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatMessageAiBinding binding;

        AiViewHolder(ItemChatMessageAiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AiChatViewModel.ChatMessage message) {
            binding.textMessage.setText(message.message);
        }
    }
}
