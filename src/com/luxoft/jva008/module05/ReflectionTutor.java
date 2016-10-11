package com.luxoft.jva008.module05;

import static com.luxoft.jva008.Logger.log;
import java.lang.reflect.Constructor;
import org.junit.Test;

public class ReflectionTutor {
	final static String introspectClass = "com.luxoft.jva008.module05.ExampleClass";

	@Test
	public void testReflection() {
		try {
			// TODO: load ExampleClass at runtime by name
			
			// TODO: show all constructors (use method showConstructors())

			// TODO: list all methods, return types and arguments 
			
			// TODO: list all fields and types of the class

			// TODO: call the printIt() method
			
			// TODO: call the printItString() method, pass a String param

			// TODO: call the printItInt() method, pass a int param

			// TODO: call the setCounter() method, pass a int param

			// TODO: call the printCounter() method
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void showConstructors(Class<String> clazz) {
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
	
	@Test
	public void testShowConstructors() {
		showConstructors(java.lang.String.class);
	}

}

