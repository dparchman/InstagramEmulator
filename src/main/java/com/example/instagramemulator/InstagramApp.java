package com.example.instagramemulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstagramApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InstagramApp.fxml"));
        Scene scene = new Scene(loader.load());

        InstagramAppController controller = loader.getController();
        controller.setStage(primaryStage); // Set the stage in the controller

        primaryStage.setTitle("Instagram Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}