package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public class ContainsFilter extends Filter {

	/**
	 * Calls the Filter's constructor
	 */
	public ContainsFilter(String SourceDir) {
		super(SourceDir);
	}

	/**
	 * Filters 'allFiles' by testing if it contains a certain name
	 * @param args the name to search for
	 * @return a filtered list
	 */
	@Override
	public ArrayList<File> filter(String[] args) {
		// todo Add Exceptions
		String name = args[0];
		ArrayList<File> filteredFiles = new ArrayList<File>();
		for(File file : allFiles) {
			if(file.getName().contains(name)) {
				filteredFiles.add(file);
			}
		}
		return filteredFiles;
	}
}
