package com.yet.spring.core;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class CacheFileEventLogger extends FileEventLogger{

	@Value("4")
	private int cacheSize;

	/*@Value("log2.txt")
	private String fileName;*/

	private File file;

	private List<Event> cache = new ArrayList<Event>();

	public CacheFileEventLogger() { }

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

	@PreDestroy
	private void destroy() {
		System.out.println("Destroy method executing...");
		if (!cache.isEmpty()) {
			writeEventsFromCache();
			
		}
	}
}
