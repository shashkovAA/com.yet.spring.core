package com.yet.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CombinedEventLogger implements IEventLogger{

	private Collection<IEventLogger> loggers;

	@Autowired
	CombinedEventLogger(Collection<IEventLogger> loggers) {
		this.loggers = loggers;
	}
	
	public void logEvent(Event event) {
		
		for (IEventLogger logger : loggers) {
			
			logger.logEvent(event);
		}
	}

}
