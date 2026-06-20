package com.carbonwise.app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "challenges")
public class Challenge {

    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String description;
    private String category;
    private int pointsReward;
    private boolean completed;
    private long assignedDate;
    private long completedDate;
    private int difficulty;

    public Challenge() {
        this.id = UUID.randomUUID().toString();
        this.assignedDate = System.currentTimeMillis();
        this.completed = false;
    }

    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getPointsReward() { return pointsReward; }
    public void setPointsReward(int pointsReward) { this.pointsReward = pointsReward; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public long getAssignedDate() { return assignedDate; }
    public void setAssignedDate(long assignedDate) { this.assignedDate = assignedDate; }

    public long getCompletedDate() { return completedDate; }
    public void setCompletedDate(long completedDate) { this.completedDate = completedDate; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
