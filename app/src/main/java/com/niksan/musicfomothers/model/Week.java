package com.niksan.musicfomothers.model;

public class Week {
    private int id;
    private String title;

    public Week(int id) {
        this.id = id;
    }

    public Week(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
