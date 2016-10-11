package com.luxoft.jva008.module05;

import static com.luxoft.jva008.Logger.log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionTutor1 {
	final static String introspectClass = "com.luxoft.jva008.module05.ExampleClass";

	@Test
	public void testReflection() {
		try {
			Class<?> c = Class.forName("com.luxoft.jva008.module05.ExampleClass");
			showConstructors(c);
			showMethods(c);
			showFields(c);
			
			Object object = c.newInstance();
			
			Method method = object.getClass().getMethod("printIt");
			method.invoke(object);
			
			method = object.getClass().getMethod("printItString", String.class);
			method.invoke(object, "abc");
			
			method = object.getClass().getMethod("printItInt", int.class);
			method.invoke(object, 1);
			
			method = object.getClass().getMethod("setCounter", int.class);
			method.invoke(object, 10);
			
			method = object.getClass().getMethod("printCounter");
			method.invoke(object);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void showConstructors(Class<?> clazz) {
		for (Constructor<?> constr: clazz.getConstructors()) {
			StringBuilder sb = new StringBuilder();
			for (Class<?> param: constr.getParameterTypes()) {
				if (sb.length() > 0) { 
					sb.append(", ");
				}
				sb.append(param.getSimpleName());
			}
			sb.insert(0, "constructor: " + constr.getName() + "(");
			sb.append(")");
			log(sb.toString());
		}
		log("SuperClass: " + clazz.getSuperclass().getSimpleName());
	}
	
	public void showMethods(Class<?> clazz) {
		for (Method method: clazz.getMethods()) {
			StringBuilder sb = new StringBuilder();

			for (Class<?> param : method.getParameterTypes()) {
				if (sb.length() > 0) { 
					sb.append(", ");
				}
				sb.append(param.getSimpleName());
			}
			
			sb.insert(0, "Method: " + method.getReturnType() + " " + method.getName() + "(");
			sb.append(") ");
			
			log(sb.toString());
		}
	}
	
	public void showFields(Class<?> clazz) {
		for (Field field: clazz.getDeclaredFields()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Field: " + field.getGenericType())
			  .append(" ")
			  .append(field.getName());
			log(sb.toString());
		}	
	}
	
	@Test
	public void testShowConstructors() {
		showConstructors(java.lang.String.class);
	}
	
	@Test
	public void testShowMethods() {
		showMethods(java.lang.String.class);
	}
	
	@Test
	public void testShowFields() {
		showFields(java.lang.String.class);
	}

}
