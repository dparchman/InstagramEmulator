package com.example.instagramemulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class ProfileViewController {
    @FXML
    private Label profileNameLabel;
    @FXML
    private TilePane imageContainer;
    @FXML
    private Button uploadButton;

    private Stage primaryStage;
    private InstagramAppController mainController;
    private Profile currentProfile;

    public void setProfile(String profileName, Profile profile, Stage primaryStage, InstagramAppController mainController) {
        this.primaryStage = primaryStage;
        this.mainController = mainController;
        this.currentProfile = profile;

        profileNameLabel.setText(profileName);
        uploadButton.setVisible("Your Profile".equals(profileName));
        loadProfileImages();
    }

    @FXML
    private GridPane imageGrid; // Link to the GridPane in FXML

    private void loadProfileImages() {
        System.out.println("Loading profile images for: " + currentProfile.name);
        imageGrid.getChildren().clear(); // Clear any existing images

        ArrayList<String> imagePaths = currentProfile.getImagePaths();
        int columns = 3; // Number of columns in the grid
        int row = 0, col = 0;

        for (String path : imagePaths) {
            ImageView imageView = new ImageView(new Image(path));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            // Add ImageView to the GridPane
            imageGrid.add(imageView, col, row);

            // Update row and column
            col++;
            if (col == columns) { // Move to next row after 3 images
                col = 0;
                row++;
            }
        }
    }


    @FXML
    public void uploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();
            currentProfile.addImage(imagePath);
            System.out.println("Image uploaded: " + imagePath); // Debugging output
            loadProfileImages(); // Refresh image container
        } else {
            System.err.println("No file selected.");
        }
    }

    @FXML
    public void goBackToHome() {
        if (mainController != null) {
            System.out.println("Switching back to the home screen...");
            mainController.showHomeScreen();
        } else {
            System.err.println("Main controller is not set. Cannot go back to the home screen.");
        }
    }
}
