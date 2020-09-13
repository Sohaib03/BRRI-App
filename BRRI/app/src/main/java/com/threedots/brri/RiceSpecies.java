package com.threedots.brri;

public class RiceSpecies {
    String name;
    int id;
    int salinityScore;
    int submergenceScore;
    int coldToleranceScore;
    int droughtScore;
    public static final int SUBMERGENCE = 1;
    public static final int SALINITY = 2;
    public static final int ID = 3;
    public static final int DROUGHT = 4;
    public static final int COLD_TOLERANCE = 5;
    public static final int NAME = 6;




    public RiceSpecies(int id, String name) {
        this.id = id;
        this.name = name;
        this.salinityScore = -1;
        this.submergenceScore = -1;
        this.coldToleranceScore = -1;
        this.droughtScore = -1;
    }

    public void setSalinityScore(int salinityScore) {
        this.salinityScore = salinityScore;
    }

    public void setSubmergenceScore(int submergenceScore) {
        this.submergenceScore = submergenceScore;
    }

    public int get(int idx) {
        if (idx == SUBMERGENCE) return submergenceScore;
        if (idx == SALINITY) return salinityScore;
        if (idx == DROUGHT) return droughtScore;
        if (idx == COLD_TOLERANCE) return coldToleranceScore;
        if (idx == ID) return id;
        return -1;
    }


}
