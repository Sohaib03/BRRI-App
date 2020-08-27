package com.threedots.brri;

public class RiceSpecies {
    String name;
    int id;
    int salinityScore;
    int submergenceScore;

    public RiceSpecies(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setSalinityScore(int salinityScore) {
        this.salinityScore = salinityScore;
    }

    public void setSubmergenceScore(int submergenceScore) {
        this.submergenceScore = submergenceScore;
    }
}
