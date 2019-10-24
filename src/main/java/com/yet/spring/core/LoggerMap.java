package com.yet.spring.core;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoggerMap {

    private Map<EventType, IEventLogger> loggers = new HashMap<EventType, IEventLogger>();

    public LoggerMap(ConsoleEventLogger consoleEventLogger, CombinedEventLogger combinedEventLogger) {
        loggers.put(EventType.INFO, consoleEventLogger);
        loggers.put(EventType.ERROR, combinedEventLogger);
    }

    public IEventLogger get(EventType type) {
        if (type == null)
            return null;
        else
            return loggers.get(type);
    }
}
