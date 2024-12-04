package com.example.instagramemulator;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import java.util.ArrayList;

public class InstagramAppController {

    @FXML
    private VBox profileContainer; // Container for profile entries
    private final ArrayList<Profile> profileData = new ArrayList<>();

    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
        System.out.println("Primary stage set in InstagramAppController.");
    }

    @FXML
    public void initialize() {
        // Initialize profiles
        Profile yourProfile = new Profile("Your Profile");
        yourProfile.setProfilePhoto(getClass().getResource("DefaultProfile.png").toString());
        profileData.add(yourProfile);

        // Mike's profile with sample images
        Profile mike = new Profile("Mike");
        mike.setProfilePhoto(getClass().getResource("friend1_photo.png").toString());
        mike.addImage(getClass().getResource("mike_photo1.png").toString());
        mike.addImage(getClass().getResource("mike_photo2.png").toString());
        mike.addImage(getClass().getResource("mike_photo3.png").toString());
        profileData.add(mike);

        // Sulley's profile with sample images
        Profile sulley = new Profile("Sulley");
        sulley.setProfilePhoto(getClass().getResource("friend2_photo.png").toString());
        sulley.addImage(getClass().getResource("sulley_photo1.png").toString());
        sulley.addImage(getClass().getResource("sulley_photo2.png").toString());
        sulley.addImage(getClass().getResource("sulley_photo3.png").toString());
        profileData.add(sulley);

        populateProfiles();
    }

    @FXML
    private void viewYourProfile() {
        Profile yourProfile = profileData.stream()
                .filter(profile -> profile.name.equals("Your Profile"))
                .findFirst()
                .orElse(null);

        if (yourProfile != null) {
            openProfileView(yourProfile);
        } else {
            System.out.println("Your Profile not found.");
        }
    }

    private void populateProfiles() {
        profileContainer.getChildren().clear();

        for (Profile profile : profileData) {
            HBox profileBox = new HBox(10);
            profileBox.setPadding(new Insets(10));
            profileBox.setStyle("-fx-alignment: center-left;");

            ImageView profilePhotoView = new ImageView(new Image(profile.getProfilePhoto()));
            profilePhotoView.setFitWidth(40);
            profilePhotoView.setFitHeight(40);
            profilePhotoView.setPreserveRatio(true);

            Label profileNameLabel = new Label(profile.name);
            profileNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Button viewProfileButton = new Button("View Profile");
            viewProfileButton.setOnAction(event -> openProfileView(profile));
            viewProfileButton.setStyle("-fx-background-color: #3897f0; -fx-text-fill: white;");

            profileBox.getChildren().addAll(profilePhotoView, profileNameLabel, viewProfileButton);
            profileContainer.getChildren().add(profileBox);
        }
    }

    private void openProfileView(Profile profile) {
        try {
            System.out.println("Opening profile view for: " + profile.name);
            System.out.println("Current images in profile: " + profile.getImagePaths());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileView.fxml"));
            Scene profileScene = new Scene(loader.load());

            ProfileViewController profileController = loader.getController();
            profileController.setProfile(profile.name, profile, primaryStage, this);

            primaryStage.setScene(profileScene);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to open profile view.");
        }
    }

    public void showHomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InstagramApp.fxml"));
            if (primaryStage.getScene() == null || !(primaryStage.getScene().getRoot() instanceof VBox)) {
                Scene homeScene = new Scene(loader.load());
                primaryStage.setScene(homeScene);
            }

            InstagramAppController controller = loader.getController();
            controller.setStage(primaryStage);

            if (controller.profileData.isEmpty()) {
                controller.profileData.addAll(this.profileData); // Preserve profiles and their data
            }

            primaryStage.setScene(primaryStage.getScene());
            System.out.println("Home screen loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load home screen.");
        }
    }
}
