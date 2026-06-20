package com.carbonwise.app.ui.track;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentTrackBinding;
import com.carbonwise.app.databinding.DialogAddEntryBinding;
import com.carbonwise.app.ui.track.adapter.CarbonEntryAdapter;
import com.carbonwise.app.util.CarbonCalculator;
import com.carbonwise.app.util.TimeUtils;
import com.carbonwise.app.viewmodel.TrackViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrackFragment extends Fragment {

    private FragmentTrackBinding binding;
    private TrackViewModel viewModel;
    private CarbonEntryAdapter entryAdapter;
    private String currentCategory = "Transport";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TrackViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTrackBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDateNavigator();
        setupTabs();
        setupRecyclerView();
        setupAddButton();
        observeViewModel();
    }

    private void setupDateNavigator() {
        binding.buttonPrevDay.setOnClickListener(v -> viewModel.previousDay());
        binding.buttonNextDay.setOnClickListener(v -> viewModel.nextDay());
        
        viewModel.getSelectedDate().observe(getViewLifecycleOwner(), date -> {
            String formatted = TimeUtils.formatDate(date);
            long today = TimeUtils.getStartOfDay(System.currentTimeMillis());
            long selected = TimeUtils.getStartOfDay(date);
            
            if (today == selected) {
                binding.textSelectedDate.setText("Today");
                binding.fabAdd.setVisibility(View.VISIBLE);
            } else {
                binding.textSelectedDate.setText(formatted);
                binding.fabAdd.setVisibility(View.GONE);
            }
        });
    }

    private void setupTabs() {
        String[] categories = {"Transport", "Energy", "Food", "Shopping", "Waste"};
        binding.tabLayout.removeAllTabs();
        for (String cat : categories) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(cat));
        }
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                currentCategory = categories[tab.getPosition()];
                viewModel.setCategory(currentCategory);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
        // Set initial category
        viewModel.setCategory(currentCategory);
    }

    private void setupRecyclerView() {
        entryAdapter = new CarbonEntryAdapter(entry -> {
            viewModel.deleteEntry(entry);
            Toast.makeText(requireContext(), R.string.entry_deleted, Toast.LENGTH_SHORT).show();
        });
        binding.recyclerEntries.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerEntries.setAdapter(entryAdapter);
    }

    private void setupAddButton() {
        binding.fabAdd.setOnClickListener(v -> showAddEntryDialog());
    }

    private void showAddEntryDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        DialogAddEntryBinding dialogBinding = DialogAddEntryBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.textCategory.setText(currentCategory);
        
        String[] types;
        boolean useSlider = false;
        String sliderLabel = "Consumption Level";

        switch (currentCategory) {
            case "Transport":
                types = new String[]{"Petrol", "Diesel", "Hybrid", "Electric", "Public Transport", "Bicycle"};
                dialogBinding.inputLayout.setHint("Distance (km)");
                break;
            case "Energy":
                types = new String[]{"Electricity", "Air Conditioning", "Water Heater"};
                dialogBinding.inputLayout.setHint("Usage (kWh)");
                break;
            case "Food":
                types = new String[]{"Beef", "Chicken", "Fish", "Dairy", "Vegetarian Meal", "Vegan Meal"};
                useSlider = true;
                sliderLabel = "Servings / Portion Size";
                dialogBinding.textSliderLow.setText("Small");
                dialogBinding.textSliderHigh.setText("Large");
                break;
            case "Shopping":
                types = new String[]{"Online Purchase", "Fast Fashion", "Electronics", "General"};
                useSlider = true;
                sliderLabel = "Purchase Size";
                dialogBinding.textSliderLow.setText("Small");
                dialogBinding.textSliderHigh.setText("Large");
                dialogBinding.inputLayout.setHint("Amount Spent (₹)");
                dialogBinding.inputLayout.setVisibility(View.VISIBLE); // Optionally show both
                break;
            case "Waste":
                types = new String[]{"Recycling", "Plastic", "Food Waste", "General Waste"};
                useSlider = true;
                sliderLabel = "Waste Quantity";
                dialogBinding.textSliderLow.setText("Low");
                dialogBinding.textSliderHigh.setText("High");
                break;
            default:
                types = new String[]{"General"};
        }

        dialogBinding.spinnerType.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, types));

        if (useSlider) {
            dialogBinding.layoutSlider.setVisibility(View.VISIBLE);
            dialogBinding.labelSlider.setText(sliderLabel);
            if (!currentCategory.equals("Shopping")) {
                dialogBinding.inputLayout.setVisibility(View.GONE);
            }
        } else {
            dialogBinding.layoutSlider.setVisibility(View.GONE);
            dialogBinding.inputLayout.setVisibility(View.VISIBLE);
        }

        AdapterView.OnItemSelectedListener updateImpact = new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { updateEstimate(dialogBinding); }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        };
        dialogBinding.spinnerType.setOnItemSelectedListener(updateImpact);
        dialogBinding.sliderLevel.addOnChangeListener((slider, value, fromUser) -> updateEstimate(dialogBinding));

        dialogBinding.buttonSave.setOnClickListener(v -> {
            String type = dialogBinding.spinnerType.getSelectedItem().toString();
            String size = getSliderLabel((int) dialogBinding.sliderLevel.getValue(), currentCategory);
            
            if (currentCategory.equals("Transport") || currentCategory.equals("Energy")) {
                String valStr = dialogBinding.inputValue.getText().toString().trim();
                if (valStr.isEmpty()) {
                    dialogBinding.inputValue.setError("Required");
                    return;
                }
                double val = Double.parseDouble(valStr);
                if (currentCategory.equals("Transport")) viewModel.addTransportEntry(type, val, type);
                else viewModel.addEnergyEntry(type, val);
            } else if (currentCategory.equals("Food")) {
                viewModel.addFoodEntry(type, size);
            } else if (currentCategory.equals("Shopping")) {
                String valStr = dialogBinding.inputValue.getText().toString().trim();
                double val = valStr.isEmpty() ? 0 : Double.parseDouble(valStr);
                viewModel.addShoppingEntry(type, size, val);
            } else if (currentCategory.equals("Waste")) {
                viewModel.addWasteEntry(type, size);
            }
            
            dialog.dismiss();
            Toast.makeText(requireContext(), R.string.entry_added, Toast.LENGTH_SHORT).show();
        });

        dialogBinding.buttonCancel.setOnClickListener(v -> dialog.dismiss());
        updateEstimate(dialogBinding);
        dialog.show();
    }

    private void updateEstimate(DialogAddEntryBinding binding) {
        String type = binding.spinnerType.getSelectedItem().toString();
        String size = getSliderLabel((int) binding.sliderLevel.getValue(), currentCategory);
        double co2 = 0;

        if (currentCategory.equals("Food")) {
            co2 = CarbonCalculator.calculateFoodEmissions(type, size);
        } else if (currentCategory.equals("Waste")) {
            co2 = CarbonCalculator.calculateWasteEmissions(type, size);
        } else if (currentCategory.equals("Shopping")) {
            String valStr = binding.inputValue.getText().toString().trim();
            double val = valStr.isEmpty() ? 0 : Double.parseDouble(valStr);
            co2 = CarbonCalculator.calculateShoppingEmissions(type, size, val);
        } else {
            // For transport/energy we need the input value
            String valStr = binding.inputValue.getText().toString().trim();
            if (!valStr.isEmpty()) {
                double val = Double.parseDouble(valStr);
                if (currentCategory.equals("Transport")) co2 = CarbonCalculator.calculateTransportEmissions(type, val);
                else co2 = CarbonCalculator.calculateElectricityEmissions(val);
            }
        }

        String impact = CarbonCalculator.getImpactLevel(co2, currentCategory);
        binding.textImpactEstimate.setText("Estimated Impact: " + impact);
        int color = impact.equals("Low") ? 0xFF34C759 : (impact.equals("Moderate") ? 0xFFFF9F0A : 0xFFFF453A);
        binding.textImpactEstimate.setTextColor(color);
    }

    private String getSliderLabel(int value, String category) {
        if (category.equals("Food")) {
            if (value == 0) return "Small";
            if (value == 1) return "Medium";
            return "Large";
        }
        if (category.equals("Waste")) {
            if (value == 0) return "Low";
            if (value == 1) return "Moderate";
            return "High";
        }
        if (category.equals("Shopping")) {
            if (value == 0) return "Small";
            if (value == 1) return "Medium";
            return "Large";
        }
        return "Moderate";
    }

    private void observeViewModel() {
        viewModel.getFilteredEntries().observe(getViewLifecycleOwner(), entries -> {
            entryAdapter.submitList(entries);
            binding.textEmpty.setVisibility(entries == null || entries.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
