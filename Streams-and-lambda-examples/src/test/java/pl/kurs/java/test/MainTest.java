package pl.kurs.java.test;

import java.util.*;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import pl.kurs.java.Main;

public class MainTest {

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

}
