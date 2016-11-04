package main.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

public class BusManager extends Observable{
	
	private HashMap<String, Bus> mBusMap;
	
	public BusManager(){
		mBusMap = new HashMap<String, Bus>();
	}
	
	public boolean createBus(String busName)
	{
		if(mBusMap.containsKey(busName))
			return false;
		mBusMap.put(busName, new Bus(busName));
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public void listBusNames()
	{
		System.out.println("Bus names :");
		for(Bus b : mBusMap.values())
		{
			System.out.println(b.getName());
		}
	}
	
	public Collection<Bus> getAllBus()
	{
		return new ArrayList<Bus>(mBusMap.values());
	}
	
	public Bus getBus(String busName)
	{
		return mBusMap.get(busName);
	}
	
	public Box getBox(String busName, String boxName) {
		if(mBusMap.containsKey(busName))
			return mBusMap.get(busName).getBox(boxName);
		return null;
	}
	
	public Collection<Box> getAllBox(String busName)
	{
		if(mBusMap.containsKey(busName))
			return mBusMap.get(busName).getAllBoxes();
		return null;
	}
	
	public Collection<String> getAllbusName()
	{
		return mBusMap.keySet();
	}
	
	public boolean createBox(String busName, String newBoxName)
	{
		if(!mBusMap.containsKey(busName))
			return false;
		boolean res = mBusMap.get(busName).createBox(newBoxName);
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	public boolean emitIntoBox(String busName, String boxName, String newMessageContent)
	{
		if(!mBusMap.containsKey(busName))
			return false;
		boolean res = mBusMap.get(busName).emitIntoBox(boxName, newMessageContent);
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	public boolean emit(String busName, String newMessageContent)
	{
		if(!mBusMap.containsKey(busName))
			return false;
		boolean res = mBusMap.get(busName).emit(newMessageContent);
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	public Collection<Message> getAllMessages(String busName, String boxName)
	{
		if(!mBusMap.containsKey(busName))
			return mBusMap.get(busName).getAllMessages(boxName);
		return null;
	}
	
	public Collection<Message> getAllMessages(String busName)
	{
		if(!mBusMap.containsKey(busName))
			return mBusMap.get(busName).getAllMessages();
		return null;
	}
	
	public Collection<Message> getAllMessages()
	{
		ArrayList<Message> res = new ArrayList<Message>();
		for(Bus b : mBusMap.values())
			res.addAll(b.getAllMessages());
		return res;
	}

	public void destroyAllMessages()
	{
		for(Bus b : mBusMap.values())
			b.destroyAllMessages();
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesOlderThan(Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(date);
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesOlderThan(int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(seconds);
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesEarlierThan(Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(date);
	}
	
	public void destroyMessagesEarlierThan(int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(seconds);
	}
	
	public void destroyAllMessages(String busName)
	{
		if(mBusMap.containsKey(busName))
			mBusMap.get(busName).destroyAllMessages();
	}
	
	public void destroyMessagesOlderThan(String busName, Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(date);
	}
	
	public void destroyMessagesOlderThan(String busName, int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(seconds);
	}
	
	public void destroyMessagesEarlierThan(String busName, Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(date);
	}
	
	public void destroyMessagesEarlierThan(String busName, int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(seconds);
	}
	
	public void destroyAllMessages(String busName, String boxName)
	{
		if(mBusMap.containsKey(busName))
			mBusMap.get(busName).destroyAllMessages(boxName);
	}
	
	public void destroyMessagesOlderThan(String busName, String boxName, Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(date);
	}
	
	public void destroyMessagesOlderThan(String busName, String boxName, int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesOlderThan(seconds);
	}
	
	public void destroyMessagesEarlierThan(String busName, String boxName, Date date)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(date);
	}
	
	public void destroyMessagesEarlierThan(String busName, String boxName, int seconds)
	{
		for(Bus b : mBusMap.values())
			b.destroyMessagesEarlierThan(seconds);
	}
	
	public String toString()
	{
		String nl = System.lineSeparator();
		
		String res = "Bus systems : " + nl;
		
		if(mBusMap.isEmpty())
			res += "empty" + nl;
		else
			for(Bus b : mBusMap.values())
				res += "---> "+ b;
		
		return res + "*************";
	}

	public boolean destroyBus(String busName) {
		if(!mBusMap.containsKey(busName))
			return false;
		mBusMap.remove(busName);
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public boolean destroyBox(String busName, String boxName) {
		if(!mBusMap.containsKey(busName))
			return false;
		
		boolean res = mBusMap.get(busName).destroyBox(boxName);
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
}
