package com.epam.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
	private static Property instance;

	private Property() {

	}

	public static Property getInstance() {
		if (instance == null) {
			instance = new Property();
		}
		return instance;
	}

	public String getPropertiesValue(String key) {
		Properties prop = null;
		try (InputStream in = new FileInputStream("resources/config.properties")) {
			prop = new Properties();
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
