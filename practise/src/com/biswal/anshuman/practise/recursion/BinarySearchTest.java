package com.biswal.anshuman.practise.recursion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BinarySearchTest {

	@Test
	final void testSearchIndexOfNumberAtRightOfMid() {
		int[] array = { 10, 20, 30, 40, 50, 60, 70 };
		assertEquals(5, BinarySearch.searchIndexOfNumber(60, array, 0, array.length - 1));
	}

	@Test
	final void testSearchIndexOfNumberAtLeftOfMid() {
		int[] array = { 10, 20, 30, 40, 50, 60, 70 };
		assertEquals(1, BinarySearch.searchIndexOfNumber(20, array, 0, array.length - 1));
	}

	@Test
	final void testSearchIndexOfNumberAtMid() {
		int[] array = { 10, 20, 30, 40, 50, 60, 70 };
		assertEquals(3, BinarySearch.searchIndexOfNumber(40, array, 0, array.length - 1));
	}

	@Test
	final void testSearchIndexOfNumberNotFound() {
		int[] array = { 10, 20, 30, 40, 50, 60, 70 };
		assertThrows(NoSuchElementException.class,
				() -> BinarySearch.searchIndexOfNumber(90, array, 0, array.length - 1));
	}

}
