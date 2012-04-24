package tetrix.core;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		return (Integer) p2.getScore() - (Integer) p1.getScore();
	}

}
