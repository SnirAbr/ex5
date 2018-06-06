package filesprocessing.orders;

import java.io.File;
import java.util.Collections;
import java.util.ArrayList;

public class OrderDecorator {

	/* The order to work with */
	Order order;

	/* whether the 'REVERSE' option was used or not */
	boolean reverse;

	/**
	 * Class' Constructor
	 */
	public OrderDecorator(Order order, boolean reverse) {
		this.order = order;
		this.reverse = reverse;
	}

	/**
	 * Filter 'allFiles' and return a list of ordered files
	 * @param files files that need to be ordered
	 * @return an array of File objects of the ordered list
	 */
	public ArrayList<File> order(ArrayList<File> files) {
		ArrayList<File> orderedFiles = this.order.order(files);
		if(reverse) {
			Collections.reverse(orderedFiles);
		}
		return orderedFiles;
	}

	/**
	 * Sets a new order to the decorator
	 * @param newOrder the new order
	 */
	public void setOrder(Order newOrder) {
		this.order = newOrder;
	}

}
