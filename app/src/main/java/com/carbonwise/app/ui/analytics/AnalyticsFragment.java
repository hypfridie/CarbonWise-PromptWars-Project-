package com.carbonwise.app.ui.analytics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentAnalyticsBinding;
import com.carbonwise.app.viewmodel.AnalyticsViewModel;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnalyticsFragment extends Fragment {

    private FragmentAnalyticsBinding binding;
    private AnalyticsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnalyticsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTimeRangeTabs();
        setupCharts();
        observeViewModel();
    }

    private void setupTimeRangeTabs() {
        binding.tabTimeRange.addTab(binding.tabTimeRange.newTab().setText(R.string.week));
        binding.tabTimeRange.addTab(binding.tabTimeRange.newTab().setText(R.string.month));

        binding.tabTimeRange.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewModel.setTimeRange(tab.getPosition() == 0
                        ? AnalyticsViewModel.TimeRange.WEEK
                        : AnalyticsViewModel.TimeRange.MONTH);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupCharts() {
        setupTrendChart();
        setupSourcesChart();
    }

    private void setupTrendChart() {
        binding.chartTrend.getDescription().setEnabled(false);
        binding.chartTrend.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        binding.chartTrend.getXAxis().setTextColor(Color.WHITE);
        binding.chartTrend.getAxisLeft().setTextColor(Color.WHITE);
        binding.chartTrend.getAxisRight().setEnabled(false);
        binding.chartTrend.getLegend().setTextColor(Color.WHITE);
        binding.chartTrend.setDrawGridBackground(false);
    }

    private void setupSourcesChart() {
        binding.chartSources.getDescription().setEnabled(false);
        binding.chartSources.setDrawHoleEnabled(true);
        binding.chartSources.setHoleColor(Color.TRANSPARENT);
        binding.chartSources.setHoleRadius(70f);
        binding.chartSources.setTransparentCircleRadius(75f);
        binding.chartSources.setDrawEntryLabels(false);
        binding.chartSources.getLegend().setTextColor(Color.WHITE);
    }

    private void observeViewModel() {
        viewModel.getTrendData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                List<BarEntry> entries = new ArrayList<>();
                List<String> labels = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    entries.add(new BarEntry(i, (float) data.get(i).value));
                    labels.add(data.get(i).day);
                }
                BarDataSet dataSet = new BarDataSet(entries, getString(R.string.daily_emissions));
                dataSet.setColor(getResources().getColor(R.color.emerald_green, null));
                dataSet.setValueTextColor(Color.WHITE);
                BarData barData = new BarData(dataSet);
                binding.chartTrend.setData(barData);
                binding.chartTrend.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
                binding.chartTrend.invalidate();
            }
        });

        viewModel.getEmissionSources().observe(getViewLifecycleOwner(), categories -> {
            if (categories != null && !categories.isEmpty()) {
                ArrayList<PieEntry> entries = new ArrayList<>();
                for (com.carbonwise.app.data.local.dao.CarbonEntryDao.CategoryTotal ct : categories) {
                    entries.add(new PieEntry((float) ct.total, ct.category));
                }
                PieDataSet dataSet = new PieDataSet(entries, "");
                dataSet.setColors(
                        getResources().getColor(R.color.chart_transport, null),
                        getResources().getColor(R.color.chart_energy, null),
                        getResources().getColor(R.color.chart_food, null),
                        getResources().getColor(R.color.chart_shopping, null),
                        getResources().getColor(R.color.chart_waste, null)
                );
                dataSet.setValueTextColor(Color.WHITE);
                dataSet.setSliceSpace(4f);
                dataSet.setDrawValues(false);
                PieData pieData = new PieData(dataSet);
                binding.chartSources.setData(pieData);
                binding.chartSources.invalidate();
            } else {
                binding.chartSources.clear();
            }
        });

        viewModel.getTotalReduction().observe(getViewLifecycleOwner(), reduction -> {
            binding.textTotalReduction.setText(String.format(java.util.Locale.getDefault(), "%.1f%%", reduction));
        });

        viewModel.getTreesEquivalent().observe(getViewLifecycleOwner(), trees -> {
            binding.textTreesEquivalent.setText(String.format(java.util.Locale.getDefault(), "%.1f", trees));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
