package com.biswal.anshuman.practise.notoptimized;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BiggestNumberTest {

	@Test
	final void testFindBiggestNumberWith10Inputs() {
		int[] arr = {1,2,3,4,8,10,0,8,9,5};
		assertEquals(10, BiggestNumber.findBiggestNumber(arr));
	}
	
	@Test
	final void testFindBiggestNumberWith5Inputs() {
		int[] arr = {100,200,400, 500, 300};
		assertEquals(500, BiggestNumber.findBiggestNumber(arr));
	}
	
	@Test
	final void testFindBiggestNumberWith100DefaultInputs() {
		int[] arr = new int[100];
		assertEquals(0, BiggestNumber.findBiggestNumber(arr));
	}
	
	@Test
	final void testFindBiggestNumberWith0Inputs() {
		int[] arr = {'\0'};
		assertEquals(0, BiggestNumber.findBiggestNumber(arr));
	}

}
