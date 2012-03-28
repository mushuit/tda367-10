package tetrix.core;

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

	}
}
