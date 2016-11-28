package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.CurrentData;
import main.model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class BusPage {

    public final ObservableList data = FXCollections.observableArrayList();

    @FXML
    private TextField field_bus_name;

    @FXML
    private Button button_create_bus;

    @FXML
    private ListView<?> table_bus_list;

    @FXML
    private Button button_open_bus;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Bus> listeBus = (ArrayList<Bus>) CurrentData.currentManager.getAllBus();
        for (int i = 0 ; i < listeBus.size() ; i++) {
            data.add(listeBus.get(i).getmName());
            table_bus_list.setItems(data);
        }
    }



    @FXML
    void createBus(ActionEvent event) {

        if (field_bus_name.getText().length() ==0)
        {
            Outils.alerte("Erreur", "", "Nom du bus incomplet ou incorrect");
        }
        else
        {
            CurrentData.currentManager.createBus(field_bus_name.getText());
            data.add(CurrentData.currentManager.getBus(field_bus_name.getText()).getmName());
            table_bus_list.setItems(data);
        }

    }

    @FXML
    void openBus(ActionEvent event) {

        if (table_bus_list.getSelectionModel().getSelectedItem() == null)
            Outils.alerte("Erreur", "", "Veuillez d'abord sélectionner un bus");
        else
        {
            String busName = table_bus_list.getSelectionModel().getSelectedItem().toString();

            try {
                FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/BoxManager.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage= new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                ((BoxManager)fxmlLoader.getController()).pipeline(CurrentData.currentManager.getBus(busName));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
