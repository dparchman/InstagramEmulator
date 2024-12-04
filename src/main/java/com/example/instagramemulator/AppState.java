package com.example.instagramemulator;

import com.example.instagramemulator.Profile;

import java.util.ArrayList;

public class AppState {
    private static AppState instance;
    private final ArrayList<Profile> profileData;

    private AppState() {
        profileData = new ArrayList<>();
    }

    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public ArrayList<Profile> getProfileData() {
        return profileData;
    }
}
