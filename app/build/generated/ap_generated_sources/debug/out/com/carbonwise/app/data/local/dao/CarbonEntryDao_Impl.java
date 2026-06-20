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
import com.carbonwise.app.model.CarbonEntry;
import java.lang.Class;
import java.lang.Double;
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
public final class CarbonEntryDao_Impl implements CarbonEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CarbonEntry> __insertionAdapterOfCarbonEntry;

  private final EntityDeletionOrUpdateAdapter<CarbonEntry> __deletionAdapterOfCarbonEntry;

  private final EntityDeletionOrUpdateAdapter<CarbonEntry> __updateAdapterOfCarbonEntry;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  public CarbonEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCarbonEntry = new EntityInsertionAdapter<CarbonEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `carbon_entries` (`id`,`userId`,`category`,`subCategory`,`carbonValue`,`unit`,`timestamp`,`notes`,`synced`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CarbonEntry entity) {
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
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getSubCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSubCategory());
        }
        statement.bindDouble(5, entity.getCarbonValue());
        if (entity.getUnit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUnit());
        }
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        final int _tmp = entity.isSynced() ? 1 : 0;
        statement.bindLong(9, _tmp);
      }
    };
    this.__deletionAdapterOfCarbonEntry = new EntityDeletionOrUpdateAdapter<CarbonEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `carbon_entries` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CarbonEntry entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfCarbonEntry = new EntityDeletionOrUpdateAdapter<CarbonEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `carbon_entries` SET `id` = ?,`userId` = ?,`category` = ?,`subCategory` = ?,`carbonValue` = ?,`unit` = ?,`timestamp` = ?,`notes` = ?,`synced` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CarbonEntry entity) {
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
        if (entity.getCategory() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCategory());
        }
        if (entity.getSubCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSubCategory());
        }
        statement.bindDouble(5, entity.getCarbonValue());
        if (entity.getUnit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUnit());
        }
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
        final int _tmp = entity.isSynced() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getId());
        }
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE carbon_entries SET synced = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final CarbonEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCarbonEntry.insert(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<CarbonEntry> entries) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCarbonEntry.insert(entries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CarbonEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCarbonEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CarbonEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCarbonEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void markAsSynced(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
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
      __preparedStmtOfMarkAsSynced.release(_stmt);
    }
  }

  @Override
  public LiveData<CarbonEntry> getById(final String id) {
    final String _sql = "SELECT * FROM carbon_entries WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<CarbonEntry>() {
      @Override
      @Nullable
      public CarbonEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
          final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final CarbonEntry _result;
          if (_cursor.moveToFirst()) {
            _result = new CarbonEntry();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _result.setCategory(_tmpCategory);
            final String _tmpSubCategory;
            if (_cursor.isNull(_cursorIndexOfSubCategory)) {
              _tmpSubCategory = null;
            } else {
              _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
            }
            _result.setSubCategory(_tmpSubCategory);
            final double _tmpCarbonValue;
            _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
            _result.setCarbonValue(_tmpCarbonValue);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            _result.setUnit(_tmpUnit);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result.setTimestamp(_tmpTimestamp);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result.setNotes(_tmpNotes);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _result.setSynced(_tmpSynced);
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
  public LiveData<List<CarbonEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM carbon_entries ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntry>>() {
      @Override
      @Nullable
      public List<CarbonEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
          final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<CarbonEntry> _result = new ArrayList<CarbonEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntry _item;
            _item = new CarbonEntry();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final String _tmpSubCategory;
            if (_cursor.isNull(_cursorIndexOfSubCategory)) {
              _tmpSubCategory = null;
            } else {
              _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
            }
            _item.setSubCategory(_tmpSubCategory);
            final double _tmpCarbonValue;
            _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
            _item.setCarbonValue(_tmpCarbonValue);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            _item.setUnit(_tmpUnit);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item.setTimestamp(_tmpTimestamp);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.setNotes(_tmpNotes);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item.setSynced(_tmpSynced);
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
  public LiveData<List<CarbonEntry>> getEntriesForDay(final long startOfDay, final long endOfDay) {
    final String _sql = "SELECT * FROM carbon_entries WHERE timestamp >= ? AND timestamp < ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntry>>() {
      @Override
      @Nullable
      public List<CarbonEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
          final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<CarbonEntry> _result = new ArrayList<CarbonEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntry _item;
            _item = new CarbonEntry();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final String _tmpSubCategory;
            if (_cursor.isNull(_cursorIndexOfSubCategory)) {
              _tmpSubCategory = null;
            } else {
              _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
            }
            _item.setSubCategory(_tmpSubCategory);
            final double _tmpCarbonValue;
            _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
            _item.setCarbonValue(_tmpCarbonValue);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            _item.setUnit(_tmpUnit);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item.setTimestamp(_tmpTimestamp);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.setNotes(_tmpNotes);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item.setSynced(_tmpSynced);
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
  public LiveData<List<CarbonEntry>> getEntriesForWeek(final long startOfWeek,
      final long endOfWeek) {
    final String _sql = "SELECT * FROM carbon_entries WHERE timestamp >= ? AND timestamp < ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfWeek);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfWeek);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntry>>() {
      @Override
      @Nullable
      public List<CarbonEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
          final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<CarbonEntry> _result = new ArrayList<CarbonEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntry _item;
            _item = new CarbonEntry();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final String _tmpSubCategory;
            if (_cursor.isNull(_cursorIndexOfSubCategory)) {
              _tmpSubCategory = null;
            } else {
              _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
            }
            _item.setSubCategory(_tmpSubCategory);
            final double _tmpCarbonValue;
            _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
            _item.setCarbonValue(_tmpCarbonValue);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            _item.setUnit(_tmpUnit);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item.setTimestamp(_tmpTimestamp);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.setNotes(_tmpNotes);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item.setSynced(_tmpSynced);
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
  public LiveData<List<CarbonEntry>> getEntriesForMonth(final long startOfMonth,
      final long endOfMonth) {
    final String _sql = "SELECT * FROM carbon_entries WHERE timestamp >= ? AND timestamp < ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfMonth);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfMonth);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntry>>() {
      @Override
      @Nullable
      public List<CarbonEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
          final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<CarbonEntry> _result = new ArrayList<CarbonEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntry _item;
            _item = new CarbonEntry();
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.setCategory(_tmpCategory);
            final String _tmpSubCategory;
            if (_cursor.isNull(_cursorIndexOfSubCategory)) {
              _tmpSubCategory = null;
            } else {
              _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
            }
            _item.setSubCategory(_tmpSubCategory);
            final double _tmpCarbonValue;
            _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
            _item.setCarbonValue(_tmpCarbonValue);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            _item.setUnit(_tmpUnit);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item.setTimestamp(_tmpTimestamp);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item.setNotes(_tmpNotes);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item.setSynced(_tmpSynced);
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
  public LiveData<Double> getTotalForDay(final long startOfDay, final long endOfDay) {
    final String _sql = "SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= ? AND timestamp < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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

  @Override
  public LiveData<Double> getTotalForWeek(final long startOfWeek, final long endOfWeek) {
    final String _sql = "SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= ? AND timestamp < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfWeek);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfWeek);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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

  @Override
  public LiveData<Double> getTotalForMonth(final long startOfMonth, final long endOfMonth) {
    final String _sql = "SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= ? AND timestamp < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfMonth);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfMonth);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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

  @Override
  public LiveData<List<CarbonEntryDao.CategoryTotal>> getCategoryBreakdown(final long startTime,
      final long endTime) {
    final String _sql = "SELECT category, SUM(carbonValue) as total FROM carbon_entries WHERE timestamp >= ? AND timestamp < ? GROUP BY category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntryDao.CategoryTotal>>() {
      @Override
      @Nullable
      public List<CarbonEntryDao.CategoryTotal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfTotal = 1;
          final List<CarbonEntryDao.CategoryTotal> _result = new ArrayList<CarbonEntryDao.CategoryTotal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntryDao.CategoryTotal _item;
            _item = new CarbonEntryDao.CategoryTotal();
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _item.category = null;
            } else {
              _item.category = _cursor.getString(_cursorIndexOfCategory);
            }
            _item.total = _cursor.getDouble(_cursorIndexOfTotal);
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
  public LiveData<List<CarbonEntryDao.DailyTotal>> getDailyTotals(final long start,
      final long end) {
    final String _sql = "SELECT STRFTIME('%Y-%m-%d', timestamp/1000, 'unixepoch') as date, SUM(carbonValue) as total FROM carbon_entries WHERE timestamp >= ? AND timestamp < ? GROUP BY date ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, end);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<List<CarbonEntryDao.DailyTotal>>() {
      @Override
      @Nullable
      public List<CarbonEntryDao.DailyTotal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfTotal = 1;
          final List<CarbonEntryDao.DailyTotal> _result = new ArrayList<CarbonEntryDao.DailyTotal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CarbonEntryDao.DailyTotal _item;
            _item = new CarbonEntryDao.DailyTotal();
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _item.date = null;
            } else {
              _item.date = _cursor.getString(_cursorIndexOfDate);
            }
            _item.total = _cursor.getDouble(_cursorIndexOfTotal);
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
  public List<CarbonEntry> getUnsyncedEntries() {
    final String _sql = "SELECT * FROM carbon_entries WHERE synced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfSubCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subCategory");
      final int _cursorIndexOfCarbonValue = CursorUtil.getColumnIndexOrThrow(_cursor, "carbonValue");
      final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
      final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
      final List<CarbonEntry> _result = new ArrayList<CarbonEntry>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final CarbonEntry _item;
        _item = new CarbonEntry();
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
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        _item.setCategory(_tmpCategory);
        final String _tmpSubCategory;
        if (_cursor.isNull(_cursorIndexOfSubCategory)) {
          _tmpSubCategory = null;
        } else {
          _tmpSubCategory = _cursor.getString(_cursorIndexOfSubCategory);
        }
        _item.setSubCategory(_tmpSubCategory);
        final double _tmpCarbonValue;
        _tmpCarbonValue = _cursor.getDouble(_cursorIndexOfCarbonValue);
        _item.setCarbonValue(_tmpCarbonValue);
        final String _tmpUnit;
        if (_cursor.isNull(_cursorIndexOfUnit)) {
          _tmpUnit = null;
        } else {
          _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
        }
        _item.setUnit(_tmpUnit);
        final long _tmpTimestamp;
        _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
        _item.setTimestamp(_tmpTimestamp);
        final String _tmpNotes;
        if (_cursor.isNull(_cursorIndexOfNotes)) {
          _tmpNotes = null;
        } else {
          _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
        }
        _item.setNotes(_tmpNotes);
        final boolean _tmpSynced;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSynced);
        _tmpSynced = _tmp != 0;
        _item.setSynced(_tmpSynced);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Integer> getEntryCount() {
    final String _sql = "SELECT COUNT(*) FROM carbon_entries";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"carbon_entries"}, false, new Callable<Integer>() {
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

  @Override
  public List<String> getActiveDays() {
    final String _sql = "SELECT DISTINCT STRFTIME('%Y-%m-%d', timestamp/1000, 'unixepoch') FROM carbon_entries ORDER BY timestamp DESC";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
