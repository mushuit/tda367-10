package tetrix.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.newdawn.slick.SavedState;


/**
 * Class representing a High score list made up of a list of Player objects. 
 * Singleton, meaning that there can only be one instance of the class.
 * @author Linus Karlsson, Andreas Karlberg
 *
 */
public class HighScore{
	private static HighScore instance = null;
	private static List<Player> players;
	private static int maxPlayers;
	
	private HighScore() {}

	public static synchronized HighScore instance() {
		if(instance == null) {
			instance = new HighScore();
			maxPlayers = 10;
			players = new ArrayList<Player>();
		}
		return instance;
	}
	
	public void addToHighScore(Player player) {
		players.add(player);
		sortByScore();
		if(players.size() > maxPlayers) {
			players.remove(maxPlayers);
		}
	}
	
	public void removeScore(Player player) {
		players.remove(player);
	}
	
	public void sortByScore() {
		Collections.sort(players, new ScoreComparator());
	}
	
	public List<Player> getList() {
		return players;
	}
	
	public void sortByName() {
		Collections.sort(players, new NameComparator());
	}
	
	public void clearList() {
		players.clear();
	}
	
	public int getMaxListLength() {
		return maxPlayers;
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
