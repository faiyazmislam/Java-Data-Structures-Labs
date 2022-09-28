//Faiyaz Islam
//CIS 2168
//Project: Index Tree
//This is the class that creates the nodes for the index tree

import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurrences;
	// A list of line numbers for this word.
	List<Integer> list; 

	IndexNode left;
	IndexNode right;

	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	public IndexNode(String word, int lineNumber){

		this.word = word;
		this.list = new ArrayList<>();
		list.add(lineNumber);
		this.occurrences = 1;

	}

	//function that increments the number of occurrences of a word and records the lines where it appears
	public void increaseOccurrence(int lineNumber){

		this.occurrences++;
		list.add(lineNumber);

	}

	// Function that returns the word, the number of occurrences, and the lines it appears on
	public String toString(){

		String info = "Word: " + word + ", # of Occurrences: "+ occurrences + ", Appears in lines: " + list;

		return info;
	}
	
	
	
}
