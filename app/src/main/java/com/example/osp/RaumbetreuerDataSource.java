package com.example.osp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.osp.data.pojo.Geraet;
import com.example.osp.data.pojo.Raum;
import com.example.osp.data.pojo.Ticket;

import java.lang.reflect.Array;

public class RaumbetreuerDataSource {
    private SQLiteDatabase database;
    private RaumbetreuerDbHelper dbHelper;

    public RaumbetreuerDataSource(Context context) {
        dbHelper = new RaumbetreuerDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void insertRaum(Raum raum) {
        ContentValues values = new ContentValues();
        values.put("Raumname", raum.raumName);
        values.put("Raumbetreuer", raum.raumBetreuer);

        database.insert("Raum", null, values);
    }

    public void insertGeraet(Geraet geraet) {
        ContentValues values = new ContentValues();
        values.put("Gerätename", geraet.geraeteName);
        values.put("Gerätenummer", geraet.id);
        values.put("YKoordinate", geraet.yKoordinate);
        values.put("XKoordinate", geraet.xKoordinate);
        values.put("Raumename", geraet.raumName);

        database.insert("Geraet", null, values);
    }

    public void insertTicket(Ticket ticket) {
        ContentValues values = new ContentValues();
        values.put("TicketID", ticket.ticketID);
        values.put("Fehlername", ticket.fehlerName);
        values.put("Gerätenummer", ticket.geraeteNummer);
        values.put("Status", ticket.status);
    }

    public Raum selectRaum(String name){
        Cursor c = database.rawQuery("SELECT * FROM Raum WHERE Raumname = ?", new String[]{name});
        if (c.moveToFirst()){

                String raumName = c.getString(0);
                String raumBetreuer = c.getString(1);

            return new Raum(raumName, raumBetreuer);
        }
        return null;
    }

    public Raum[] selectRaeume() {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(raumName) FROM Raum", null);
        if (c.moveToFirst()){
            int groesse = c.getInt(0);
            Raum[] raeume = new Raum[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM Raum", null);
            if (c1.moveToFirst()){
                do {
                    String column1 = c1.getString(0);
                    String column2 = c1.getString(1);

                    Raum raum = new Raum(column1, column2);
                    raeume[counter] = raum;
                    counter++;
                } while(c1.moveToNext());
            }
            return raeume;
        }
        return null;
    }

    public Geraet selectGeraet(int id) {
        database.rawQuery("SELECT * FROM Geraet WHERE Geraetenummer = ?", new String[] {Integer.toString(id)} );


        return null;
    }

    public Geraet[] selectGeraete() {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(id) FROM Geraet", null);
        if(c.moveToFirst()){
            int groesse = c.getInt(0);
            Geraet[] geraete = new Geraet[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM Geraet", null);
            if(c1.moveToFirst()) {
                do{
                    int id = c1.getInt(0);
                    String name = c1.getString(1);
                    int y = c1.getInt(2);
                    int x = c1.getInt(3);
                    String raum = c1.getString(4);

                    Geraet geraet = new Geraet(id, x, y, raum, name);
                    geraete[counter] = geraet;
                    counter++;
                } while (c.moveToNext());
                return geraete;
            }
        }
        return null;
    }
}
