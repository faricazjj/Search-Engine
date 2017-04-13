//Farica Zhuang

import java.util.LinkedList;
import java.util.*;

public class BSTree<T extends Comparable<? super T>>{

	private int nelems;
	private BSTNode root;
	private BSTNode currNode;
	private boolean found;
	private BSTNode foundKey;
	
	 protected class BSTNode{

		T key;
		LinkedList<T> relatedInfo;
		BSTNode left;
		BSTNode right;
		
		/**
		 * A constructor that initializes the BSTNode instance variables
		 * @param left is the left child
		 * @param right is the right child
		 * @param relatedInfo is the list of related informations
		 * @param key is the element of this node
		 */
		public BSTNode(BSTNode left, BSTNode right, LinkedList<T> relatedInfo, T key) {
			this.left = left;
			this.right = right;
			//instantiate the list
			setRelatedInfo(relatedInfo);
			this.key = key;
		}
		
		/**
		 * A constructor that initializes the BSTNode variables
		 * @param left is the left node
		 * @param right is the right node
		 * @param key is the element of this node
		 */
		public BSTNode(BSTNode left, BSTNode right, T key) {
			this.left = left;
			this.right = right;
			this.key = key;
			this.relatedInfo = new LinkedList<T>();
		}
		
		/**
		 * Getter method for the key
		 * @return the key
		 */
		public T getKey() {
			return this.key;
		}
		
		/**
		 * Getter method for the left child of the node
		 * @return the left node
		 */
		public BSTNode getLeft() {
			return this.left;
		}
		
		/**
		 * Getter method for the right node
		 * @return the right node
		 */
		public BSTNode getRight() {
			return this.right;
		}
		
		/**
		 * Getter method for the LinkedList of the node
		 * @return the LinkedList
		 */
		public LinkedList<T> getRelatedInfo() {
			return this.relatedInfo;
		}
		
		/**
		 * Setter method for the left pointer of the node
		 * @param the new child that the left pointer points to
		 */
		public void setLeft(BSTNode newLeft) {
			this.left = newLeft;
		}
		
		/**
		 * Setter method for the right pointer of the node
		 * @param the new child that the right pointer points to
		 */
		public void setRight(BSTNode newRight) {
			this.right = newRight;
		}
		
		/**
		 * Setter method for the LinkedList of the node
		 * @param the new LinkedList of the node
		 */
		public void setRelatedInfo(LinkedList<T> newInfo) {
			this.relatedInfo = newInfo;
		}
		
		/**
		 * Method that appends new info to the end of the existing LinkedList of the
		 * node
		 * @param the new info to be added to the list
		 */
		public void addNewInfo(T info) {
			
			if(info != null) {
				getRelatedInfo().addLast(info);
			}
		}
		
		/**
		 * Method that removes 'info' from the LinkedList of the node and return true
		 * If the LinkedList does not contain the value 'info', return false
		 * @param info to be removed from the list
		 * @return false if info is not in the list, true if info has been successfully
		 * removed from the list
		 */
		public boolean removeInfo(T info) {
			return relatedInfo.remove(info);
		}
	}

	 
	 
	//BSTree methods here
	 
	 /**
	  * A 0-arg constructor that initializes root to null and nelems to 0
	  */
	 public BSTree() {
		 this.root = null;
		 this.nelems = 0;
		 this.currNode = null;
		 this.found = false;
		 this.foundKey = null;
	 }
	 
	 /**
	  * A getter method for the root, returns null if empty
	  * @return the roof of the BSTree
	  */
	 public BSTNode getRoot() {
		 //if the tree is not empty, return the root
		 if(this.nelems > 0) {
			 return this.root;
		 }
		 
		 //if the tree is empty, return null
		 return null;
	 }
	 
	 /**
	  * Getter for the size of the BSTree
	  * @return nelems
	  */
	 public int getSize() {
		 return this.nelems;
	 }
	 
