package pl.kurs.java.service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pl.kurs.java.model.Person;

public class PersonService {	
	
	public static Person findOldestWomen(List<Person> list) throws NoWomenException {
		return Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(o -> o.getName() != null)
				.filter(o -> o.getName().endsWith("a"))
				.max((p1, p2) -> p1.getAge() - p2.getAge())
				.orElseThrow(NoWomenException::new);
	}

	public static List<Person> womenList(List<Person> list) {
		return Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(x -> x.getName().endsWith("a"))
				.collect(Collectors.toList());
	}
	

	public static List<Person> manList(List<Person> list) {
		return Optional.ofNullable(list)
				.orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(x -> !x.getName().endsWith("a"))
				.collect(Collectors.toList());
	}
			
	public static double getPersonsAverageAge(List<Person> list) {		
		return  Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.averagingDouble(Person::getAge));
	}
	
	
	// * - metode ktora zwraca sredni wiek mezczyzn 
	public static double getManAverageAge(List<Person> list) {
		return  Optional.ofNullable(manList(list))
				.orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.averagingDouble(Person::getAge));
	}
	
	//	 * - metode ktora zwrca sredni wiek kobiet 
	public static double getWomenAverageAge(List<Person> list) {

		return Optional.ofNullable(womenList(list))
				.orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.averagingDouble(Person::getAge));
	}
	
	// * - metode ktora agreguje dwie metody powyzej (tzn: jako drugi parametr przyjmuje funkcje ktora okresla plec) 
	public static double getAverageAgeFunction(List<Person> list, Predicate<Person> predicate) {
		
		return Optional.ofNullable(list)
				.orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(predicate)
				.collect(Collectors.averagingDouble(Person::getAge));
	}
	
	// * - metode ktora znajdze miasto w ktorym zyje najwiecej ludzi 
	
	public static String getCityWithBiggestPopulation(List<Person> list) {
								
		return  Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(x -> Objects.nonNull(x.getCity()))
				.collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey).orElse(null);
	}
	
	
	// * - metode ktora zwroci wszystkie rozne miasta z listy osob(rozne tzn: bez powtorzen)
	public static List<String> allCities(List<Person> list){
			return Optional.ofNullable(list)
					.orElse(Collections.emptyList())
					.stream()
					.map(Person::getCity)
					.distinct()
					.collect(Collectors.toList());
	}
}
