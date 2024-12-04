package com.example.instagramemulator;

import java.util.ArrayList;

public class Profile { // Changed access modifier to 'public'
    String name;
    private final ArrayList<String> imagePaths = new ArrayList<>();
    private String profilePhoto;

    public Profile(String name) {
        this.name = name;
    }

    public void addImage(String imagePath) {
        imagePaths.add(imagePath);
    }

    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    public void setProfilePhoto(String photoPath) {
        this.profilePhoto = photoPath;
    }

    public String getProfilePhoto() {
        return profilePhoto != null ? profilePhoto : getClass().getResource("DefaultProfile.png").toString();
    }
}
