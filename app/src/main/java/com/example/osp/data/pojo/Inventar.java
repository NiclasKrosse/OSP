package com.example.osp.data.pojo;

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
