package com.mmn.circuitscourts.models;

public class Vehicule {
    private String numImmate;
    private int poidsMax;

    public Vehicule(String numImmate, int poidsmax) {
        this.numImmate = numImmate;
        this.poidsMax = poidsmax;
    }

    public String getNumImmate() {
        return numImmate;
    }

    public void setNumImmate(String numImmate) {
        this.numImmate = numImmate;
    }

    public int getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(int poidsMax) {
        this.poidsMax = poidsMax;
    }
}
