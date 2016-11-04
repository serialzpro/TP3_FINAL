package main.view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.model.Box;
import main.model.BusManager;

public class GUIMessageView implements Observer {

	private final ListView<String> mListView;
	private final Button mCButton;
	private final TextField mTBusName;
	
	String mBusName;
	String mBoxName;
	
	public GUIMessageView(String busName, String boxName)
	{
		mBusName = busName;
		mBoxName = boxName;
		
		mTBusName = new TextField();
        mCButton = new Button("emit message");
        mListView = new ListView<String>();
	}
	
	public String getBusName()
	{
		return mBusName;
	}
	
	public String getBoxName()
	{
		return mBoxName;
	}
	
	public void start(Box box)
	{
		mListView.setItems(FXCollections.observableArrayList(box.getAllMessagesContent()));
		
		final HBox hbButtons = new HBox();
	    hbButtons.setSpacing(10.0);
	    final Label lBusName = new Label("message content:");
        hbButtons.getChildren().addAll(mListView, lBusName, mTBusName, mCButton); 
        final Scene scene = new Scene(hbButtons, 1200, 400, Color.ALICEBLUE);
        
        Stage stage = new Stage();
        stage.setScene(scene); 
        stage.setTitle("Box : "+mBoxName+" of Bus : "+mBusName); 
        stage.show(); 
	}
	
	public void setOnCreate(EventHandler<ActionEvent> e)
	{
		mCButton.setOnAction(e);
	}
	
	public String getSelectedItem() {
		return mListView.getSelectionModel().getSelectedItem();
	}
	
	public String getText() {
		return mTBusName.getText();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Box)
		{
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					mListView.setItems(FXCollections.observableArrayList(((Box) arg0).getAllMessagesContent()));
				} 
			});	
		}
	}
}
