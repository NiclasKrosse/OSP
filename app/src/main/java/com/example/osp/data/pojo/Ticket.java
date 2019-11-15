package com.example.osp.data.pojo;

/**
 * Klasse um ein Ticket aus der DB zu halten
 */
public class Ticket {

    public final String ticketID;
    public final String status;
    public final int geraeteNummer;
    public final String fehlerName;


    public Ticket(String ticketID, String status, int geraeteNummer, String fehlerName) {
        this.ticketID = ticketID;
        this.status = status;
        this.geraeteNummer = geraeteNummer;
        this.fehlerName = fehlerName;
    }


}
