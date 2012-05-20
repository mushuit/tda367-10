package tetrix.core;

import java.util.Comparator;

public class NameComparator implements Comparator<Entry> {

	@Override
	public int compare(Entry e1, Entry e2) {
		return e1.getName().compareTo(e2.getName());
	}

}
