package com.example.osp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RaumbetreuerDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = RaumbetreuerDbHelper.class.getSimpleName();

    public RaumbetreuerDbHelper(Context context) {
        super(context, "raumbetreuer.db", null, 1);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableName = "Raum";
        String raumeName = "raumName";
        String raumBetreuer = "raumBetreuer";
        try {
            db.execSQL("CREATE TABLE "+ tableName +
                    " ("+raumeName+" VARCHAR(255) PRIMARY KEY, "+raumBetreuer+" VARCHAR(255));"); // sql script
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
