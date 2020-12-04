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
		return Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.filter(x -> !x.getName().endsWith("a"))
				.collect(Collectors.toList());
	}
	
	//Napisz metodę, która liczy średnią wieku osób
	public static double getPersonsAverageAge2(List<Person> list) {
		if(list == null || list.isEmpty()) {
			throw new IllegalArgumentException("WTF");
		}
		
		return Math.round((list
				.stream()
				.filter(Objects::nonNull)
				.mapToDouble(p->p.getAge())
				.sum()/list.size())*100)/100.0;
	}

	
	
	public static double getPersonsAverageAge(List<Person> list) {		
		return  Optional.ofNullable(list).orElse(Collections.emptyList())
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.averagingDouble(Person::getAge));
	}
	
	
	// * - metode ktora zwraca sredni wiek mezczyzn 
	public static double getManAverageAge(List<Person> list) {
		if(list == null) {
			throw new IllegalArgumentException("Lista nie może być nullem");
		}
		
		List<Person> mans = manList(list);
		
		return Math.round((mans.stream().filter(Objects::nonNull).mapToDouble(p->p.getAge()).sum()/mans.size())*100)/100.0;
	}
	
	//	 * - metode ktora zwrca sredni wiek kobiet 
	public static double getWomenAverageAge(List<Person> list) {
		if(list == null) {
			throw new IllegalArgumentException("Lista nie może być nullem");
		}
		
		List<Person> womens = womenList(list);
		
		return Math.round((womens.stream().filter(Objects::nonNull).mapToDouble(p->p.getAge()).sum()/womens.size())*100)/100.0;
	}
	
	// * - metode ktora agreguje dwie metody powyzej (tzn: jako drugi parametr przyjmuje funkcje ktora okresla plec) 
	public static double getAverageAgeFunction(List<Person> list, Predicate<Person> predicate) {

		if (list == null) {
			throw new IllegalArgumentException("Lista nie może być nullem");
		}
		
		long count = list.stream().filter(Objects::nonNull).filter(predicate).count();

		return (list.stream().filter(Objects::nonNull).filter(predicate).mapToDouble(p -> p.getAge()).sum() / count);
	}
	
	// * - metode ktora znajdze miasto w ktorym zyje najwiecej ludzi 
	
	public static String getCityWithBiggestPopulation(List<Person> list) {
								
		return Optional.ofNullable(list).orElse(Collections.emptyList())
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
		if (list == null) {
			throw new IllegalArgumentException("Lista nie może być nullem");
		}

		return list.stream().map(Person::getCity).distinct().collect(Collectors.toList());
	}
}
