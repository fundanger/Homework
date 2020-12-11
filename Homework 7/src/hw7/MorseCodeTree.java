package hw7;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

	
	public MorseCodeTree() {
		super(new Node<Character> ('$'));
		readTextFile();
		
	}
    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
    	//System.out.println("Morse Code input:" + morseCode);
    	StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(morseCode);
        Node<Character> current = null;
    	while(scan.hasNext()) {
    		current = root;
    		String temp = scan.next();
    		for(char c : temp.toCharArray()) {
    			
    			if(temp.length()>4)
    				throw new Exception();
    			if(c != '*' && c != '-') 
    				throw new Exception();
    			
    			if(c == '*') {
    			current = current.left;
    			}
    			if(c == '-') {
    			current = current.right;	
    			}
    		}
    		sb.append(current.data);
    		
    	}
    	scan.close();
    	return sb.toString();
    }
    
    
    public BinaryTree<Character> readTextFile() {
    	Scanner file = null;
    	try {
    	File text = new File("C:/Users/brand/Desktop/MorseCode.txt");
    	file = new Scanner(text);
    	} catch (FileNotFoundException o) {
    		System.out.print("Error");
    	}
    	while (file.hasNext()) {
    		String seq = file.nextLine();
    		char node = seq.charAt(0);
    		seq = seq.substring(2);
    		Node<Character> current = root;
    		for(int i = 0; i < seq.length(); i++) {
    			if(seq.charAt(i) == '-') {
    				if(current.right == null) {
    					current.right = new Node<Character>(node);
    				}
    				current = current.right;	
    			}
    			if(seq.charAt(i) == '*') {
    				if(current.left == null) {
    					current.left = new Node<Character>(node);
    				}
    				current = current.left;
    			}
    		}
    	}
    	
    	return new BinaryTree<Character>(root);
    }
    

} // End of class MorseCodeTree