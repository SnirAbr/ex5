package filesprocessing.filters;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterDecorator {

	/* The filter to work with */
	Filter filter;

	/* whether the 'NOT' option was used or not */
	boolean not;

	/**
	 * Class' Constructor
	 */
	public FilterDecorator(Filter filter, boolean not) {
		this.filter = filter;
		this.not = not;
	}

	/**
	 * Filter 'allFiles' and return a list of filtered files
	 * @param args filter arguments
	 * @return an array of File objects of the filtered list
	 */
	public ArrayList<File> filter(String[] args) {
		ArrayList<File> filteredFiles = this.filter.filter(args);
		if(not) {
			ArrayList<File> allFilesCopy = new ArrayList<File>(allFiles);
			allFilesCopy.removeAll(filteredFiles);
			return allFilesCopy;
		}
		return filteredFiles;
	}

}
