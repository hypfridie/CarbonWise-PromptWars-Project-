package com.carbonwise.app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "carbon_goals")
public class CarbonGoal {

    @PrimaryKey
    @NonNull
    private String id;
    private String userId;
    private String title;
    private String description;
    private double targetReductionPercent;
    private double currentReductionPercent;
    private String category;
    private boolean active;
    private long startDate;
    private long targetDate;
    private long createdAt;

    public CarbonGoal() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.currentReductionPercent = 0.0;
        this.createdAt = System.currentTimeMillis();
    }

    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTargetReductionPercent() { return targetReductionPercent; }
    public void setTargetReductionPercent(double targetReductionPercent) { this.targetReductionPercent = targetReductionPercent; }

    public double getCurrentReductionPercent() { return currentReductionPercent; }
    public void setCurrentReductionPercent(double currentReductionPercent) { this.currentReductionPercent = currentReductionPercent; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public long getStartDate() { return startDate; }
    public void setStartDate(long startDate) { this.startDate = startDate; }

    public long getTargetDate() { return targetDate; }
    public void setTargetDate(long targetDate) { this.targetDate = targetDate; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarbonGoal that = (CarbonGoal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
