package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TypeOrder extends Order {

	/**
	 * Calls the Order's constructor
	 */
	public TypeOrder(ArrayList<File> files) {
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
				String firstType = getFileExtension(o1);
				String secondType = getFileExtension(o2);
				if(firstType == secondType) {
					return 0;
				}
				return firstType.compareTo(secondType) < 0 ? -1 : 1;
			}
		});
		return orderedList;
	}

	/**
	 * Finds the extension of a file
	 * @param file the file to get the extension of
	 * @return the extension of the file
	 */
	private String getFileExtension(File file) {
		String absPath = file.getAbsolutePath();
		return absPath.substring(absPath.lastIndexOf('.') + 1);
	}

}
