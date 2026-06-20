# CarbonWise 🌿

<div align="center">
  <h3>🌍 Track • Reduce • Sustain</h3>
  <p><strong>A premium AI-powered carbon footprint tracker for Android — understand your impact, change your habits</strong></p>
</div>

---

## 📱 Overview

CarbonWise is a comprehensive personal carbon footprint tracker built for Android that transforms complex environmental data into simple, actionable insights. From daily commutes to food habits and energy usage, CarbonWise converts your everyday actions into measurable CO₂ impact — and then helps you reduce it through AI-powered guidance, eco challenges, and visual progress tracking.

**Why CarbonWise?**
- 🤖 **AI-Powered Insights** — Gemini AI gives personalized sustainability advice based on your actual tracked data
- 📊 **Full Footprint Picture** — Transportation, energy, food, shopping, and waste — all in one place
- 🏆 **Gamified Sustainability** — Green Points, streaks, achievements, and daily eco challenges keep you motivated
- 🎨 **Premium Dark Interface** — Deep glassmorphism design with emerald green accents and Apple-inspired layouts

---

## ✨ Features

### 🚀 Smart Onboarding
CarbonWise builds your personalized carbon profile during setup:

- **Personal Info** — Age group, country, and city for region-accurate emission benchmarks
- **Vehicle & Commute** — Vehicle type (Petrol / Diesel / Hybrid / Electric / Public Transport / Bicycle) and daily travel distance
- **Home Info** — Number of family members, monthly electricity consumption, and cooking fuel type
- **Food Habits** — Vegetarian / Eggetarian / Mixed / Non-Vegetarian diet classification
- **Shopping Habits** — Low / Moderate / High consumption profile

### 🧮 Carbon Footprint Calculator
Estimates your emissions across five major lifestyle categories:

| Category | What's Tracked |
|----------|---------------|
| 🚗 **Transportation** | Car, bike, flights, public transport, ride-sharing |
| ⚡ **Energy** | Electricity, air conditioning, water heater |
| 🍽️ **Food** | Meat, dairy, local vs. imported food |
| 🛍️ **Shopping** | Online purchases, fast fashion, electronics |
| ♻️ **Waste** | Recycling habits, plastic usage, food waste |

### 🏠 Dashboard
- **Daily Carbon Score** — At-a-glance CO₂ impact for the current day (e.g. *"3.8 kg CO₂"*)
- **Weekly Summary** — Total emissions with percentage change vs. previous week
- **Monthly Trends** — Interactive graphs showing your history and sustainability improvements
- **Carbon Health Indicator** — Four-tier rating: Excellent / Good / Average / Poor
- **Dark Glass Widgets** — Beautiful home-screen widgets for carbon score, goal progress, and daily challenge

### 🤖 AI Sustainability Assistant
Powered by the **Gemini API**, the in-app assistant answers questions like:

- *"How can I reduce my emissions this week?"*
- *"Is public transport better than driving for my commute?"*
- *"How much CO₂ does a flight to Delhi produce?"*
- *"Suggest eco-friendly alternatives to my current habits"*

Responses are personalized using your tracked activity data and carbon profile — not generic advice.

### 💡 Personalized Recommendations
CarbonWise generates specific, data-driven suggestions such as:

- 🚌 *"Use public transport twice a week to reduce emissions by ~8 kg CO₂ per month."*
- 💡 *"Switching to LED bulbs could reduce your electricity consumption by 15%."*
- 🥩 *"Reducing red meat by one meal per week could meaningfully lower your footprint."*

### 🌱 Daily Eco Challenges
A fresh set of micro-challenges every day:

- Avoid single-use plastic today
- Walk instead of driving for short trips
- Carry a reusable bottle
- Switch off unused appliances before bed

Completing challenges earns **Green Points** and advances your streak.

### 🏅 Green Points & Levels
Earn points by logging activities, completing challenges, and hitting reduction goals. Progress through five tiers:

| Level | Title |
|-------|-------|
| 🌱 | **Seedling** |
| 🌿 | **Sapling** |
| 🌳 | **Tree** |
| 🌲 | **Forest Guardian** |
| 🌍 | **Earth Champion** |

Unlock achievement badges: **Carbon Saver**, **Eco Hero**, and **Green Warrior**.

### 🎯 Carbon Reduction Goals
Set and track personal sustainability targets:

- Reduce total emissions by 10% this month
- Use public transport 3 days per week
- Lower electricity usage by a set amount

Goals are tracked with visual progress indicators and milestone notifications.

### 📊 Analytics
Rich visual analytics across all emission categories:

- **Emission Sources** — Pie chart breakdown: Transport / Energy / Food / Shopping / Waste
- **Trend Analysis** — Line graph of daily, weekly, and monthly emissions over time
- **Progress Chart** — Reduction percentage tracked across your journey
- **Streak Tracking** — 7-day and 30-day consistency streaks

