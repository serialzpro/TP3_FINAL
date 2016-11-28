package main.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observable;

import javafx.util.Callback;

public class Box extends Observable{

	final private String mName;
	final private ArrayList<Message> mMessages;
	
	public Box(String name)
	{	
		mName = name;
		mMessages = new ArrayList<Message>();
	}
	
	public String getName()
	{
		return mName;
	}
	
	/**
	 * add (if not already into the bus) a message into the bus through 
	 * reference or after creating the message through its content
	 * @return 
	 */
	
	public boolean emit(String newMessageContent)
	{
		boolean res = mMessages.add(new Message(newMessageContent));
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	/**
	 * return messages into the bus in different ways.
	 * - all the messages
	 * - all too old or too early messages (comparing against a date)
	 */
	
	public Collection<Message> getAllMessages()
	{
		return new ArrayList<Message>(mMessages);
	}
	
	public boolean isEmpty()
	{
		return mMessages.isEmpty();
	}
	
	public String toString()
	{
		String nl = System.lineSeparator();
		
		String res = mName+" Box :"+nl;
		
		if(mMessages.isEmpty())
			res += "empty" + nl;
		else
			for(Message m : mMessages)
				res += m + nl;
		
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
	
	public boolean destroyMessage(Message message)
	{
		if(!mMessages.contains(message))
			return false;
		mMessages.remove(message);
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public boolean destroyMessages(Collection<Message> messages)
	{
		if(!mMessages.containsAll(messages) || messages == null) 
			return false;
		mMessages.removeAll(messages);
		
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	public boolean destroyMessagesOlderThan(Date date)
	{		
		boolean res = destroyMessages(getMessagesOlderThan(date));
		
		setChanged();
		notifyObservers();
		
		return res;
	}
	
	public boolean destroyMessagesOlderThan(int seconds)
	{
		setChanged();
		notifyObservers();
		
		return destroyMessages(getMessagesOlderThan(seconds));
	}
	
	public boolean destroyMessagesEarlierThan(Date date)
	{
		return destroyMessages(getMessagesEarlierThan(date));
	}
	
	public boolean destroyMessagesEarlierThan(int seconds)
	{
		return destroyMessages(getMessagesEarlierThan(seconds));
	}
	
	public void destroyAllMessages()
	{
		mMessages.clear();
		
		setChanged();
		notifyObservers();
	}
	
	
	public Collection<Message> getMessagesOlderThan(Date date)
	{
		ArrayList<Message> olders = new ArrayList<Message>();
		for(Message m : mMessages)
		{
			if(m.isOlderThan(date))
				olders.add(m);
		}
		
		setChanged();
		notifyObservers();
		
		return olders;
	}
	
	public Collection<Message> getMessagesOlderThan(int seconds)
	{
		ArrayList<Message> olders = new ArrayList<Message>();
		for(Message m : mMessages)
		{
			if(m.isOlderThan(seconds))
				olders.add(m);
		}
		
		setChanged();
		notifyObservers();
		
		return olders;
	}
	
	public Collection<Message> getMessagesEarlierThan(Date date)
	{
		ArrayList<Message> earliers = new ArrayList<Message>();
		for(Message m : mMessages)
		{
			if(m.isOlderThan(date))
				earliers.add(m);
		}
		return earliers;
	}
	
	public Collection<Message> getMessagesEarlierThan(int seconds)
	{
		ArrayList<Message> earliers = new ArrayList<Message>();
		for(Message m : mMessages)
		{
			if(m.isEarlierThan(seconds))
				earliers.add(m);
		}
		return earliers;
	}

	public Collection<String> getAllMessagesContent() {
		ArrayList<String> res = new ArrayList<String>();
		for(Message m : mMessages)
			res.add(m.getContent());
		return res;
	}

	public String getLastMessage() {
		return mMessages.get(mMessages.size()-1).getContent();
	}
	
	
}
