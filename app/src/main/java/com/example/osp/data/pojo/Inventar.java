package com.example.osp.data.pojo;

/**
 * Klasse um ein Inventar aus der DB zu halten
 */
public class Inventar {

   public final int id;
   public final int xKoordinate;
   public final int yKoordinate;
   public final String raumName;

    public Inventar(int id, int xKoordinate, int yKoordinate, String raumName) {
        this.id = id;
        this.xKoordinate = xKoordinate;
        this.yKoordinate = yKoordinate;
        this.raumName = raumName;
    }
}
