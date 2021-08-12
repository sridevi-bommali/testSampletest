package com.testSample.PropertiesFlow;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.io.OutputStream;


public class Globals {
   
	private static String propertiesFile = "config.properties";
	public static String RUN_TIMESTAMP = null;
	public static int SHORT_WAIT = 20;
	public static int MEDIUM_WAIT = 40;
	public static int LONG_WAIT = 60;
	public static int TYPING_SPEED = 500;
	public static String HEADLESS = "no";
	public static String TESTDOC = "path to doc";
	public static String USERNAME = "username";
	public static String PASSWORD = "password";
	public static String CHROMEDRIVER = "path to chromedriver";
	public static String IEDRIVER = "path to iedriver";
	
	private static Properties prop = null;
	
	public static String attachRunTimestamp() {
		return " - " + RUN_TIMESTAMP;
	}
	public static void setRunTimestamp() {
		loadProperties();
		String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
		LocalDateTime date = LocalDateTime.now();
		RUN_TIMESTAMP = date.format(DateTimeFormatter.ofPattern(FORMAT));
		saveProperties();
		
	}
	public static void setHeadlessMode(String mode) {
		
		loadProperties();
		HEADLESS = mode;
		saveProperties();
	}
	
	
	public static void readRunTimestamp () {
		
		if (Globals.RUN_TIMESTAMP == null || Globals.RUN_TIMESTAMP.isEmpty()){
			Globals.loadProperties();
		}
		
		
	}
	public static void saveProperties () {
		OutputStream output = null;
		try {
			output = new FileOutputStream(propertiesFile);
		}catch (FileNotFoundException e) {
			//TODO Auto -generated catch block
			e.printStackTrace();
			
		}
		//set the properties value
		prop.setProperty("run.timestamp", RUN_TIMESTAMP);
		prop.setProperty("short.wait", Integer.toString(SHORT_WAIT));
		prop.setProperty("medium.wait", Integer.toString(MEDIUM_WAIT));
		prop.setProperty("long.wait", Integer.toString(LONG_WAIT));
		prop.setProperty("typing.speed", Integer.toString(TYPING_SPEED));
		prop.setProperty("headless", HEADLESS);
		prop.setProperty("testdoc", TESTDOC);
		prop.setProperty("username", USERNAME);
		prop.setProperty("password", PASSWORD);
		prop.setProperty("chromedriver", CHROMEDRIVER);
		prop.setProperty("iedriver", IEDRIVER);
		
		
		//save properties to project root folder
		
		try {
			prop.store(output, null);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				output.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}
	public static void loadProperties() {
		try {
			
			InputStream input = new FileInputStream("config.properties");
			
			prop = new Properties();
			
			//load a properties
			prop.load(input);
			
			RUN_TIMESTAMP = prop.getProperty("run.timestamp");
			SHORT_WAIT = Integer.parseInt(prop.getProperty("short.wait").trim());
			MEDIUM_WAIT = Integer.parseInt(prop.getProperty("medium.wait").trim());
			LONG_WAIT = Integer.parseInt(prop.getProperty("long.wait").trim());
			TYPING_SPEED = Integer.parseInt(prop.getProperty("typing.speed").trim());
			HEADLESS = prop.getProperty("headless");
			TESTDOC = prop.getProperty("testdoc");
			USERNAME = prop.getProperty("username");
			PASSWORD = prop.getProperty("password");
			CHROMEDRIVER = prop.getProperty("chromedriver");
			IEDRIVER = prop.getProperty("iedriver");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
