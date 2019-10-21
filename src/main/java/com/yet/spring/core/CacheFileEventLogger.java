package com.yet.spring.core;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
	
	private int cacheSize;
	private List<Event> cache = new ArrayList<Event>();
	
	public CacheFileEventLogger(int cacheSize, String fileName) {
		super(fileName);
		this.cacheSize = cacheSize;		
	}
	
	public void logEvent(Event event) {
		
		cache.add(event);
		System.out.println("Add event with id["+event.getId()+"] to cache");
			
		if (cache.size() == cacheSize) {
			
			System.out.println("Cache is full");
			writeEventsFromCache();
			System.out.println("Cache is clear");
			cache.clear();
		}
					
	}

	private void writeEventsFromCache() {
		
		for (Event evt : cache) {
			System.out.println("Write event with id["+evt.getId()+"] to file [" + super.getFileName() + "]");
			super.logEvent(evt);
		}
		
	}
	
	private void destroy() {
		System.out.println("Destroy method executing...");
		if (!cache.isEmpty()) {
			writeEventsFromCache();
			
		}
	}
}
