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

	public static void shotPlay(){
		sfxShot.play();
	}

	public static void shotLoop(){
		sfxShot.loop();
	}

	public static void shotStop(){
		sfxShot.stop();
	}

	public static void blowUpPlay(){
		sfxBlowUp.play();
	}

	public static void blowUpLoop(){
		sfxBlowUp.loop();
	}

	public static void blowUpStop(){
		sfxBlowUp.stop();
	}

	public static void menuClickPlay(){
		sfxMenuClick.play();
	}

	public static void menuClickLoop(){
		sfxMenuClick.loop();
	}

	public static void menuClickStop(){
		sfxMenuClick.stop();
	}

	public static void highScorePlay(){
		sfxHighScore.play();
	}

	public static void highScoreLoop(){
		sfxHighScore.loop();
	}

	public static void highScoreStop(){
		sfxHighScore.stop();
	}

	public static void rowClearedPlay(){
		sfxRowCleared.play();
	}

	public static void deathLoop(){
		sfxRowCleared.loop();
	}

	public static void rowClearedStop(){
		sfxRowCleared.stop();
	}

	public static void explodePlay(){
		sfxExplode.play();
	}
	
	public static void explodeLoop(){
		sfxExplode.loop();
	}

	public static void explodeStop(){
		sfxExplode.stop();
	}


	
}