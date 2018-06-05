package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

abstract class Order {

	/**
	 * order 'allFiles' and return a list of ordered files
	 * @return an array of File objects of the filtered list
	 */
	abstract public ArrayList<File> order(ArrayList<File> files);

}
