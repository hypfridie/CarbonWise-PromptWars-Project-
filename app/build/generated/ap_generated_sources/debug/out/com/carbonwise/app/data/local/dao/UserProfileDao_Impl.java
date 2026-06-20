package com.carbonwise.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.carbonwise.app.model.UserProfile;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfile> __insertionAdapterOfUserProfile;

  private final EntityDeletionOrUpdateAdapter<UserProfile> __updateAdapterOfUserProfile;

  private final SharedSQLiteStatement __preparedStmtOfAddPoints;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStreak;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfile = new EntityInsertionAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`userId`,`name`,`ageGroup`,`country`,`city`,`vehicleType`,`dailyCommuteKm`,`familyMembers`,`monthlyElectricityKwh`,`cookingFuelType`,`dietType`,`shoppingHabits`,`greenPoints`,`currentLevel`,`currentStreak`,`longestStreak`,`monthlyCarbonGoal`,`onboardingComplete`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final UserProfile entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAgeGroup() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAgeGroup());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCountry());
        }
        if (entity.getCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCity());
        }
        if (entity.getVehicleType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getVehicleType());
        }
        statement.bindDouble(7, entity.getDailyCommuteKm());
        statement.bindLong(8, entity.getFamilyMembers());
        statement.bindDouble(9, entity.getMonthlyElectricityKwh());
        if (entity.getCookingFuelType() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCookingFuelType());
        }
        if (entity.getDietType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDietType());
        }
        if (entity.getShoppingHabits() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getShoppingHabits());
        }
        statement.bindLong(13, entity.getGreenPoints());
        if (entity.getCurrentLevel() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCurrentLevel());
        }
        statement.bindLong(15, entity.getCurrentStreak());
        statement.bindLong(16, entity.getLongestStreak());
        statement.bindDouble(17, entity.getMonthlyCarbonGoal());
        final int _tmp = entity.isOnboardingComplete() ? 1 : 0;
        statement.bindLong(18, _tmp);
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
      }
    };
    this.__updateAdapterOfUserProfile = new EntityDeletionOrUpdateAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profile` SET `userId` = ?,`name` = ?,`ageGroup` = ?,`country` = ?,`city` = ?,`vehicleType` = ?,`dailyCommuteKm` = ?,`familyMembers` = ?,`monthlyElectricityKwh` = ?,`cookingFuelType` = ?,`dietType` = ?,`shoppingHabits` = ?,`greenPoints` = ?,`currentLevel` = ?,`currentStreak` = ?,`longestStreak` = ?,`monthlyCarbonGoal` = ?,`onboardingComplete` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `userId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final UserProfile entity) {
        if (entity.getUserId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAgeGroup() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAgeGroup());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCountry());
        }
        if (entity.getCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCity());
        }
        if (entity.getVehicleType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getVehicleType());
        }
        statement.bindDouble(7, entity.getDailyCommuteKm());
        statement.bindLong(8, entity.getFamilyMembers());
        statement.bindDouble(9, entity.getMonthlyElectricityKwh());
        if (entity.getCookingFuelType() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCookingFuelType());
        }
        if (entity.getDietType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDietType());
        }
        if (entity.getShoppingHabits() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getShoppingHabits());
        }
        statement.bindLong(13, entity.getGreenPoints());
        if (entity.getCurrentLevel() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCurrentLevel());
        }
        statement.bindLong(15, entity.getCurrentStreak());
        statement.bindLong(16, entity.getLongestStreak());
        statement.bindDouble(17, entity.getMonthlyCarbonGoal());
        final int _tmp = entity.isOnboardingComplete() ? 1 : 0;
        statement.bindLong(18, _tmp);
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
        if (entity.getUserId() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getUserId());
        }
      }
    };
    this.__preparedStmtOfAddPoints = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE user_profile SET greenPoints = greenPoints + ?, updatedAt = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateStreak = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE user_profile SET currentStreak = ?, longestStreak = CASE WHEN ? > longestStreak THEN ? ELSE longestStreak END, updatedAt = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final UserProfile profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserProfile.insert(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final UserProfile profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUserProfile.handle(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void addPoints(final int points, final long timestamp) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfAddPoints.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, points);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, timestamp);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfAddPoints.release(_stmt);
    }
  }

  @Override
  public void updateStreak(final int streak, final long timestamp) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStreak.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, streak);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, streak);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, streak);
    _argIndex = 4;
    _stmt.bindLong(_argIndex, timestamp);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateStreak.release(_stmt);
    }
  }

  @Override
  public LiveData<UserProfile> getById(final String userId) {
    final String _sql = "SELECT * FROM user_profile WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"user_profile"}, false, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAgeGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "ageGroup");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfVehicleType = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleType");
          final int _cursorIndexOfDailyCommuteKm = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyCommuteKm");
          final int _cursorIndexOfFamilyMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "familyMembers");
          final int _cursorIndexOfMonthlyElectricityKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyElectricityKwh");
          final int _cursorIndexOfCookingFuelType = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingFuelType");
          final int _cursorIndexOfDietType = CursorUtil.getColumnIndexOrThrow(_cursor, "dietType");
          final int _cursorIndexOfShoppingHabits = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingHabits");
          final int _cursorIndexOfGreenPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "greenPoints");
          final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfMonthlyCarbonGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyCarbonGoal");
          final int _cursorIndexOfOnboardingComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingComplete");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            _result = new UserProfile();
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _result.setUserId(_tmpUserId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _result.setName(_tmpName);
            final String _tmpAgeGroup;
            if (_cursor.isNull(_cursorIndexOfAgeGroup)) {
              _tmpAgeGroup = null;
            } else {
              _tmpAgeGroup = _cursor.getString(_cursorIndexOfAgeGroup);
            }
            _result.setAgeGroup(_tmpAgeGroup);
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            _result.setCountry(_tmpCountry);
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            _result.setCity(_tmpCity);
            final String _tmpVehicleType;
            if (_cursor.isNull(_cursorIndexOfVehicleType)) {
              _tmpVehicleType = null;
            } else {
              _tmpVehicleType = _cursor.getString(_cursorIndexOfVehicleType);
            }
            _result.setVehicleType(_tmpVehicleType);
            final double _tmpDailyCommuteKm;
            _tmpDailyCommuteKm = _cursor.getDouble(_cursorIndexOfDailyCommuteKm);
            _result.setDailyCommuteKm(_tmpDailyCommuteKm);
            final int _tmpFamilyMembers;
            _tmpFamilyMembers = _cursor.getInt(_cursorIndexOfFamilyMembers);
            _result.setFamilyMembers(_tmpFamilyMembers);
            final double _tmpMonthlyElectricityKwh;
            _tmpMonthlyElectricityKwh = _cursor.getDouble(_cursorIndexOfMonthlyElectricityKwh);
            _result.setMonthlyElectricityKwh(_tmpMonthlyElectricityKwh);
            final String _tmpCookingFuelType;
            if (_cursor.isNull(_cursorIndexOfCookingFuelType)) {
              _tmpCookingFuelType = null;
            } else {
              _tmpCookingFuelType = _cursor.getString(_cursorIndexOfCookingFuelType);
            }
            _result.setCookingFuelType(_tmpCookingFuelType);
            final String _tmpDietType;
            if (_cursor.isNull(_cursorIndexOfDietType)) {
              _tmpDietType = null;
            } else {
              _tmpDietType = _cursor.getString(_cursorIndexOfDietType);
            }
            _result.setDietType(_tmpDietType);
            final String _tmpShoppingHabits;
            if (_cursor.isNull(_cursorIndexOfShoppingHabits)) {
              _tmpShoppingHabits = null;
            } else {
              _tmpShoppingHabits = _cursor.getString(_cursorIndexOfShoppingHabits);
            }
            _result.setShoppingHabits(_tmpShoppingHabits);
            final int _tmpGreenPoints;
            _tmpGreenPoints = _cursor.getInt(_cursorIndexOfGreenPoints);
            _result.setGreenPoints(_tmpGreenPoints);
            final String _tmpCurrentLevel;
            if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
              _tmpCurrentLevel = null;
            } else {
              _tmpCurrentLevel = _cursor.getString(_cursorIndexOfCurrentLevel);
            }
            _result.setCurrentLevel(_tmpCurrentLevel);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            _result.setCurrentStreak(_tmpCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            _result.setLongestStreak(_tmpLongestStreak);
            final double _tmpMonthlyCarbonGoal;
            _tmpMonthlyCarbonGoal = _cursor.getDouble(_cursorIndexOfMonthlyCarbonGoal);
            _result.setMonthlyCarbonGoal(_tmpMonthlyCarbonGoal);
            final boolean _tmpOnboardingComplete;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfOnboardingComplete);
            _tmpOnboardingComplete = _tmp != 0;
            _result.setOnboardingComplete(_tmpOnboardingComplete);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result.setCreatedAt(_tmpCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result.setUpdatedAt(_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<UserProfile> getFirst() {
    final String _sql = "SELECT * FROM user_profile LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"user_profile"}, false, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAgeGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "ageGroup");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfVehicleType = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleType");
          final int _cursorIndexOfDailyCommuteKm = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyCommuteKm");
          final int _cursorIndexOfFamilyMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "familyMembers");
          final int _cursorIndexOfMonthlyElectricityKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyElectricityKwh");
          final int _cursorIndexOfCookingFuelType = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingFuelType");
          final int _cursorIndexOfDietType = CursorUtil.getColumnIndexOrThrow(_cursor, "dietType");
          final int _cursorIndexOfShoppingHabits = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingHabits");
          final int _cursorIndexOfGreenPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "greenPoints");
          final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
          final int _cursorIndexOfMonthlyCarbonGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyCarbonGoal");
          final int _cursorIndexOfOnboardingComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingComplete");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            _result = new UserProfile();
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _result.setUserId(_tmpUserId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _result.setName(_tmpName);
            final String _tmpAgeGroup;
            if (_cursor.isNull(_cursorIndexOfAgeGroup)) {
              _tmpAgeGroup = null;
            } else {
              _tmpAgeGroup = _cursor.getString(_cursorIndexOfAgeGroup);
            }
            _result.setAgeGroup(_tmpAgeGroup);
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            _result.setCountry(_tmpCountry);
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            _result.setCity(_tmpCity);
            final String _tmpVehicleType;
            if (_cursor.isNull(_cursorIndexOfVehicleType)) {
              _tmpVehicleType = null;
            } else {
              _tmpVehicleType = _cursor.getString(_cursorIndexOfVehicleType);
            }
            _result.setVehicleType(_tmpVehicleType);
            final double _tmpDailyCommuteKm;
            _tmpDailyCommuteKm = _cursor.getDouble(_cursorIndexOfDailyCommuteKm);
            _result.setDailyCommuteKm(_tmpDailyCommuteKm);
            final int _tmpFamilyMembers;
            _tmpFamilyMembers = _cursor.getInt(_cursorIndexOfFamilyMembers);
            _result.setFamilyMembers(_tmpFamilyMembers);
            final double _tmpMonthlyElectricityKwh;
            _tmpMonthlyElectricityKwh = _cursor.getDouble(_cursorIndexOfMonthlyElectricityKwh);
            _result.setMonthlyElectricityKwh(_tmpMonthlyElectricityKwh);
            final String _tmpCookingFuelType;
            if (_cursor.isNull(_cursorIndexOfCookingFuelType)) {
              _tmpCookingFuelType = null;
            } else {
              _tmpCookingFuelType = _cursor.getString(_cursorIndexOfCookingFuelType);
            }
            _result.setCookingFuelType(_tmpCookingFuelType);
            final String _tmpDietType;
            if (_cursor.isNull(_cursorIndexOfDietType)) {
              _tmpDietType = null;
            } else {
              _tmpDietType = _cursor.getString(_cursorIndexOfDietType);
            }
            _result.setDietType(_tmpDietType);
            final String _tmpShoppingHabits;
            if (_cursor.isNull(_cursorIndexOfShoppingHabits)) {
              _tmpShoppingHabits = null;
            } else {
              _tmpShoppingHabits = _cursor.getString(_cursorIndexOfShoppingHabits);
            }
            _result.setShoppingHabits(_tmpShoppingHabits);
            final int _tmpGreenPoints;
            _tmpGreenPoints = _cursor.getInt(_cursorIndexOfGreenPoints);
            _result.setGreenPoints(_tmpGreenPoints);
            final String _tmpCurrentLevel;
            if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
              _tmpCurrentLevel = null;
            } else {
              _tmpCurrentLevel = _cursor.getString(_cursorIndexOfCurrentLevel);
            }
            _result.setCurrentLevel(_tmpCurrentLevel);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            _result.setCurrentStreak(_tmpCurrentStreak);
            final int _tmpLongestStreak;
            _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
            _result.setLongestStreak(_tmpLongestStreak);
            final double _tmpMonthlyCarbonGoal;
            _tmpMonthlyCarbonGoal = _cursor.getDouble(_cursorIndexOfMonthlyCarbonGoal);
            _result.setMonthlyCarbonGoal(_tmpMonthlyCarbonGoal);
            final boolean _tmpOnboardingComplete;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfOnboardingComplete);
            _tmpOnboardingComplete = _tmp != 0;
            _result.setOnboardingComplete(_tmpOnboardingComplete);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result.setCreatedAt(_tmpCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result.setUpdatedAt(_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public UserProfile getFirstSync() {
    final String _sql = "SELECT * FROM user_profile LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAgeGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "ageGroup");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfVehicleType = CursorUtil.getColumnIndexOrThrow(_cursor, "vehicleType");
      final int _cursorIndexOfDailyCommuteKm = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyCommuteKm");
      final int _cursorIndexOfFamilyMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "familyMembers");
      final int _cursorIndexOfMonthlyElectricityKwh = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyElectricityKwh");
      final int _cursorIndexOfCookingFuelType = CursorUtil.getColumnIndexOrThrow(_cursor, "cookingFuelType");
      final int _cursorIndexOfDietType = CursorUtil.getColumnIndexOrThrow(_cursor, "dietType");
      final int _cursorIndexOfShoppingHabits = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingHabits");
      final int _cursorIndexOfGreenPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "greenPoints");
      final int _cursorIndexOfCurrentLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "currentLevel");
      final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
      final int _cursorIndexOfLongestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "longestStreak");
      final int _cursorIndexOfMonthlyCarbonGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyCarbonGoal");
      final int _cursorIndexOfOnboardingComplete = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingComplete");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
      final UserProfile _result;
      if (_cursor.moveToFirst()) {
        _result = new UserProfile();
        final String _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
        }
        _result.setUserId(_tmpUserId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpAgeGroup;
        if (_cursor.isNull(_cursorIndexOfAgeGroup)) {
          _tmpAgeGroup = null;
        } else {
          _tmpAgeGroup = _cursor.getString(_cursorIndexOfAgeGroup);
        }
        _result.setAgeGroup(_tmpAgeGroup);
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        _result.setCountry(_tmpCountry);
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        _result.setCity(_tmpCity);
        final String _tmpVehicleType;
        if (_cursor.isNull(_cursorIndexOfVehicleType)) {
          _tmpVehicleType = null;
        } else {
          _tmpVehicleType = _cursor.getString(_cursorIndexOfVehicleType);
        }
        _result.setVehicleType(_tmpVehicleType);
        final double _tmpDailyCommuteKm;
        _tmpDailyCommuteKm = _cursor.getDouble(_cursorIndexOfDailyCommuteKm);
        _result.setDailyCommuteKm(_tmpDailyCommuteKm);
        final int _tmpFamilyMembers;
        _tmpFamilyMembers = _cursor.getInt(_cursorIndexOfFamilyMembers);
        _result.setFamilyMembers(_tmpFamilyMembers);
        final double _tmpMonthlyElectricityKwh;
        _tmpMonthlyElectricityKwh = _cursor.getDouble(_cursorIndexOfMonthlyElectricityKwh);
        _result.setMonthlyElectricityKwh(_tmpMonthlyElectricityKwh);
        final String _tmpCookingFuelType;
        if (_cursor.isNull(_cursorIndexOfCookingFuelType)) {
          _tmpCookingFuelType = null;
        } else {
          _tmpCookingFuelType = _cursor.getString(_cursorIndexOfCookingFuelType);
        }
        _result.setCookingFuelType(_tmpCookingFuelType);
        final String _tmpDietType;
        if (_cursor.isNull(_cursorIndexOfDietType)) {
          _tmpDietType = null;
        } else {
          _tmpDietType = _cursor.getString(_cursorIndexOfDietType);
        }
        _result.setDietType(_tmpDietType);
        final String _tmpShoppingHabits;
        if (_cursor.isNull(_cursorIndexOfShoppingHabits)) {
          _tmpShoppingHabits = null;
        } else {
          _tmpShoppingHabits = _cursor.getString(_cursorIndexOfShoppingHabits);
        }
        _result.setShoppingHabits(_tmpShoppingHabits);
        final int _tmpGreenPoints;
        _tmpGreenPoints = _cursor.getInt(_cursorIndexOfGreenPoints);
        _result.setGreenPoints(_tmpGreenPoints);
        final String _tmpCurrentLevel;
        if (_cursor.isNull(_cursorIndexOfCurrentLevel)) {
          _tmpCurrentLevel = null;
        } else {
          _tmpCurrentLevel = _cursor.getString(_cursorIndexOfCurrentLevel);
        }
        _result.setCurrentLevel(_tmpCurrentLevel);
        final int _tmpCurrentStreak;
        _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
        _result.setCurrentStreak(_tmpCurrentStreak);
        final int _tmpLongestStreak;
        _tmpLongestStreak = _cursor.getInt(_cursorIndexOfLongestStreak);
        _result.setLongestStreak(_tmpLongestStreak);
        final double _tmpMonthlyCarbonGoal;
        _tmpMonthlyCarbonGoal = _cursor.getDouble(_cursorIndexOfMonthlyCarbonGoal);
        _result.setMonthlyCarbonGoal(_tmpMonthlyCarbonGoal);
        final boolean _tmpOnboardingComplete;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOnboardingComplete);
        _tmpOnboardingComplete = _tmp != 0;
        _result.setOnboardingComplete(_tmpOnboardingComplete);
        final long _tmpCreatedAt;
        _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
        _result.setCreatedAt(_tmpCreatedAt);
        final long _tmpUpdatedAt;
        _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
        _result.setUpdatedAt(_tmpUpdatedAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean isOnboardingComplete() {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM user_profile WHERE onboardingComplete = 1)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if (_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
