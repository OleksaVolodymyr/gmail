package com.epam.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
	private static final Logger LOG = Logger.getLogger(ExtendedFieldDecorator.class);

	public ExtendedFieldDecorator(SearchContext searchContext) {
		super(new DefaultElementLocatorFactory(searchContext));
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (field.isAnnotationPresent(FindBy.class) || field.isAnnotationPresent(FindAll.class)) {
			ElementLocator locator = factory.createLocator(field);
			if (locator == null) {
				return null;
			}
			if (List.class.isAssignableFrom(field.getType())) {
				return proxyForListLocator(loader, locator, field);
			}
			try {
				return proxyForLocator(loader, locator, field);
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
		}
		return super.decorate(loader, field);
	}

	@SuppressWarnings("unchecked")
	protected <T> T proxyForLocator(ClassLoader loader, ElementLocator locator, Field field)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		WebElement proxy = proxyForLocator(loader, locator);
		Class<?> clazz = field.getType();
		LOG.info("create element " + clazz.getSimpleName());
		T element = (T) clazz.getConstructor(WebElement.class).newInstance(proxy);
		return element;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> proxyForListLocator(ClassLoader loader, ElementLocator locator, Field field) {
		Type typelist = field.getGenericType();
		Class<?> clazz = (Class<?>) ((ParameterizedType) typelist).getActualTypeArguments()[0];
		LOG.info("create list element " + clazz.getSimpleName());
		InvocationHandler handler = new LocatingCustomElementListHandler<>(locator, clazz);
		List<T> proxy = (List<T>) Proxy.newProxyInstance(loader, new Class[] { List.class }, handler);
		return proxy;
	}

}
