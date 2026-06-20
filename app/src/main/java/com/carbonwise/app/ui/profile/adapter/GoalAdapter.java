package com.carbonwise.app.ui.profile.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.databinding.ItemGoalBinding;
import com.carbonwise.app.model.CarbonGoal;

public class GoalAdapter extends ListAdapter<CarbonGoal, GoalAdapter.ViewHolder> {

    private final OnGoalActionListener listener;

    public interface OnGoalActionListener {
        void onDeactivate(CarbonGoal goal);
    }

    public GoalAdapter(OnGoalActionListener listener) {
        super(new DiffUtil.ItemCallback<CarbonGoal>() {
            @Override
            public boolean areItemsTheSame(@NonNull CarbonGoal oldItem, @NonNull CarbonGoal newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull CarbonGoal oldItem, @NonNull CarbonGoal newItem) {
                return oldItem.getCurrentReductionPercent() == newItem.getCurrentReductionPercent() &&
                       oldItem.isActive() == newItem.isActive();
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemGoalBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemGoalBinding binding;

        ViewHolder(ItemGoalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CarbonGoal goal) {
            binding.textTitle.setText(goal.getTitle());
            binding.textProgress.setText(String.format(java.util.Locale.getDefault(), "%.0f%%",
                    goal.getCurrentReductionPercent()));
            binding.progressGoal.setProgress((int) goal.getCurrentReductionPercent());
            binding.buttonRemove.setOnClickListener(v -> listener.onDeactivate(goal));
        }
    }
}
