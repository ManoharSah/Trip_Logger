package com.usc.MyTriplogger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;


public class TripHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "TripLogs.db";

    public TripHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TripDbSchema.ActivityTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TripDbSchema.ActivityTable.Cols.UUID + ", "+
                TripDbSchema.ActivityTable.Cols.TYPE + ", "+
                TripDbSchema.ActivityTable.Cols.DATE + ", "+
                TripDbSchema.ActivityTable.Cols.TITLE + ", "+
                TripDbSchema.ActivityTable.Cols.COMMENT + ", "+
                TripDbSchema.ActivityTable.Cols.DURATION + ", "+
                TripDbSchema.ActivityTable.Cols.LOCATION + ", "+
                TripDbSchema.ActivityTable.Cols.DESTINATION +
            ")"
        );

        db.execSQL("create table " + TripDbSchema.SettingsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TripDbSchema.SettingsTable.Cols.ID + ", "+
                TripDbSchema.SettingsTable.Cols.UUID + ", "+
                TripDbSchema.SettingsTable.Cols.NAME + ", "+
                TripDbSchema.SettingsTable.Cols.EMAIL + ", "+
                TripDbSchema.SettingsTable.Cols.GENDER + ", "+
                TripDbSchema.SettingsTable.Cols.COMMENT +
                ")"
        );

        db.execSQL("insert into " + TripDbSchema.SettingsTable.NAME + "(" +
                TripDbSchema.SettingsTable.Cols.ID + ", "+
                TripDbSchema.SettingsTable.Cols.UUID + ", "+
                TripDbSchema.SettingsTable.Cols.NAME + ", "+
                TripDbSchema.SettingsTable.Cols.EMAIL + ", "+
                TripDbSchema.SettingsTable.Cols.GENDER + ", "+
                TripDbSchema.SettingsTable.Cols.COMMENT +
                ") values ('1106250','"+ UUID.randomUUID()+"','Manohar Sah','M_S291@student.usc.edu.au','Male','No Comments')"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
