package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.repository.AchievementRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.Achievement;
import com.carbonwise.app.model.Challenge;
import com.carbonwise.app.model.UserProfile;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ChallengesViewModel extends ViewModel {

    private final ChallengeRepository challengeRepository;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @Inject
    public ChallengesViewModel(ChallengeRepository challengeRepository,
                               AchievementRepository achievementRepository,
                               UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
    }

    public LiveData<UserProfile> getUserProfile() {
        return userRepository.getFirstProfile();
    }

    public LiveData<List<Challenge>> getActiveChallenges() {
        return challengeRepository.getActiveChallenges();
    }

    public LiveData<List<Challenge>> getCompletedChallenges() {
        return challengeRepository.getCompletedChallenges();
    }

    public void completeChallenge(Challenge challenge) {
        challengeRepository.completeChallenge(challenge);
        userRepository.addPoints(challenge.getPointsReward());
        // Streak is recalculated inside repository method now
    }

    public LiveData<List<Achievement>> getAchievements() {
        return achievementRepository.getAllAchievements();
    }

    public LiveData<List<Achievement>> getUnlockedAchievements() {
        return achievementRepository.getUnlockedAchievements();
    }
}
