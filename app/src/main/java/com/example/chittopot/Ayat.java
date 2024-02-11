package com.example.chittopot;

public class Ayat {

    private String id;
    private String ayatText;
    private String ayatCategoryText;

    public Ayat() {
        // Default constructor required for calls to DataSnapshot.getValue(Ayat.class)
    }

    public Ayat(String id, String ayatText, String ayatCategoryText) {
        this.id = id;
        this.ayatText = ayatText;
        this.ayatCategoryText = ayatCategoryText;
    }

    public String getId() {
        return id;
    }

    public String getAyatText() {
        return ayatText;
    }

    public String getAyatCategoryText() {
        return ayatCategoryText;
    }
}
