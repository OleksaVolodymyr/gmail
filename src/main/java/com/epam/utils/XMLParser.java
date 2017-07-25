package com.epam.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.epam.model.Users;

public class XMLParser {

	@SuppressWarnings("unchecked")
	public static <T> T parse(String path, T object) {
		JAXBContext jaxbContext;
		T newObject = null;
		try {
			jaxbContext = JAXBContext.newInstance(Users.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			newObject = (T) jaxbUnmarshaller.unmarshal(new File(path));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newObject;
	}
}
