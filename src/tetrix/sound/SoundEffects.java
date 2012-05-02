package tetrix.sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * A class that provides sound effects. 
 * 
 * @author Andreas Karlberg
 *
 */

public class SoundEffects {
	private static Sound sfxShot;
	private static Sound sfxBlowUp;
	private static Sound sfxMenuClick;
	private static Sound sfxHighScore;
	private static Sound sfxRowCleared;
	private static Sound sfxExplode;

	public SoundEffects() throws SlickException{
		sfxShot = new Sound("Sound/shot.wav");		// Sound of the bullet when you shoot
		sfxBlowUp = new Sound("Sound/hit.wav");	// Sound of a block blown up
		sfxMenuClick = new Sound("Sound/button.wav");	// Sound of a menu click
		sfxHighScore = new Sound("Sound/powerup.wav");	// Sound when you break a new High Score
		sfxRowCleared = new Sound("Sound/rowcleared.wav");		// Sound when it is Game Over
		sfxExplode = new Sound("Sound/explode.wav");		// Sound when the game pauses
	}

	public void shotPlay(){
		sfxShot.play();
	}

	public void shotLoop(){
		sfxShot.loop();
	}

	public void shotStop(){
		sfxShot.stop();
	}

	public void blowUpPlay(){
		sfxBlowUp.play();
	}

	public void blowUpLoop(){
		sfxBlowUp.loop();
	}

	public void blowUpStop(){
		sfxBlowUp.stop();
	}

	public void menuClickPlay(){
		sfxMenuClick.play();
	}

	public void menuClickLoop(){
		sfxMenuClick.loop();
	}

	public void menuClickStop(){
		sfxMenuClick.stop();
	}

	public void highScorePlay(){
		sfxHighScore.play();
	}

	public void highScoreLoop(){
		sfxHighScore.loop();
	}

	public void highScoreStop(){
		sfxHighScore.stop();
	}

	public void rowClearedPlay(){
		sfxRowCleared.play();
	}

	public void deathLoop(){
		sfxRowCleared.loop();
	}

	public void rowClearedStop(){
		sfxRowCleared.stop();
	}

	public void explodePlay(){
		sfxExplode.play();
	}
	
	public void explodeLoop(){
		sfxExplode.loop();
	}

	public void explodeStop(){
		sfxExplode.stop();
	}


	
}