package com.coderswise.lba.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jakir Hossain
 * Date: 2/27/2017.
 */
public class SQL_Helper extends SQLiteOpenHelper {

    private static final String TAG = SQL_Helper.class.getSimpleName();
    SQLiteDatabase database;
    private static final String DB_NAME = "DB_LBA";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "alarm";
    private static final String ID_FIELD = "_id";
    private static final String TITLE_FIELD = "title";
    private static final String NOTIFICATION_FIELD = "notification";
    private static final String LATITUDE_FIELD = "latitude";
    private static final String LONGITUDE_FIELD = "longitude";
    private static final String STATUS_FIELD = "status";

    private static final String SQL = "CREATE TABLE " + TABLE_NAME + " ("
            + ID_FIELD + " INTEGER PRIMARY KEY, " + TITLE_FIELD + " text, "
            + NOTIFICATION_FIELD + " text, " + LATITUDE_FIELD + " double, "
            + LONGITUDE_FIELD + " double, " + STATUS_FIELD + " integer )";

    public SQL_Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private void openDB() {
        database = this.getWritableDatabase();
    }

    private void closeDB() {
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNewAlarm(ModelAlarmList model_alarmList) {
        openDB();
        ContentValues values = new ContentValues();
        values.put(LATITUDE_FIELD, model_alarmList.getLatitude());
        values.put(LONGITUDE_FIELD, model_alarmList.getLongitude());
        values.put(NOTIFICATION_FIELD, model_alarmList.getAlarmNotification());
        values.put(TITLE_FIELD, model_alarmList.getLocationTitle());
        values.put(STATUS_FIELD, model_alarmList.getState());
        long insert = database.insert(TABLE_NAME, null, values);
        closeDB();
        return insert;
    }

    public ArrayList<ModelAlarmList> getAlarms() {

        ArrayList<ModelAlarmList> list = new ArrayList<>();
        openDB();

        Cursor cursor = database
                .query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));

                String title = cursor.getString(cursor
                        .getColumnIndex(TITLE_FIELD));
                String notification = cursor.getString(cursor
                        .getColumnIndex(NOTIFICATION_FIELD));
                String latitude = cursor.getString(cursor
                        .getColumnIndex(LATITUDE_FIELD));
                String longitude = cursor.getString(cursor
                        .getColumnIndex(LONGITUDE_FIELD));
                int state = cursor.getInt(cursor.getColumnIndex(STATUS_FIELD));
                ModelAlarmList alarm = new ModelAlarmList(id, title, notification,
                        latitude, longitude, state);
                list.add(alarm);

                cursor.moveToNext();
            }
        }
        cursor.close();
        closeDB();
        return list;
    }

    public ArrayList<ModelAlarmList> getAlarmByID(int _id) {

        ArrayList<ModelAlarmList> list = new ArrayList<>();
        openDB();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_FIELD + " = " + _id;
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
            String title = cursor.getString(cursor
                    .getColumnIndex(TITLE_FIELD));
            String notification = cursor.getString(cursor
                    .getColumnIndex(NOTIFICATION_FIELD));
            String latitude = cursor.getString(cursor
                    .getColumnIndex(LATITUDE_FIELD));
            String longitude = cursor.getString(cursor
                    .getColumnIndex(LONGITUDE_FIELD));
            int state = cursor.getInt(cursor.getColumnIndex(STATUS_FIELD));
            ModelAlarmList alarm = new ModelAlarmList(id, title, notification,
                    latitude, longitude, state);
            list.add(alarm);

            cursor.moveToNext();
        }
        cursor.close();
        closeDB();
        return list;
    }

    public void deleteAlarm(long id) {
        openDB();
        database.delete(TABLE_NAME, ID_FIELD + "=?",
                new String[]{String.valueOf(id)});

        closeDB();
    }

    public void allDelete() {
        openDB();
        database.delete(TABLE_NAME, null, null);
        closeDB();
    }

    public void updateState(int id, int state) {
        openDB();
        ContentValues values = new ContentValues();
        values.put(STATUS_FIELD, state);

        database.update(TABLE_NAME, values, ID_FIELD + "=?",
                new String[]{String.valueOf(id)});
        Log.d(TAG, "Stat Update on ID= " + id + " and State= " + state);
        closeDB();
    }

    public long updateAlarm(int id, ModelAlarmList model_alarmList) {
        openDB();
        ContentValues values = new ContentValues();

        values.put(LATITUDE_FIELD, model_alarmList.getLatitude());
        values.put(LONGITUDE_FIELD, model_alarmList.getLongitude());
        values.put(NOTIFICATION_FIELD, model_alarmList.getAlarmNotification());
        values.put(TITLE_FIELD, model_alarmList.getLocationTitle());
        values.put(STATUS_FIELD, model_alarmList.getState());

        long update = database.update(TABLE_NAME, values, ID_FIELD + "=?",
                new String[]{String.valueOf(id)});
        closeDB();
        return update;
    }

    public ArrayList<ModelAlarmList> getActiveAlarm() {
        ArrayList<ModelAlarmList> list = new ArrayList<>();
        openDB();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + STATUS_FIELD + " = 1";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
            String title = cursor.getString(cursor
                    .getColumnIndex(TITLE_FIELD));
            String notification = cursor.getString(cursor
                    .getColumnIndex(NOTIFICATION_FIELD));
            String latitude = cursor.getString(cursor
                    .getColumnIndex(LATITUDE_FIELD));
            String longitude = cursor.getString(cursor
                    .getColumnIndex(LONGITUDE_FIELD));
            int state = cursor.getInt(cursor.getColumnIndex(STATUS_FIELD));
            ModelAlarmList alarm = new ModelAlarmList(id, title, notification,
                    latitude, longitude, state);
            list.add(alarm);

            cursor.moveToNext();
        }
        cursor.close();
        closeDB();
        return list;
    }
}
