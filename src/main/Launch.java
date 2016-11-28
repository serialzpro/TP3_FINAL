package main;/**
 * Created by Thomas on 17/11/2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.model.BusManager;

import java.io.IOException;
import java.net.URL;

public class Launch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CurrentData.currentManager = new BusManager();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/BusPage.fxml"));
            // Chargement du FXML.
            Parent root = fxmlLoader.load();
            // Création de la scène.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Erreur au chargement: " + ex);
        }
        primaryStage.setTitle("Test FXML");
        primaryStage.show();
    }
}
