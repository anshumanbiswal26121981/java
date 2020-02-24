/**
 * @author abiswal
 */
package com.biswal.anshuman.practise;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class of factorial class
 * @author abiswal
 *
 */
class TestFactorial {

	@Test
	final void factorial_10() {
		assertEquals( 3628800, Factorial.factorial(10));
	}

	@Test
	final void factorial_5() {
		assertEquals( 120, Factorial.factorial(5));
	}
	
	@Test
	final void factorial_3() {
		assertEquals( 6, Factorial.factorial(3));
	}
	
	@Test
	final void factorial_0() {
		assertEquals( 1, Factorial.factorial(0));
	}
	
	@Test
	final void factorial_1() {
		assertEquals( 1, Factorial.factorial(1));
	}
	
	@Test()
	final void factorialOfNegativeNumber() {
		assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-10));
	}
	
	@Test()
	final void factorialOfNegativeNumber_minusthree() {
		assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-3));
	}

}
