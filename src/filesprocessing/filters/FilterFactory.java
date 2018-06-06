package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterFactory {

	private static final SizeFilter GREATER = new SizeFilter(SizeFilter.SIZE_TYPE.GREATER);
	private static final SizeFilter BETWEEN = new SizeFilter(SizeFilter.SIZE_TYPE.BETWEEN);
	private static final SizeFilter SMALLER = new SizeFilter(SizeFilter.SIZE_TYPE.SMALLER);
	private static final NameFilter FILE = new NameFilter(NameFilter.NAME_TYPE.FILE);
	private static final NameFilter CONTAINS = new NameFilter(NameFilter.NAME_TYPE.CONTAINS);
	private static final NameFilter PREFIX = new NameFilter(NameFilter.NAME_TYPE.PREFIX);
	private static final NameFilter SUFFIX = new NameFilter(NameFilter.NAME_TYPE.SUFFIX);
	private static final PropertyFilter EXE = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.EXE);
	private static final PropertyFilter WRITE = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.WRITABLE);
	private static final PropertyFilter HIDDEN = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.HIDDEN);
	private static final AllFilter ALL = new AllFilter();

	static ArrayList<File> allFiles;

	public FilterDecorator createFilter(String filesDir, String type, String[] args, boolean not) {
		FilterDecorator decorator = new FilterDecorator(null, not);
		File[] files = new File(filesDir).listFiles();
		allFiles = new ArrayList<File>(Arrays.asList(files));
		switch(type) {
			case "greater_than":
				decorator.setFilter(GREATER);
				break;
			case "between":
				decorator.setFilter(BETWEEN);
				break;
			case "smaller_than":
				decorator.setFilter(SMALLER);
				break;
			case "file":
				decorator.setFilter(FILE);
				break;
			case "contains":
				decorator.setFilter(CONTAINS);
				break;
			case "prefix":
				decorator.setFilter(PREFIX);
				break;
			case "suffix":
				decorator.setFilter(SUFFIX);
				break;
			case "writeable":
				decorator.setFilter(WRITE);
				break;
			case "executable":
				decorator.setFilter(EXE);
				break;
			case "hidden":
				decorator.setFilter(HIDDEN);
				break;
			case "all":
				decorator.setFilter(ALL);
				break;
			default:
				// todo: add error
				break;
		}
		return decorator;
	}
}
