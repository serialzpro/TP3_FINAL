package main.model;

import java.util.Date;

public class Message {

	final private String mContent;
	final private Date mDate;
	
	public Message(String content)
	{
		mContent = content;
		mDate = new Date();
	}
	
	public String toString()
	{
		return "Message from "+mDate+" : "+mContent;
	}
	
	public String getContent()
	{
		return mContent;
	}
	
	public boolean isOlderThan(Date date)
	{
		return mDate.before(date);
	}
	
	public boolean isOlderThan(int seconds)
	{
		return isOlderThan(new Date(new Date().getTime() - seconds*1000));
	}
	
	public boolean isEarlierThan(Date date)
	{
		return mDate.after(date);
	}
	
	public boolean isEarlierThan(int seconds)
	{
		return isEarlierThan(new Date(new Date().getTime() - seconds*1000));
	}
	
}
