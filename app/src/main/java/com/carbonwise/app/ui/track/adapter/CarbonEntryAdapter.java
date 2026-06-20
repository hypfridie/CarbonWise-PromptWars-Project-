package com.carbonwise.app.ui.track.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.ItemCarbonEntryBinding;
import com.carbonwise.app.model.CarbonEntry;
import com.carbonwise.app.util.CarbonCalculator;
import com.carbonwise.app.util.TimeUtils;

public class CarbonEntryAdapter extends ListAdapter<CarbonEntry, CarbonEntryAdapter.ViewHolder> {

    private final OnEntryDeleteListener listener;

    public interface OnEntryDeleteListener {
        void onDelete(CarbonEntry entry);
    }

    public CarbonEntryAdapter(OnEntryDeleteListener listener) {
        super(new DiffUtil.ItemCallback<CarbonEntry>() {
            @Override
            public boolean areItemsTheSame(@NonNull CarbonEntry oldItem, @NonNull CarbonEntry newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull CarbonEntry oldItem, @NonNull CarbonEntry newItem) {
                return oldItem.getCarbonValue() == newItem.getCarbonValue() &&
                       oldItem.getCategory().equals(newItem.getCategory());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCarbonEntryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCarbonEntryBinding binding;

        ViewHolder(ItemCarbonEntryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CarbonEntry entry) {
            binding.textCategory.setText(entry.getCategory());
            binding.textSubCategory.setText(entry.getSubCategory());
            
            String impact = CarbonCalculator.getImpactLevel(entry.getCarbonValue(), entry.getCategory());
            binding.textImpact.setText(impact);
            binding.textValue.setText(String.format(java.util.Locale.getDefault(), "%.2f kg", entry.getCarbonValue()));
            
            int color = 0xFFFF9F0A; // Default orange
            if (impact.equals("Low")) color = 0xFF34C759;
            else if (impact.equals("Moderate")) color = 0xFFFF9F0A;
            else if (impact.equals("High")) color = 0xFFFF453A;

            binding.textImpact.setTextColor(color);

            int iconRes = R.drawable.ic_leaf;
            switch (entry.getCategory()) {
                case "Transport": iconRes = R.drawable.ic_transport; break;
                case "Food": iconRes = R.drawable.ic_food; break;
                case "Shopping": iconRes = R.drawable.ic_shopping; break;
                case "Waste": iconRes = R.drawable.ic_waste; break;
                case "Energy": iconRes = R.drawable.ic_energy; break;
            }
            binding.iconCategory.setImageResource(iconRes);

            binding.textTime.setText(TimeUtils.getRelativeTime(entry.getTimestamp()));
            binding.buttonDelete.setOnClickListener(v -> listener.onDelete(entry));
        }
    }
}
