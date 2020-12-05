package pl.kurs.java.test;

import java.time.LocalDate;
import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.kurs.java.Main;
import pl.kurs.java.model.Person;
import pl.kurs.java.service.InvalidPeselException;
import pl.kurs.java.service.NoWomenException;
import pl.kurs.java.service.PersonService;

public class MainTest {

	PersonService ws = new PersonService();

	@Before
	public void init() {
		ws = new PersonService();
		Person.getExtension().clear();
	}

	@Test
	public void shouldReturnProperList() {
		List<Integer> lista = Collections.unmodifiableList(Arrays.asList(1, 9, 8, 2, 3, 7, 4, 5, 6));
		int n = 5;

		Assert.assertEquals(Main.biggestNumbers(n, lista), Collections.unmodifiableList(Arrays.asList(9, 8, 7, 6, 5)));
	}

	@Test
	public void shouldReturnEmptyList() {
		List<Integer> lista = Collections.unmodifiableList(Arrays.asList(1, 9, 8, 2));
		int n = 5;

		Assert.assertEquals(Main.biggestNumbers(n, lista), Collections.unmodifiableList(Arrays.asList()));
	}

	@Test
	public void shouldReturnEmptyList2() {
		List<Integer> lista = null;

		Assert.assertEquals(Main.biggestNumbers(5, lista), Collections.unmodifiableList(Arrays.asList()));
	}

	@Test
	public void shouldReturnOldestWomen() throws NoWomenException {

		Person p1 = new Person("Anna", "Jankowska", "Warsaw", 55);
		Person p2 = new Person("Tomasz", "Dupa", "Warsaw", 58);
		Person p3 = new Person("Gabrysia", "Siusiak", "Pcim dolny", 25);
		Person p4 = new Person("Hanna", "Cyc", "Warsaw", 29);
		Person p5 = new Person("Waldemar", "Pieprzak", "Poznan", 77);

		Assert.assertEquals(p1, PersonService.findOldestWomen(Person.getExtension()));
	}

	@Test
	public void shouldReturnAvgAge() {

		Person p1 = new Person("Anna", "Jankowska", "Warsaw", 1);
		Person p2 = new Person("Tomasz", "Dupa", "Warsaw", 2);
		Person p3 = new Person("Gabrysia", "Siusiak", "Pcim dolny", 3);
		Person p4 = new Person("Hanna", "Cyc", "Warsaw", 4);
		Person p5 = new Person("Waldemar", "Pieprzak", "Poznan", 5);

		List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5));

		Assert.assertEquals(3.0, PersonService.getPersonsAverageAge(list), 0.001);
	}

	@Test
	public void shouldReturnMostPopularCity() {

		Person p1 = new Person("Anna", "Jankowska", "Warsaw", 1);
		Person p2 = new Person("Tomasz", "Dupa", "Warsaw", 2);
		Person p3 = new Person("Gabrysia", "Siusiak", "Pcim dolny", 3);
		Person p4 = new Person("Hanna", "Cyc", "Warsaw", 4);
		Person p5 = new Person("Waldemar", "Pieprzak", "Poznan", 5);

		List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5));

		Assert.assertEquals("Warsaw", PersonService.getCityWithBiggestPopulation(list));
	}

	//tu lepiej zrobic to wszystko w jednym tescie czy rozbic na 5?
	@Test 
	public void shouldReturnStringLength() {
		Assert.assertEquals(0, Main.gibeNameSir(null));
		Assert.assertEquals(0, Main.gibeNameSir(""));
		Assert.assertEquals(3, Main.gibeNameSir("dup"));
		Assert.assertEquals(4, Main.gibeNameSir("dupa"));
		Assert.assertEquals(5, Main.gibeNameSir("dupaa"));
	}

	@Test
	public void shouldReturnUniqueCities() {

		Person p1 = new Person("Jan", "Kowalski", "Warsaw", 66);
		Person p2 = new Person("Anna", "Jankowska", "Warsaw", 55);
		Person p3 = new Person("Tomasz", "Dupa", "Warsaw", 58);
		Person p4 = new Person("Gabrysia", "Siusiak", "Pcim dolny", 25);
		Person p5 = new Person("Hanna", "Cyc", "Warsaw", 29);
		Person p6 = new Person("Waldemar", "Pieprzak", "Poznan", 77);
		Person p7 = new Person("Marcin", "Niemadry", "Warsaw", 85);

		List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
		List<String> cities = new ArrayList<>(Arrays.asList("Warsaw", "Pcim dolny", "Poznan"));

		Assert.assertEquals(cities, PersonService.allCities(list));
	}

	@Test(expected = NoWomenException.class)
	public void shouldThrowNoWomenExceptionIfThereIsNoWomen() throws NoWomenException {

		Person p1 = new Person("Tomasz", "Dupa", "Warsaw", 2);
		Person p2 = new Person("Waldemar", "Pieprzak", "Poznan", 5);
		Person p3 = new Person("Marcin", "Niemadry", "Warsaw", 85);

		List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3));
		PersonService.findOldestWomen(list);
	}
	
	@Test(expected = InvalidPeselException.class)
	public void shouldThrowInvalidPeselException() throws NoWomenException {

		Main.gibePeselSir("823");
	}
	
	@Test(expected = InvalidPeselException.class)
	public void shouldThrowInvalidPeselException2() throws NoWomenException {

		Main.gibePeselSir(null);
	}

 
}
