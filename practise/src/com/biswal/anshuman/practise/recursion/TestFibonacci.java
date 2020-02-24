/**
 * @author abiswal
 */
package com.biswal.anshuman.practise.recursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This is the Junit test class of fibonacci class
 *
 */
class TestFibonacci {

	@Test
	final void fibonacci0() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> Fibonacci.fibonacci(0));
	}
	
	@Test
	final void fibonacci1() {
		Assertions.assertEquals(0, Fibonacci.fibonacci(1));
	}
	
	@Test
	final void fibonacci2() {
		Assertions.assertEquals(1, Fibonacci.fibonacci(2));
	}
	
	@Test
	final void fibonacci3() {
		Assertions.assertEquals(1, Fibonacci.fibonacci(3));
	}
	
	@Test
	final void fibonacci4() {
		Assertions.assertEquals(2, Fibonacci.fibonacci(4));
	}
	
	@Test
	final void fibonacci5() {
		Assertions.assertEquals(3, Fibonacci.fibonacci(5));
	}

}
