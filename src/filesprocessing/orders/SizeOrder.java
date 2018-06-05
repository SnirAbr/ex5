package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SizeOrder extends Order {

	/**
	 * Calls the Order's constructor
	 */
	SizeOrder(ArrayList<File> files) {
		super(files);
	}

	/**
	 * Order 'allFiles' by absolute path
	 * @return the ordered list
	 */
	@Override
	public ArrayList<File> order() {
		ArrayList<File> orderedList = new ArrayList<File>(allFiles);
		Collections.sort(orderedList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				long firstSize = o1.length();
				long secondSize = o2.length();
				if(firstSize == secondSize) {
					return 0;
				}
				return firstSize < secondSize ? -1 : 1;
			}
		});
		return orderedList;
	}

}
