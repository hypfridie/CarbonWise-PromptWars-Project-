package com.carbonwise.app.model;

public class DashboardSummary {

    private double todayCarbonKg;
    private double weeklyTotalKg;
    private double weeklyChangePercent;
    private double monthlyTotalKg;
    private String carbonHealthStatus = "Average";
    private int currentStreak;
    private int greenPoints;
    private String currentLevel = "Seedling";
    private double goalProgressPercent;
    private double transportPercent;
    private double energyPercent;
    private double foodPercent;
    private double shoppingPercent;
    private double wastePercent;

    public DashboardSummary() {}

    public double getTodayCarbonKg() { return todayCarbonKg; }
    public void setTodayCarbonKg(double todayCarbonKg) { this.todayCarbonKg = todayCarbonKg; }

    public double getWeeklyTotalKg() { return weeklyTotalKg; }
    public void setWeeklyTotalKg(double weeklyTotalKg) { this.weeklyTotalKg = weeklyTotalKg; }

    public double getWeeklyChangePercent() { return weeklyChangePercent; }
    public void setWeeklyChangePercent(double weeklyChangePercent) { this.weeklyChangePercent = weeklyChangePercent; }

    public double getMonthlyTotalKg() { return monthlyTotalKg; }
    public void setMonthlyTotalKg(double monthlyTotalKg) { this.monthlyTotalKg = monthlyTotalKg; }

    public String getCarbonHealthStatus() { return carbonHealthStatus; }
    public void setCarbonHealthStatus(String carbonHealthStatus) { this.carbonHealthStatus = carbonHealthStatus; }

    public int getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(int currentStreak) { this.currentStreak = currentStreak; }

    public int getGreenPoints() { return greenPoints; }
    public void setGreenPoints(int greenPoints) { this.greenPoints = greenPoints; }

    public String getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(String currentLevel) { this.currentLevel = currentLevel; }

    public double getGoalProgressPercent() { return goalProgressPercent; }
    public void setGoalProgressPercent(double goalProgressPercent) { this.goalProgressPercent = goalProgressPercent; }

    public double getTransportPercent() { return transportPercent; }
    public void setTransportPercent(double transportPercent) { this.transportPercent = transportPercent; }

    public double getEnergyPercent() { return energyPercent; }
    public void setEnergyPercent(double energyPercent) { this.energyPercent = energyPercent; }

    public double getFoodPercent() { return foodPercent; }
    public void setFoodPercent(double foodPercent) { this.foodPercent = foodPercent; }

    public double getShoppingPercent() { return shoppingPercent; }
    public void setShoppingPercent(double shoppingPercent) { this.shoppingPercent = shoppingPercent; }

    public double getWastePercent() { return wastePercent; }
    public void setWastePercent(double wastePercent) { this.wastePercent = wastePercent; }
}
