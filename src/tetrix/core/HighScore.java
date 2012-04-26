package tetrix.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.newdawn.slick.SavedState;


/**
 * Class which is keeping track of a high score list.
 * @author Linus Karlsson, Andreas Karlberg
 *
 */

public class HighScore{
	private List<Player> scores;
	private int maxValue;

	public HighScore(){
		scores = new ArrayList<Player>();	
		maxValue = 10;
	}
	
	public void addToHighScore(Player player) {
		scores.add(player);
		sortByScore();
	}
	
	public void removeScore(Player player) {
		scores.remove(player);
	}
	
	public void sortByScore() {
		Collections.sort(scores, new ScoreComparator());
	}
	
	public List<Player> getList() {
		return scores;
	}
	
	public void sortByName() {
		Collections.sort(scores, new NameComparator());
	}
	
	public int numberOfPlayersInList() {
		return scores.size();
	}
	
	public void clearList() {
		scores.clear();
	}
	
	public int getMaxListLength() {
		return maxValue;
	}
	
	public void save() {

	}
	
	public void load() {
		
	}
	
	public void getString(){
		
	}
	
	public void getNumber(){
		
	}
}
