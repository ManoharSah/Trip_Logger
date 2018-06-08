package com.usc.MyTriplogger.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usc.MyTriplogger.database.TripDbSchema;
import com.usc.MyTriplogger.cursors.TripCursorWrapper;
import com.usc.MyTriplogger.database.TripHelper;
import com.usc.MyTriplogger.models.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LogLab {

    private static LogLab sMyActivitiesLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private List<Trip> mMyActivities;

    public static LogLab get(Context context){
        if(sMyActivitiesLab == null){
            sMyActivitiesLab = new LogLab(context);
        }
        return sMyActivitiesLab;
    }

    private LogLab(Context context){

        mContext = context.getApplicationContext();
        mDatabase = new TripHelper(mContext).getWritableDatabase();

    }

    public Trip addActivity(Trip t){
        ContentValues values = getContentValues(t);

        mDatabase.insert(TripDbSchema.ActivityTable.NAME,null,values);
        return t;
    }

    public List<Trip> getActivities(){
        List<Trip> activities = new ArrayList<>();
        TripCursorWrapper cursor = queryActivity(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                activities.add(cursor.getActivity());
                cursor.moveToNext();
            }
        } finally {

        }

        return activities;

    }

    public Trip getActivities(UUID id){

        TripCursorWrapper cursor = queryActivity(TripDbSchema.ActivityTable.Cols.UUID + " =?",new String[]{ id.toString() });

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getActivity();

        } finally {
            cursor.close();
        }

    }

    public void updateLog(Trip t){

        String uuidString = t.getId().toString();
        ContentValues values = getContentValues(t);

        mDatabase.update(TripDbSchema.ActivityTable.NAME, values, TripDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });

    }

    public void deleteLog(Trip t){
        String uuidString = t.getId().toString();
        mDatabase.delete(TripDbSchema.ActivityTable.NAME, TripDbSchema.ActivityTable.Cols.UUID + " =?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(Trip activities){

        ContentValues values = new ContentValues();
        values.put(TripDbSchema.ActivityTable.Cols.UUID,activities.getId().toString());
        values.put(TripDbSchema.ActivityTable.Cols.TITLE,activities.getTitle());
        values.put(TripDbSchema.ActivityTable.Cols.COMMENT,activities.getComment());
        values.put(TripDbSchema.ActivityTable.Cols.DURATION,activities.getDuration());
        values.put(TripDbSchema.ActivityTable.Cols.LOCATION,activities.getLocation());
        values.put(TripDbSchema.ActivityTable.Cols.TYPE,activities.getType());
        values.put(TripDbSchema.ActivityTable.Cols.DATE,activities.getDate());
        values.put(TripDbSchema.ActivityTable.Cols.DESTINATION,activities.getDestination());

        return values;
    }

    private TripCursorWrapper queryActivity(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                TripDbSchema.ActivityTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TripCursorWrapper(cursor);
    }

}
