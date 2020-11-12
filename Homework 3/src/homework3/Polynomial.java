package homework3;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class Polynomial {
	
	private LinkedList<Term> terms;

//Default Constructor	
	public Polynomial() {
		this.terms = new LinkedList<Term>();
	}
	
//Full constructor 
	public Polynomial(LinkedList<Term> term) {
		terms = term;
	}
	
//Copy Constructor 
	public Polynomial(Polynomial o) {
		this.terms= new LinkedList<Term>();
			for(int i = 0; i < o.getNumTerms(); i++)
				{
					Term tempTerm = new Term(o.getTerm(i));
					terms.add(new Term(tempTerm));
				}
			}
			
//Get size of terms
	public int getNumTerms() {
		return terms.size();
	}
	
//Empties the Terms linked list
	public void clear() {
		terms.clear();
	}
	
//Returns a Term at a specific index	
	public Term getTerm(int index)
	{
		return this.terms.get(index);
	}
	
//Sort Method
	public void sort() {
		Comparator<Term> c = Collections.reverseOrder();
		Collections.sort(terms,c);
	}
	
//Adds a term, if it's similar to a term that already exists, it adds the coefficients
	public void addTerm(Term o) {
		if(getNumTerms() == 0) {//if theres nothing in the terms list, we just add the term.
			terms.add(o);
		}
		else {
		boolean flag = true; //This is to keep track if there was a combination of like terms, if so, it becomes false
		for (int i = 0; i < getNumTerms(); i++) {//loops through all of the 
			if (o.getExponent() == this.getTerm(i).getExponent()) {
				int tempCo = o.getCoefficient() + this.getTerm(i).getCoefficient();
				this.getTerm(i).setCoefficient(tempCo);
				flag = false;
			}
		}
		if(flag) {  //Checks to see if o was combined with a like term, if not then it just adds it to the list
			terms.add(o);
		}
		sort();
		}
	}
	
//Removes a term at a specific index
	public void remove(int index) {
		terms.remove(index);
	}
	
//Sorts both of polynomials, then checks the second polynomials terms to the first one. if they match, then it will add them, if it doesnt, then 
	public void add(Polynomial o) {
		sort();
		o.sort();
		for(int j = 0; j < o.getNumTerms();j++)
		{
			boolean flag = true;
			for(int i = 0; i < getNumTerms(); i++) 
			{
				if (o.getTerm(j).getExponent() == this.getTerm(i).getExponent()) 
				{
					int tempCo = o.getTerm(j).getCoefficient() + this.getTerm(i).getCoefficient();
					this.getTerm(i).setCoefficient(tempCo);
					if(this.getTerm(i).getCoefficient() == 0) {
						this.remove(i);
					}
					flag = false;
					break;
				}
			}
			if (flag) {
				this.terms.add(o.getTerm(j));
			}
		 }
		sort();
	}
	
//toString for Polynomials	
		public String toString(){
		String output = "";
			sort();
			if(this.terms == null || this.terms.size() == 0){
			   output += "0";
			}
			else{
				for(int i =0; i < terms.size(); i++) {
					output += terms.get(i).toString();
				if (output.charAt(0) == '+'){
	                output = output.substring(1);//removes the plus sign
				}
	        }			
		}
			return output;
		}
		
		
//Equals Method
		public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Polynomial temp = (Polynomial) o;
	        return terms == temp.terms;
	    }
	}
	 
	
