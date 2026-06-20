package com.carbonwise.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.carbonwise.app.R;
import com.carbonwise.app.databinding.ActivityMainBinding;
import com.carbonwise.app.ui.chat.AiChatActivity;
import com.carbonwise.app.ui.onboarding.OnboardingActivity;
import com.carbonwise.app.util.PreferenceManager;
import com.carbonwise.app.viewmodel.HomeViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Inject
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!preferenceManager.isOnboardingComplete()) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
            return;
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupNavigation();
        setupFab();
    }

    private void setupNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        binding.navHome.setOnClickListener(v -> navController.navigate(R.id.navigation_home));
        binding.navTrack.setOnClickListener(v -> navController.navigate(R.id.navigation_track));
        binding.navChallenges.setOnClickListener(v -> navController.navigate(R.id.navigation_challenges));
        binding.navAnalytics.setOnClickListener(v -> navController.navigate(R.id.navigation_analytics));
        binding.navProfile.setOnClickListener(v -> navController.navigate(R.id.navigation_profile));

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();
            updateCustomBottomNav(id);
            if (id == R.id.navigation_home || id == R.id.navigation_track ||
                id == R.id.navigation_challenges || id == R.id.navigation_analytics ||
                id == R.id.navigation_profile) {
                binding.bottomNavContainer.setVisibility(View.VISIBLE);
                binding.fabChat.setVisibility(View.VISIBLE);
                binding.navHostFragment.setPadding(0, 0, 0, dpToPx(100));
            } else {
                binding.bottomNavContainer.setVisibility(View.GONE);
                binding.fabChat.setVisibility(View.GONE);
                binding.navHostFragment.setPadding(0, 0, 0, 0);
            }
        });
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private void updateCustomBottomNav(int activeId) {
        // Reset all
        resetNavItem(binding.iconHome, binding.textHome);
        resetNavItem(binding.iconTrack, binding.textTrack);
        resetNavItem2(binding.iconChallenges, binding.textChallenges);
        resetNavItem(binding.iconAnalytics, binding.textAnalytics);
        resetNavItem(binding.iconProfile, binding.textProfile);
        binding.containerChallengesIcon.setBackground(null);

        // Highlight active
        if (activeId == R.id.navigation_home) highlightItem(binding.iconHome, binding.textHome);
        else if (activeId == R.id.navigation_track) highlightItem(binding.iconTrack, binding.textTrack);
        else if (activeId == R.id.navigation_challenges) {
            highlightItem(binding.iconChallenges, binding.textChallenges);
            binding.containerChallengesIcon.setBackgroundResource(R.drawable.bg_nav_indicator_gradient);
            binding.iconChallenges.setImageTintList(ContextCompat.getColorStateList(this, R.color.white));
            binding.textChallenges.setTextColor(ContextCompat.getColor(this, R.color.ios_blue));
        }
        else if (activeId == R.id.navigation_analytics) highlightItem(binding.iconAnalytics, binding.textAnalytics);
        else if (activeId == R.id.navigation_profile) highlightItem(binding.iconProfile, binding.textProfile);
    }

    private void resetNavItem(android.widget.ImageView icon, android.widget.TextView text) {
        icon.setImageTintList(ContextCompat.getColorStateList(this, R.color.text_secondary));
        text.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
    }

    private void resetNavItem2(android.widget.ImageView icon, android.widget.TextView text) {
        icon.setImageTintList(ContextCompat.getColorStateList(this, R.color.emerald_green));
        text.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
    }

    private void highlightItem(android.widget.ImageView icon, android.widget.TextView text) {
        icon.setImageTintList(ContextCompat.getColorStateList(this, R.color.ios_blue));
        text.setTextColor(ContextCompat.getColor(this, R.color.ios_blue));
    }

    private void setupFab() {
        binding.fabChat.setOnClickListener(v -> {
            Intent intent = new Intent(this, AiChatActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController != null ? navController.navigateUp() || super.onSupportNavigateUp()
                : super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
