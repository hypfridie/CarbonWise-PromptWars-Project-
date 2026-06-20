package com.carbonwise.app.util;

import com.carbonwise.app.model.UserProfile;

public class CarbonCalculator {

    private CarbonCalculator() {}

    // Emission factors (kg CO2 per unit)
    private static final double ELECTRICITY_FACTOR = 0.5;
    private static final double PETROL_CAR_FACTOR = 0.21;
    private static final double DIESEL_CAR_FACTOR = 0.17;
    private static final double HYBRID_CAR_FACTOR = 0.12;
    private static final double ELECTRIC_CAR_FACTOR = 0.05;
    private static final double PUBLIC_TRANSPORT_FACTOR = 0.04;
    private static final double FLIGHT_SHORT_FACTOR = 255.0;
    private static final double FLIGHT_LONG_FACTOR = 1020.0;

    // Qualitative factors (kg CO2)
    private static final double BEEF_MEAL = 6.0;
    private static final double CHICKEN_MEAL = 1.8;
    private static final double FISH_MEAL = 1.5;
    private static final double VEG_MEAL = 0.8;
    private static final double VEGAN_MEAL = 0.5;
    private static final double DAIRY_HIGH = 2.0;
    private static final double DAIRY_MED = 1.0;
    private static final double DAIRY_LOW = 0.4;

    private static final double ELECTRONIC_LARGE = 500.0;
    private static final double ELECTRONIC_MED = 100.0;
    private static final double ELECTRONIC_SMALL = 20.0;
    private static final double FASHION_ITEM = 15.0;
    
    private static final double WASTE_BAG_LARGE = 5.0;
    private static final double WASTE_BAG_MED = 2.0;
    private static final double WASTE_BAG_SMALL = 0.8;

    public static double calculateTransportEmissions(String vehicleType, double distanceKm) {
        if (vehicleType == null) return 0;
        switch (vehicleType.toLowerCase()) {
            case "petrol": return distanceKm * PETROL_CAR_FACTOR;
            case "diesel": return distanceKm * DIESEL_CAR_FACTOR;
            case "hybrid": return distanceKm * HYBRID_CAR_FACTOR;
            case "electric": return distanceKm * ELECTRIC_CAR_FACTOR;
            case "public transport": return distanceKm * PUBLIC_TRANSPORT_FACTOR;
            case "bicycle": return 0;
            default: return distanceKm * PETROL_CAR_FACTOR;
        }
    }

    public static double calculateElectricityEmissions(double kwh) {
        return kwh * ELECTRICITY_FACTOR;
    }

    public static double calculateFoodEmissions(String type, String servingSize) {
        double base = 0;
        if (type == null) return 0;
        switch (type.toLowerCase()) {
            case "beef": base = BEEF_MEAL; break;
            case "chicken": base = CHICKEN_MEAL; break;
            case "fish": base = FISH_MEAL; break;
            case "dairy": base = DAIRY_MED; break;
            case "vegetarian meal": base = VEG_MEAL; break;
            case "vegan meal": base = VEGAN_MEAL; break;
            default: base = VEG_MEAL;
        }

        if (servingSize == null) return base;
        switch (servingSize.toLowerCase()) {
            case "small": return base * 0.7;
            case "large": return base * 1.5;
            case "1 meal": return base;
            case "2 meals": return base * 2;
            case "3 meals": return base * 3;
            case "4+ meals": return base * 5;
            case "light": return DAIRY_LOW;
            case "moderate": return DAIRY_MED;
            case "heavy consumption": return DAIRY_HIGH;
            default: return base;
        }
    }

