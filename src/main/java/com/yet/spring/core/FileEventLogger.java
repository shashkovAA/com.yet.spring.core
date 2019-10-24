package com.yet.spring.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FileEventLogger implements IEventLogger{

	@Value("log.txt")
	private String filename;

	private File file;
	
	public FileEventLogger() {}	

	public FileEventLogger(String filename) {

		this.filename = filename;
	}

	@PostConstruct
	public void init() throws IOException {
		this.file = new File(filename);
		boolean result = file.canWrite();
		System.out.println("Init method for bean [fileEventLogger] returns :" + result);
	}
	
	
	public void logEvent(Event event) {

		try {
			FileUtils.writeStringToFile(file, event.toString(), true);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getFileName() {
		return filename;
	}
	

}
