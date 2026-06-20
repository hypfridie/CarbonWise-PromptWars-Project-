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
import com.carbonwise.app.model.Challenge;
import java.lang.Class;
import java.lang.Exception;
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
public final class ChallengeDao_Impl implements ChallengeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Challenge> __insertionAdapterOfChallenge;

  private final EntityDeletionOrUpdateAdapter<Challenge> __updateAdapterOfChallenge;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldCompleted;

  public ChallengeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChallenge = new EntityInsertionAdapter<Challenge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `challenges` (`id`,`title`,`description`,`category`,`pointsReward`,`completed`,`assignedDate`,`completedDate`,`difficulty`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Challenge entity) {
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
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        statement.bindLong(5, entity.getPointsReward());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getAssignedDate());
        statement.bindLong(8, entity.getCompletedDate());
        statement.bindLong(9, entity.getDifficulty());
      }
    };
    this.__updateAdapterOfChallenge = new EntityDeletionOrUpdateAdapter<Challenge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `challenges` SET `id` = ?,`title` = ?,`description` = ?,`category` = ?,`pointsReward` = ?,`completed` = ?,`assignedDate` = ?,`completedDate` = ?,`difficulty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Challenge entity) {
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
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        statement.bindLong(5, entity.getPointsReward());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getAssignedDate());
        statement.bindLong(8, entity.getCompletedDate());
        statement.bindLong(9, entity.getDifficulty());
        if (entity.getId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteOldCompleted = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM challenges WHERE completed = 1 AND completedDate < ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Challenge challenge) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChallenge.insert(challenge);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Challenge> challenges) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChallenge.insert(challenges);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Challenge challenge) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfChallenge.handle(challenge);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteOldCompleted(final long cutoffDate) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldCompleted.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cutoffDate);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteOldCompleted.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Challenge>> getAllChallenges() {
    final String _sql = "SELECT * FROM challenges ORDER BY assignedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"challenges"}, false, new Callable<List<Challenge>>() {
      @Override
      @Nullable
      public List<Challenge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPointsReward = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsReward");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfAssignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedDate");
          final int _cursorIndexOfCompletedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDate");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final List<Challenge> _result = new ArrayList<Challenge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Challenge _item;
            _item = new Challenge();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final int _tmpPointsReward;
            _tmpPointsReward = _cursor.getInt(_cursorIndexOfPointsReward);
            _item.setPointsReward(_tmpPointsReward);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            _item.setCompleted(_tmpCompleted);
            final long _tmpAssignedDate;
            _tmpAssignedDate = _cursor.getLong(_cursorIndexOfAssignedDate);
            _item.setAssignedDate(_tmpAssignedDate);
            final long _tmpCompletedDate;
            _tmpCompletedDate = _cursor.getLong(_cursorIndexOfCompletedDate);
            _item.setCompletedDate(_tmpCompletedDate);
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            _item.setDifficulty(_tmpDifficulty);
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
  public LiveData<List<Challenge>> getActiveChallenges() {
    final String _sql = "SELECT * FROM challenges WHERE completed = 0 ORDER BY assignedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"challenges"}, false, new Callable<List<Challenge>>() {
      @Override
      @Nullable
      public List<Challenge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPointsReward = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsReward");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfAssignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedDate");
          final int _cursorIndexOfCompletedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDate");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final List<Challenge> _result = new ArrayList<Challenge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Challenge _item;
            _item = new Challenge();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final int _tmpPointsReward;
            _tmpPointsReward = _cursor.getInt(_cursorIndexOfPointsReward);
            _item.setPointsReward(_tmpPointsReward);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            _item.setCompleted(_tmpCompleted);
            final long _tmpAssignedDate;
            _tmpAssignedDate = _cursor.getLong(_cursorIndexOfAssignedDate);
            _item.setAssignedDate(_tmpAssignedDate);
            final long _tmpCompletedDate;
            _tmpCompletedDate = _cursor.getLong(_cursorIndexOfCompletedDate);
            _item.setCompletedDate(_tmpCompletedDate);
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            _item.setDifficulty(_tmpDifficulty);
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
  public LiveData<List<Challenge>> getCompletedChallenges() {
    final String _sql = "SELECT * FROM challenges WHERE completed = 1 ORDER BY completedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"challenges"}, false, new Callable<List<Challenge>>() {
      @Override
      @Nullable
      public List<Challenge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPointsReward = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsReward");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfAssignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedDate");
          final int _cursorIndexOfCompletedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDate");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final List<Challenge> _result = new ArrayList<Challenge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Challenge _item;
            _item = new Challenge();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final int _tmpPointsReward;
            _tmpPointsReward = _cursor.getInt(_cursorIndexOfPointsReward);
            _item.setPointsReward(_tmpPointsReward);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            _item.setCompleted(_tmpCompleted);
            final long _tmpAssignedDate;
            _tmpAssignedDate = _cursor.getLong(_cursorIndexOfAssignedDate);
            _item.setAssignedDate(_tmpAssignedDate);
            final long _tmpCompletedDate;
            _tmpCompletedDate = _cursor.getLong(_cursorIndexOfCompletedDate);
            _item.setCompletedDate(_tmpCompletedDate);
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            _item.setDifficulty(_tmpDifficulty);
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
  public List<String> getCompletedDays() {
    final String _sql = "SELECT DISTINCT STRFTIME('%Y-%m-%d', completedDate/1000, 'unixepoch') FROM challenges WHERE completed = 1 ORDER BY completedDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final String _item;
        final String _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(0);
        }
        _item = _tmp;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Challenge> getById(final String id) {
    final String _sql = "SELECT * FROM challenges WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"challenges"}, false, new Callable<Challenge>() {
      @Override
      @Nullable
      public Challenge call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPointsReward = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsReward");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfAssignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedDate");
          final int _cursorIndexOfCompletedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDate");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final Challenge _result;
          if (_cursor.moveToFirst()) {
            _result = new Challenge();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _result.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _result.setDescription(_tmpDescription);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _result.setCategory(_tmpCategory);
            final int _tmpPointsReward;
            _tmpPointsReward = _cursor.getInt(_cursorIndexOfPointsReward);
            _result.setPointsReward(_tmpPointsReward);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            _result.setCompleted(_tmpCompleted);
            final long _tmpAssignedDate;
            _tmpAssignedDate = _cursor.getLong(_cursorIndexOfAssignedDate);
            _result.setAssignedDate(_tmpAssignedDate);
            final long _tmpCompletedDate;
            _tmpCompletedDate = _cursor.getLong(_cursorIndexOfCompletedDate);
            _result.setCompletedDate(_tmpCompletedDate);
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            _result.setDifficulty(_tmpDifficulty);
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
  public int getCompletedCountForDay(final long startOfDay) {
    final String _sql = "SELECT COUNT(*) FROM challenges WHERE completed = 1 AND completedDate >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
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
