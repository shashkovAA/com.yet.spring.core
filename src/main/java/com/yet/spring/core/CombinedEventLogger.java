package com.yet.spring.core;

import java.util.Collection;

public class CombinedEventLogger implements IEventLogger{

	private Collection<IEventLogger> loggers;
	
	CombinedEventLogger(Collection<IEventLogger> loggers) {
		this.loggers = loggers;
	}
	
	public void logEvent(Event event) {
		
		for (IEventLogger logger : loggers) {
			
			logger.logEvent(event);
		}
	}

}
