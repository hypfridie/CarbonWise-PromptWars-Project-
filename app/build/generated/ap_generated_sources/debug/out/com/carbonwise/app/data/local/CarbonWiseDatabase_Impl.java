package com.carbonwise.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.carbonwise.app.data.local.dao.AchievementDao;
import com.carbonwise.app.data.local.dao.AchievementDao_Impl;
import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.CarbonEntryDao_Impl;
import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import com.carbonwise.app.data.local.dao.CarbonGoalDao_Impl;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.ChallengeDao_Impl;
import com.carbonwise.app.data.local.dao.UserProfileDao;
import com.carbonwise.app.data.local.dao.UserProfileDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CarbonWiseDatabase_Impl extends CarbonWiseDatabase {
  private volatile CarbonEntryDao _carbonEntryDao;

  private volatile UserProfileDao _userProfileDao;

  private volatile ChallengeDao _challengeDao;

  private volatile AchievementDao _achievementDao;

  private volatile CarbonGoalDao _carbonGoalDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `carbon_entries` (`id` TEXT NOT NULL, `userId` TEXT, `category` TEXT, `subCategory` TEXT, `carbonValue` REAL NOT NULL, `unit` TEXT, `timestamp` INTEGER NOT NULL, `notes` TEXT, `synced` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profile` (`userId` TEXT NOT NULL, `name` TEXT, `ageGroup` TEXT, `country` TEXT, `city` TEXT, `vehicleType` TEXT, `dailyCommuteKm` REAL NOT NULL, `familyMembers` INTEGER NOT NULL, `monthlyElectricityKwh` REAL NOT NULL, `cookingFuelType` TEXT, `dietType` TEXT, `shoppingHabits` TEXT, `greenPoints` INTEGER NOT NULL, `currentLevel` TEXT, `currentStreak` INTEGER NOT NULL, `longestStreak` INTEGER NOT NULL, `monthlyCarbonGoal` REAL NOT NULL, `onboardingComplete` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `challenges` (`id` TEXT NOT NULL, `title` TEXT, `description` TEXT, `category` TEXT, `pointsReward` INTEGER NOT NULL, `completed` INTEGER NOT NULL, `assignedDate` INTEGER NOT NULL, `completedDate` INTEGER NOT NULL, `difficulty` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `achievements` (`id` TEXT NOT NULL, `title` TEXT, `description` TEXT, `iconName` TEXT, `pointsRequired` INTEGER NOT NULL, `unlocked` INTEGER NOT NULL, `unlockedDate` INTEGER NOT NULL, `category` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `carbon_goals` (`id` TEXT NOT NULL, `userId` TEXT, `title` TEXT, `description` TEXT, `targetReductionPercent` REAL NOT NULL, `currentReductionPercent` REAL NOT NULL, `category` TEXT, `active` INTEGER NOT NULL, `startDate` INTEGER NOT NULL, `targetDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '99d2e11f7a623689d6db990c215034a0')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `carbon_entries`");
        db.execSQL("DROP TABLE IF EXISTS `user_profile`");
        db.execSQL("DROP TABLE IF EXISTS `challenges`");
        db.execSQL("DROP TABLE IF EXISTS `achievements`");
        db.execSQL("DROP TABLE IF EXISTS `carbon_goals`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCarbonEntries = new HashMap<String, TableInfo.Column>(9);
        _columnsCarbonEntries.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("userId", new TableInfo.Column("userId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("subCategory", new TableInfo.Column("subCategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("carbonValue", new TableInfo.Column("carbonValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("unit", new TableInfo.Column("unit", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonEntries.put("synced", new TableInfo.Column("synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCarbonEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCarbonEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCarbonEntries = new TableInfo("carbon_entries", _columnsCarbonEntries, _foreignKeysCarbonEntries, _indicesCarbonEntries);
        final TableInfo _existingCarbonEntries = TableInfo.read(db, "carbon_entries");
        if (!_infoCarbonEntries.equals(_existingCarbonEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "carbon_entries(com.carbonwise.app.model.CarbonEntry).\n"
                  + " Expected:\n" + _infoCarbonEntries + "\n"
                  + " Found:\n" + _existingCarbonEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsUserProfile = new HashMap<String, TableInfo.Column>(20);
        _columnsUserProfile.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("ageGroup", new TableInfo.Column("ageGroup", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("vehicleType", new TableInfo.Column("vehicleType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("dailyCommuteKm", new TableInfo.Column("dailyCommuteKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("familyMembers", new TableInfo.Column("familyMembers", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("monthlyElectricityKwh", new TableInfo.Column("monthlyElectricityKwh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("cookingFuelType", new TableInfo.Column("cookingFuelType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("dietType", new TableInfo.Column("dietType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("shoppingHabits", new TableInfo.Column("shoppingHabits", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("greenPoints", new TableInfo.Column("greenPoints", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("currentLevel", new TableInfo.Column("currentLevel", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("longestStreak", new TableInfo.Column("longestStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("monthlyCarbonGoal", new TableInfo.Column("monthlyCarbonGoal", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("onboardingComplete", new TableInfo.Column("onboardingComplete", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfile.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
        final TableInfo _existingUserProfile = TableInfo.read(db, "user_profile");
        if (!_infoUserProfile.equals(_existingUserProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profile(com.carbonwise.app.model.UserProfile).\n"
                  + " Expected:\n" + _infoUserProfile + "\n"
                  + " Found:\n" + _existingUserProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsChallenges = new HashMap<String, TableInfo.Column>(9);
        _columnsChallenges.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("pointsReward", new TableInfo.Column("pointsReward", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("assignedDate", new TableInfo.Column("assignedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("completedDate", new TableInfo.Column("completedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChallenges.put("difficulty", new TableInfo.Column("difficulty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChallenges = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChallenges = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChallenges = new TableInfo("challenges", _columnsChallenges, _foreignKeysChallenges, _indicesChallenges);
        final TableInfo _existingChallenges = TableInfo.read(db, "challenges");
        if (!_infoChallenges.equals(_existingChallenges)) {
          return new RoomOpenHelper.ValidationResult(false, "challenges(com.carbonwise.app.model.Challenge).\n"
                  + " Expected:\n" + _infoChallenges + "\n"
                  + " Found:\n" + _existingChallenges);
        }
        final HashMap<String, TableInfo.Column> _columnsAchievements = new HashMap<String, TableInfo.Column>(8);
        _columnsAchievements.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("iconName", new TableInfo.Column("iconName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("pointsRequired", new TableInfo.Column("pointsRequired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("unlocked", new TableInfo.Column("unlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("unlockedDate", new TableInfo.Column("unlockedDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAchievements = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAchievements = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAchievements = new TableInfo("achievements", _columnsAchievements, _foreignKeysAchievements, _indicesAchievements);
        final TableInfo _existingAchievements = TableInfo.read(db, "achievements");
        if (!_infoAchievements.equals(_existingAchievements)) {
          return new RoomOpenHelper.ValidationResult(false, "achievements(com.carbonwise.app.model.Achievement).\n"
                  + " Expected:\n" + _infoAchievements + "\n"
                  + " Found:\n" + _existingAchievements);
        }
        final HashMap<String, TableInfo.Column> _columnsCarbonGoals = new HashMap<String, TableInfo.Column>(11);
        _columnsCarbonGoals.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("userId", new TableInfo.Column("userId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("targetReductionPercent", new TableInfo.Column("targetReductionPercent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("currentReductionPercent", new TableInfo.Column("currentReductionPercent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("targetDate", new TableInfo.Column("targetDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCarbonGoals.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCarbonGoals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCarbonGoals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCarbonGoals = new TableInfo("carbon_goals", _columnsCarbonGoals, _foreignKeysCarbonGoals, _indicesCarbonGoals);
        final TableInfo _existingCarbonGoals = TableInfo.read(db, "carbon_goals");
        if (!_infoCarbonGoals.equals(_existingCarbonGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "carbon_goals(com.carbonwise.app.model.CarbonGoal).\n"
                  + " Expected:\n" + _infoCarbonGoals + "\n"
                  + " Found:\n" + _existingCarbonGoals);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "99d2e11f7a623689d6db990c215034a0", "32ecedf562f651f8f763bf5d2ea426c3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "carbon_entries","user_profile","challenges","achievements","carbon_goals");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `carbon_entries`");
      _db.execSQL("DELETE FROM `user_profile`");
      _db.execSQL("DELETE FROM `challenges`");
      _db.execSQL("DELETE FROM `achievements`");
      _db.execSQL("DELETE FROM `carbon_goals`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CarbonEntryDao.class, CarbonEntryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChallengeDao.class, ChallengeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AchievementDao.class, AchievementDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CarbonGoalDao.class, CarbonGoalDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CarbonEntryDao carbonEntryDao() {
    if (_carbonEntryDao != null) {
      return _carbonEntryDao;
    } else {
      synchronized(this) {
        if(_carbonEntryDao == null) {
          _carbonEntryDao = new CarbonEntryDao_Impl(this);
        }
        return _carbonEntryDao;
      }
    }
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public ChallengeDao challengeDao() {
    if (_challengeDao != null) {
      return _challengeDao;
    } else {
      synchronized(this) {
        if(_challengeDao == null) {
          _challengeDao = new ChallengeDao_Impl(this);
        }
        return _challengeDao;
      }
    }
  }

  @Override
  public AchievementDao achievementDao() {
    if (_achievementDao != null) {
      return _achievementDao;
    } else {
      synchronized(this) {
        if(_achievementDao == null) {
          _achievementDao = new AchievementDao_Impl(this);
        }
        return _achievementDao;
      }
    }
  }

  @Override
  public CarbonGoalDao carbonGoalDao() {
    if (_carbonGoalDao != null) {
      return _carbonGoalDao;
    } else {
      synchronized(this) {
        if(_carbonGoalDao == null) {
          _carbonGoalDao = new CarbonGoalDao_Impl(this);
        }
        return _carbonGoalDao;
      }
    }
  }
}
