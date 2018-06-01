///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieDbMain.java
// File:             IndexTreeIterator.java
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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The Iterator for IndexTree that is built using Java's Stack class. This
 * iterator steps through the IndexTree using an INORDER traversal.
 *
 * @author apul
 */
public class IndexTreeIterator<K extends Comparable<K>, V> implements
	Iterator<IndexTreeNode<K, V>> {

	// Stack to track where the iterator is in the tree.
	Stack<IndexTreeNode<K, V>> stack;

	/**
	 * Constructs the iterator so that it is initially at the smallest value in
	 * the set. Hint: Go to farthest left node and push each node onto the stack
	 * while stepping down the IndexTree to get there.
	 *
	 * @param node
	 *            the root node of the IndexTree
	 */
	public IndexTreeIterator(IndexTreeNode<K, V> node) {
		// TODO
		//initizlised new stack then use helper funciton to initialize the 
		//iterator
		stack = new Stack<IndexTreeNode<K, V>>();
		traverseTree(node,stack);
		
	}
	
	private void traverseTree( IndexTreeNode<K, V> node,Stack<IndexTreeNode<K, V>> stack){
		if(null==node){
			//base case if node is null
			return;
		}else{
			//call recursive method and travers through right subtree
			traverseTree(node.getRightChild(),stack);
			//push it intro the new strack
			stack.push(node);
			//call recursive method to traverse through left subtree
			traverseTree(node.getLeftChild(),stack);
		}
	}

	/**
	 * Returns true iff the iterator has more items.
	 *
	 * @return true iff the iterator has more items
	 */
	public boolean hasNext() {
		//if stack is not empty return true other wise false
		if(!stack.isEmpty()){
			return true;
		}
		return false;
	}


	/**
	 * Returns the next node in the iteration.
	 *
	 * @return the next item in the iteration
	 * @throws NoSuchElementException
	 *             if the iterator has no more items.
	 */
	public IndexTreeNode<K, V> next() {
		// TODO
		//if tree is empty return no such elemtn
		if(hasNext()==false){
			throw new NoSuchElementException();
		}
		//return the enxt element in stack
		return stack.pop();
		
	}

	/**
	 * Not Supported
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
