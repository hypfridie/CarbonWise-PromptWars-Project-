package com.carbonwise.app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentEditProfileBinding;
import com.carbonwise.app.model.UserProfile;
import com.carbonwise.app.viewmodel.ProfileViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditProfileFragment extends Fragment {

    private FragmentEditProfileBinding binding;
    private ProfileViewModel viewModel;
    private UserProfile currentProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        viewModel.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                currentProfile = profile;
                binding.editName.setText(profile.getName());
                binding.editGoal.setText(String.valueOf(profile.getMonthlyCarbonGoal()));
            }
        });

        binding.buttonSaveProfile.setOnClickListener(v -> {
            if (currentProfile == null) return;
            
            String name = binding.editName.getText().toString().trim();
            String goalStr = binding.editGoal.getText().toString().trim();

            if (!name.isEmpty() && !goalStr.isEmpty()) {
                try {
                    double goal = Double.parseDouble(goalStr);
                    currentProfile.setName(name);
                    currentProfile.setMonthlyCarbonGoal(goal);
                    viewModel.updateProfile(currentProfile);
                    Toast.makeText(requireContext(), R.string.goal_updated, Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigateUp();
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), R.string.invalid_number, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