	 /**
	  * Method that inserts a key into the BST by inserting a node with the key and 
	  * an empty LinkedList into the tree
	  * @param the key to be inserted
	  * @throws NullPointerException if key is null
	  */
	 public void insert(T key) {
		 //if key is null, throw an excpetion
		 if(key == null) {
			 throw new NullPointerException();
		 }
		 
		 //if the tree is empty, the node will be the root node
		 if(getSize() == 0) {
			 //create an empty linkedlist
			 LinkedList<T> list = new LinkedList<T>();
			 //create the node with the key and the empty linkedlist
			 BSTNode node = new BSTNode(null, null, list, key);
			 this.root = node;
			 this.currNode = root;
			 //increment nelems
			 this.nelems += 1;
		 }
		 
		 //otherwise, insert the node into the tree by finding its place recursively
		 else {
			 //if currNode is smaller than to key, go to the right node
			 if(this.currNode.getKey().compareTo(key) < 0) {
				 //if the right node is not null, set the currNode to the right node and
				 //do recursion
				 if(this.currNode.getRight() != null) {
					 currNode = currNode.getRight();
					 insert(key);
				 }
				 
				 //if this node has not right node, then set the key to be the right
				 //node and we are done
				 else {
					 //create an empty linkedlist
					 LinkedList<T> list = new LinkedList<T>();
					 //create the node with the key and the empty linkedlist
					 BSTNode node = new BSTNode(null, null, list, key);
					 this.currNode.setRight(node);
					 //increment nelem
					 this.nelems += 1;
					 //reset currNode to root
					 this.currNode = root;
				 }
			 }
			 
			 //else if currNode is greater than key, go to the left node
			 else {
				 //if left node is not null, set currNode to this left node and do 
				 //recursion
				 if(this.currNode.getLeft() != null) {
					 currNode = currNode.getLeft();
					 insert(key);
				 }
				 
				 //else if the left node is null, set key as the left node and we are done
				 else {
					//create an empty linkedlist
					 LinkedList<T> list = new LinkedList<T>();
					 //create the node with the key and the empty LinkedList
					 BSTNode node = new BSTNode(null, null, list, key);
					 this.currNode.setLeft(node);
					 //increment number of elements
					 this.nelems += 1;
					 //reset the currNode to the root
					 this.currNode = root;
				 }
			 }
		 }
	 }
	 
	 /**
	  * This method finds the key in the tree
	  * @param the key to be searched
	  * @return true if the key is found and false otherwise
	  */
	 public boolean findKey(T key) {	
		 //if tree is empty, return false because there is no node
		 if(getSize() == 0) {
			 this.found = false;
		 }
		 
		 //if currNode is not equal to key, check
		 else if(currNode.getKey().compareTo(key) != 0) {
			 //if currNode is smaller than key, check the left node
			 if(currNode.getKey().compareTo(key) < 0) {
				 //if left node is not null, set currNode to its left node and do 
				 //recursion
				 if(currNode.getRight() != null) {
					 currNode = currNode.getRight();
					 findKey(key);
				 }
				 
				 //if the right node is null, then key is not found so return false
				 else {
					 //reset currNode to root
					 this.currNode = root;
					 this.found = false;
				 }
			 }
			 
			 //if currNode is greater than key, check the right node
			 else {
				 if(currNode.getLeft() != null) {
					 currNode = currNode.getLeft();
					 findKey(key);
				 }
				 
				 //else if the right node is null, then key is not found so return false
				 else {
					 //reset currNode to root
					 this.currNode = root;
					 this.found = false;;
				 }
				 
			 }
		 }
	
		 //else, currNode is equal to key, we have found the key so return true
		 else {
			 //set foundKey to be this
			 this.foundKey = currNode;
			 //reset currNode to root
			 this.currNode = root;
			 this.found = true;
		 }
		 
		 return this.found;
	 }
	 
	 /**
	  * This method inserts 'info' into the LinkedList of the node whose key is 'key'
	  * @param key is the node whose key is this
	  * @param info is the info to be inserted in the key specified
	  * @throws NullPointerException if 'key' or 'info' is null
	  * @throws IllegalArgumentExcpetion if 'key' if not found
	  */
	 public void insertInformation(T key, T info) {	 
		 //check for exceptions
		 //throw exception if key or info is null
		 if(key == null || info == null) {
			 throw new NullPointerException();
		 }
		 
		 //throw exception if key is not found
		 if(!findKey(key)) {
			 throw new IllegalArgumentException();
		 }
		 
		 //since we have already called findKey() method and it returned true, we can
		 //access foundKey whose key is 'key' and insert the info into its list
		 foundKey.addNewInfo(info);
	 }
	 
	 /**
	  * This method returns the LinkedLIst of the node with key value 'key'
	  * @param key is the key whose LinkedList the method will return
	  * @return the LinkedList of the node with key value 'key'
	  * @throw NullPointerException if key is null
	  * @throw IllegalArgumentException if 'key' is not found in the BST
	  */
	 public LinkedList<T> findMoreInformation(T key) {
		 //if key is not found, throw an exception
		 if(!findKey(key)) {
			 throw new IllegalArgumentException();
		 }
		 
		 //if key is null, throw an exception
		 if(key == null) {
			 throw new NullPointerException();
		 }
		 
		 //since we have called findKey(), the node foundKey is already set to key
		 //now return foundkey's LinkedList
		 return this.foundKey.getRelatedInfo();
	 }
	 
