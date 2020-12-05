package pl.kurs.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import pl.kurs.java.model.Person;
import pl.kurs.java.service.InvalidPeselException;
import pl.kurs.java.service.NoWomenException;
import pl.kurs.java.service.PersonService;

public class Main {

	public static void main(String[] args) throws NoWomenException {
 
		System.out.println(biggestNumbers(5, Collections.unmodifiableList(Arrays.asList(1, 9, 4,0,30))));

		/*
		 * 3. Stworz sobie klase Osoba (imie, nazwisko, miasto, wiek) 
		 * - metoda co zwraca najstarsza kobiete (imie konczy sie na 'a')  lub NoWomenException jesli brak kobiet na liscie 
		 * - metode ktora zwraca sredni wiek wszystkich osob 
		 * - metode ktora zwraca sredni wiek mezczyzn 
		 * - metode ktora zwrca sredni wiek kobiet 
		 * - metode ktora agreguje dwie metody powyzej (tzn: jako drugi parametr przyjmuje funkcje ktora okresla plec) 
		 * - metode ktora znajdze miasto w ktorym zyje najwiecej ludzi 
		 * - metode ktora zwroci wszystkie rozne miasta z listy osob(rozne tzn: bez powtorzen)
		 */

		Person p1 = new Person("Jan", "Kowalski", "Warsaw", 66);
		Person p2 = new Person("Anna", "Jankowska", "Warsaw", 55);
		Person p3 = new Person("Tomasz", "Dupa", "Warsaw", 58);
		Person p4 = new Person("Gabrysia", "Siusiak", "Pcim dolny", 25);
		Person p5 = new Person("Hanna", "Cyc", "Warsaw", 29);
		Person p6 = new Person("Waldemar", "Pieprzak", "Poznan", 77);
		Person p7 = new Person("Marcin", "Niemadry", "Warsaw", 85);
		Person p8 = new Person("Agnieszka", "Stara", "Krakow", 83);
		Person p9 = null;

		System.out.println("Cała ekstensja: " + Person.getExtension());
		System.out.println("Lista kobiet: " + PersonService.womenList(Person.getExtension()));
		System.out.println("Lista mężczyzn: " + PersonService.manList(Person.getExtension()));
		System.out.println("Najstarsza kobieta: " + PersonService.findOldestWomen(Person.getExtension()));
		System.out.println("Średni wiek wszystkich: " + PersonService.getPersonsAverageAge(Person.getExtension()));
		System.out.println("Średni wiek mężczyzn: " + PersonService.getManAverageAge(Person.getExtension()));
		System.out.println("Średni wiek kobiet: " + PersonService.getWomenAverageAge(Person.getExtension()));
		System.out.println("Średni wiek mężczyzn: " + PersonService.getAverageAgeFunction(Person.getExtension(), x->!x.getName().endsWith("a")));		
		System.out.println("Średni wiek kobiet: " + PersonService.getAverageAgeFunction(Person.getExtension(), x->x.getName().endsWith("a")));
		System.out.println("Najpopularniejsze miasto to: " + PersonService.getCityWithBiggestPopulation(Person.getExtension()));		
		System.out.println("Unikalne miasta: " + PersonService.allCities(Person.getExtension()));
		
		//zad 1
		System.out.println(gibeNameSir(null));
		System.out.println(gibeNameSir(""));
    	System.out.println(gibeNameSir("Janek"));
    	System.out.println();
    	
    	System.out.println(gibePeselSir("89031507573"));
    	System.out.println(gibePeselSir("8234"));
 	 	System.out.println(gibePeselSir(""));
 	 	System.out.println(gibePeselSir(null));
    	

	}

	// 2. Wykorzystując optionale i streamy:
	// - napisz metode ktora jako argument pobiera liste integerow a jako wynik
	// zwraca 5 najwkeiszych elementow na liscie,
	// lub pustą listę jeśli z jakiegokolwiek powodu nie da rady znaleźć 5
	// najwiekszych elementow
	public static List<Integer> biggestNumbers(int n, List<Integer> list) {

		return Optional.ofNullable(list)
				.map(x -> x.stream().filter(Objects::nonNull).collect(Collectors.toList()))
				.filter(x -> x.size()>n)
				.orElse(Collections.emptyList())
				.stream()
				.sorted(Collections.reverseOrder())
				.limit(n)
				.collect(Collectors.toList());
	}
	
	// * 1. Wykorzystując optional:
	// - zapytaj użytkownika o jego imie, jeśli imie będzie podane to wypisz długość
	// imienia, jeśli nie to wypisz 0.

	public static int gibeNameSir(String name) {
		return Optional.ofNullable(name)
				.orElse("")
				.length();
	}

	// - zapytaj użytkownika o pesel, jeśli będzie podany poprawny pesel (11 zankow
	// powiedzmy wiekszej walidacji nie ma co robic, no i nie null i nie pusty) to
	// bierzemy date urodzenia z tego numeru, w przeciwnym przypadku rzucamy
	// wyjatkiem InvlaidPeselException
	public static LocalDate gibePeselSir(String pesel) {
		return Optional.ofNullable(pesel)
				.filter(p -> p.matches("\\d{11}"))
				.map(x -> LocalDate.of(Integer.parseInt(whichCentury(pesel)+pesel.substring(0, 2)),
						Integer.parseInt(pesel.substring(2, 4)),
						Integer.parseInt(pesel.substring(4, 6))))
				//.orElseThrow(()-> new RuntimeException(""));
				.orElseThrow(InvalidPeselException::new);
	}

	public static String whichCentury(String source) {
		return Integer.parseInt(source.substring(2, 4))<20 ? "19" : "20";
	}
}
