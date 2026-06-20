package com.carbonwise.app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "achievements")
public class Achievement {

    @PrimaryKey
    @NonNull
    private String id;
    private String title;
    private String description;
    private String iconName;
    private int pointsRequired;
    private boolean unlocked;
    private long unlockedDate;
    private String category;

    public Achievement() {
        this.unlocked = false;
    }

    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIconName() { return iconName; }
    public void setIconName(String iconName) { this.iconName = iconName; }

    public int getPointsRequired() { return pointsRequired; }
    public void setPointsRequired(int pointsRequired) { this.pointsRequired = pointsRequired; }

    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }

    public long getUnlockedDate() { return unlockedDate; }
    public void setUnlockedDate(long unlockedDate) { this.unlockedDate = unlockedDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
