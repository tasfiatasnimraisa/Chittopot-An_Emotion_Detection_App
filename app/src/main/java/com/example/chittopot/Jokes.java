package com.example.chittopot;

public class Jokes {

    private String id;
    private String jokesText;
    private String jokescatagoryText;

    public Jokes() {
        // Default constructor required for calls to DataSnapshot.getValue(Jokes.class)
    }

    public Jokes(String id, String jokesText, String jokescatagoryText) {
        this.id = id;
        this.jokesText = jokesText;
        this.jokescatagoryText = jokescatagoryText;
    }

    public String getId() {
        return id;
    }

    public String getJokesText() {
        return jokesText;
    }

    public String getJokescatagoryText() {
        return jokescatagoryText;
    }
}