    public static double calculateShoppingEmissions(String type, String size, double value) {
        if (type == null) return 0;
        switch (type.toLowerCase()) {
            case "electronics":
                if (size != null) {
                    switch (size.toLowerCase()) {
                        case "smartphone": return ELECTRONIC_SMALL;
                        case "laptop": return ELECTRONIC_MED;
                        case "appliance": return ELECTRONIC_LARGE;
                        default: return ELECTRONIC_SMALL;
                    }
                }
                return ELECTRONIC_SMALL;
            case "fast fashion":
                if (size != null) {
                    if (size.contains("2-5")) return FASHION_ITEM * 3;
                    if (size.contains("5+")) return FASHION_ITEM * 7;
                }
                return FASHION_ITEM;
            default:
                // General or others based on value or size
                if (value > 5000 || (size != null && size.equalsIgnoreCase("large"))) return 20.0;
                if (value > 1000 || (size != null && size.equalsIgnoreCase("medium"))) return 8.0;
                return 3.0;
        }
    }

    public static double calculateWasteEmissions(String type, String size) {
        double base = 0;
        if (type == null) return 0;
        switch (type.toLowerCase()) {
            case "plastic": base = 1.2; break;
            case "food waste": base = 0.8; break;
            case "recycling": return -0.5; // Negative impact
            default: base = 0.5;
        }

        if (size == null) return base;
        switch (size.toLowerCase()) {
            case "small portion":
            case "small bag":
            case "low": return base * WASTE_BAG_SMALL;
            case "one meal":
            case "medium bag":
            case "moderate": return base * WASTE_BAG_MED;
            case "multiple meals":
            case "large bag":
            case "high": return base * WASTE_BAG_LARGE;
            default: return base * WASTE_BAG_MED;
        }
    }

    public static double estimateMonthlyCarbon(UserProfile profile) {
        if (profile == null) return 0;

        double transportEmissions = calculateTransportEmissions(
                profile.getVehicleType(),
                profile.getDailyCommuteKm() * 30
        );

        double electricityEmissions = calculateElectricityEmissions(profile.getMonthlyElectricityKwh());

        double dietMultiplier = getDietMultiplier(profile.getDietType());
        double baseFoodEmissions = 200 * dietMultiplier;

        double shoppingMultiplier = getShoppingMultiplier(profile.getShoppingHabits());
        double baseShoppingEmissions = 150 * shoppingMultiplier;

        return transportEmissions + electricityEmissions + baseFoodEmissions + baseShoppingEmissions;
    }

    public static double getDietMultiplier(String dietType) {
        if (dietType == null) return 1.0;
        switch (dietType.toLowerCase()) {
            case "vegetarian": return 0.6;
            case "eggetarian": return 0.75;
            case "mixed diet": return 1.0;
            case "non-vegetarian": return 1.4;
            default: return 1.0;
        }
    }

    public static double getShoppingMultiplier(String shoppingHabits) {
        if (shoppingHabits == null) return 1.0;
        switch (shoppingHabits.toLowerCase()) {
            case "low": return 0.5;
            case "moderate": return 1.0;
            case "high": return 1.8;
            default: return 1.0;
        }
    }

    public static String getCarbonHealthStatus(double monthlyEmissionsKg) {
        if (monthlyEmissionsKg < 300) return "Excellent";
        if (monthlyEmissionsKg < 600) return "Good";
        if (monthlyEmissionsKg < 1000) return "Average";
        return "Poor";
    }

    public static int getCarbonHealthColor(String status) {
        if (status == null) return 0xFFFF9F0A; // Default to Average/Orange if null
        switch (status) {
            case "Excellent": return 0xFF34C759; // emerald_green
            case "Good": return 0xFF007AFF;      // ios_blue
            case "Average": return 0xFFFF9F0A;   // orange
            default: return 0xFFFF453A;        // soft_red
        }
    }

    public static String getImpactLevel(double value, String category) {
        double low = 2.0;
        double med = 5.0;

        if ("Transport".equalsIgnoreCase(category)) {
            low = 5.0; med = 15.0;
        } else if ("Energy".equalsIgnoreCase(category)) {
            low = 10.0; med = 30.0;
        } else if ("Food".equalsIgnoreCase(category)) {
            low = 1.0; med = 3.0;
        } else if ("Shopping".equalsIgnoreCase(category)) {
            low = 5.0; med = 20.0;
        } else if ("Waste".equalsIgnoreCase(category)) {
            low = 1.0; med = 3.0;
        }

        if (value < low) return "Low";
        if (value < med) return "Moderate";
        return "High";
    }
}
