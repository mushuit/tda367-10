package tetrix.database;

/**
 * An interface for handling the highscores for the database.
 * 
 * @author Jonathan Kara
 *
 */
public interface HighscoreHandler {

	/**
	 * Inserts the information about the highscores in the database
	 * @param name The player's name
	 * @param score The score
	 */
	public void saveHighscore(String name, int score);
	
	/**
	 * A method that loads information from the database about highscores. 
	 * 
	 * @return a matrix containing the players' names and highscores
	 */
	public String[][] loadHighscore();
}
