package com.example.osp.data.pojo;

/**
 * Klasse um einen Raum aus der DB zu halten
 */
public class Raum {
    public final String raumName;
    public final String raumBetreuer;

    public Raum(String raumName, String raumBetreuer) {
        this.raumName = raumName;
        this.raumBetreuer = raumBetreuer;
    }
}
