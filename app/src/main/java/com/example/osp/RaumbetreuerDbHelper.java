package com.example.osp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

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
            String content = new Scanner(new File("src/main/res/raumbetreuer.sql")).useDelimiter("\\Z").next();
            System.out.println(content);
            db.execSQL(content);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Tabelle: " + tableName + " konnte nicht erzeugt werden.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
