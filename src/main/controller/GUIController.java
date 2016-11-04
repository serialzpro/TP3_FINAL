package main.controller;


import main.model.Box;
import main.model.Bus;
import main.model.BusManager;
import main.view.GUIBoxView;
import main.view.GUIBusView;
import main.view.GUIMessageView;
import javafx.stage.Stage; 

public class GUIController{

	private GUIBusView mBusUi;
	
	private BusManager mBusMgr;
	
	public GUIController(BusManager busMgr)
	{
		mBusMgr = busMgr;
		
		mBusUi = new GUIBusView();
	}

	public void start(Stage primaryStage) throws Exception {
		mBusMgr.addObserver(mBusUi);
		mBusUi.setOnCreate(actionEvent0 -> mBusMgr.createBus(mBusUi.getText()));
		mBusUi.setOnRemove(actionEvent0 -> mBusMgr.destroyBus(mBusUi.getSelectedItem()));
		mBusUi.setOnOpen(actionEvent0 -> {
			
			Bus bus = mBusMgr.getBus(mBusUi.getSelectedItem());
			if(bus != null)
			{
				GUIBoxView mBoxUi = new GUIBoxView(mBusUi.getSelectedItem());
				bus.addObserver(mBoxUi);
				mBoxUi.setOnCreate(actionEvent1 -> mBusMgr.createBox(mBoxUi.getBusName(), mBoxUi.getText()));
				mBoxUi.setOnRemove(actionEvent1 -> mBusMgr.destroyBox(mBoxUi.getBusName(), mBoxUi.getSelectedItem()));
				mBoxUi.setOnOpen(actionEvent1 -> {
					
					Box box = mBusMgr.getBox(mBoxUi.getBusName(), mBoxUi.getSelectedItem());
					if(box != null)
					{
						GUIMessageView mMessageUi = new GUIMessageView(mBoxUi.getBusName(), mBoxUi.getSelectedItem());
						box.addObserver(mMessageUi);
						mMessageUi.setOnCreate(actionEvent2 -> mBusMgr.emitIntoBox(mMessageUi.getBusName(), mMessageUi.getBoxName(), mMessageUi.getText()));
						mMessageUi.start(box);
					}
				});
				
				mBoxUi.start(bus);
			}
		});
		
		mBusUi.start(mBusMgr);
	}
}
