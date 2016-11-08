package Zillow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

class InvalidSortField extends Exception {
	public InvalidSortField(String msg) {
		super(msg);
	}
}

public class PersonWrapper {

	/*************************************************************************************
	 * Sort : This function returns the sorted List of Persons according to the sortfield.
	 * 		  The sorting direction is ascending or descending based on ascending flag.
	 * 		  Default direction, if ascending flag is null will be descending.
	 * 		  If the input list contains a null Person, that item is not considered for
	 * 		  sorting. So the size of the input and output list may differ.
	*************************************************************************************/
	static List<Person> sort(Iterable<Person> people, String sortField, String ascending) 
			throws InvalidSortField {
		if(people == null) return null;
		
		List<Person> pList = new ArrayList<Person>();
		for (Person p : people) {
			if(p != null)
				// Sort and return only non null Persons
				pList.add(p);
		}

		// if the sortField is invalid throw an exception to the caller
		if(sortField == null)
			throw new InvalidSortField("sortField can not be null !");
		sortField = sortField.trim();
		if(!Person.getcompMap().containsKey(sortField))
			throw new 
				InvalidSortField("Field " + sortField + " does not exist in class Person !");
		
		Comparator<Person> comparator = Person.getcompMap().get(sortField);
		if (Boolean.parseBoolean(ascending))
			// If the sortField is null for some Persons then they are going
			// to be at the end after ascending sorting
			Collections.sort(pList, comparator);
		else
			// If the sortField is null for some Persons then they are going
			// to be at the end after descending sorting
			Collections.sort(pList, Collections.reverseOrder(comparator));

		return pList;
	}
}
