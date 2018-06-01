import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Tester implementation using ArrayList as the data structure.
 *
 * @author apul
 */
public class ArrayListTester implements TesterADT<String, MovieRecord> {
	// ArrayList of movie records.
	private List<MovieRecord> movieRecordList;

	/**
	 * Constructor that initializes the movieRecordList.
	 * 
	 * @param movieRecordList
	 *            list of movie records.
	 */
	public ArrayListTester(List<MovieRecord> movieRecordList) {
		System.out.println(movieRecordList.size());
		this.movieRecordList = movieRecordList;
	}

	/**
	 * Returns size of the data list.
	 * 
	 * @return number of movie records.
	 */
	public int size() {
		return movieRecordList.size();
	}

	/**
	 * Search for records having index value equal to key.
	 * 
	 * @param index
	 *            the index (attribute) we want to search for.
	 * @param key
	 *            the key value we are looking for.
	 * @return the list of movie records.
	 */
    @Override
	public List<MovieRecord> searchByKey(String index, String key) {
    	// TODO
    	// Go over all movie records and compare the attribute (index
    	// e.g., director) with the key (e.g, Christopher Nolan)
   	    // NOTE: Ignore case while comparing the attribute (index) value
        // with the key.
    		List<MovieRecord> result = new ArrayList<>();
    		for(MovieRecord e:movieRecordList)
    		{

				String string = null;

				switch (index){
				case Config.DIRECTOR: string = e.getDirector();break;
				case Config.RATING: string = e.getRating();break;
				case Config.TITLE : string = e.getTitle();break;
				case Config.RELEASE_YEAR : string = e.getReleaseYear(); break;
				}
				
		
				if(string.equalsIgnoreCase(key)){
					result.add(e);
				}
				
			}
    		
    	

    	return result;
    }

    /**
     * Search for records having index value within the range minVal and maxVal.
     * 
     * @param index
     *            the index tree to search.
     * @param minVal
     *            minimum value of the range (inclusive).
     * @param maxVal
     *            maximum value of the range (inclusive).
     * @return list of MovieRecords with index key in the specified range (both inclusive).
     */
	@Override
	public List<MovieRecord> rangeSearch(String index, String minVal, String maxVal) {
		List<MovieRecord> result = new ArrayList<>();
		for(MovieRecord e:movieRecordList)
		{

			String string = null;

			switch (index){
			case Config.DIRECTOR: string = e.getDirector();break;
			case Config.RATING: string = e.getRating();break;
			case Config.TITLE : string = e.getTitle();break;
			case Config.RELEASE_YEAR : string = e.getReleaseYear(); break;
			}
			
			if(string.compareTo(minVal)>=0&&string.compareTo(maxVal)<=0){
				result.add(e);
			}
			
		}
    	return result;
    }

	/**
	 * Returns a sorted list of keys - index defines which key we want to sort on.
	 * Hint: You can define a Comparator to compare two MovieRecords based on
	 * specified index (e.g., director). You can then use this comparator class
	 * to directly sort using List.sort method.
	 * 
	 * @param index the index to which sort on.
	 * @return Sorted list of key values. 
	 * E.g., [..., "Christopher Nolan", ..., "James Cameron", ...] for director as index.
	 */
	@Override
	public List<String> allSortedKeys(String index){
		
		Comparator<MovieRecord> c = new Comparator<MovieRecord>(){
			@Override
			public int compare(MovieRecord o1, MovieRecord o2) {
				String o1String = null;
				String o2String = null;

				switch (index){
				case Config.DIRECTOR: 
					o1String = o1.getDirector();
					o2String = o2.getDirector();
					break;
				case Config.RATING:
					o1String = o1.getRating();
					o2String = o2.getRating();
					break;
				case Config.TITLE : 
					o1String = o1.getTitle();
					o2String = o2.getTitle();
					break;
				case Config.RELEASE_YEAR : 
					o1String = o1.getReleaseYear();
					o2String = o2.getReleaseYear();
					break;
				}

				return o1String.compareToIgnoreCase(o2String);
			}

			
		};
		movieRecordList.sort(c);
		List<String> sortedList = new ArrayList<>();
		for(MovieRecord e : movieRecordList){
				String string = null;

				switch (index){
				case Config.DIRECTOR: string = e.getDirector();break;
				case Config.RATING: string = e.getRating();break;
				case Config.TITLE : string = e.getTitle();break;
				case Config.RELEASE_YEAR : string = e.getReleaseYear(); break;
				}
				sortedList.add(string);

				
			}

		 return sortedList;	
		}
	
	}
	
		
	
	
	
