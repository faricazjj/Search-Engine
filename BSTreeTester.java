//Farica Zhuang


import static org.junit.Assert.*;
import org.junit.*;


import java.util.*;

public class BSTreeTester {
	
	BSTree<Integer> emptyTree;
	BSTree<Integer> tree1;
	Iterator<Integer> iter;
	
	/**
	 * Set up method to be implemented before every test
	 */
	@Before
	public void setUp() {
		emptyTree = new BSTree<Integer>();
		tree1 = new BSTree<Integer>();
		for(int i = 0; i < 5; i++) {
			tree1.insert(new Integer(i));
		}
		
		//create an iterator for tree1
		iter = tree1.iterator();
	}
	
	//BSTree test 1
	/**
	 * Check if getRoot() correctly returns the root of the tree
	 */
	@Test
	public void testGetRoot() {
		assertEquals("getRoot() should return 0", 0, (int)tree1.getRoot().getKey());
	}

	//BSTree test 2
	/**
	 * Check if getSize() correctly returns the number of elements in the tree
	 */
	@Test
	public void testGetSize() {
		assertEquals("The size of the tree should be 5", 5, tree1.getSize());
	}
	
	//BSTree test 3
	/**
	 * Check if insert() successfully inserts a key into the tree
	 */
	@Test
	public void testInsert() {
		emptyTree.insert(new Integer(100));
		assertEquals("The size of the tree should be 1", 1, emptyTree.getSize());
		assertEquals("The root of the tree should be 100", 100,
						(int)emptyTree.getRoot().getKey());
		emptyTree.insert(new Integer(200));
		assertEquals("The size of the tree shoul dbe 2", 2, emptyTree.getSize());
		assertEquals("The root of the tree should still be 100", 100,
				(int)emptyTree.getRoot().getKey());
	}
	
	//BSTree test 4
	/**
	 * Checks if findKey() successfully returns true if the key is in the tree and 
	 * false otherwise
	 */
	@Test
	public void testFindKey() {
		assertTrue("Integer 100 is not in tree1 so findKey should return false", 
						!tree1.findKey(new Integer(100)));
		assertTrue("Integer 0 is in tree1 so findKey should return true",
						tree1.findKey(new Integer(1)));
	}
	
	//BSTree test 5
	/**
	 * Checks if insertInformation() successfully find a key and inserts the new
	 * info in that key
	 */
	@Test
	public void testInsertInformation() {
		tree1.insertInformation(new Integer(0), new Integer(33333));
		LinkedList<Integer> link = new LinkedList<Integer>();
		link = tree1.getRoot().getRelatedInfo();
		assertEquals("The size of the linkedlist should be 1", 1, link.size());
		assertEquals("The first element of the linkedlist should be 33333", 33333,
						(int)link.get(0));		
	}
	
	//BSTree test 6
	/**
	 * Checks if findMoreInformation() correctly returns the LinkedLIst of the node
	 * with the value 'key'
	 */
	@Test
	public void testFindMoreInformation() {
		//fill up the tree with nodes
		emptyTree.insert(new Integer(1125));
		emptyTree.insert(new Integer(3000));
		emptyTree.insert(new Integer(800));
		//insert information 
		emptyTree.insertInformation(new Integer(800), new Integer(8));
		emptyTree.insertInformation(new Integer(800), new Integer(0));
		emptyTree.insertInformation(new Integer(800), new Integer(0));
		LinkedList<Integer> link = new LinkedList<Integer>();
		link = emptyTree.findMoreInformation(new Integer(800));
		assertEquals("The size of the link should be 3", 3, link.size());
		assertEquals("The first element in the list should be 8", 8, (int)link.get(0));
		assertEquals("The second element in the list should be 0", 0, (int)link.get(1));
		assertEquals("The third element in the list should be 0", 0, (int)link.get(2));
	}
	
	//BSTree test 7
	/**
	 * Checks if findHeight correctly returns the height of the tree
	 */
	@Test
	public void testFindHeight() {
		assertEquals("The height of tree1 should be 4", 4, tree1.findHeight());
		assertEquals("The height of emptyTree should be -1", -1, emptyTree.findHeight());
	}
	
	//BSTree test 8
	/**
	 * Checks if leafCount correctly returns the numeber of leafs of a tree
	 */
	@Test
	public void testLeafCount() {
		assertEquals("tree1 should have 1 leaf", 1, tree1.leafCount());
		assertEquals("emptyTree should have 0 leaf", 0, emptyTree.leafCount());
	}
	
