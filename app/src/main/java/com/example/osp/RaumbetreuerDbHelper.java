package com.example.osp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RaumbetreuerDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = RaumbetreuerDbHelper.class.getSimpleName();

    public RaumbetreuerDbHelper(Context context) {
        super(context, "raumbetreuer-test-3.db", null, 1);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS Standardfehler (\n" +
                    "\tFehlername VARCHAR(255) PRIMARY KEY,\n" +
                    "\tFehlerbeschreibung VARCHAR(255),\n" +
                    "\tPrioritaet\tVARCHAR(255)\n" +
                    ");");
            db.execSQL("CREATE TABLE IF NOT EXISTS Raum (\n" +
                    "\tRaumname\tVARCHAR(255) PRIMARY KEY,\n" +
                    "\tRaumbetreuer\tVARCHAR(255)\n" +
                    ");");
            db.execSQL("CREATE TABLE IF NOT EXISTS Geraet (\n" +
                    "\tGeraeteID\tINTEGER PRIMARY KEY ,\n" +
                    "\tGeraetename\tVARCHAR(255) NOT NULL,\n" +
                    "\txKoordinate\tINTEGER,\n" +
                    "\tyKoordinate\tINTEGER,\n" +
                    "\tRaumname\tVARCHAR(255),\n" +
                    "\tFOREIGN KEY(Raumname) REFERENCES Raum(Raumbetreuer)\n" +
                    ");");
            db.execSQL("CREATE TABLE IF NOT EXISTS Inventar (\n" +
                    "\tInventar\tVARCHAR(255) PRIMARY KEY,\n" +
                    "\txKoordinate\tINTEGER,\n" +
                    "\tyKoordinate\tINTEGER,\n" +
                    "\tRaumname\tVARCHAR(255),\n" +
                    "\tFOREIGN KEY(Raumname) REFERENCES Raum(Raumname)\n" +
                    ");");
            db.execSQL("CREATE TABLE IF NOT EXISTS Ticket (\n" +
                    "\tTicketID\tINTEGER PRIMARY KEY ,\n" +
                    "\tStatus\tVARCHAR(255),\n" +
                    "\tGeraeteID\tINTEGER,\n" +
                    "\tFehlername\tVARCHAR(255),\n" +
                    "\tFOREIGN KEY (GeraeteID) REFERENCES Geraete(GeraeteID),\n" +
                    "\tFOREIGN  KEY(Fehlername) REFERENCES Standardfehler(Fehlername)\n" +
                    ");");
            db.execSQL("CREATE TABLE IF NOT EXISTS Benutzer (\n" +
                    "\tRolle\tVARCHAR(255),\n" +
                    "\tEMail\tVARCHAR(255),\n" +
                    "\tPasswort\tVARCHAR(255)\n" +
                    ");");

            db.execSQL("INSERT INTO Raum ('Raumname', 'Raumbetreuer') VALUES ('C025', 'Herr Jansen');");
            db.execSQL("INSERT INTO Raum ('Raumname', 'Raumbetreuer') VALUES ('C013', 'Herr Mustermann');");
            db.execSQL("INSERT INTO Raum ('Raumname', 'Raumbetreuer') VALUES ('C006', 'Frau Doe');\n");

            db.execSQL("INSERT INTO Geraet('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname') VALUES (0, 'PC-11', 1, 1, 'C025')");
            db.execSQL("INSERT INTO Geraet('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null, 'PC-12', 1, 2, 'C025')");
            db.execSQL("INSERT INTO Geraet('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname') VALUES ( null,'PC-22', 2, 2, 'C025')");
            db.execSQL("INSERT INTO Geraet('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname') VALUES ( null,'PC-21', 2, 1, 'C025')");

            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null, 'PC-11', 1, 1, 'C006')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-12', 1, 2, 'C006')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-22', 2, 2, 'C006')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-21', 2, 1, 'C006')");

            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-11', 1, 1, 'C013')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-12', 1, 2, 'C013')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-22', 2, 2, 'C013')");
            db.execSQL("INSERT INTO Geraet ('GeraeteID', 'Geraetename', 'xKoordinate', 'yKoordinate', 'Raumname')VALUES ( null,'PC-21', 2, 1, 'C013')");

            db.execSQL("INSERT INTO Standardfehler ('Fehlername', 'Fehlerbeschreibung', 'Prioritaet')VALUES ('Hardware defekt', 'Hardware Fehler (z.B. Maus, Monitor, Tastatur defekt)', 'normal')");
            db.execSQL("INSERT INTO Standardfehler ('Fehlername', 'Fehlerbeschreibung', 'Prioritaet')VALUES ('Software defekt', 'Software Fehler (z.B. Windows startet nicht, Bluescreen', 'normal')");
            db.execSQL("INSERT INTO Standardfehler ('Fehlername', 'Fehlerbeschreibung', 'Prioritaet')VALUES ('Performance Probleme', 'Performance Probleme (z.B. Unverhältnismäßige  Ladezeiten)', 'niedrig')");
            db.execSQL("INSERT INTO Standardfehler ('Fehlername', 'Fehlerbeschreibung', 'Prioritaet')VALUES ('Netzwerk Fehler', 'Netzwerk Fehler (z..B. kein Zugriff auf Intranet oder Internet)', 'hoch')");
            db.execSQL("INSERT INTO Standardfehler ('Fehlername', 'Fehlerbeschreibung', 'Prioritaet')VALUES ('Unbekannter Fehler', '', 'hoch')");

            db.execSQL("INSERT INTO Ticket ('TicketID', 'Status', 'GeraeteID', 'Fehlername')VALUES (null, 'Offen', 1, 'Hardware defekt')");

        } catch (Exception e) {
            Log.d(LOG_TAG, "Tabellen konnten nicht erzeugt werden.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
