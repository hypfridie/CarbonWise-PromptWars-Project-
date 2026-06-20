package com.carbonwise.app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentHomeBinding;
import com.carbonwise.app.ui.home.adapter.ChallengeAdapter;
import com.carbonwise.app.util.CarbonCalculator;
import com.carbonwise.app.viewmodel.HomeViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private ChallengeAdapter challengeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupChallengeRecycler();
        observeViewModel();
        binding.swipeRefresh.setOnRefreshListener(() -> {
            viewModel.refreshData();
            binding.swipeRefresh.setRefreshing(false);
        });
    }

    private void setupChallengeRecycler() {
        challengeAdapter = new ChallengeAdapter(challenge -> {
            // Handle challenge click
        });
        binding.recyclerChallenges.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerChallenges.setAdapter(challengeAdapter);
    }

    private void observeViewModel() {
        viewModel.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                binding.textLevel.setText(profile.getCurrentLevel());
                binding.textStreak.setText(getString(R.string.streak_days, profile.getCurrentStreak()));
                binding.textPoints.setText(String.valueOf(profile.getGreenPoints()));
            }
        });

        viewModel.getDashboardSummary().observe(getViewLifecycleOwner(), summary -> {
            if (summary != null) {
                binding.textDailyCarbon.setText(String.format(java.util.Locale.getDefault(), "%.1f", summary.getTodayCarbonKg()));
                binding.textWeeklyTotal.setText(String.format(java.util.Locale.getDefault(), "%.1f kg", summary.getWeeklyTotalKg()));
                binding.textWeeklyChange.setText(String.format(java.util.Locale.getDefault(), "%.1f%%", summary.getWeeklyChangePercent()));
                binding.textHealthStatus.setText(summary.getCarbonHealthStatus());

                int healthColor = CarbonCalculator.getCarbonHealthColor(summary.getCarbonHealthStatus());
                binding.cardHealth.setStrokeColor(healthColor);
                binding.textHealthStatus.setTextColor(healthColor);

                binding.progressGoal.setProgress((int) summary.getGoalProgressPercent());
                binding.textGoalPercent.setText(String.format(java.util.Locale.getDefault(), "%.0f%%", summary.getGoalProgressPercent()));

                setupPieChart(summary);
            }
        });

        viewModel.getActiveChallenges().observe(getViewLifecycleOwner(), challenges -> {
            if (challenges != null && !challenges.isEmpty()) {
                challengeAdapter.submitList(challenges.subList(0, Math.min(3, challenges.size())));
                binding.textNoChallenges.setVisibility(View.GONE);
            } else {
                binding.textNoChallenges.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupPieChart(com.carbonwise.app.model.DashboardSummary summary) {
        java.util.ArrayList<com.github.mikephil.charting.data.PieEntry> entries = new java.util.ArrayList<>();
        if (summary.getTransportPercent() > 0) entries.add(new com.github.mikephil.charting.data.PieEntry((float) summary.getTransportPercent(), "Transport"));
        if (summary.getEnergyPercent() > 0) entries.add(new com.github.mikephil.charting.data.PieEntry((float) summary.getEnergyPercent(), "Energy"));
        if (summary.getFoodPercent() > 0) entries.add(new com.github.mikephil.charting.data.PieEntry((float) summary.getFoodPercent(), "Food"));
        if (summary.getShoppingPercent() > 0) entries.add(new com.github.mikephil.charting.data.PieEntry((float) summary.getShoppingPercent(), "Shopping"));
        if (summary.getWastePercent() > 0) entries.add(new com.github.mikephil.charting.data.PieEntry((float) summary.getWastePercent(), "Waste"));

        if (entries.isEmpty()) {
            binding.pieChart.setVisibility(View.GONE);
            return;
        }

        binding.pieChart.setVisibility(View.VISIBLE);
        com.github.mikephil.charting.data.PieDataSet dataSet = new com.github.mikephil.charting.data.PieDataSet(entries, "");
        dataSet.setColors(
                getResources().getColor(R.color.chart_transport, null),
                getResources().getColor(R.color.chart_energy, null),
                getResources().getColor(R.color.chart_food, null),
                getResources().getColor(R.color.chart_shopping, null),
                getResources().getColor(R.color.chart_waste, null)
        );
        dataSet.setDrawValues(false);
        dataSet.setSliceSpace(4f);
        dataSet.setSelectionShift(5f);

        com.github.mikephil.charting.data.PieData data = new com.github.mikephil.charting.data.PieData(dataSet);
        binding.pieChart.setData(data);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.setDrawHoleEnabled(true);
        binding.pieChart.setHoleColor(android.graphics.Color.TRANSPARENT);
        binding.pieChart.setHoleRadius(70f);
        binding.pieChart.setTransparentCircleRadius(75f);
        binding.pieChart.setDrawEntryLabels(false);
        binding.pieChart.getLegend().setEnabled(false);
        binding.pieChart.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