	//BSTree test 9
	/**
	 * Checks if insert() correctly throws NullPointerExcpetion when key is null
	 */
	@Test
	public void insertException() {
		//when key is null
		try {
			tree1.insert(null);
			fail("Should  have generated a NullPointerException");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	//BSTree test 10
	/**
	 * Checks if findKey() correctly throws a NullPointerException when key is null
	 */
	@Test
	public void findKeyException() {
		//when key is null
		try {
			tree1.findKey(null);
			fail("Should  have generated a NullPointerException");
		}
		catch (NullPointerException e) {
			
		}
	}

	//BSTree test 11
	/**
	 * Checks if insertInformation() correctly throws NullPointerException when key
	 * or info is null, and an IllegalArgumentException when key is not found
	 */
	@Test
	public void testInsertInformationException() {
		//when key is null
		try {
			emptyTree.insertInformation(null,new Integer(5));
			fail("Should have generated a NullPointerExcpetion");
		} 
		catch (NullPointerException e) {
			
		}
		
		//when info is null
		try {
			emptyTree.insertInformation(new Integer(5), null);
			fail("Should have generated an NullPointerExcpetion");
		} 
		catch (NullPointerException e) {
			
		}
		
		//when key and info are null
		try {
			emptyTree.insertInformation(null,null);
			fail("Should have generated an NullPointerExcpetion");
		} 
		catch (NullPointerException e) {
			
		}
		
		//when tree is empty
		try {
			emptyTree.insertInformation(new Integer(0), new Integer(1));
			fail("Should have generated an IllegalArgumentExcpetion");
		} 
		catch (IllegalArgumentException e) {
			
		}
		
		//when key is not found
		try {
			tree1.insertInformation(new Integer(10), new Integer(1));
			fail("Should have generated an IllegalArgumentExcpetion");
		} 
		catch (IllegalArgumentException e) {
			
		}
	}
	
	//BSTree test 12
	/**
	 * Checks if findMoreInformation correctly throws a NullPointerException if key is null
	 * and an IllegalArgumentException when key is not found in the tree
	 */
	@Test
	public void testFindMoreInformationException() {
		//when key is null
		try{
			tree1.findMoreInformation(null);
			fail("Should have generated a NullPointerException");
		}
		catch(NullPointerException e) {
			
		}
		
		//when key is not found in the tree
		try {
			tree1.findMoreInformation(new Integer(1000));
			fail("Should have generated an IllegalArgumentException");
		}
		catch(IllegalArgumentException e) {
			
		}
	}
	
	/**
	 * Checks if intersection() correctly returns the intersection nodes of two trees
	 */
	public void testIntersection() {
		//create iterator for empty tree
		Iterator<Integer> iterEmpty = emptyTree.iterator();
		ArrayList<Integer> intersection = new ArrayList<Integer>();
		intersection = tree1.intersection(iterEmpty, iter);
		assertEquals("The list should be empty", 0, intersection.size());
		//insert 0 and 5 to emptyTree
		emptyTree.insert(new Integer(0));
		emptyTree.insert(new Integer(5));
		iterEmpty = emptyTree.iterator();
		intersection = tree1.intersection(iterEmpty, iter);
		assertEquals("The list should have size 1", 1, intersection.size());
		assertEquals("The first element in the list should be 0", 0, 
						(int)intersection.get(0));
	}
	
	/*-------------------Tester for iterator----------------------------------------*/
	
	//BSTree_Iterator test 1
	/**
	 * Checks if this method correctly creates a stack of the leftPath of a tree
	 */
	@Test
	public void testBSTree_Iterator() {
		Iterator<Integer> iterEmpty = emptyTree.iterator();
		assertTrue("This should return false", !iterEmpty.hasNext());
	}
	
	//BSTree_Iterator test 2
	/**
	 * Checks if hasNext() correctly returns false if there are no more nodes left
	 * to iterate and true otherwise
	 */
	@Test
	public void testHasNext() {
		assertTrue("This should return true", iter.hasNext());
		for(int i = 0; i < 5; i++) {
			iter.next();
		}
		assertTrue("Now this should return false", !iter.hasNext());
	}
	
	//BSTree_Iterator test 3
	/**
	 * Checks if next correctly returns the next element in the stack
	 */
	@Test
	public void testNext() {
		assertEquals("This should return 0 since it's the smallest element", 
						0, (int)iter.next());
		assertEquals("This should return 0 since it's the next smallest element", 
						1, (int)iter.next());
		assertEquals("This should return 0 since it's the next smallest element", 
						2, (int)iter.next());
		assertEquals("This should return 0 since it's the next smallest element", 
						3, (int)iter.next());
		assertEquals("This should return 0 since it's the next smallest element", 
						4, (int)iter.next());
	}
	
	//BSTree_Iterator test 4
	/**
	 * Checks if iterator() correctly returns a new BSTree_Iterator
	 */
	@Test
	public void testIterator() {
		BSTree<Integer>.BSTree_Iterator emptyIter = tree1.new BSTree_Iterator();
		for(int i = 0; i < 5; i++) {
			emptyIter.next();
		}
		assertTrue("emptyIter should be empty", !emptyIter.hasNext());
		
		emptyIter = emptyIter.iterator();
		assertTrue("emptyIter should be reset so it is not empty", emptyIter.hasNext());
		assertEquals("This should return 0 since it's the smallest element", 
				0, (int)emptyIter.next());
		assertEquals("This should return 1 since it's the next smallest element", 
				1, (int)emptyIter.next());
		assertEquals("This should return 2 since it's the next smallest element", 
				2, (int)emptyIter.next());
		assertEquals("This should return 3 since it's the next smallest element", 
				3, (int)emptyIter.next());
		assertEquals("This should return 4 since it's the next smallest element", 
				4, (int)emptyIter.next());
	}
	
	//BSTree_Iterator test 5
	/**
	 * Checks if next() correctly throws an exception when there is no next item
	 */
	@Test
	public void testNextException() {
		try {
			for(int i = 0; i <= 5; i++) {
				iter.next();
			}
		}
		catch(NoSuchElementException e) {
			
		}
		
	}
}
