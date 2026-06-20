package com.carbonwise.app.ui.challenges.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.ItemChallengeListBinding;
import com.carbonwise.app.model.Challenge;

import java.util.Locale;

public class ChallengeListAdapter extends ListAdapter<Challenge, ChallengeListAdapter.ViewHolder> {

    private final OnChallengeActionListener listener;

    public interface OnChallengeActionListener {
        void onComplete(Challenge challenge);
    }

    public ChallengeListAdapter(OnChallengeActionListener listener) {
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
        return new ViewHolder(ItemChallengeListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemChallengeListBinding binding;

        ViewHolder(ItemChallengeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Challenge challenge) {
            binding.textTitle.setText(challenge.getTitle());
            binding.textDescription.setText(challenge.getDescription());
            binding.textCategory.setText(challenge.getCategory());
            binding.textPoints.setText(String.format(Locale.getDefault(), "+%d pts", challenge.getPointsReward()));

            int iconRes = switch (challenge.getCategory() != null ? challenge.getCategory() : "") {
                case "Food" -> R.drawable.ic_food;
                case "Transport" -> R.drawable.ic_transport;
                case "Energy" -> R.drawable.ic_energy;
                case "Shopping" -> R.drawable.ic_shopping;
                case "Waste" -> R.drawable.ic_waste;
                default -> R.drawable.ic_leaf;
            };

            int tintColor = switch (challenge.getCategory() != null ? challenge.getCategory() : "") {
                case "Food" -> 0xFFFF9F0A; // orange
                case "Transport" -> 0xFF007AFF; // ios_blue
                case "Energy" -> 0xFF5AC8FA; // ios_teal
                case "Shopping" -> 0xFFAF52DE; // ios_purple
                case "Waste" -> 0xFFFF3B30; // red
                default -> 0xFF34C759; // emerald_green
            };

            binding.iconChallenge.setImageResource(iconRes);
            binding.iconChallenge.setImageTintList(ColorStateList.valueOf(tintColor));
            binding.textCategory.setTextColor(tintColor);

            if (challenge.isCompleted()) {
                binding.buttonComplete.setText("Done");
                binding.buttonComplete.setEnabled(false);
                binding.buttonComplete.setAlpha(0.5f);
            } else {
                binding.buttonComplete.setText("Complete");
                binding.buttonComplete.setEnabled(true);
                binding.buttonComplete.setAlpha(1.0f);
                binding.buttonComplete.setOnClickListener(v -> listener.onComplete(challenge));
            }
        }
    }
}
