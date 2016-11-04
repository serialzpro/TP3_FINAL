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
import main.model.Bus;
import main.model.BusManager;

public class GUIBusView implements Observer {

	private final ListView<String> mListView;
	private final Button mCButton;
	private final Button mOButton;
	private final Button mLButton;
	private final TextField mTBusName;
	
	public GUIBusView()
	{
		mTBusName = new TextField();
        mCButton = new Button("create bus");
        mOButton = new Button("open bus");
        mLButton = new Button("remove bus");
        mListView = new ListView<String>();
	}
	
	public void start(BusManager busMgr)
	{
		mListView.setItems(FXCollections.observableArrayList(busMgr.getAllbusName()));
		
		final HBox hbButtons = new HBox();
	    hbButtons.setSpacing(10.0);
	    final Label lBusName = new Label("bus name:");
        hbButtons.getChildren().addAll(mListView, lBusName, mTBusName, mCButton, mOButton, mLButton); 
        final Scene scene = new Scene(hbButtons, 850, 100);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("bus manager"); 
        stage.show(); 
	}
	
	public void setOnCreate(EventHandler<ActionEvent> e)
	{
		mCButton.setOnAction(e);
	}
	
	public String getSelectedItem() {
		return mListView.getSelectionModel().getSelectedItem();
	}

	public void setOnOpen(EventHandler<ActionEvent> e) {
		mOButton.setOnAction(e);
	}
	
	public void setOnRemove(EventHandler<ActionEvent> e)
	{
		mLButton.setOnAction(e);
	}
	
	public String getText() {
		return mTBusName.getText();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof BusManager)
		{
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					mListView.setItems(FXCollections.observableArrayList(((BusManager)arg0).getAllbusName()));
				} 
			});	
		}
	}
}
