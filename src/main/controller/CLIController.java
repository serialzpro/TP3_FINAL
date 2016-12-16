package main.controller;

import main.CurrentData;
import main.model.BusManager;
import main.view.CLIUi;

public class CLIController extends Thread{

	private CLIUi mUi;

	
	public CLIController()
	{
		mUi = new CLIUi();
		CurrentData.currentManager.addObserver(mUi);
	}
	
	public void run ()
    {
		boolean running = true;
		String[] command = null;
		
		mUi.help();
		
		while(running)
		{
			command = mUi.getCommand();
			
			if(command[0].equals("mkbus") && command.length == 2)
			{
				if(CurrentData.currentManager.createBus(command[1]))
				{
					mUi.print("cmd success: bus "+command[1]+" created");

				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("mkbox") && command.length == 3)
			{
				if(CurrentData.currentManager.createBox(command[1], command[2]))
				{
					mUi.print("cmd success: box "+command[2]+" created into bus "+command[1]);
				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("ebus") && command.length == 3)
			{
				if(CurrentData.currentManager.emit(command[1], command[2]))
				{
					mUi.print("cmd success: message "+command[2]+" emit into bus "+command[1]);
				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("ebox") && command.length == 4)
			{
				if(CurrentData.currentManager.emitIntoBox(command[1], command[2], command[3]))
				{
					mUi.print("cmd success: message "+command[3]+" emit into box "+command[2]+" of box "+command[3]);
				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("ls") && command.length == 1)
			{
				mUi.printBusManager(CurrentData.currentManager);
			}
			else if(command[0].equals("rmbus") && command.length == 2)
			{
				if(CurrentData.currentManager.destroyBus(command[1]))
				{
					mUi.print("cmd success: bus "+command[1]+" destroyed ");
				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("rmbox") && command.length == 3)
			{
				if(CurrentData.currentManager.destroyBox(command[1], command[2]))
				{
					mUi.print("cmd success: box "+command[2]+" of bus "+command[1]+" destroyed ");
				}
				else
				{
					mUi.print("cmd fail: try help cmd for more informations");
				}
			}
			else if(command[0].equals("rmallolder") && command.length == 2)
			{
				CurrentData.currentManager.destroyMessagesOlderThan(Integer.parseInt(command[1]));
			}
			else if(command[0].equals("rmallearlier") && command.length == 2)
			{
				CurrentData.currentManager.destroyMessagesEarlierThan(Integer.parseInt(command[1]));
			}
			else
				mUi.help();
		}
    }
}
