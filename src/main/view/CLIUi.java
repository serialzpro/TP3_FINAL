package main.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import main.model.BusManager;

public class CLIUi implements Observer {

	private Scanner sc;
	
	public CLIUi()
	{
		sc = new Scanner(System.in);
	}
	
	public void help()
	{
		String nl = System.lineSeparator();
		System.out.println(
				"***** create commands: *****"+nl+
				">to create a bus -> mkbus 'bus name'"+nl+
				">to create a box -> mkbox 'bus name' 'box name'"+nl+
				">to add message into a bus -> ebus 'bus name' 'content'"+nl+
				">to add message into a box -> ebox 'bus name' 'box name' 'content'"+nl+
				"***** read commands: *****"+nl+
				">to read all contents -> ls"+nl+
				"***** delete commands: *****"+nl+
				">to delete bus -> rmbus 'bus name'"+nl+
				">to delete box -> rmbox 'bus name' 'box name'"+nl+
				">to delete all messages -> rmall[older|earlier] ['elapsed time']"+nl+
				">to delete bus messages -> rmbus[older|earlier] 'bus name' 'elapsed time'"+nl+
				">to delete box messages -> rmbox[older|earlier] 'bus name' 'box name' 'elapsed time'"+nl+
				"********************************");
	}
	
	 public  String[] getCommand() 
	 {
		 return sc.nextLine().split(" "); 
	 }

	public void printBusManager(BusManager busMgr) {
		System.out.println(busMgr);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof BusManager)
		{
			printBusManager((BusManager)arg0);
		}
	}

	public void print(String string) {
		System.out.println(string);
	}
}
