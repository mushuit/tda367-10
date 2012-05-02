package tetrix.core;

import java.util.List;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public interface HighScores {
	List<Entry> getHighScore(String fileName);
	void setHighScore(String playerName, int score); 
}
