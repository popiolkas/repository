package pl.kurs.java.service;

public class NoWomenException extends Exception {

	public NoWomenException() {
		System.out.println("Brak kobiet, nie poruchamy dziś Waldziu :( ");
	}
}
