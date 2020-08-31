package com.threedots.brri;

public class RiceSpecies {
    String name;
    int id;
    int salinityScore;
    int submergenceScore;
    public static final int SUBMERGENCE = 1;
    public static final int SALINITY = 2;
    public static final int ID = 3;
    public static final int NAME = 4;
    public static final int OTHER = 5;



    public RiceSpecies(int id, String name) {
        this.id = id;
        this.name = name;
        this.salinityScore = -1;
        this.submergenceScore = -1;
    }

    public void setSalinityScore(int salinityScore) {
        this.salinityScore = salinityScore;
    }

    public void setSubmergenceScore(int submergenceScore) {
        this.submergenceScore = submergenceScore;
    }
}