### 📚 Sustainability Knowledge Hub
Educational content organized into five categories:

- 🌡️ Climate Change
- ☀️ Renewable Energy
- ♻️ Recycling
- 🚲 Green Transportation
- 🛒 Sustainable Living

Presented as short, modern cards with illustrations — quick reads, real knowledge.

### 🌳 Carbon Offset Suggestions
When reduction isn't enough, CarbonWise suggests ways to offset:

- Tree planting programs
- Renewable energy initiatives
- Community sustainability projects

Each suggestion includes an estimated offset potential in kg CO₂.

---

## 🛠️ Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Kotlin |
| **UI Framework** | Jetpack Compose + Material 3 |
| **Architecture** | MVVM (Model-View-ViewModel) |
| **Dependency Injection** | Hilt |
| **Database** | Room Persistence Library |
| **Networking** | Retrofit |
| **Charts** | MPAndroidChart |
| **AI Assistant** | Gemini API |
| **Local Storage** | DataStore Preferences |
| **Authentication** | Firebase Authentication |
| **Backend & Database** | Firebase Firestore |
| **Push Notifications** | Firebase Cloud Messaging |
| **Min SDK** | Android 8.0 (API 26) |

---

## 🎨 Design System

CarbonWise uses an ultra-modern premium design language inspired by Apple's Human Interface Guidelines:

| Token | Value |
|-------|-------|
| **Background** | Deep Charcoal Black `#0B0B0D` |
| **Cards** | Dark Glassmorphism Surfaces |
| **Accent** | Emerald Green `#34C759` |
| **Secondary** | Electric Blue `#0A84FF` |
| **Error** | Soft Red `#FF453A` |
| **Corner Radius** | 20–28dp |
| **Typography** | SF Pro-style — large bold headlines, clean hierarchy |
| **Animations** | Smooth, fluid, Apple-like transitions |

---

