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
	
	private static SoundEffects instance = null;
	
	private SoundEffects(){}

	private static float fxVolume = 1;
	
	public static synchronized SoundEffects instance() throws SlickException {
		if(instance == null) {
			sfxShot = new Sound("sound/shot.wav");		// Sound of the bullet when you shoot
			sfxBlowUp = new Sound("sound/hit.wav");	// Sound of a block blown up
			sfxMenuClick = new Sound("sound/button.wav");	// Sound of a menu click
			sfxHighScore = new Sound("sound/powerup.wav");	// Sound when you break a new High Score
			sfxRowCleared = new Sound("sound/rowcleared.wav");		// Sound when it is Game Over
			sfxExplode = new Sound("sound/explosion.wav");		// Sound when the game pauses
		}
		
		return instance;
	}

	public static void shot(){
		sfxShot.play();
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

	public static void rowClearedLoop(){
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

	public static void setFxVolume(float fx) {
		fxVolume = fx;
	}
	
	public static float getFxVolume() {
		return fxVolume;
	}


	
}