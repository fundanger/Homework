package hw7;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeDriver {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int choice;
		MorseCodeTree mct = new MorseCodeTree();
		Scanner keyboard = new Scanner(System.in);
		do {
		System.out.println("Welcome to the Morse Code Translator.");
		System.out.println("Please select a choice from the following:");
		System.out.println("1. Test output for all Morse Code letters.");
		System.out.println("2. Enter a file for translation.");
		System.out.println("3. Enter in a line of Morse Code to translate.");
		System.out.println("4. Exit program.");	
		choice = keyboard.nextInt();
		switch(choice) {
		case 1: 
			System.out.println("a: *-\n"
					+ "b: -***\n"
					+ "c: -*-*\n"
					+ "d: -**\n"
					+ "e: *\n"
					+ "f: **-*\n"
					+ "g: --*\n"
					+ "h: ****\n"
					+ "i: **\n"
					+ "j: *---\n"
					+ "k: -*-\n"
					+ "l: *-**\n"
					+ "m: --\n"
					+ "n: -*\n"
					+ "o: ---\n"
					+ "p: *--*\n"
					+ "q: --*-\n"
					+ "r: *-*\n"
					+ "s: ***\n"
					+ "t: -\n"
					+ "u: **-\n"
					+ "v: ***-\n"
					+ "w: *--\n"
					+ "x: -**-\n"
					+ "y: -*--\n"
					+ "z: --**");
			break;
			
		case 2:
			try {
				Scanner filePath = new Scanner (System.in);
				System.out.println("Please enter a file path:");
				String path = filePath.nextLine();
				File text = new File(path);
				Scanner parse = new Scanner(text);
				StringBuilder sb = new StringBuilder();
				while (parse.hasNext()) {
					String line = parse.nextLine();
					sb.append(mct.translateFromMorseCode(line));
				}
				System.out.println(sb.toString());
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
			break;
		case 3: 
			Scanner takeIt = new Scanner(System.in);
			System.out.print("Please input a String to translate:");
			String input = takeIt.nextLine();
			System.out.print(mct.translateFromMorseCode(input));
			takeIt.close();
			break;
			
		case 4:
			System.out.println("Goodbye!");
			System.exit(0);
			break;
			
		default:
			System.out.println("You have entered an invalid option.");
			System.out.println("Please enter another choice.");
			break;
		} 
		}while (choice != 4);
		keyboard.close();
	}
}
