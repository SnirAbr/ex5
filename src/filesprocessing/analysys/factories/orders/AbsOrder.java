package filesprocessing.analysys.factories.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AbsOrder extends Order {

	/**
	 * Calls the Order's constructor
	 */
	public AbsOrder(ArrayList<File> files) {
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
				String firstPath = o1.getAbsolutePath();
				String secondPath = o2.getAbsolutePath();
				if(firstPath == secondPath) {
					return 0;
				}
				return firstPath.compareTo(secondPath) < 0 ? -1 : 1;
			}
		});
		return orderedList;
	}


}
