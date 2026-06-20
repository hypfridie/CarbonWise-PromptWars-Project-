package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.repository.GoalRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.CarbonGoal;
import com.carbonwise.app.model.UserProfile;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final GoalRepository goalRepository;

    @Inject
    public ProfileViewModel(UserRepository userRepository, GoalRepository goalRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
    }

    public LiveData<UserProfile> getUserProfile() {
        return userRepository.getFirstProfile();
    }

    public LiveData<List<CarbonGoal>> getActiveGoals() {
        return goalRepository.getActiveGoals();
    }

    public void updateProfile(UserProfile profile) {
        profile.setUpdatedAt(System.currentTimeMillis());
        userRepository.saveProfile(profile);
    }

    public void addGoal(CarbonGoal goal) {
        goalRepository.insertGoal(goal);
    }

    public void deactivateGoal(String goalId) {
        goalRepository.deactivateGoal(goalId);
    }

    public void updateGoalProgress(String goalId, double percent) {
        goalRepository.updateProgress(goalId, percent);
    }

    public void updateCarbonGoal(double goal) {
        UserProfile profile = userRepository.getFirstProfileSync();
        if (profile != null) {
            profile.setMonthlyCarbonGoal(goal);
            profile.setUpdatedAt(System.currentTimeMillis());
            userRepository.updateProfile(profile);
        }
    }
}
