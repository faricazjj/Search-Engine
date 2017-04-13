//Farica Zhuang


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class SearchEngine {

	/**Populate a BST from a file
	 * @param searchTree - BST to be populated
	 * @param fileName - name of the input file
	 * @returns false if file not found, true otherwise
	 */
	public static boolean populateSearchTree(BSTree<String> searchTree, String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//read two lines - one for document and the next line for the list of keywords
				String document = scanner.nextLine().trim().toLowerCase();
				String keywords[] = scanner.nextLine().split(" ");
				//convert keywords to lower case
				for(int i = 0; i < keywords.length; i++) {
					keywords[i].toLowerCase();
				}
				
				//create a node in the tree to represent the key
				searchTree.insert(document);
				//insert the information in the key
				for(int i = 0; i < keywords.length; i++) {
					searchTree.insertInformation(document, keywords[i]);
					//if the keyword is not a node in the tree, create the node
					if(!searchTree.findKey(keywords[i])) {
						//create a new node
						searchTree.insert(keywords[i]);
					}
					
					//insert the document to the list of the keyword
					searchTree.insertInformation(keywords[i], document);
				}
				
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nFile not found!!");
			return false;
		}
		return true;
	}
	
	/**Search a query in a BST
	 * @param searchTree - BST to be searched
	 * @param fileName - query string
	 * @returns LinkedList of documents in which the query string is found
	 */
	public static void searchMyQuery(BSTree<String> searchTree, String query) {
		query = query.toLowerCase();
		String[] queryWords = query.split(" ");
		LinkedList<String> firstList = new LinkedList<String>();
		LinkedList<String> copyList = new LinkedList<String>();

		//firstList contains the words from first key
		if(!searchTree.findKey(queryWords[0])) {
			copyList = null;
		}
		else {
			firstList = searchTree.findMoreInformation(queryWords[0]);
			//make a copy of the list that we will use
			for(int i = 0; i < firstList.size(); i++) {
				copyList.add(firstList.get(i));
			}
		}
		
		//retain in copyList only intersecting words
		if(queryWords.length > 1) {
			for(int i = 1; i < queryWords.length; i++) {
				LinkedList<String> toCompare = new LinkedList<String>();
				if(!searchTree.findKey(queryWords[i])) {
					toCompare = null;
				}
				else {
					toCompare = searchTree.findMoreInformation(queryWords[i]);
				
					//only keep the intersection of the two lists
					if(copyList != null) {
						copyList.retainAll(toCompare);
					}
				}
			}
		}
		
		//print the first list
		print(query, copyList);
		
		if(queryWords.length > 1) {
			LinkedList<String> printedOut = new LinkedList<String>();
			//populate printedOut with the words we already printedOut
			if(copyList != null) {
				for(int i = 0; i < copyList.size(); i++) {
					printedOut.add(copyList.get(i));
				}
			}
			
			//loop for every word in the array queryWords
			for(int i = 0 ; i < queryWords.length; i++) {
				LinkedList<String> toCheck = new LinkedList<String>();
				if(!searchTree.findKey(queryWords[i])) {
					toCheck = null;
				}
				else {
					toCheck = searchTree.findMoreInformation(queryWords[i]);
				}
				LinkedList<String> toPrint = new LinkedList<String>();
				//populate the empty list toPrint with words that copyList does not have
				if(toCheck != null && toCheck.size() > 0) {
					for(int j = 0; j < toCheck.size(); j++) {
						//if the word is not in printedOut, add it to toPrint list
						if(!printedOut.contains(toCheck.get(j))) {
							toPrint.add(toCheck.get(j));
							printedOut.add(toCheck.get(j));
						}
					}
				}

				if(toCheck == null || toPrint.size() > 0) {
					//print the content of toPrint
					print(queryWords[i], toPrint);
				}
				
				
				
			}
		}
	}
	
	/**Print method 
	 * @param query input
	 * @param documents - result of SearchMyQuery
	 */
	 
	public static void print(String query, LinkedList<String> documents) {
		if(documents==null || documents.isEmpty())
			System.out.println("The search yielded no results for "+query);
		else {
			Object[] converted = documents.toArray();
			Arrays.sort(converted);
			System.out.println("Documents related to "+ query +" are: "+Arrays.toString(converted));
		}
	}
	
	public static void main( String[] args ) {
		
		if(args.length < 2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		BSTree<String> searchTree = new BSTree<>();
		
		String fileName = args[0];
		String query = args[1];
		
		//Create my BST from file
		boolean check = populateSearchTree(searchTree, fileName);
		if(check == false) {
			System.out.println("\nUnable to create search tree");
		}
		
		searchMyQuery(searchTree, query);
	}
}
