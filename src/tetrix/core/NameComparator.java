package tetrix.core;

import java.util.Comparator;

public class NameComparator implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		return p1.getName().compareTo(p2.getName());
	}

}
