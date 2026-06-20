package com.carbonwise.app.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.carbonwise.app.BuildConfig;
import com.carbonwise.app.R;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.databinding.FragmentSettingsBinding;
import com.carbonwise.app.util.PreferenceManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    UserRepository userRepository;

    // ── Picker options ──────────────────────────────────────────────────
    private static final String[] EMISSION_UNITS = {"kg CO₂", "lbs CO₂", "tonnes CO₂"};
    private static final String[] LANGUAGES      = {
            "English", "Hindi", "Spanish", "French", "German", "Chinese", "Arabic"
    };

    // ── Lifecycle ───────────────────────────────────────────────────────

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        loadUserProfile();
        loadPreferences();
        setupListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // ── Setup ────────────────────────────────────────────────────────────

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v ->
                Navigation.findNavController(v).navigateUp());
    }

    private void loadUserProfile() {
        userRepository.getFirstProfile().observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                binding.textUserName.setText(profile.getName());
                // If you had an email in UserProfile, you'd set it here. 
                // For now keeping the placeholder or using a default.
                binding.textUserEmail.setText("carbonwise.user@example.com");
            }
        });

        // Version from BuildConfig — auto-updated by Gradle
        binding.textAppVersion.setText(BuildConfig.VERSION_NAME);
    }

    private void loadPreferences() {

        binding.switchNotifications.setChecked(preferenceManager.isNotificationsEnabled());
        binding.switchDarkMode.setChecked(preferenceManager.isDarkMode());


        binding.switchWeeklySummary.setChecked(true);
        binding.switchReminders.setChecked(false);
        binding.switchSync.setChecked(false);
        binding.textUnitsValue.setText("kg CO₂");
        binding.textLanguageValue.setText("English");
        binding.textGoalValue.setText("120 kg");
    }

    private void setupListeners() {
        setupProfileListeners();
        setupPreferenceListeners();
        setupCarbonTrackingListeners();
        setupPrivacyDataListeners();
        setupAboutListeners();
    }

    // ── Listener Groups ─────────────────────────────────────────────────

    private void setupProfileListeners() {
        binding.cardProfile.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_settings_to_edit));
    }

    private void setupPreferenceListeners() {
        binding.switchNotifications.setOnCheckedChangeListener((btn, checked) ->
                preferenceManager.setNotificationsEnabled(checked));

        binding.switchDarkMode.setOnCheckedChangeListener((btn, checked) -> {
            preferenceManager.setDarkMode(checked);

        });

        binding.rowUnits.setOnClickListener(v -> showUnitsPicker());
        binding.rowLanguage.setOnClickListener(v -> showLanguagePicker());
    }

    private void setupCarbonTrackingListeners() {
        binding.switchWeeklySummary.setOnCheckedChangeListener((btn, checked) -> {

            showToast(checked ? "Weekly summary on" : "Weekly summary off");
        });

        binding.switchReminders.setOnCheckedChangeListener((btn, checked) -> {

            if (checked) {

                showToast("Reminders on — set up notifications in your WorkManager task");
            }
        });

        binding.rowCarbonGoal.setOnClickListener(v -> showCarbonGoalDialog());
    }

    private void setupPrivacyDataListeners() {
        binding.switchSync.setOnCheckedChangeListener((btn, checked) -> {

            showToast(checked ? "Cloud sync enabled" : "Cloud sync disabled");

        });

        binding.rowExportData.setOnClickListener(v -> exportData());
        binding.rowClearData.setOnClickListener(v -> confirmClearData());
    }

    private void setupAboutListeners() {
        binding.rowPrivacyPolicy.setOnClickListener(v ->
                openUrl("https://carbonwise.app/privacy")); // TODO: Replace with your URL

        binding.rowTerms.setOnClickListener(v ->
                openUrl("https://carbonwise.app/terms"));   // TODO: Replace with your URL

        binding.rowRateApp.setOnClickListener(v -> rateApp());
        binding.rowShareApp.setOnClickListener(v -> shareApp());
    }

    // ── Pickers ─────────────────────────────────────────────────────────

    private void showUnitsPicker() {
        String current = binding.textUnitsValue.getText().toString();
        int currentIndex = 0;
        for (int i = 0; i < EMISSION_UNITS.length; i++) {
            if (EMISSION_UNITS[i].equals(current)) { currentIndex = i; break; }
        }
        final int[] selected = {currentIndex};

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Emission Units")
                .setSingleChoiceItems(EMISSION_UNITS, currentIndex,
                        (dialog, which) -> selected[0] = which)
                .setPositiveButton("Save", (dialog, which) -> {
                    String unit = EMISSION_UNITS[selected[0]];
                    binding.textUnitsValue.setText(unit);
                    // preferenceManager.setEmissionUnit(unit);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showLanguagePicker() {
        String current = binding.textLanguageValue.getText().toString();
        int currentIndex = 0;
        for (int i = 0; i < LANGUAGES.length; i++) {
            if (LANGUAGES[i].equals(current)) { currentIndex = i; break; }
        }
        final int[] selected = {currentIndex};

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Language")
                .setSingleChoiceItems(LANGUAGES, currentIndex,
                        (dialog, which) -> selected[0] = which)
                .setPositiveButton("Apply", (dialog, which) -> {
                    String lang = LANGUAGES[selected[0]];
                    binding.textLanguageValue.setText(lang);
                    // preferenceManager.setLanguage(lang);
                    // For full locale switching use:
                    // AppCompatDelegate.setApplicationLocales(
                    //     LocaleListCompat.forLanguageTags(toLanguageTag(lang)));
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // ── Dialogs ─────────────────────────────────────────────────────────

    private void showCarbonGoalDialog() {
        // Parse current value, stripping the " kg" suffix
        String raw = binding.textGoalValue.getText().toString()
                .replace(" kg", "").trim();

        EditText input = new EditText(requireContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("kg CO₂ / month");
        input.setText(raw);
        input.setSelection(raw.length()); // cursor at end
        
        // Pad the input field so it doesn't press against dialog edges
        int dp20 = (int) (20 * getResources().getDisplayMetrics().density);
        input.setPadding(dp20, dp20, dp20, dp20);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Monthly CO₂ Goal")
                .setMessage("How many kg of CO₂ do you aim to emit per month?")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String val = input.getText().toString().trim();
                    if (!val.isEmpty()) {
                        try {
                            int goal = Integer.parseInt(val);
                            binding.textGoalValue.setText(goal + " kg");
                            // preferenceManager.setCarbonGoal(goal);
                        } catch (NumberFormatException e) {
                            showToast("Enter a valid number");
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void confirmClearData() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Clear All Data?")
                .setMessage("This permanently erases all tracked emissions, goals, and history. " +
                        "This action cannot be undone.")
                .setPositiveButton("Clear Everything", (dialog, which) -> {
                    preferenceManager.clearAll();
                    // TODO: Also wipe your Room database here, e.g.:
                    // AppDatabase.getInstance(requireContext()).clearAllTables();
                    showToast("All data cleared");
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // ── Actions ──────────────────────────────────────────────────────────

    /**
     * Export the user's footprint data as a CSV/JSON file.
     * TODO: Query your Room DAO, serialise to CSV, write to Downloads via
     *       MediaStore or FileProvider, then open a share sheet.
     */
    private void exportData() {
        showToast("Preparing your data export…");

    }

    private void openUrl(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            showToast("No browser app found");
        }
    }

    private void rateApp() {
        String pkg = requireContext().getPackageName();
        try {
            // Opens Play Store app if installed
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + pkg)));
        } catch (Exception e) {
            // Fall back to browser
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + pkg)));
        }
    }

    private void shareApp() {
        String pkg = requireContext().getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Track your carbon footprint — CarbonWise");
        intent.putExtra(Intent.EXTRA_TEXT,
                "I've been tracking my carbon footprint with CarbonWise! 🌿\n" +
                        "Check it out: https://play.google.com/store/apps/details?id=" + pkg);
        startActivity(Intent.createChooser(intent, "Share CarbonWise"));
    }

    // ── Helpers ──────────────────────────────────────────────────────────

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}