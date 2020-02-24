/**
 * @author abiswal
 *
 */
package com.biswal.anshuman.practise;

/**
 * This is a sample class to check the non recursive call flow
 * @author abiswal
 *
 */
public class NonrecursiveCallFlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bar();
		
		System.out.println("I am in Main method");

	}

	private static void bar() {
		doWork();
		System.out.println("I am in bar method");
		
	}

	private static void doWork() {
		doMore();
		System.out.println("I am in do work method");
		
	}

	private static void doMore() {
		System.out.println("i am in do more method");
		
	}

}
