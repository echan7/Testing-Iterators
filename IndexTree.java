///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieDbMain.java
// File:             IndexTree.java
// Semester:         Fall 2016
//
// Author:           Eric Chan
// CS Login:         echan
// Lecturer's Name:  Deb Deppeler
// Lab Section:      Lecture 002
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jie Shen Ong
// Email:            jong4@wisc.edu
// CS Login:         jieo
// Lecturer's Name:  Deb Deppeler
// Lab Section:      Lecture 002
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generic IndexTree implementation where each node is identified by a key and
 * can have a list of values i.e duplicate keys are allowed.
 * 
 * For example: You can insert &lt;K1, V1&gt; and &lt;K1, V2&gt; in the IndexTree.
 * After you insert, the node corresponding to key K1 will have a list of data
 * values [V1, V2].
 *
 * @author CS367
 */
public class IndexTree<K extends Comparable<K>, V> implements IndexTreeADT<K, V>, 
Iterable<IndexTreeNode<K, List<V>>> {

	// Root node.
	private IndexTreeNode<K, List<V>> root;

	/**
	 * Constructs a IndexTree and initializes the root node.
	 */
	public IndexTree() {
		root = null;
	}

	/**
	 * Returns iterator with respect to the root node.
	 * 
	 * @return the iterator for the IndexTree.
	 */
	public Iterator<IndexTreeNode<K, List<V>>> iterator() {
		return new IndexTreeIterator<K, List<V>>(root);
	}

	/**
	 * Search for the node with key equals to input key. Hint: Call
	 * a search helper method to recursively traverse the tree.
	 *
	 * @param key
	 *            the key to search.
	 * @return data value in the tree for the corresponding key.
	 * @throws IllegalArgumentException
	 *             if key is null.
	 * @throws KeyNotFoundException
	 * 				if key is not found in the tree.
	 */	
	public List<V> search(K key) {
		// TODO
		// 1. Check for null key and throw IllegalArgumentException() exception.
		if(key == null){
			throw new IllegalArgumentException();
		}
		// 2. Call Search helper with root and key to be searched. 
		List<V> data =  search(key,root);
		// 3. Throw KeyNotFoundException() if key is not found.

		if(data == null){
			throw new KeyNotFoundException();
		}
		// return the list that are equal to keys
		return data;
	}
	/**
	 * helper function to help search method
	 * with parameter: key and node 
	 * return the node with equals key
	 */
	private List<V> search(K key,IndexTreeNode<K, List<V>> node ){
	if(node==null){
		// if node is null return null
			return null;
	}else{
	/**
	 * if node is same return node's data as base case
	 * else get left subtree if key < node
	 * else get right subtreee if key > node
	 */
			if(node.getKey().equals(key)){
				return node.getData();
			}
	
			if(node.getKey().compareTo(key)>0){
				return search(key, node.getLeftChild());
			}
	
			if(node.getKey().compareTo(key)<0){
				return search(key,node.getRightChild());
			}
	}
	return node.getData();
	}

	/**
	 * Inserts a (key, value) pair into the IndexTree. This will call a recursive
	 * method with root node and (key, value) to be inserted in the IndexTree.
	 * 
	 * @param key
	 *            key of the new data to be inserted.
	 * @param value
	 *            data to be inserted.
	 * @throws IllegalArgumentException
	 *             if key or value is null.
	 */
	public void insert(K key, V value) {
		// TODO
	 
		  if(null == root){
			  //assign function to root if root is null
			  root = insert(root,key,value);
		  }else{
			  //else insert on the existing root
		  insert(root,key,value);
		  }
	}

	/** 
	 * Recursive helper method to find the position and insert a key and value
	 * into the IndexTree. 
	 * 
	 * NOTE: STUDENTS MUST IMPLEMENT insert recursively, 
	 * but you may define your own recursive helper method instead of
	 * defining and using this method. 
	 * 
	 * @param node
	 *            node is the recursive parameter with initial value being root
	 *            of the IndexTree.
	 * @param key
	 *            key of the new data to be inserted.
	 * @param value
	 *            data to be inserted.
	 */	
	private IndexTreeNode<K, List<V>> insert(IndexTreeNode<K, List<V>> node, 
					K key, V value) {
		// TODO
		// 1. Check if node is null. If so, create a new IndexTreeNode<K,List<V>>.
		
		if(node==null){
		//    a.  create an ArrayList that stores type V items
				ArrayList<V> dataList = new ArrayList<>() ;
		//    b.  add value to that array list
				dataList.add(value);
		//    c.  create the IndexTreeNode with the specified key and the list 
				IndexTreeNode<K, List<V>> newNode = new IndexTreeNode<K, List
						<V>>(key, dataList);
				
			
				return newNode;
		//    d.  return the newly created node
		}else{
		// TODO
			
		// 2. If node is not null, compare key with current node's key. 
		// There are 3 cases:
		// 	a. If key = current node's key, 
				if(node.getKey().compareTo(key)==0){
					node.getData().add(value);
					return node;
					//if key < node;s key, get left sub tree 
				}else if(node.getKey().compareTo(key)>0){
					 node.setLeftChild(insert(node.getLeftChild(),key,value));
					 //if key > node's key, get right sub tree
				}else if(node.getKey().compareTo(key)<0){
					node.setRightChild(insert(node.getRightChild(),key,value));
				}
		}
		//return the node that has been inserted
		return node;
		
		}

	/**
	 * Returns a list of data values which have keys in the specified range
	 * (inclusive of minValue and maxValue). Hint: This must be done recursively
	 * using a range search helper method and call it with root.
	 * Note: Range values are always compared lexicographically For example,
	 * "15" &lt; "7" lexicographically.
	 *
	 * @param minValue
	 *            the minimum value of the desired range (inclusive).
	 * @param maxValue
	 *            the maximum value of the desired range (inclusive).
	 * @return list of data values having key in the specified range.
	 * @throws IllegalArgumentException
	 *             if either minValue or maxValue is null.
	 */	
    public List<V> rangeSearch(K minValue, K maxValue) {
		// TODO check for IllegalArgumentException
    	if(minValue==null||maxValue==null){
    		throw new IllegalArgumentException();
    	}

    	// Ensure min is less than max
		if ( minValue.compareTo(maxValue) > 0 ) {
			K t = minValue;
			minValue = maxValue;
			maxValue = t;
		}
		List<V> results = new ArrayList<V>();

    	return rangeSearch( minValue, maxValue,root,results);
    }
    /**
     * helper function for range search with 4 parameters
     */
    private List<V> rangeSearch(K minValue, K maxValue,IndexTreeNode<K, List<V>> 
    node, List<V> results) {
	
    if(node==null){
    	//if node is null, return the newly created list 
			return results;
    	}else{
    		//if in range, add data to list 
			if(node.getKey().compareTo(minValue)>=0 && 
					node.getKey().compareTo(maxValue)<=0){
				results.addAll(node.getData());
			} 
			//recursive functions to get all left and right child
				 rangeSearch(minValue,maxValue, node.getLeftChild(),results);
				 rangeSearch(minValue,maxValue, node.getRightChild(), results);
			
	}
    //return the newly created list
	return results;
	
    }
	/**
	 * Returns the number of nodes in the tree. This must be done recursively
	 * using the helper method to get the number of nodes.
	 * 
	 *  @return number of nodes in the tree.
	 */
	public int size() {	
		return size(root);
	}
	
	/**
	 * helper function for size
	 */
	private int size(IndexTreeNode<K, List<V>> node){
		if(node==null){
			//base case if next node is null, return 0
			return 0;
		}
		//return 1 for current node + recursive method of left and right child
		return 1 + size(node.getLeftChild()) + size(node.getRightChild());
		
	}

	/**
	 * Returns height of the tree. Hint: Use a recursive helper method
	 * and call it with root node to calculate the height.
 	 *
 	 * @return the height of the tree.
	 */
	public int getHeight() {
		// TODO
		
		return getHeight(root);
	}
	
	private int getHeight(IndexTreeNode<K, List<V>> node){
		if(node == null){
			// base case if node is null return 0
			return 0;
		}
		//return current 1 (for the current level) + maximum of recursive level 
		//of left and right sub tree
		return 1 + Math.max(getHeight(node.getLeftChild()), 
				getHeight(node.getRightChild()));
	}
	
	/**
	 * Returns total number of data values in the tree.
	 * Hint: Call a recursive helper method to recursively count the nodes.
	 * 
	 * @return the total data count (values of all data values in the tree).
	 */
	public int getTotalDataCount() {

		return getTotalDataCount(root);
	}
	
	private int getTotalDataCount(IndexTreeNode<K, List<V>> node){
		
		if(node==null){
			// base case if node is null return 0
			return 0;
		}
		//return the current data size + recursive method for left and 
		//right sub tree
		return node.getData().size() + getTotalDataCount(node.getLeftChild()) + 
				getTotalDataCount(node.getRightChild());
	}

	/**
	 * Returns average number of data values per node (E.g., Node with key "key"
	 * and list of values List&lt;V&gt; = {v1, v2, v3} has number of data values 
	 * as 3)
	 * rounded to 3 decimal places.
	 * Hint: Use getTotalDataCount() and size().
	 * 
	 * @return the average data count.
	 */
	public double getAvgDataCount() {

		//use simple algebra for total data count/ size
			double count = getTotalDataCount()/(double)size();
			//round to 3 decimals places
			count = Math.round(count*1000);
			count = count/(double)1000;
			//return the results
		return count ;
	}
	
	/**
	 * Displays the top maxNumLevels of the tree. DO NOT CHANGE IT!
	 * You can use this method to debug your code.
	 *
	 * @param maxNumLevels
	 *            from the top of the IndexTree that will be displayed.
	 */
	public void displayTree(int maxNumLevels) {
		System.out.println("---------------------------" +
					"IndexTree Display--------------------------------");
		displayTree(root, 0, maxNumLevels);
	}

	/**
	 * Recursive helper function to display the top levels of the IndexTree.
	 * 
	 * @param node
	 *            initial value being root of IndexTree.
	 * @param curLevel
	 *            initial value 0.
	 * @param maxNumLevels
	 *            initial value being the number of levels of the tree to be
	 *            displayed.
	 */
	private void displayTree(IndexTreeNode<K, List<V>> node, int curLevel,
			int maxNumLevels) {
		//i cahnge this because TA say so, your wish is mine to command
		if (maxNumLevels <= curLevel)
			return;
		if (node == null)
			return;
		for (int i = 0; i < curLevel; i++) {
			System.out.print("|--");
		}
		System.out.println(node.getKey());
		displayTree(node.getLeftChild(), curLevel + 1, maxNumLevels);
		displayTree(node.getRightChild(), curLevel + 1, maxNumLevels);
	}

}
