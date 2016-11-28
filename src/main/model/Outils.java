package main.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Thomas on 17/11/2016.
 */
public class Outils {


    public static void alerte(String titre, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }




}
