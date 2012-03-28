package tetrix.core;

<<<<<<< HEAD
public class HighScore{
	private int Score;
	private String PlayerName;

	public int getScore(){
		return Score;
	}
	public String getName(){
		return PlayerName;
	}
	public HighScore(int Score, String PlayerName){
		this.Score = Score;
		this.PlayerName = PlayerName;

=======
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class which is keeping track of a high score list.
 * @author Linus Karlsson, Andreas Karlberg
 *
 */
public class HighScore{
	private List<Player> scores;

	public HighScore(){
		scores = new ArrayList<Player>();	
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
	
	public void sortByName() {
		Collections.sort(scores, new NameComparator());
>>>>>>> Did some changes in the HighScore class and added a description to the other classes.
	}
}
