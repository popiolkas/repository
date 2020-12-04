package pl.kurs.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pl.kurs.java.model.Person;
import pl.kurs.java.service.NoWomenException;
import pl.kurs.java.service.PersonService;

public class Main {

	public static void main(String[] args) throws NoWomenException {

		// * 1. Wykorzystując optional:
		// - zapytaj użytkownika o jego imie, jeśli imie będzie podane to wypisz długość
		// imienia, jeśli nie to wypisz 0.
		// - zapytaj użytkownika o pesel, jeśli będzie podany poprawny pesel (11 zankow
		// powiedzmy wiekszej walidacji nie ma co robic, no i nie null i nie pusty) to
		// bierzemy date urodzenia z tego numeru, w przeciwnym przypadku rzucamy
		// wyjatkiem InvlaidPeselException

		// 2. Wykorzystując optionale i streamy:
		// - napisz metode ktora jako argument pobiera liste integerow a jako wynik
		// zwraca 5 najwkeiszych elementow na liscie,
		// lub pustą listę jeśli z jakiegokolwiek powodu nie da rady znaleźć 5
		// najwiekszych elementow

		System.out.println(biggestNumbers(5, Collections.unmodifiableList(Arrays.asList(1, 9, 8, 2, 3, 7, 4, 5, 6))));

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

		//Person.getExtension().add(null);

		System.out.println("Cała ekstensja: " + Person.getExtension());
		System.out.println("Lista kobiet: " + PersonService.womenList(Person.getExtension()));
		System.out.println("Lista mężczyzn: " + PersonService.manList(Person.getExtension()));
		System.out.println("Najstarsza kobieta: " + PersonService.findOldestWomen(Person.getExtension()));
		System.out.println("Średni wiek wszystkich: " + PersonService.getPersonsAverageAge(Person.getExtension()));
		System.out.println("Średni wiek wszystkich 2: " + PersonService.getPersonsAverageAge2(Person.getExtension()));
		System.out.println("Średni wiek mężczyzn: " + PersonService.getManAverageAge(Person.getExtension()));
		System.out.println("Średni wiek kobiet: " + PersonService.getWomenAverageAge(Person.getExtension()));
		System.out.println("Średni wiek mężczyzn: " + PersonService.getAverageAgeFunction(Person.getExtension(), x->!x.getName().endsWith("a")));		
		System.out.println("Średni wiek kobiet: " + PersonService.getAverageAgeFunction(Person.getExtension(), x->x.getName().endsWith("a")));
		System.out.println("Najpopularniejsze miasto to: " + PersonService.getCityWithBiggestPopulation(Person.getExtension()));
		PersonService.allCities(Person.getExtension());

	}

	public static List<Integer> biggestNumbers(int n, List<Integer> list) {

		if (list == null) {
			return new ArrayList<>();
		}
		// liczymy ile mamy nienullowych elementow
		long count = list.stream().filter(Objects::nonNull).count();

		if (n <= 0 || n > count) {
			return new ArrayList<>();
		}

		return list.stream().filter(Objects::nonNull).sorted(Collections.reverseOrder()).limit(n)
				.collect(Collectors.toList());
	}

}
