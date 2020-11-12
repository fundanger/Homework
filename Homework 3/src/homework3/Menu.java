package homework3;

import java.util.Scanner;

public class Menu {
		public static void main(String[] args) {
			Polynomial polyA = new Polynomial();
			Polynomial polyB = new Polynomial();
			boolean exit = false;
			int mainMenu, choice, amount, coefficient, exponent;
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner (System.in);
			
			
			System.out.println("Welcome to the polynomial program!");
			
		do {
			System.out.println("Please select a choice from the following:");
			System.out.println("1.Edit first polynomial");
			System.out.println("2.Edit second polynomial");
			System.out.println("3.Add both polynomials");
			System.out.println("4.Exit");
			mainMenu = keyboard.nextInt();
			
			
			switch(mainMenu) {
			case 1:
				System.out.println("Please select a choice from the following for the first polynomial: ");
				System.out.println("1.Create new polynomials");
				System.out.println("2.Clear the Polynomial ");
				System.out.println("3.Back to main menu ");
				choice = keyboard.nextInt();
				switch(choice) {
				case 1:
					System.out.println("How many terms would you like to add?");
					amount = keyboard.nextInt();
						for (int i = 0; i < amount; i++) {
							System.out.println("What coefficient would you like to add?");
							coefficient = keyboard.nextInt();
							System.out.println("What exponent would you like to add?");
							exponent = keyboard.nextInt();
							Term termite = new Term(coefficient,exponent);
							polyA.addTerm(termite);
							
					}
						break;
				case 2:
					System.out.println("Clearing polynomial");
					polyA.clear();
					break;
				case 3:
					System.out.println("Returning to main menu");
					break;
				}
				break;
			case 2:
				System.out.println("Please select an action for the second polynomial: ");
				System.out.println("1.Create a new polynomials");
				System.out.println("2.Clear the Polynomial");
				System.out.println("3.Return to Main Menu");
				choice = keyboard.nextInt();
				switch(choice) {
				case 1:
					System.out.println("How many terms would you like to add?");
					amount = keyboard.nextInt();
						for (int i = 0; i < amount; i++) {
							System.out.println("What coefficient would you like to add?");
							coefficient = keyboard.nextInt();
							System.out.println("What exponent would you like to add?");
							exponent = keyboard.nextInt();
							Term termite = new Term(coefficient,exponent);
							polyB.addTerm(termite);
						
					}
						break;
				case 2:
					System.out.println("Clearing polynomial");
					polyB.clear();
					break;
				case 3:
					System.out.println("Returning to the main menu");
					break;
				}
				break;
			case 3:
				System.out.println("Adding both polynomials");
				polyA.add(polyB);
				System.out.println(polyA);
				break;
			case 4:
				System.out.println("Thank you, come again!");
				exit = true;
				break;
			}
			}while(exit !=true);

		}
}
