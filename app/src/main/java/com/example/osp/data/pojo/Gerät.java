package com.example.osp.data.pojo;

public class Gerät extends Inventar{
    public final String geraeteName;

    public Gerät(int id, int xKoordinate, int yKoordinate, String raumName, String geraeteName) {
        super(id, xKoordinate, yKoordinate, raumName);
        this.geraeteName = geraeteName;
    }
}
