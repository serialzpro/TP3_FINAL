package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controller.CLIController;
import main.controller.GUIController;
import main.model.BusManager;

public class App extends Application{

	private BusManager mBusMgr;
	
	private CLIController mCliCtr;
	private GUIController mGuiCtr;
	
	public App()
	{
		mBusMgr = new BusManager();
		mCliCtr = new CLIController(mBusMgr);
		mGuiCtr = new GUIController(mBusMgr);
	}

	@Override 
    public void init() throws Exception { 
        super.init();  
        mCliCtr.start();
    } 
	
	@Override
	public void start(Stage scene) throws Exception {
		mGuiCtr.start(scene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
