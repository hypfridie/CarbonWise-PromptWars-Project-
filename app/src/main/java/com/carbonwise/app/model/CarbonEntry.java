package com.carbonwise.app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "carbon_entries")
public class CarbonEntry {

    @PrimaryKey
    @NonNull
    private String id;
    private String userId;
    private String category;
    private String subCategory;
    private double carbonValue;
    private String unit;
    private long timestamp;
    private String notes;
    private boolean synced;

    public CarbonEntry() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.synced = false;
    }

    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public double getCarbonValue() { return carbonValue; }
    public void setCarbonValue(double carbonValue) { this.carbonValue = carbonValue; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean isSynced() { return synced; }
    public void setSynced(boolean synced) { this.synced = synced; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarbonEntry that = (CarbonEntry) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