## 📦 Installation

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 26+
- A Firebase project (for Auth, Firestore, and FCM)
- A Gemini API key from [Google AI Studio](https://aistudio.google.com)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/CarbonWise.git
   cd CarbonWise
   ```

2. **Open in Android Studio**
   ```
   File → Open → Select the CarbonWise root directory
   ```

3. **Add your `google-services.json`**

   Create a Firebase project, register the app with package name `com.carbonwise.app`, and place `google-services.json` at `app/google-services.json`.

4. **Add your Gemini API key**

   In `local.properties` (never commit this file):
   ```properties
   GEMINI_API_KEY=your_api_key_here
   ```

5. **Build and run**
   ```bash
   ./gradlew assembleDebug
   # Or press the green ▶ Run button in Android Studio
   ```

---

## 🔧 Firebase Setup

### Authentication
1. Firebase Console → **Authentication → Sign-in method**
2. Enable **Email/Password** and **Google** sign-in providers

### Firestore
1. Firebase Console → **Firestore Database → Create database**
2. Start in test mode during development; apply security rules before release

### Cloud Messaging
1. Firebase Console → **Cloud Messaging** is enabled by default
2. Download the updated `google-services.json` after enabling all services

---

## 📱 Screenshots

<div align="center">

| Onboarding | Dashboard | AI Assistant |
|------------|-----------|--------------|
| ![Onboarding](screenshots/onboarding.png) | ![Dashboard](screenshots/dashboard.png) | ![AI](screenshots/ai_assistant.png) |

| Challenges | Analytics | Knowledge Hub |
|------------|-----------|---------------|
| ![Challenges](screenshots/challenges.png) | ![Analytics](screenshots/analytics.png) | ![Hub](screenshots/knowledge_hub.png) |

</div>

---

## 🏗️ Project Structure

```
CarbonWise/
├── app/
│   ├── src/main/
│   │   ├── java/com/carbonwise/app/
│   │   │   ├── CarbonWiseApp.kt              # Application class (Hilt)
│   │   │   ├── MainActivity.kt               # Bottom-nav host
│   │   │   │
│   │   │   ├── data/
│   │   │   │   ├── local/
│   │   │   │   │   ├── AppDatabase.kt        # Room database
│   │   │   │   │   ├── dao/                  # DAOs for activities, goals, challenges
│   │   │   │   │   └── entities/             # Room entities
│   │   │   │   ├── remote/
│   │   │   │   │   ├── GeminiApiService.kt   # Retrofit interface for Gemini
│   │   │   │   │   └── FirestoreService.kt   # Firestore read/write helpers
│   │   │   │   ├── preferences/
│   │   │   │   │   └── UserPreferences.kt    # DataStore preferences
│   │   │   │   └── repository/
│   │   │   │       ├── CarbonRepository.kt
│   │   │   │       ├── ChallengeRepository.kt
│   │   │   │       └── GoalRepository.kt
│   │   │   │
│   │   │   ├── domain/
│   │   │   │   ├── model/                    # Domain models (CarbonEntry, Challenge, etc.)
│   │   │   │   ├── usecase/                  # Business logic use cases
│   │   │   │   └── calculator/               # Carbon footprint calculation engine
│   │   │   │
│   │   │   ├── di/
│   │   │   │   ├── DatabaseModule.kt
│   │   │   │   ├── NetworkModule.kt
│   │   │   │   └── RepositoryModule.kt
│   │   │   │
│   │   │   └── ui/
│   │   │       ├── theme/                    # Compose theme, colors, typography
│   │   │       ├── components/               # Reusable Composables (GlassCard, etc.)
│   │   │       ├── onboarding/               # Onboarding flow screens
│   │   │       ├── home/                     # Dashboard screen + ViewModel
│   │   │       ├── track/                    # Activity logging screen + ViewModel
│   │   │       ├── challenges/               # Eco challenges screen + ViewModel
│   │   │       ├── analytics/                # Charts and insights screen + ViewModel
│   │   │       ├── assistant/                # Gemini AI chat screen + ViewModel
│   │   │       ├── hub/                      # Knowledge hub screen
│   │   │       └── profile/                  # Goals, settings, achievements
│   │   │
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   ├── font/
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── screenshots/
├── docs/
├── LICENSE
└── README.md
```

---

## 🗺️ Navigation Structure

```
Bottom Navigation
├── 🏠 Home        → Dashboard (daily score, weekly summary, health indicator)
├── 📝 Track       → Activity logging (transportation, energy, food, shopping, waste)
├── 🌿 Challenges  → Daily eco tasks, streak tracker, points
├── 📊 Analytics   → Pie charts, trend graphs, progress charts
└── 👤 Profile     → Goals, achievements, settings, AI assistant
```

---

## 🤝 Contributing

Contributions are welcome! Whether it's a new carbon calculation model, a UI improvement, or a new eco challenge — all pull requests are appreciated.


### Areas for Contribution
- 🐛 Bug reports and fixes
- 🌐 Localization and region-specific carbon emission factors
- 📊 New chart types and analytics views
- 🤖 Improved Gemini prompt engineering for better recommendations
- 🎨 UI animations and Compose component improvements
- 📖 Sustainability Knowledge Hub content

---

## 🐛 Known Issues

- Gemini API responses may be slower on poor network conditions; retry logic is recommended
- Carbon calculations use global average emission factors; region-specific accuracy varies
- Firebase Firestore offline persistence may cause brief sync delays on first launch after reconnecting
- MPAndroidChart animations may stutter on devices with less than 2 GB RAM

---

## 🗺️ Roadmap

- [ ] Wearable integration (Wear OS) for on-the-go activity logging
- [ ] Social leaderboard — compare footprint with friends
- [ ] Carbon offset marketplace with in-app tree planting
- [ ] CSV / PDF export of monthly carbon report
- [ ] Widget support for home screen carbon score display
- [ ] Regional emission factor database for more accurate calculations
- [ ] Apple Watch / iOS companion app

---

## 🆘 Support & Help

### Getting Help
- 🐛 **Bug Reports:** ayusharyan.online@gmail.com
- 💡 **Feature Requests:** ayusharyan.online@gmail.com
- 💬 **General Questions:** ayusharyan.online@gmail.com

### FAQ

**Q: How accurate are the carbon calculations?**  
A: CarbonWise uses globally recognized emission factors as baselines, personalized by your country, vehicle type, and lifestyle inputs. Calculations are estimates — actual emissions may vary based on regional energy grids and individual behavior.

**Q: Is my data stored on the cloud?**  
A: Activity data is synced to Firebase Firestore tied to your account. You can delete your account at any time to remove all cloud data. See the Privacy Policy for details.

**Q: Does the AI assistant require an internet connection?**  
A: Yes. The Gemini-powered assistant requires an active internet connection to generate responses.

**Q: Can I use CarbonWise without creating an account?**  
A: Basic tracking works locally without an account. Firebase Authentication is required for cross-device sync, cloud backup, and community challenges.

**Q: How do Green Points work?**  
A: Points are awarded for logging daily activities, completing eco challenges, maintaining streaks, and achieving reduction goals. Points contribute to your level progression from Seedling to Earth Champion.

---

## 🌟 Show Your Support

If CarbonWise has inspired you to think about your environmental impact, please consider:

- ⭐ **Star this repository** to help others discover it
- 🍴 **Fork the project** to contribute improvements
- 📢 **Share with friends** who want to reduce their carbon footprint
- 🌱 **Plant a tree** — because why not

---

## 📄 Licence

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

- **Developer:** Designed, developed, and maintained by a single independent developer.
- **Email:** ayusharyan.online@gmail.com
- **Twitter:** https://x.com/hypfridie

---

<div align="center">
  <p>Made with ❤️ for the planet</p>
  <p><strong>CarbonWise</strong> — Small habits. Big impact.</p>
</div>
