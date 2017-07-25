package com.epam.properties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
		try (BufferedReader is = new BufferedReader(
				new InputStreamReader(new FileInputStream("resources/config.properties"), "UTF8"));) {
			prop = new Properties();
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
