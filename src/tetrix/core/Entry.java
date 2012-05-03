package tetrix.core;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public class Entry implements Comparable<Entry> {
	private String name;
	private int points;
	
	public Entry (String name, int points){
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int compareTo(Entry arg0) {
		if(arg0.points < points){
			return -1;
		} else if (arg0.points > points){
			return 1;
		} else {
			return 0;
		}
	}
}
