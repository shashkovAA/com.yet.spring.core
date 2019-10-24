package com.yet.spring.core;

import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements IEventLogger {

	public void logEvent(Event event) {
		System.out.print(event.toString());
	}

	public void logEvent(String message) {
		System.out.println(message);

	}
}
