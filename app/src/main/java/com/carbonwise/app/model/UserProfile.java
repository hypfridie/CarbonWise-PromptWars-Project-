package com.carbonwise.app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "user_profile")
public class UserProfile {

    @PrimaryKey
    @NonNull
    private String userId;
    private String name;
    private String ageGroup;
    private String country;
    private String city;
    private String vehicleType;
    private double dailyCommuteKm;
    private int familyMembers;
    private double monthlyElectricityKwh;
    private String cookingFuelType;
    private String dietType;
    private String shoppingHabits;
    private int greenPoints;
    private String currentLevel;
    private int currentStreak;
    private int longestStreak;
    private double monthlyCarbonGoal;
    private boolean onboardingComplete;
    private long createdAt;
    private long updatedAt;

    public UserProfile() {
        this.currentLevel = "Seedling";
        this.greenPoints = 0;
        this.currentStreak = 0;
        this.longestStreak = 0;
        this.monthlyCarbonGoal = 100.0;
        this.onboardingComplete = false;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    @NonNull
    public String getUserId() { return userId; }
    public void setUserId(@NonNull String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public double getDailyCommuteKm() { return dailyCommuteKm; }
    public void setDailyCommuteKm(double dailyCommuteKm) { this.dailyCommuteKm = dailyCommuteKm; }

    public int getFamilyMembers() { return familyMembers; }
    public void setFamilyMembers(int familyMembers) { this.familyMembers = familyMembers; }

    public double getMonthlyElectricityKwh() { return monthlyElectricityKwh; }
    public void setMonthlyElectricityKwh(double monthlyElectricityKwh) { this.monthlyElectricityKwh = monthlyElectricityKwh; }

    public String getCookingFuelType() { return cookingFuelType; }
    public void setCookingFuelType(String cookingFuelType) { this.cookingFuelType = cookingFuelType; }

    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }

    public String getShoppingHabits() { return shoppingHabits; }
    public void setShoppingHabits(String shoppingHabits) { this.shoppingHabits = shoppingHabits; }

    public int getGreenPoints() { return greenPoints; }
    public void setGreenPoints(int greenPoints) { this.greenPoints = greenPoints; }

    public String getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(String currentLevel) { this.currentLevel = currentLevel; }

    public int getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(int currentStreak) { this.currentStreak = currentStreak; }

    public int getLongestStreak() { return longestStreak; }
    public void setLongestStreak(int longestStreak) { this.longestStreak = longestStreak; }

    public double getMonthlyCarbonGoal() { return monthlyCarbonGoal; }
    public void setMonthlyCarbonGoal(double monthlyCarbonGoal) { this.monthlyCarbonGoal = monthlyCarbonGoal; }

    public boolean isOnboardingComplete() { return onboardingComplete; }
    public void setOnboardingComplete(boolean onboardingComplete) { this.onboardingComplete = onboardingComplete; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(long updatedAt) { this.updatedAt = updatedAt; }

    public void updateLevel() {
        if (greenPoints >= 5000) {
            currentLevel = "Earth Champion";
        } else if (greenPoints >= 2500) {
            currentLevel = "Forest Guardian";
        } else if (greenPoints >= 1000) {
            currentLevel = "Tree";
        } else if (greenPoints >= 300) {
            currentLevel = "Sapling";
        } else {
            currentLevel = "Seedling";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
