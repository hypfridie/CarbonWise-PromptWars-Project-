package com.carbonwise.app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentProfileBinding;
import com.carbonwise.app.model.CarbonGoal;
import com.carbonwise.app.model.UserProfile;
import com.carbonwise.app.ui.profile.adapter.GoalAdapter;
import com.carbonwise.app.viewmodel.ProfileViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    private GoalAdapter goalAdapter;
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
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupGoalsRecycler();
        setupButtons();
        observeViewModel();
    }

    private void setupGoalsRecycler() {
        goalAdapter = new GoalAdapter(goal -> {
            viewModel.deactivateGoal(goal.getId());
            Toast.makeText(requireContext(), R.string.goal_removed, Toast.LENGTH_SHORT).show();
        });
        binding.recyclerGoals.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerGoals.setAdapter(goalAdapter);
    }

    private void setupButtons() {
        binding.buttonAddGoal.setOnClickListener(v -> showAddGoalDialog());
        binding.buttonEditGoal.setOnClickListener(v -> showEditGoalDialog());
        binding.cardSettings.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_profile_to_settings));
        binding.cardAchievements.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt("initialTab", 2);
            Navigation.findNavController(v).navigate(R.id.navigation_challenges, args);
        });
    }

    private void showAddGoalDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(R.string.add_carbon_goal);

        View view = getLayoutInflater().inflate(R.layout.dialog_add_goal, null);
        EditText inputTitle = view.findViewById(R.id.input_goal_title);
        EditText inputTarget = view.findViewById(R.id.input_goal_target);

        builder.setView(view);
        builder.setPositiveButton(R.string.save, (dialog, which) -> {
            String title = inputTitle.getText().toString().trim();
            String targetStr = inputTarget.getText().toString().trim();
            if (!title.isEmpty() && !targetStr.isEmpty()) {
                try {
                    double target = Double.parseDouble(targetStr);
                    CarbonGoal goal = new CarbonGoal();
                    goal.setTitle(title);
                    goal.setTargetReductionPercent(target);
                    goal.setCategory("General");
                    goal.setStartDate(System.currentTimeMillis());
                    goal.setTargetDate(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
                    viewModel.addGoal(goal);
                    Toast.makeText(requireContext(), R.string.goal_added, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), R.string.invalid_number, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    private void showEditGoalDialog() {
        if (currentProfile == null) return;
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(R.string.edit_monthly_goal);

        EditText input = new EditText(requireContext());
        input.setText(String.valueOf(currentProfile.getMonthlyCarbonGoal()));
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        
        // Pad the input field so it doesn't press against dialog edges
        int dp20 = (int) (20 * getResources().getDisplayMetrics().density);
        input.setPadding(dp20, dp20, dp20, dp20);

        builder.setView(input);

        builder.setPositiveButton(R.string.save, (dialog, which) -> {
            String value = input.getText().toString().trim();
            if (!value.isEmpty()) {
                try {
                    double goal = Double.parseDouble(value);
                    viewModel.updateCarbonGoal(goal);
                    Toast.makeText(requireContext(), R.string.goal_updated, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), R.string.invalid_number, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    private void observeViewModel() {
        viewModel.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                currentProfile = profile;
                binding.textName.setText(profile.getName());
                binding.textLevel.setText(profile.getCurrentLevel());
                binding.textPoints.setText(String.valueOf(profile.getGreenPoints()));
                binding.textStreak.setText(getString(R.string.streak_days, profile.getCurrentStreak()));
                binding.textMonthlyGoal.setText(getString(R.string.goal_kg, profile.getMonthlyCarbonGoal()));


            }
        });

        viewModel.getActiveGoals().observe(getViewLifecycleOwner(), goals -> {
            goalAdapter.submitList(goals);
            binding.textNoGoals.setVisibility(goals == null || goals.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
