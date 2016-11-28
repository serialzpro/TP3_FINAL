package main.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.CurrentData;
import main.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BoxManager {

    private String currentBusName;

    public final ObservableList data = FXCollections.observableArrayList();
    @FXML
    private Button button_create_box;

    @FXML
    private Label topText;

    @FXML
    private TextField field_box_name;

    @FXML
    private ListView<?> table_box_list;



    public void pipeline(Bus bus) {
        currentBusName = bus.getmName();
        ArrayList<String> listeBox = new ArrayList<String>(CurrentData.currentManager.getBus(currentBusName).getAllBoxesName());
        for (int i = 0 ; i < listeBox.size() ; i++) {
            data.add(listeBox.get(i));
            table_box_list.setItems(data);
        }
        topText.setText("Bus "+currentBusName);
    }

    @FXML
    void openBox(ActionEvent event) {

        if (table_box_list.getSelectionModel().getSelectedItem() == null)
            Outils.alerte("Erreur", "", "Veuillez d'abord sélectionner une boite");
        else
        {
            String boxName = table_box_list.getSelectionModel().getSelectedItem().toString();

            try {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/MessageManager.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage= new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                ((MessageManager)fxmlLoader.getController()).pipeline(CurrentData.currentManager.getBox(currentBusName, boxName), currentBusName);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void createBox(ActionEvent event) {
        if (field_box_name.getText().length() ==0)
        {
            Outils.alerte("Erreur", "", "Nom de boite incomplet ou incorrect");
        }
        CurrentData.currentManager.createBox(currentBusName, field_box_name.getText());
        data.add(CurrentData.currentManager.getBox(currentBusName, field_box_name.getText()).getName());
        table_box_list.setItems(data);
    }

}
