package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.repository.CarbonRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.CarbonEntry;
import com.carbonwise.app.model.UserProfile;
import com.carbonwise.app.util.CarbonCalculator;
import com.carbonwise.app.util.TimeUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TrackViewModel extends ViewModel {

    private final CarbonRepository carbonRepository;
    private final UserRepository userRepository;
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>("Transport");
    private final MutableLiveData<Long> selectedDate = new MutableLiveData<>(System.currentTimeMillis());

    @Inject
    public TrackViewModel(CarbonRepository carbonRepository, UserRepository userRepository) {
        this.carbonRepository = carbonRepository;
        this.userRepository = userRepository;
    }

    public LiveData<UserProfile> getUserProfile() {
        return userRepository.getFirstProfile();
    }

    public void setCategory(String category) {
        selectedCategory.setValue(category);
    }

    public void nextDay() {
        Long current = selectedDate.getValue();
        if (current != null) {
            selectedDate.setValue(current + 86400000L);
        }
    }

    public void previousDay() {
        Long current = selectedDate.getValue();
        if (current != null) {
            selectedDate.setValue(current - 86400000L);
        }
    }

    public LiveData<Long> getSelectedDate() {
        return selectedDate;
    }

    public LiveData<List<CarbonEntry>> getFilteredEntries() {
        return Transformations.switchMap(selectedDate, date -> {
            long startOfDay = TimeUtils.getStartOfDay(date);
            long endOfDay = TimeUtils.getEndOfDay(date);
            LiveData<List<CarbonEntry>> dateEntries = carbonRepository.getEntriesForDay(startOfDay, endOfDay);
            
            return Transformations.switchMap(selectedCategory, cat -> 
                Transformations.map(dateEntries, entries -> {
                    if (entries == null) return null;
                    return entries.stream()
                            .filter(e -> e.getCategory().equalsIgnoreCase(cat))
                            .collect(Collectors.toList());
                })
            );
        });
    }

    public void addTransportEntry(String subCategory, double distanceKm, String vehicleType) {
        CarbonEntry entry = new CarbonEntry();
        entry.setTimestamp(selectedDate.getValue() != null ? selectedDate.getValue() : System.currentTimeMillis());
        entry.setCategory("Transport");
        entry.setSubCategory(subCategory);
        double emissions = CarbonCalculator.calculateTransportEmissions(vehicleType, distanceKm);
        entry.setCarbonValue(emissions);
        entry.setUnit("kg CO2");
        entry.setNotes(distanceKm + " km via " + vehicleType);
        carbonRepository.insertEntry(entry);
    }

    public void addEnergyEntry(String subCategory, double kwh) {
        CarbonEntry entry = new CarbonEntry();
        entry.setTimestamp(selectedDate.getValue() != null ? selectedDate.getValue() : System.currentTimeMillis());
        entry.setCategory("Energy");
        entry.setSubCategory(subCategory);
        double emissions = CarbonCalculator.calculateElectricityEmissions(kwh);
        entry.setCarbonValue(emissions);
        entry.setUnit("kg CO2");
        entry.setNotes(kwh + " kWh");
        carbonRepository.insertEntry(entry);
    }

    public void addFoodEntry(String type, String servingSize) {
        CarbonEntry entry = new CarbonEntry();
        entry.setTimestamp(selectedDate.getValue() != null ? selectedDate.getValue() : System.currentTimeMillis());
        entry.setCategory("Food");
        entry.setSubCategory(type);
        double emissions = CarbonCalculator.calculateFoodEmissions(type, servingSize);
        entry.setCarbonValue(emissions);
        entry.setUnit("kg CO2");
        entry.setNotes(servingSize + " " + type);
        carbonRepository.insertEntry(entry);
    }

    public void addShoppingEntry(String type, String size, double value) {
        CarbonEntry entry = new CarbonEntry();
        entry.setTimestamp(selectedDate.getValue() != null ? selectedDate.getValue() : System.currentTimeMillis());
        entry.setCategory("Shopping");
        entry.setSubCategory(type);
        double emissions = CarbonCalculator.calculateShoppingEmissions(type, size, value);
        entry.setCarbonValue(emissions);
        entry.setUnit("kg CO2");
        entry.setNotes(size + " " + type + " (₹" + value + ")");
        carbonRepository.insertEntry(entry);
    }

    public void addWasteEntry(String type, String size) {
        CarbonEntry entry = new CarbonEntry();
        entry.setTimestamp(selectedDate.getValue() != null ? selectedDate.getValue() : System.currentTimeMillis());
        entry.setCategory("Waste");
        entry.setSubCategory(type);
        double emissions = CarbonCalculator.calculateWasteEmissions(type, size);
        entry.setCarbonValue(emissions);
        entry.setUnit("kg CO2");
        entry.setNotes(size + " " + type);
        carbonRepository.insertEntry(entry);
    }

    public void deleteEntry(CarbonEntry entry) {
        carbonRepository.deleteEntry(entry);
    }
}
