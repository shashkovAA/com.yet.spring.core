package com.yet.spring.core;

public class ConsoleEventLogger implements IEventLogger{
	
	public void logEvent(Event event) {
		System.out.print(event.toString());
	}

}
