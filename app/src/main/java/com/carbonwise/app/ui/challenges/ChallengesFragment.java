package com.carbonwise.app.ui.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.FragmentChallengesBinding;
import com.carbonwise.app.model.Achievement;
import com.carbonwise.app.model.Challenge;
import com.carbonwise.app.ui.challenges.adapter.AchievementListAdapter;
import com.carbonwise.app.ui.challenges.adapter.ChallengeListAdapter;
import com.carbonwise.app.viewmodel.ChallengesViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChallengesFragment extends Fragment {

    private FragmentChallengesBinding binding;
    private ChallengesViewModel viewModel;
    private ChallengeListAdapter challengeAdapter;
    private AchievementListAdapter achievementAdapter;
    
    private List<Challenge> activeChallenges = new ArrayList<>();
    private List<Challenge> completedChallenges = new ArrayList<>();
    private List<Achievement> achievements = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChallengesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentChallengesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupTabs();
        observeViewModel();
        handleInitialTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListForCurrentTab();
    }

    private void updateListForCurrentTab() {
        int position = binding.tabLayout.getSelectedTabPosition();
        if (position == 2) {
            binding.recyclerChallenges.setAdapter(achievementAdapter);
            achievementAdapter.submitList(achievements);
            updateEmptyState(achievements == null || achievements.isEmpty(), R.string.no_challenges_here);
        } else if (position == 1) {
            binding.recyclerChallenges.setAdapter(challengeAdapter);
            challengeAdapter.submitList(completedChallenges);
            updateEmptyState(completedChallenges == null || completedChallenges.isEmpty(), R.string.no_challenges_here);
        } else {
            binding.recyclerChallenges.setAdapter(challengeAdapter);
            challengeAdapter.submitList(activeChallenges);
            updateEmptyState(activeChallenges == null || activeChallenges.isEmpty(), R.string.no_challenges);
        }
    }

    private void updateEmptyState(boolean isEmpty, int stringRes) {
        binding.textEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        binding.textEmpty.setText(stringRes);
    }

    private void handleInitialTab() {
        if (getArguments() != null) {
            int initialTab = getArguments().getInt("initialTab", 0);
            if (initialTab >= 0 && initialTab < binding.tabLayout.getTabCount()) {
                TabLayout.Tab tab = binding.tabLayout.getTabAt(initialTab);
                if (tab != null) {
                    tab.select();
                    updateListForCurrentTab();
                }
            } else {
                updateListForCurrentTab();
            }
        } else {
            updateListForCurrentTab();
        }
    }

    private void setupRecyclerView() {
        challengeAdapter = new ChallengeListAdapter(challenge -> {
            viewModel.completeChallenge(challenge);
            Toast.makeText(requireContext(),
                    getString(R.string.challenge_completed, challenge.getPointsReward()),
                    Toast.LENGTH_SHORT).show();
        });
        achievementAdapter = new AchievementListAdapter();
        binding.recyclerChallenges.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerChallenges.setAdapter(challengeAdapter);
    }

    private void setupTabs() {
        binding.tabLayout.removeAllTabs();
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.active));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.completed));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.achievements));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                updateListForCurrentTab();
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void observeViewModel() {
        // Observe Profile for points
        viewModel.getUserProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                binding.textPoints.setText(String.format(java.util.Locale.getDefault(), "%d", profile.getGreenPoints()));
                binding.textLevel.setText(profile.getCurrentLevel());
            }
        });

        // Observe Data Lists
        viewModel.getActiveChallenges().observe(getViewLifecycleOwner(), list -> {
            activeChallenges = list;
            if (binding.tabLayout.getSelectedTabPosition() == 0) {
                challengeAdapter.submitList(list);
                updateEmptyState(list == null || list.isEmpty(), R.string.no_challenges);
            }
        });

        viewModel.getCompletedChallenges().observe(getViewLifecycleOwner(), list -> {
            completedChallenges = list;
            if (binding.tabLayout.getSelectedTabPosition() == 1) {
                challengeAdapter.submitList(list);
                updateEmptyState(list == null || list.isEmpty(), R.string.no_challenges_here);
            }
        });

        viewModel.getAchievements().observe(getViewLifecycleOwner(), list -> {
            achievements = list;
            if (binding.tabLayout.getSelectedTabPosition() == 2) {
                achievementAdapter.submitList(list);
                updateEmptyState(list == null || list.isEmpty(), R.string.no_challenges_here);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
