package com.usc.MyTriplogger.cursors;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.MyTriplogger.database.TripDbSchema;
import com.usc.MyTriplogger.models.Trip;

import java.util.Date;
import java.util.UUID;


public class TripCursorWrapper extends CursorWrapper {

    public TripCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Trip getActivity(){

        long date = getLong(getColumnIndex(TripDbSchema.ActivityTable.Cols.DATE));
        String type = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.TYPE));
        String title = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.TITLE));
        String comment = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.COMMENT));
        String uuidString  = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.UUID));
        String duration = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.DURATION));
        String location = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.LOCATION));
        String destination = getString(getColumnIndex(TripDbSchema.ActivityTable.Cols.DESTINATION));

        Trip activity = new Trip(UUID.fromString(uuidString));
        activity.setType(type);
        activity.setTitle(title);
        activity.setComment(comment);
        activity.setDuration(duration);
        activity.setLocation(location);
        activity.setDestination(destination);
        activity.setDate(new Date(date).toString());
        return activity;

    }

}
