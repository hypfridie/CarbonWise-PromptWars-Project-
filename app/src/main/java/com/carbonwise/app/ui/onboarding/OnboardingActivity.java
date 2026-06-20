package com.carbonwise.app.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.carbonwise.app.databinding.ActivityOnboardingBinding;
import com.carbonwise.app.ui.MainActivity;
import com.carbonwise.app.util.PreferenceManager;
import com.carbonwise.app.viewmodel.ProfileViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;
    private ProfileViewModel viewModel;
    private int currentStep = 0;

    @Inject
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        setupStepIndicators();
        setupSpinners();
        setupButtons();
        showStep(0);
    }

    private void setupStepIndicators() {
        binding.stepIndicator.setStepCount(4);
    }

    private void setupSpinners() {
        binding.spinnerAge.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"18-24", "25-34", "35-44", "45-54", "55-64", "65+"}));

        binding.spinnerVehicle.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Petrol", "Diesel", "Hybrid", "Electric", "Public Transport", "Bicycle"}));

        binding.spinnerDiet.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Vegetarian", "Eggetarian", "Mixed Diet", "Non-Vegetarian"}));

        binding.spinnerShopping.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Low", "Moderate", "High"}));
    }

    private void setupButtons() {
        binding.buttonNext.setOnClickListener(v -> {
            if (validateCurrentStep()) {
                if (currentStep < 3) {
                    currentStep++;
                    showStep(currentStep);
                    binding.stepIndicator.setCurrentStep(currentStep);
                } else {
                    saveProfile();
                }
            }
        });

        binding.buttonBack.setOnClickListener(v -> {
            if (currentStep > 0) {
                currentStep--;
                showStep(currentStep);
                binding.stepIndicator.setCurrentStep(currentStep);
            }
        });

        binding.buttonSkip.setOnClickListener(v -> {
            preferenceManager.setOnboardingComplete(true);
            startMainActivity();
        });
    }

    private void showStep(int step) {
        binding.layoutPersonal.setVisibility(step == 0 ? View.VISIBLE : View.GONE);
        binding.layoutLifestyle.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        binding.layoutHome.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
        binding.layoutHabits.setVisibility(step == 3 ? View.VISIBLE : View.GONE);

        binding.buttonBack.setVisibility(step > 0 ? View.VISIBLE : View.GONE);
        binding.buttonNext.setText(step == 3 ? "Get Started" : "Next");
        binding.textStepTitle.setText(getStepTitle(step));
        binding.textStepSubtitle.setText(getStepSubtitle(step));
    }

    private String getStepTitle(int step) {
        switch (step) {
            case 0: return "About You";
            case 1: return "Your Transport";
            case 2: return "Home & Energy";
            case 3: return "Your Habits";
            default: return "";
        }
    }

    private String getStepSubtitle(int step) {
        switch (step) {
            case 0: return "Let's personalize your carbon tracking experience";
            case 1: return "Tell us about your daily commute";
            case 2: return "Help us understand your energy usage";
            case 3: return "Your diet and shopping preferences";
            default: return "";
        }
    }

    private boolean validateCurrentStep() {
        boolean valid = true;
        if (currentStep == 0) {
            if (binding.inputName.getText().toString().trim().isEmpty()) {
                binding.inputName.setError("Required");
                valid = false;
            }
            if (binding.inputCountry.getText().toString().trim().isEmpty()) {
                binding.inputCountry.setError("Required");
                valid = false;
            }
        }
        return valid;
    }

    private void saveProfile() {
        com.carbonwise.app.model.UserProfile profile = new com.carbonwise.app.model.UserProfile();
        profile.setUserId(java.util.UUID.randomUUID().toString());
        profile.setName(binding.inputName.getText().toString().trim());
        profile.setAgeGroup(binding.spinnerAge.getSelectedItem().toString());
        profile.setCountry(binding.inputCountry.getText().toString().trim());
        profile.setCity(binding.inputCity.getText().toString().trim());
        profile.setVehicleType(binding.spinnerVehicle.getSelectedItem().toString());

        String commuteStr = binding.inputCommute.getText().toString().trim();
        profile.setDailyCommuteKm(commuteStr.isEmpty() ? 0 : Double.parseDouble(commuteStr));

        String membersStr = binding.inputFamily.getText().toString().trim();
        profile.setFamilyMembers(membersStr.isEmpty() ? 1 : Integer.parseInt(membersStr));

        String electricityStr = binding.inputElectricity.getText().toString().trim();
        profile.setMonthlyElectricityKwh(electricityStr.isEmpty() ? 0 : Double.parseDouble(electricityStr));

        profile.setCookingFuelType(binding.inputFuel.getText().toString().trim());
        profile.setDietType(binding.spinnerDiet.getSelectedItem().toString());
        profile.setShoppingHabits(binding.spinnerShopping.getSelectedItem().toString());
        profile.setOnboardingComplete(true);

        viewModel.updateProfile(profile);
        preferenceManager.setUserId(profile.getUserId());
        preferenceManager.setOnboardingComplete(true);

        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
