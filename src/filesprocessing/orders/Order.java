package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

abstract public class Order {

	/**
	 * list of files that need to be ordered
	 */
	protected final ArrayList<File> allFiles;

	/**
	 * constructs a filter with file directory
	 * @param files an array list of files
	 */
	public Order(ArrayList<File> files){
		// todo Check for nullity
		allFiles = files;
	}

	/**
	 * order 'allFiles' and return a list of ordered files
	 * @return an array of File objects of the filtered list
	 */
	abstract public ArrayList<File> order();

}
