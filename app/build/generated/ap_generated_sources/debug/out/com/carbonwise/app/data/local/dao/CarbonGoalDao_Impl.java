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
import com.carbonwise.app.model.CarbonGoal;
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
public final class CarbonGoalDao_Impl implements CarbonGoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CarbonGoal> __insertionAdapterOfCarbonGoal;

  private final EntityDeletionOrUpdateAdapter<CarbonGoal> __updateAdapterOfCarbonGoal;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateGoal;

  private final SharedSQLiteStatement __preparedStmtOfUpdateProgress;

  public CarbonGoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCarbonGoal = new EntityInsertionAdapter<CarbonGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `carbon_goals` (`id`,`userId`,`title`,`description`,`targetReductionPercent`,`currentReductionPercent`,`category`,`active`,`startDate`,`targetDate`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CarbonGoal entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        statement.bindDouble(5, entity.getTargetReductionPercent());
        statement.bindDouble(6, entity.getCurrentReductionPercent());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindLong(9, entity.getStartDate());
        statement.bindLong(10, entity.getTargetDate());
        statement.bindLong(11, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfCarbonGoal = new EntityDeletionOrUpdateAdapter<CarbonGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `carbon_goals` SET `id` = ?,`userId` = ?,`title` = ?,`description` = ?,`targetReductionPercent` = ?,`currentReductionPercent` = ?,`category` = ?,`active` = ?,`startDate` = ?,`targetDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CarbonGoal entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        statement.bindDouble(5, entity.getTargetReductionPercent());
        statement.bindDouble(6, entity.getCurrentReductionPercent());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindLong(9, entity.getStartDate());
        statement.bindLong(10, entity.getTargetDate());
        statement.bindLong(11, entity.getCreatedAt());
        if (entity.getId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeactivateGoal = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE carbon_goals SET active = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateProgress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE carbon_goals SET currentReductionPercent = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final CarbonGoal goal) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCarbonGoal.insert(goal);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CarbonGoal goal) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCarbonGoal.handle(goal);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deactivateGoal(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateGoal.acquire();
    int _argIndex = 1;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeactivateGoal.release(_stmt);
    }
  }

  @Override
  public void updateProgress(final String id, final double percent) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateProgress.acquire();
    int _argIndex = 1;
    _stmt.bindDouble(_argIndex, percent);
    _argIndex = 2;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateProgress.release(_stmt);
    }
  }

  @Override
  public LiveData<List<CarbonGoal>> getActiveGoals() {
    final String _sql = "SELECT * FROM carbon_goals WHERE active = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_goals"}, false, new Callable<List<CarbonGoal>>() {
      @Override
      @Nullable
      public List<CarbonGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "targetReductionPercent");
          final int _cursorIndexOfCurrentReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "currentReductionPercent");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CarbonGoal> _result = new ArrayList<CarbonGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonGoal _item;
            _item = new CarbonGoal();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item.setUserId(_tmpUserId);
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
            final double _tmpTargetReductionPercent;
            _tmpTargetReductionPercent = _cursor.getDouble(_cursorIndexOfTargetReductionPercent);
            _item.setTargetReductionPercent(_tmpTargetReductionPercent);
            final double _tmpCurrentReductionPercent;
            _tmpCurrentReductionPercent = _cursor.getDouble(_cursorIndexOfCurrentReductionPercent);
            _item.setCurrentReductionPercent(_tmpCurrentReductionPercent);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            _item.setActive(_tmpActive);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            _item.setStartDate(_tmpStartDate);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            _item.setTargetDate(_tmpTargetDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item.setCreatedAt(_tmpCreatedAt);
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
  public LiveData<List<CarbonGoal>> getAllGoals() {
    final String _sql = "SELECT * FROM carbon_goals ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_goals"}, false, new Callable<List<CarbonGoal>>() {
      @Override
      @Nullable
      public List<CarbonGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "targetReductionPercent");
          final int _cursorIndexOfCurrentReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "currentReductionPercent");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<CarbonGoal> _result = new ArrayList<CarbonGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonGoal _item;
            _item = new CarbonGoal();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _item.setUserId(_tmpUserId);
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
            final double _tmpTargetReductionPercent;
            _tmpTargetReductionPercent = _cursor.getDouble(_cursorIndexOfTargetReductionPercent);
            _item.setTargetReductionPercent(_tmpTargetReductionPercent);
            final double _tmpCurrentReductionPercent;
            _tmpCurrentReductionPercent = _cursor.getDouble(_cursorIndexOfCurrentReductionPercent);
            _item.setCurrentReductionPercent(_tmpCurrentReductionPercent);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            _item.setActive(_tmpActive);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            _item.setStartDate(_tmpStartDate);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            _item.setTargetDate(_tmpTargetDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item.setCreatedAt(_tmpCreatedAt);
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
  public LiveData<CarbonGoal> getById(final String id) {
    final String _sql = "SELECT * FROM carbon_goals WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_goals"}, false, new Callable<CarbonGoal>() {
      @Override
      @Nullable
      public CarbonGoal call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "targetReductionPercent");
          final int _cursorIndexOfCurrentReductionPercent = CursorUtil.getColumnIndexOrThrow(_cursor, "currentReductionPercent");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final CarbonGoal _result;
          if (_cursor.moveToFirst()) {
            _result = new CarbonGoal();
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            _result.setUserId(_tmpUserId);
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
            final double _tmpTargetReductionPercent;
            _tmpTargetReductionPercent = _cursor.getDouble(_cursorIndexOfTargetReductionPercent);
            _result.setTargetReductionPercent(_tmpTargetReductionPercent);
            final double _tmpCurrentReductionPercent;
            _tmpCurrentReductionPercent = _cursor.getDouble(_cursorIndexOfCurrentReductionPercent);
            _result.setCurrentReductionPercent(_tmpCurrentReductionPercent);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _result.setCategory(_tmpCategory);
            final boolean _tmpActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActive);
            _tmpActive = _tmp != 0;
            _result.setActive(_tmpActive);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            _result.setStartDate(_tmpStartDate);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            _result.setTargetDate(_tmpTargetDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result.setCreatedAt(_tmpCreatedAt);
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
