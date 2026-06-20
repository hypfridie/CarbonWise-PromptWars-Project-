package com.carbonwise.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.carbonwise.app.model.Achievement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AchievementDao_Impl implements AchievementDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Achievement> __insertionAdapterOfAchievement;

  private final EntityDeletionOrUpdateAdapter<Achievement> __updateAdapterOfAchievement;

  public AchievementDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAchievement = new EntityInsertionAdapter<Achievement>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `achievements` (`id`,`title`,`description`,`iconName`,`pointsRequired`,`unlocked`,`unlockedDate`,`category`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Achievement entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getIconName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIconName());
        }
        statement.bindLong(5, entity.getPointsRequired());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getUnlockedDate());
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
      }
    };
    this.__updateAdapterOfAchievement = new EntityDeletionOrUpdateAdapter<Achievement>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `achievements` SET `id` = ?,`title` = ?,`description` = ?,`iconName` = ?,`pointsRequired` = ?,`unlocked` = ?,`unlockedDate` = ?,`category` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final Achievement entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getIconName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIconName());
        }
        statement.bindLong(5, entity.getPointsRequired());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getUnlockedDate());
        if (entity.getCategory() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategory());
        }
        if (entity.getId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getId());
        }
      }
    };
  }

  @Override
  public void insert(final Achievement achievement) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAchievement.insert(achievement);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Achievement> achievements) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAchievement.insert(achievements);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Achievement achievement) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAchievement.handle(achievement);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Achievement>> getAllAchievements() {
    final String _sql = "SELECT * FROM achievements ORDER BY unlocked DESC, pointsRequired ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"achievements"}, false, new Callable<List<Achievement>>() {
      @Override
      @Nullable
      public List<Achievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfPointsRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsRequired");
          final int _cursorIndexOfUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "unlocked");
          final int _cursorIndexOfUnlockedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedDate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<Achievement> _result = new ArrayList<Achievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Achievement _item;
            _item = new Achievement();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final String _tmpIconName;
            if (_cursor.isNull(_cursorIndexOfIconName)) {
              _tmpIconName = null;
            } else {
              _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            }
            _item.setIconName(_tmpIconName);
            final int _tmpPointsRequired;
            _tmpPointsRequired = _cursor.getInt(_cursorIndexOfPointsRequired);
            _item.setPointsRequired(_tmpPointsRequired);
            final boolean _tmpUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUnlocked);
            _tmpUnlocked = _tmp != 0;
            _item.setUnlocked(_tmpUnlocked);
            final long _tmpUnlockedDate;
            _tmpUnlockedDate = _cursor.getLong(_cursorIndexOfUnlockedDate);
            _item.setUnlockedDate(_tmpUnlockedDate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            _result.add(_item);
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
  public LiveData<List<Achievement>> getUnlockedAchievements() {
    final String _sql = "SELECT * FROM achievements WHERE unlocked = 1 ORDER BY unlockedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"achievements"}, false, new Callable<List<Achievement>>() {
      @Override
      @Nullable
      public List<Achievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfPointsRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsRequired");
          final int _cursorIndexOfUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "unlocked");
          final int _cursorIndexOfUnlockedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedDate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<Achievement> _result = new ArrayList<Achievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Achievement _item;
            _item = new Achievement();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final String _tmpIconName;
            if (_cursor.isNull(_cursorIndexOfIconName)) {
              _tmpIconName = null;
            } else {
              _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            }
            _item.setIconName(_tmpIconName);
            final int _tmpPointsRequired;
            _tmpPointsRequired = _cursor.getInt(_cursorIndexOfPointsRequired);
            _item.setPointsRequired(_tmpPointsRequired);
            final boolean _tmpUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUnlocked);
            _tmpUnlocked = _tmp != 0;
            _item.setUnlocked(_tmpUnlocked);
            final long _tmpUnlockedDate;
            _tmpUnlockedDate = _cursor.getLong(_cursorIndexOfUnlockedDate);
            _item.setUnlockedDate(_tmpUnlockedDate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            _result.add(_item);
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
  public LiveData<List<Achievement>> getLockedAchievements() {
    final String _sql = "SELECT * FROM achievements WHERE unlocked = 0 ORDER BY pointsRequired ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"achievements"}, false, new Callable<List<Achievement>>() {
      @Override
      @Nullable
      public List<Achievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfPointsRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsRequired");
          final int _cursorIndexOfUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "unlocked");
          final int _cursorIndexOfUnlockedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedDate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<Achievement> _result = new ArrayList<Achievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Achievement _item;
            _item = new Achievement();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final String _tmpIconName;
            if (_cursor.isNull(_cursorIndexOfIconName)) {
              _tmpIconName = null;
            } else {
              _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            }
            _item.setIconName(_tmpIconName);
            final int _tmpPointsRequired;
            _tmpPointsRequired = _cursor.getInt(_cursorIndexOfPointsRequired);
            _item.setPointsRequired(_tmpPointsRequired);
            final boolean _tmpUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUnlocked);
            _tmpUnlocked = _tmp != 0;
            _item.setUnlocked(_tmpUnlocked);
            final long _tmpUnlockedDate;
            _tmpUnlockedDate = _cursor.getLong(_cursorIndexOfUnlockedDate);
            _item.setUnlockedDate(_tmpUnlockedDate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            _result.add(_item);
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
  public LiveData<Integer> getUnlockedCount() {
    final String _sql = "SELECT COUNT(*) FROM achievements WHERE unlocked = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"achievements"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
