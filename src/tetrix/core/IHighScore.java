package tetrix.core;

import java.util.List;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public interface IHighScore {
	List<Entry> getHighScore();
	void setHighScore(String playerName, int score); 
}
