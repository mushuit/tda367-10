package tetrix.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public interface IHighScore {
	List<Entry> getHighScore() throws FileNotFoundException;
	void setHighScore(String playerName, int score) throws IOException; 
}
