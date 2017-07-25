package com.epam.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epam.anotation.CSVElement;
import com.epam.model.UsersModel;

public class Parser {

	@SuppressWarnings("unchecked")
	public static <T> T XMLParse(String path, T object) {
		JAXBContext jaxbContext;
		T newObject = null;
		try {
			jaxbContext = JAXBContext.newInstance(UsersModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			newObject = (T) jaxbUnmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return newObject;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> XLSXParse(String path, T object) {
		List<T> list = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(path)))) {
			Sheet dataSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = dataSheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				T newObject = (T) object.getClass().getConstructor(String.class, String.class)
						.newInstance(currentRow.getCell(0).toString(), currentRow.getCell(1).toString());
				list.add(newObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> CSVParse(String path, T object, String separator) {
		List<T> list = new ArrayList<>();
		try (BufferedReader read = new BufferedReader(new FileReader(path))) {
			String line = "";
			while ((line = read.readLine()) != null) {
				String[] split = line.split(separator);
				list.add(createElement(object, split));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	private static <T> T createElement(T object, String[] split) {
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isAnnotationPresent(CSVElement.class)) {
				fields[i].setAccessible(true);
				try {
					fields[i].set(object, parse(fields[i], split[i]));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return object;
	}

	private static Object parse(Field field, String value) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object parseValue = null;
		if (field.getType().isPrimitive()) {
			if (field.getType().equals(int.class)) {
				parseValue = Integer.parseInt(value);
			} else if (field.getClass().equals(double.class)) {
				parseValue = Double.parseDouble(value);
			} else if (field.getClass().equals(float.class)) {
				parseValue = Float.parseFloat(value);
			} else if (field.getClass().equals(byte.class)) {
				parseValue = Byte.parseByte(value);
			} else if (field.getClass().equals(boolean.class)) {
				parseValue = Boolean.parseBoolean(value);
			} else if (field.getClass().equals(short.class)) {
				parseValue = Short.parseShort(value);
			} else if (field.getClass().equals(long.class)) {
				parseValue = Long.parseLong(value);
			}
		} else {
			parseValue = field.getType().getConstructor(String.class).newInstance(value);
		}
		return parseValue;
	}
}
