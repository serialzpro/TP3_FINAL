package main.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

public class Bus extends Observable{
	
	final private String mName;
	final private HashMap<String, Box> mBoxMap;
	
	public Bus(String name)
	{	
		mName = name;
		mBoxMap = new HashMap<String, Box>();
		mBoxMap.put("default", new Box("default"));
	}
	
	//added for busManager (TD3)
	public String getName()
	{
		return mName;
	}
	
	public boolean createBox(String newBoxName)
	{
		if(mBoxMap.containsKey(newBoxName))
			return false;
		mBoxMap.put(newBoxName, new Box(newBoxName));
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public void deleteBox(String boxName)
	{
		if(boxName.equals("default"))
			return;
		if(mBoxMap.containsKey(boxName))
			return;
		mBoxMap.remove(boxName);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * add (if not already into the bus) a message into the bus through 
	 * reference or after creating the message through its content
	 * @return 
	 */
	
	public boolean emit(String newMessageContent)
	{
		return mBoxMap.get("default").emit(newMessageContent);
	}
	
	public boolean emitIntoBox(String boxName, String newMessageContent)
	{
		if(!mBoxMap.containsKey(boxName))
			return false;
		boolean res = mBoxMap.get(boxName).emit(newMessageContent);
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	/**
	 * return messages into the bus in different ways.
	 * - all the messages
	 * - all too old or too early messages (comparing against a date)
	 */
	
	public Collection<Message> getAllMessages(String boxName) {
		if(mBoxMap.containsKey(boxName))
			return mBoxMap.get(boxName).getAllMessages();
		return null;
	}
	
	public Collection<Message> getAllMessages()
	{
		ArrayList<Message> res = new ArrayList<Message>();
		for(Box b : mBoxMap.values())
			res.addAll(b.getAllMessages());
		return res;
	}
	
	public boolean isEmpty()
	{
		for(Box b : mBoxMap.values())
			if(!b.isEmpty())
				return false;
		return true;
	}
	
	public String toString()
	{
		String nl = System.lineSeparator();
		
		String res = mName+" Bus :"+nl;
		
		if(mBoxMap.isEmpty())
			res += "empty" + nl;
		else
			for(Box b : mBoxMap.values())
				res += "> "+ b + nl;
		
		return res;
	}
	
	/**
	 * destroy messages into the bus in different ways.
	 * - one message through its reference (if it is present)
	 * - all the messages
	 * - all too old or too early messages (comparing against a date or seconds)
	 * @return true if the message list of the bus have changed after 
	 * realizing services
	 */
	
	
	public void destroyMessagesOlderThan(Date date)
	{
		for(Box b : mBoxMap.values())
			b.destroyMessagesOlderThan(date);
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesOlderThan(int seconds)
	{
		for(Box b : mBoxMap.values())
			b.destroyMessagesOlderThan(seconds);
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesOlderThan(String boxName, Date date)
	{
		if(mBoxMap.containsKey(boxName))
			mBoxMap.get(boxName).destroyMessagesOlderThan(date);
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyMessagesOlderThan(String boxName, int seconds)
	{
		if(mBoxMap.containsKey(boxName))
			mBoxMap.get(boxName).destroyMessagesOlderThan(seconds);
		
		setChanged();
		notifyObservers();
	}
	
	
	public void destroyMessagesEarlierThan(Date date)
	{
		for(Box b : mBoxMap.values())
			b.destroyMessagesEarlierThan(date);
	}
	
	public void destroyMessagesEarlierThan(int seconds)
	{
		for(Box b : mBoxMap.values())
			b.destroyMessagesEarlierThan(seconds);
	}
	
	public void destroyMessagesEarlierThan(String boxName, Date date)
	{
		if(mBoxMap.containsKey(boxName))
			mBoxMap.get(boxName).destroyMessagesEarlierThan(date);
	}
	
	public void destroyMessagesEarlierThan(String boxName, int seconds)
	{
		if(mBoxMap.containsKey(boxName))
			mBoxMap.get(boxName).destroyMessagesEarlierThan(seconds);
	}
	
	public void destroyAllMessages()
	{
		for(Box b : mBoxMap.values())
			b.destroyAllMessages();
		
		setChanged();
		notifyObservers();
	}
	
	public void destroyAllMessages(String boxName)
	{
		if(mBoxMap.containsKey(boxName))
			mBoxMap.get(boxName).destroyAllMessages();
		
		setChanged();
		notifyObservers();
	}
	
	public Collection<Message> getMessagesOlderThan(Date date)
	{
		ArrayList<Message> olders = new ArrayList<Message>();
		for(Box b : mBoxMap.values())
			olders.addAll(b.getMessagesOlderThan(date));
		return olders;
	}
	
	public Collection<Message> getMessagesOlderThan(int seconds)
	{
		ArrayList<Message> olders = new ArrayList<Message>();
		for(Box b : mBoxMap.values())
			olders.addAll(b.getMessagesOlderThan(seconds));
		return olders;
	}
	
	public Collection<Message> getMessagesOlderThan(String boxName, Date date)
	{
		if(mBoxMap.containsKey(boxName))
			return mBoxMap.get(boxName).getMessagesOlderThan(date);
		return null;
	}
	
	public Collection<Message> getMessagesOlderThan(String boxName, int seconds)
	{
		if(mBoxMap.containsKey(boxName))
			return mBoxMap.get(boxName).getMessagesOlderThan(seconds);
		return null;
	}
	
	public Collection<Message> getMessagesEarlierThan(Date date)
	{
		ArrayList<Message> earliers = new ArrayList<Message>();
		for(Box b : mBoxMap.values())
			earliers.addAll(b.getMessagesEarlierThan(date));
		return earliers;
	}
	
	public Collection<Message> getMessagesEarlierThan(int seconds)
	{
		ArrayList<Message> earliers = new ArrayList<Message>();
		for(Box b : mBoxMap.values())
			earliers.addAll(b.getMessagesEarlierThan(seconds));
		return earliers;
	}
	
	public Collection<Message> getMessagesEarlierThan(String boxName, Date date)
	{
		if(mBoxMap.containsKey(boxName))
			return mBoxMap.get(boxName).getMessagesOlderThan(date);
		return null;
	}
	
	public Collection<Message> getMessagesEarlierThan(String boxName, int seconds)
	{
		if(mBoxMap.containsKey(boxName))
			return mBoxMap.get(boxName).getMessagesOlderThan(seconds);
		return null;
	}

	public boolean destroyBox(String boxName) {
		if(!mBoxMap.containsKey(boxName))
			return false;
		mBoxMap.remove(boxName);
		
		setChanged();
		notifyObservers();
		
		return true;
	}

	public Collection<Box> getAllBoxes() {
		return mBoxMap.values();
	}
	
	public Collection<String> getAllBoxesName()
	{
		return mBoxMap.keySet();
	}

	public Box getBox(String boxName) {
		return mBoxMap.get(boxName);
	}

}
