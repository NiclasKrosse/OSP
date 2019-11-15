package com.example.osp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.osp.data.pojo.Geraet;
import com.example.osp.data.pojo.Raum;
import com.example.osp.data.pojo.Standardfehler;
import com.example.osp.data.pojo.Ticket;

import java.lang.reflect.Array;

public class RaumbetreuerDataSource {
    private SQLiteDatabase database;
    private RaumbetreuerDbHelper dbHelper;

    public RaumbetreuerDataSource(Context context) {
        dbHelper = new RaumbetreuerDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Funktion um einen Raum in die DB zu schreiben
     * @param raum Raum, der in die DB geschrieben wird.
     */
    public void insertRaum(Raum raum) {
        ContentValues values = new ContentValues();
        values.put("Raumname", raum.raumName);
        values.put("Raumbetreuer", raum.raumBetreuer);

        database.insert("Raum", null, values);
    }

    /**
     * Funktion um ein Gerät in die DB zu schreiben
     * @param geraet Gerät, dass in die DB geschrieben wird
     */
    public void insertGeraet(Geraet geraet) {
        ContentValues values = new ContentValues();
        values.put("Geraetename", geraet.geraeteName);
        values.put("Geraetenummer", geraet.id);
        values.put("YKoordinate", geraet.yKoordinate);
        values.put("XKoordinate", geraet.xKoordinate);
        values.put("Raumname", geraet.raumName);

        database.insert("Geraet", null, values);
    }

    /**
     * Funktion um ein Ticket in die DB zu schreiben
     * @param ticket Ticket, dass in die DB geschrieben wird
     */
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

    /**
     * Alle Räume aus der DB
     * @return Räume als Array
     */
    public Raum[] selectRaeume() {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(Raumname) FROM Raum", null);
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

    /**
     * Alle Geräte für einen bestimmten Raum, die in der DB gespeichert sind
     * @param raumname Raum für den die Geräte ausgelesen werden sollen
     * @return Geräte als Array
     */
    public Geraet[] selectGeraete(String raumname)
    {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(GeraeteID) FROM Geraet WHERE raumname = ?", new String[] {raumname});
        if(c.moveToFirst()){
            int groesse = c.getInt(0);
            Geraet[] geraete = new Geraet[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM Geraet where raumname = ?", new String[] {raumname});
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
                } while (c1.moveToNext());
                return geraete;
            }
        }
        return null;
    }

    /**
     * Funktion, die alle Standardfehler aus der DB ausliest
     * @return Standardfehler aus der DB als Array
     */
    public Standardfehler[] selectStandardfehler() {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(Fehlername) FROM STANDARDFEHLER", null);
        if(c.moveToFirst()) {
            int groesse = c.getInt(0);
            Standardfehler[] fehler = new Standardfehler[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM STANDARDFEHLER", null);
            if(c1.moveToFirst()){
                do{
                    String name = c1.getString(0);
                    String beschreibung = c1.getString(1);
                    String prio = c1.getString(2);

                    Standardfehler f = new Standardfehler(name, beschreibung, prio);
                    fehler[counter] = f;
                    counter++;
                } while (c1.moveToNext());
                return fehler;
            }
        }
        return null;
    }

    /**
     * Funktion, die alle Tickets mit der übergebenen @param GeaeteID aus der DB ausliest
     * @param GeraeteId Die GeraeteID für die, alle Tickets ausgeliefert werden
     * @return alle Tickets in einem Array
     */

    public Ticket[] selectTickets(int GeraeteId) {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(TicketID) FROM Ticket where GeraeteID = ?", new String[] {Integer.toString(GeraeteId)});
        if(c.moveToFirst()) {
            int groesse = c.getInt(0);
            Ticket[] tickets = new Ticket[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM Ticket where GeraeteID = ?", new String[] {Integer.toString(GeraeteId)});
            if(c1.moveToFirst()){
                do{
                    String id = c1.getString(0);
                    String status = c1.getString(1);
                    int gID = c1.getInt(2);
                    String fehlername = c1.getString(3);

                    Ticket ticket = new Ticket(id, status, gID, fehlername);
                    tickets[counter] = ticket;
                    counter++;
                } while (c1.moveToNext());
                return tickets;
            }
        }
        return null;
    }

    /**
     * Funktion, die alle Tickets aus der DB ausliest
     * @return alle Tickets in einem Array
     */
    public Ticket[] selectTickets() {
        int counter = 0;
        Cursor c = database.rawQuery("SELECT COUNT(TicketID) FROM Ticket", null);
        if(c.moveToFirst()) {
            int groesse = c.getInt(0);
            Ticket[] tickets = new Ticket[groesse];

            Cursor c1 = database.rawQuery("SELECT * FROM Ticket", null);
            if(c1.moveToFirst()){
                do{
                    String id = c1.getString(0);
                    String status = c1.getString(1);
                    int gID = c1.getInt(2);
                    String fehlername = c1.getString(3);

                    Ticket ticket = new Ticket(id, status, gID, fehlername);
                    tickets[counter] = ticket;
                    counter++;
                } while (c1.moveToNext());
                return tickets;
            }
        }
        return null;
    }
}
