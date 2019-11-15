package com.example.osp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.osp.RaumbetreuerDbHelper;

public class RaumbetreuerDataSource {
    private SQLiteDatabase database;
    private RaumbetreuerDbHelper dbHelper;

    public RaumbetreuerDataSource(Context context) {
        dbHelper = new RaumbetreuerDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }


}
