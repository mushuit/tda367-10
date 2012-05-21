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
	private static Sound sfxSpeedUp;
	
	private static SoundEffects instance = null;
	
	private SoundEffects(){}

	private static float fxVolume = 1;
	
	public static synchronized SoundEffects instance() throws SlickException {
		if(instance == null) {
			sfxShot = new Sound("sound/shot.wav");		// Sound of the bullet when you shoot
			sfxBlowUp = new Sound("sound/hit.wav");		// Sound of a block blown up
			sfxMenuClick = new Sound("sound/button.wav");	// Sound of a menu click
			sfxHighScore = new Sound("sound/speedup.wav");	// Sound when you break a new High Score
			sfxRowCleared = new Sound("sound/rowcleared.wav");		// Sound when a row is cleared
			sfxExplode = new Sound("sound/explosion.wav");		// Sound when the game pauses and when it is Game Over
			sfxSpeedUp = new Sound("sound/speedup.wav");		// Sound when the game is speeded up
		}
		
		return instance;
	}

	public static void shot(){
		sfxShot.play(1, fxVolume);
	}

	public static void blowUpPlay(){
		sfxBlowUp.play(1, fxVolume);
	}

	public static void blowUpLoop(){
		sfxBlowUp.loop(1, fxVolume);
	}

	public static void blowUpStop(){
		sfxBlowUp.stop();
	}

	public static void menuClickPlay(){
		sfxMenuClick.play(1, fxVolume);
	}

	public static void menuClickLoop(){
		sfxMenuClick.loop(1, fxVolume);
	}

	public static void menuClickStop(){
		sfxMenuClick.stop();
	}

	public static void highScorePlay(){
		sfxHighScore.play(1, fxVolume);
	}

	public static void highScoreLoop(){
		sfxHighScore.loop(1, fxVolume);
	}

	public static void highScoreStop(){
		sfxHighScore.stop();
	}

	public static void rowClearedPlay(){
		sfxRowCleared.play(1, fxVolume);
	}

	public static void rowClearedLoop(){
		sfxRowCleared.loop(1, fxVolume);
	}

	public static void rowClearedStop(){
		sfxRowCleared.stop();
	}
	
	public static void speedUpPlay(){
		sfxSpeedUp.play(1, fxVolume);
	}

	public static void speedUpLoop(){
		sfxSpeedUp.loop(1, fxVolume);
	}

	public static void speedUpStop(){
		sfxSpeedUp.stop();
	}

	public static void explodePlay(){
		sfxExplode.play(1, fxVolume);
	}
	
	public static void explodeLoop(){
		sfxExplode.loop(1, fxVolume);
	}

	public static void explodeStop(){
		sfxExplode.stop();
	}

	public static void setFxVolume(float fx) {
		fxVolume = fx;
	}
	
}