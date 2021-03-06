package tetrix.core;

/**
 * A class that keeps track of the player's name and score
 * 
 * @author Linus Karlsson
 *
 */

public class Player{
	private static int score;
	private String name;
	
	public Player() {
		score = 0;
	}
	
	public Player(String name) {
		this.score = 0;
		this.name = name;
	}
	
	public Player(int score, String name) {
		this.score = score;
		this.name = name;
	}
	
	public static int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore() {
		increaseScore(100);
	}
	
	public void increaseScore(int score) {
		this.score += score;
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
