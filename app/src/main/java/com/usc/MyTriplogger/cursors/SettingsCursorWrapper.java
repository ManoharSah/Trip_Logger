package com.usc.MyTriplogger.cursors;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.usc.MyTriplogger.database.TripDbSchema;
import com.usc.MyTriplogger.models.Settings;

import java.util.UUID;


public class SettingsCursorWrapper extends CursorWrapper {

    public SettingsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Settings getSetting(){

        String id = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.ID));
        String name = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.NAME));
        String email = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.COMMENT));
        String uuidString  = getString(getColumnIndex(TripDbSchema.SettingsTable.Cols.UUID));

        Settings setting = new Settings(UUID.fromString(uuidString));
        setting.setName(name);
        setting.setId(id);
        setting.setEmail(email);
        setting.setGender(gender);
        setting.setComment(comment);
        return setting;

    }

}
