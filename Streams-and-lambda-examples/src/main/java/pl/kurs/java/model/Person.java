package pl.kurs.java.model;

import java.util.*;

public class Person {

	private String name, surname, city;
	private int age;

	private static List<Person> extension = new ArrayList<>();

	public Person(String name, String surname, String city, int age) {
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.age = age;
		
		extension.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static List<Person> getExtension() {
		return extension;
	}

	public String toString() {
		return name + " " + surname;
	}

}
