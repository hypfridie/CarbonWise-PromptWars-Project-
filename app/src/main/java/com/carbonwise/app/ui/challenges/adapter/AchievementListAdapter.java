package com.carbonwise.app.ui.challenges.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.databinding.ItemAchievementBinding;
import com.carbonwise.app.model.Achievement;

public class AchievementListAdapter extends ListAdapter<Achievement, AchievementListAdapter.ViewHolder> {

    public AchievementListAdapter() {
        super(new DiffUtil.ItemCallback<Achievement>() {
            @Override
            public boolean areItemsTheSame(@NonNull Achievement oldItem, @NonNull Achievement newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Achievement oldItem, @NonNull Achievement newItem) {
                return oldItem.isUnlocked() == newItem.isUnlocked() &&
                        oldItem.getTitle().equals(newItem.getTitle());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemAchievementBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAchievementBinding binding;

        ViewHolder(ItemAchievementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Achievement achievement) {
            binding.textTitle.setText(achievement.getTitle());
            binding.textDescription.setText(achievement.getDescription());
            
            if (achievement.isUnlocked()) {
                binding.imageLock.setVisibility(View.GONE);
                binding.iconAchievement.setAlpha(1.0f);
            } else {
                binding.imageLock.setVisibility(View.VISIBLE);
                binding.iconAchievement.setAlpha(0.3f);
            }
        }
    }
}
