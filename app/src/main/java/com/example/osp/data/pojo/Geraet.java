package com.example.osp.data.pojo;

/**
 * Klasse um ein Gerät aus der DB zu halten
 */
public class Geraet extends Inventar{
    public final String geraeteName;

    public Geraet(int id, int xKoordinate, int yKoordinate, String raumName, String geraeteName) {
        super(id, xKoordinate, yKoordinate, raumName);
        this.geraeteName = geraeteName;
    }
}
