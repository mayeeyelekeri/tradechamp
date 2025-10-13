package com.mine.tradechamp.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	static LoadProperties instance; 
	private static final Object lock = new Object();
	static Properties prop = new Properties();
	private static String propertyFilePath = System.getProperty("user.dir") +
            "\\src\\test\\resources\\config.properties";
	 
	public static String mainURL; 
	public static String driverPath; 
	
	//Create a Singleton instance. We need only one instance of Property Manager.
    public static LoadProperties getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new LoadProperties();
                instance.loadData();
            }
        }
        return instance;
    }
    
  //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        //Get properties from configuration.properties
        mainURL = prop.getProperty("mainURL");
        driverPath = prop.getProperty("driverPath");
    }

	public String getMainURL() {
		return mainURL;
	}

	public String getDriverPath() {
		return driverPath;
	}
    
}
