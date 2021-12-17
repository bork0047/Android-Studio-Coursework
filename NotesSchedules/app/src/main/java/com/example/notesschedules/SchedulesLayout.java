package com.example.notesschedules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//just a leftover of an attempt of making a schedules app + callender
public class SchedulesLayout {

    String Title,Description;
    int ScreenImg;

    public SchedulesLayout(String title, String description, int screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}