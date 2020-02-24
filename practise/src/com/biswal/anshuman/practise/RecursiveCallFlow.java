/**
 * @author abiswal
 */
package com.biswal.anshuman.practise;

/**
 *  This is a sample class to check the recursive call flow
 * @author abiswal
 *
 */
public class RecursiveCallFlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		foo(3);
	}

	private static void foo(int i) {
		if (i < 1) {
			return;
		} else {
			foo(i-1);
			System.out.println("Inside foo with i value = "+i);
		}
		
	}

}