	 /**
	  * This method calculates the height of the tree, the length of the longest
	  * downward path to a leaf from the root
	  * @return the longest downward path to a leaf from the root
	  */
	 public int findHeight() {
		return findHeight(getRoot());
	 }
	 
	 /** 
	  * A private helper method to be recursively called to calculate the height of
	  * the tree
	  * @param the starting node
	  * @return the height of the tree
	  */
	 private int findHeight(BSTNode node) {
		if (node == null) {
			return -1;
		}

		int leftHeight = findHeight(node.getLeft());
		int rightHeight = findHeight(node.getRight());

		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		}
		else {
			return rightHeight + 1;
		}
		
	 }
	 
	 /**
	  * This method counts the number of leaf nodes in the tree
	  * @return the number of leaf nodes in the tree
	  */
	 public int leafCount() {
		 return leafCount(getRoot());
	 }
	 
	 /**
	  * private helper method that counts the number of leaf nodes in the tree recursively
	  * @return the number of leaf node in the tree
	  */
	 private int leafCount(BSTNode node) {
		 if( node == null ) {
			 return 0;
		 }
		 if( node.getLeft() == null && node.getRight() == null ) {
			 return 1;
		 } 
		 else {
			 return leafCount(node.getLeft()) + leafCount(node.getRight());
		}
		
	 }
	 
	 
	 /**
	  * This method creates a new iterator and returns it
	  * @return the BSTree_Iterator
	  */
	 public Iterator<T> iterator() {
		 return new BSTree_Iterator();
	 }
	 
	 /**
	  * Extra credit method that returns the intersection of two BSTree
	  * @param iter1 is one of the iterators of the two trees to be checks
	  * @param iter2 is the second iterator of the second tree to be checked
	  * @return the arraylist containing the intersection of two BSTrees
	  *
	  */
	 public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
		 ArrayList<T> list1 = new ArrayList<T>();
		 //populate list1 with elements from iter1
		 while(iter1.hasNext()) {
			 list1.add(iter1.next());
		 }
		 ArrayList<T> list2 = new ArrayList<T>();
		 //populate list2 with elements from iter1
		 while(iter2.hasNext()) {
			 list2.add(iter2.next());
		 }
		 //retain the intersection
		 list1.retainAll(list2);
		 //return the list containing the intersection
		 return list1;
	 }
	 
	 /**
	  * Extra credit method that returns the number of nodes at a given level
	  * @param the level to be checked
	  * @return the number of nodes at a given leve
	  *
	  */
	 public int levelCount(int level) {
		 //if level = 0, and tree is not emtpy, return 1 because this is the root
		 if(level == 0 && getSize() > 0) {
			 return 1;
		 }
		 
		 //if level is out of bounds, return -1
		 else if(level >= findHeight()) {
			 return -1;
		 }
		 
		 return 0;
	 }
	 
	//BSTree_Iterator here
	 public class BSTree_Iterator implements Iterator<T> {
		 Stack<BSTNode> stack;
		 BSTNode thisNode;
		 
		 /**
		  * Constructor that initializes the stack with the leftPath of the root
		  */
		 public BSTree_Iterator() {
			 stack = new Stack<BSTNode>();
			 //set currNode to root
			 thisNode = getRoot();
			 //initializes the stack with the leftPath of the root
			 if(thisNode != null) {
				 stack.push(thisNode);
				 while(thisNode.getLeft() != null) {
					 thisNode = thisNode.getLeft();
					 stack.push(thisNode);
				 }
			 }
		 }
		 
		 /**
		  * Method that checks if stack is not empty
		  * @return false if the Stack is empty and true otherwise
		  */
		 public boolean hasNext() {
			 return !stack.isEmpty();
		 }
		 
		 /**
		  * This method returns the next item in the BST
		  * @return the next item in the BST
		  * @throws NoSuchElementException if there is no next item
		  */
		 public T next() {
			 //if there is no next item, throw an exception
			 if(!hasNext()) {
				 throw new NoSuchElementException();
			 }
			 BSTNode toReturn = stack.pop();
			 thisNode = toReturn;
			 //update the stack
			 if(thisNode.getRight() != null) {
				 thisNode = thisNode.getRight();
				 stack.push(thisNode);
				 while(thisNode.getLeft() != null) {
					 thisNode = thisNode.getLeft();
					 stack.push(thisNode);
				 }
			 }
			 return toReturn.getKey();
		 }
		 
		 /**
		  * This method returns a new BSTree_Iterator
		  */
		 public BSTree_Iterator iterator() {
			 return new BSTree_Iterator();
		 }
	 }
}
