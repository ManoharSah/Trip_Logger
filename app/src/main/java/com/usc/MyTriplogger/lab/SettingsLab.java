package com.usc.MyTriplogger.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.MyTriplogger.database.TripDbSchema;
import com.usc.MyTriplogger.models.Settings;
import com.usc.MyTriplogger.cursors.SettingsCursorWrapper;
import com.usc.MyTriplogger.database.TripHelper;

import java.util.ArrayList;
import java.util.List;

public class SettingsLab {

    private static SettingsLab sSettingsLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Settings> mSettings;

    public static SettingsLab get(Context context){
        if(sSettingsLab == null){
            sSettingsLab = new SettingsLab(context);
        }
        return sSettingsLab;
    }

    private SettingsLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new TripHelper(mContext).getWritableDatabase();

    }

    public Settings getSettings(){
        List<Settings> settings = new ArrayList<>();
        SettingsCursorWrapper cursor = querySettings(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                settings.add(cursor.getSetting());
                cursor.moveToNext();
            }
        } finally {

        }

        return settings.get(0);

    }

    public void updateSettings(Settings s){

        String uuidString = s.getStudentID().toString();
        ContentValues values = getContentValues(s);

        mDatabase.update(TripDbSchema.SettingsTable.NAME, values, TripDbSchema.SettingsTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    private static ContentValues getContentValues(Settings settings){

        ContentValues values = new ContentValues();
        values.put(TripDbSchema.SettingsTable.Cols.ID,settings.getId());
        values.put(TripDbSchema.SettingsTable.Cols.NAME,settings.getName());
        values.put(TripDbSchema.SettingsTable.Cols.EMAIL,settings.getEmail());
        values.put(TripDbSchema.SettingsTable.Cols.GENDER,settings.getGender());
        values.put(TripDbSchema.SettingsTable.Cols.COMMENT,settings.getComment());
        values.put(TripDbSchema.SettingsTable.Cols.UUID,settings.getStudentID().toString());

        return values;
    }

    private SettingsCursorWrapper querySettings(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TripDbSchema.SettingsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SettingsCursorWrapper(cursor);
    }

}
