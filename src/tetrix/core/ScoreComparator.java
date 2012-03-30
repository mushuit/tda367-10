package tetrix.core;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		Integer score1 = (Integer) p1.getScore();
		Integer score2 = (Integer) p2.getScore();
		
		
		return score1.compareTo(score2);
	}

}
