package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.CurrentData;
import main.model.Box;
import main.model.Bus;
import main.model.Outils;
import javafx.event.ActionEvent;
import java.util.ArrayList;

public class MessageManager {

    private String currentBus;
    private String currentBox;
    public final ObservableList data = FXCollections.observableArrayList();

    @FXML
    private Button button_create_message;

    @FXML
    private ListView<?> table_message_list;

    @FXML
    private Label topText;

    @FXML
    private TextArea field_message;

    public void pipeline(Box boite, String bus) {

        currentBox = boite.getName();
        currentBus = bus;
        ArrayList<String> listeMessage = new ArrayList<String>(CurrentData.currentManager.getBus(currentBus).getBox(currentBox).getAllMessagesContent());
        for (int i = 0 ; i < listeMessage.size() ; i++) {
            data.add(listeMessage.get(i));
            table_message_list.setItems(data);
        }
        topText.setText("Bus "+currentBus+","+"Boite "+currentBox);
    }

    @FXML
    void createMessage(ActionEvent event) {
        if (field_message.getText().length() ==0)
        {
            Outils.alerte("Erreur", "", "Veuillez entrer un corps de message");
        }
        Box box = CurrentData.currentManager.getBox(currentBus,currentBox);
        box.emit((box.getAllMessages().size()+1)+"\t"+field_message.getText()+"\n");
        ArrayList<String> listeMessages = new ArrayList<String>(box.getAllMessagesContent());
        data.add(box.getLastMessage());
        table_message_list.setItems(data);
    }
}


