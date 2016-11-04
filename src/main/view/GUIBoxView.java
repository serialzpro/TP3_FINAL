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
import main.model.Bus;

public class GUIBoxView implements Observer {

	private final ListView<String> mListView;
	private final Button mCButton;
	private final Button mOButton;
	private final Button mLButton;
	private final TextField mTBusName;
	
	private String mBusName;
	
	public GUIBoxView(String busName)
	{
		mBusName = busName;
		
		mTBusName = new TextField();
        mCButton = new Button("create box");
        mOButton = new Button("open box");
        mLButton = new Button("remove box");
        mListView = new ListView<String>();
	}
	
	public String getBusName()
	{
		return mBusName;
	}
	
	public void start(Bus bus)
	{
		mListView.setItems(FXCollections.observableArrayList(bus.getAllBoxesName()));
		
		final HBox hbButtons = new HBox();
	    hbButtons.setSpacing(10.0);
	    final Label lBusName = new Label("box name:");
        hbButtons.getChildren().addAll(mListView, lBusName, mTBusName, mCButton, mOButton, mLButton); 
        final Scene scene = new Scene(hbButtons, 850, 200, Color.BEIGE);
        
        Stage stage = new Stage();
        stage.setScene(scene); 
        stage.setTitle("Bus : "+mBusName); 
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
		if(arg0 instanceof Bus)
		{
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					mListView.setItems(FXCollections.observableArrayList(((Bus) arg0).getAllBoxesName()));
				} 
			});	
		}
	}
}
