package com.carbonwise.app.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.databinding.ItemChallengeCardBinding;
import com.carbonwise.app.model.Challenge;

public class ChallengeAdapter extends ListAdapter<Challenge, ChallengeAdapter.ViewHolder> {

    private final OnChallengeClickListener listener;

    public interface OnChallengeClickListener {
        void onChallengeClick(Challenge challenge);
    }

    public ChallengeAdapter(OnChallengeClickListener listener) {
        super(new DiffUtil.ItemCallback<Challenge>() {
            @Override
            public boolean areItemsTheSame(@NonNull Challenge oldItem, @NonNull Challenge newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Challenge oldItem, @NonNull Challenge newItem) {
                return oldItem.isCompleted() == newItem.isCompleted() &&
                       oldItem.getTitle().equals(newItem.getTitle());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemChallengeCardBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemChallengeCardBinding binding;

        ViewHolder(ItemChallengeCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Challenge challenge) {
            binding.textTitle.setText(challenge.getTitle());
            binding.textDescription.setText(challenge.getDescription());
            binding.textPoints.setText("+" + challenge.getPointsReward() + " pts");
            binding.getRoot().setOnClickListener(v -> listener.onChallengeClick(challenge));
        }
    }
}
