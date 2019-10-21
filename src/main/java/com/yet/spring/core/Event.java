package com.yet.spring.core;

import java.text.DateFormat;
import java.util.Date;

public class Event {
	private int id;
	private String msg;
	private Date date;
	private DateFormat df;
	
	
	public Event(){}
	
	public Event(Date date) {
		this.date = date;
		id = getRandomNumberInRange(1,65000);
	}
	
	public Event(Date date, DateFormat df) {
		this.date = date;
		this.df = df;
		id = getRandomNumberInRange(1,65000);
	}
	
	public String toString() {
		if (df == null)
			return "ID:" + id + ", " + msg + ", " + date.toString() + "\r\n";
		else		
			return "ID:" + id + ", " + msg + ", " + df.format(date) + "\r\n";
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	public int getId() {
		return id;
	}

}
