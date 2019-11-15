package com.example.osp.data.pojo;

/**
 * Klasse um einen Standardfehler aus der DB zu halten
 */
public class Standardfehler {

    public final String fehlerName;
    public final String fehlerBeschreibung;
    public final String priorität;

    public Standardfehler(String fehlerName, String fehlerBeschreibung, String priorität) {
        this.fehlerName = fehlerName;
        this.fehlerBeschreibung = fehlerBeschreibung;
        this.priorität = priorität;
    }
}
