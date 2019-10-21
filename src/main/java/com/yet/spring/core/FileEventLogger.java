package com.yet.spring.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements IEventLogger{

	private String filename;
	private File file;
	
	public FileEventLogger() {}	
	
	public FileEventLogger(String filename) {

		this.filename = filename;
	}
	
	public void init() throws IOException {
		this.file = new File(filename);
		boolean result = file.canWrite();
		System.out.println("Init method for bean [fileEventLogger] returns :" + result);
	}
	
	
	public void logEvent(Event event) {
			
		//System.out.println(file);
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
