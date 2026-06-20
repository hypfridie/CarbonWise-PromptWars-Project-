package com.carbonwise.app.data.repository;

import androidx.lifecycle.LiveData;

import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.model.Challenge;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ChallengeRepository {

    private final ChallengeDao challengeDao;
    private final ExecutorService executor;

    @Inject
    public ChallengeRepository(ChallengeDao challengeDao) {
        this.challengeDao = challengeDao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Challenge>> getAllChallenges() {
        return challengeDao.getAllChallenges();
    }

    public LiveData<List<Challenge>> getActiveChallenges() {
        return challengeDao.getActiveChallenges();
    }

    public LiveData<List<Challenge>> getCompletedChallenges() {
        return challengeDao.getCompletedChallenges();
    }

    public void insertChallenge(Challenge challenge) {
        executor.execute(() -> challengeDao.insert(challenge));
    }

    public void insertChallenges(List<Challenge> challenges) {
        executor.execute(() -> challengeDao.insertAll(challenges));
    }

    public void completeChallenge(Challenge challenge) {
        challenge.setCompleted(true);
        challenge.setCompletedDate(System.currentTimeMillis());
        executor.execute(() -> challengeDao.update(challenge));
    }

    public void updateChallenge(Challenge challenge) {
        executor.execute(() -> challengeDao.update(challenge));
    }
}
