package tetrix.core;

/**
 * 
 * This class makes an Entry of the player name and the players points.
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

	@Override
	public String toString() {
		return "Entry [name=" + name + ", points=" + points + "]";
	}

}
