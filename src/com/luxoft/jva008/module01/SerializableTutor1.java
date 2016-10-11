package com.luxoft.jva008.module01;

import static com.luxoft.jva008.Logger.log;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;


public class SerializableTutor1 {

	private static final String FILE_OBJECT_DATA = "files/object.data";
	private static Calendar dayOfBirth;
	
	static {
		dayOfBirth = Calendar.getInstance();
		dayOfBirth.set(Calendar.YEAR, 2000);
		dayOfBirth.set(Calendar.MONTH, Calendar.OCTOBER);
		dayOfBirth.set(Calendar.DAY_OF_MONTH, 10);
	}

	/**
    * Make the class static, implement Serializable
    * and mark the field age as transient
    */
	static class Person implements Serializable {
		private static final long serialVersionUID = -1742272123720693335L;
		
		public String name;
		public Date birthdate;
		public List<Address> addressList = new ArrayList<Address>();
		transient int age;
		
		public Person(String name, Date birthdate) {
			this.name = name;
			this.birthdate = birthdate;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(birthdate);
			age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR) ;
		}
		
		public void addAddress(Address address) {
			addressList.add(address);
		}
	}
	
	static class Address implements Serializable {
		private static final long serialVersionUID = 5555711516498582916L;
		
		public String city;
		public String street;
		public int appartement;
		public Address(String city, String street, int appartement) {
			this.city = city;
			this.street = street;
			this.appartement = appartement;
		}
	}
	
	/**
	 * Should write the data of Person to file FILE_OBJECT_DATA,
	 * using ObjectOutputStream
	 * @param person
	 */
	public void writeToFile(Person person) {
		try (ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(FILE_OBJECT_DATA))) {
			output.writeObject(person);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads and returns data from file FILE_OBJECT_DATA
	 * @return
	 */
	public Person readFromFile() {
		Person person = null;
		try (ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(FILE_OBJECT_DATA))) {
			person = (Person) input.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}
	
	@Test
	public void testSerializeObject() {
		Person person = new Person("John Johnes", dayOfBirth.getTime());
		writeToFile(person);
		log("Age: " + person.age);
		Person personFromFile = readFromFile();
		log("Name from file: " + personFromFile.name);
		log("Age from file: " + personFromFile.age);
		assertEquals(personFromFile.name, personFromFile.name);
		assertNotEquals("Name age was not marked as transient", person.age, personFromFile.age);
	}
	
	@Test
	public void testSerializePersonAddress() {
		Person person = new Person("John Johnes", dayOfBirth.getTime());
		Address address = new Address("New York", "Water street", 10);  
		person.addAddress(address);

		writeToFile(person);
		
		log("Age: " + person.age);
		Person personFromFile = readFromFile();
		log("Name from file: " + personFromFile.name);
		log("Age from file: " + personFromFile.age);
		assertEquals(personFromFile.name, personFromFile.name);
		assertNotEquals(person.age, personFromFile.age);

		Address addressFromFile = personFromFile.addressList.get(0);
		log("City from file: " + addressFromFile.city);
		log("Appartment from file: " + addressFromFile.appartement);
		assertEquals(addressFromFile.city, address.city);
		assertEquals(addressFromFile.appartement, address.appartement);
	}

}
