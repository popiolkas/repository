package pl.kurs.java.service;

public class InvalidPeselException extends IllegalArgumentException {

	public InvalidPeselException(String s) {
		  super(s);
	}
	
	public InvalidPeselException() {
	}
	
}
