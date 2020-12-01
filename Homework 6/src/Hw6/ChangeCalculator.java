package Hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

		public static void main(String[] args) {
			
			try {
	    		File file = new File("C:\\Users\\brand\\Desktop\\CoinCombinations.txt");
        		FileWriter fileWriter = new FileWriter(file);
        		PrintWriter keyboard = new PrintWriter(fileWriter);
        		
			  ArrayList<Integer> coins = new ArrayList<>();
			  int[] amounts = { 25, 10, 5, 1}; // Coin amounts; quarter, dime, nickel, penny
			  calculateChange(coins, amounts, 0, 0, 100, keyboard);
			  keyboard.close();
			  
			}catch (IOException ex){  
            	System.out.printf("Error: %s\n", ex);
            	}
		}
		
    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    	  public static void calculateChange(ArrayList<Integer> coins, int[] amounts, int highest, int sum, int goal,PrintWriter keyboard) {
    	        // Test sum to find if we have reached our goal
    	        if (sum == goal) { //if the sum for the calculation is equal to the goal we are trying to reach
    	        	printCombinationsToFile(coins, amounts, keyboard); // Print to the text file
    	            return;
    	        }
    	        // Cannot go over goal
    	        if (sum > goal) {
    	            return;
    	        }

    	        // Add higher amounts to a new ArrayList 
    	        for (int value : amounts) {
    	            if (value >= highest) { // Highest = highest value that can go into the array
    	                ArrayList<Integer> dup = new ArrayList<>(); // Makes a new array every time the method loops to hold combinations
    	                dup.addAll(coins); // adds combinations to array
    	                dup.add(value); // adds how many
    	                calculateChange(dup, amounts, value, sum + value, goal, keyboard);
    	            }
    	        }
    	    }

    	    public static void printCombinationsToFile(ArrayList<Integer> coins, int[] amounts,PrintWriter keyboard) {
    	    	
            		String[] names = {"Penny:", "Nickle:", "Dimes:", "Quarters:"};       
    	    	      for (int amount : amounts) { //each integer in the amounts array (1,5,10,25)
    	    	         int count = 0;
    	    	            for (int coin : coins) { // go through coins array 
    	    	                if (coin == amount) {
    	    	                    count++;
    	    	                }
    	    	            }
    	    	            if (amount == 25) { //If it's a quarter
    	    	            keyboard.print(names[3]); //print name
    	    	            keyboard.println(count); // print how many
    	    	          }
    	    	            else if (amount == 10) { // If it's a Dime
    	    	        	  keyboard.print(names[2]);// print name
    	    	        	  keyboard.println(count); // print how many
    	    	          }
    	    	          else if (amount == 5) { // If it's a 
    	    	        	  keyboard.print(names[1]); // print name
    	    	        	  keyboard.println(count); // print how many
    	    	          }
    	    	          else if (amount == 1) { // If it's a penny
    	    	        	  keyboard.print(names[0]); // print name
    	    	        	  keyboard.println(count); //print how many
    	    	          }
    	    	        }
    	    	        
    	    
   
    	    	 try{
	                    FileOutputStream op = new FileOutputStream("C:/Users/brand/Desktop/CoinCombinations.txt", true); //open FileOutputStream
	                 } catch(FileNotFoundException ex) // catch filenotfound exception
	            	 { 
	                  System.out.println(ex.getMessage());
	                 } 
  
 }
}

 // End of class ChangeCalculator