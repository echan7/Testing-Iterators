///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieDbTesterMain.java
// File:             IndexTreeTester.java
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Tester implementation using IndexTree as the data structure.
 *
 * DO NOT MODIFY THIS CLASS.
 *
 * @author apul
 */

public class IndexTreeTester implements TesterADT<String, MovieRecord> {

	// MovieDb object which creates IndexTrees and provides an interface to
	// query the IndexTree for given index (e.g., director).
	private MovieDb movieDb;
	
	public IndexTreeTester(List<MovieRecord> movieRecords) {
		//initizlize new movieDB
		movieDb = new MovieDb(movieRecords);	
	}
	
	/**
	 * Returns a list of data values having index key equal to input key.
	 * For example, search for MovieRecords with director (index) =
	 * James Cameron (key). Hint: Use movieDb methods. 
	 * 
	 * @param index
	 *            index (attribute) of the movie record by which search is to be
	 *            done.
	 * @param key
	 *            key to be searched.
	 * @return list of movie records that match the key.
	 */
	@Override
	public List<MovieRecord> searchByKey(String index, String key) {
		// TODO
		//return the call to search method 
		return movieDb.getIndexTree(index).search(key);
	}

	/**
	 * Returns a list of data values having index key in the specified range.
	 * For example, search for MovieRecords with 2015 &gt;= releaseYear (index) &lt;= 2016.
	 * Hint: Use movieDb methods. 
	 * 
	 * @param index
	 *            attribute of the movie record by which search is to be done.
	 * @param minVal
	 *            lower bound of the range search.
	 * @param maxVal
	 *            upper bound of the range search.
	 * @return list of movie records that fall in the given range.
	 */
	@Override
	public List<MovieRecord> rangeSearch(String index, String minVal, String maxVal) {
		// TODO
		//return the call to rangesearch method
		return movieDb.getIndexTree(index).rangeSearch(minVal, maxVal);
	}

	/**
	 * Returns a sorted list of keys - index defines which key we want to sort
	 * on. Hint: Use IndexTreeIterator to do in-order traversal.
	 * 
	 * @param index
	 *            the index to which sort on.
	 * @return list of index values sorted in lexicographically increasing order.
	 * E.g., [..., "Christopher Nolan", ..., "James Cameron", ...] for director
	 * as index.
	 */
	@Override
	public List<String> allSortedKeys(String index) {
		//declare new array list 
		ArrayList<String>sortedList = new ArrayList<String>();
		//declare iterator
		Iterator<IndexTreeNode<String, List<MovieRecord>>> itr = movieDb.getIndexTree(index).iterator();
		while(itr.hasNext()){
			//check if theres element then add to enw list
			sortedList.add(itr.next().getKey());
		}
		
		//return the new list
		return sortedList;
	}

}
