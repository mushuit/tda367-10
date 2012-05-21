package tetrix.sound;

import java.io.FileNotFoundException;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import tetrix.core.HighScore;
import tetrix.view.theme.ThemeHandler;

/**
 * A class that provides game music
 * 
 * @author Andreas Karlberg
 * 
 */

public class GameMusic {
	private static Music gameMusic;
	private static GameMusic instance;

	private GameMusic() {
	}

	public static synchronized GameMusic instance()
			throws FileNotFoundException, SlickException {
		if (instance == null) {
			instance = new GameMusic();
			gameMusic = ThemeHandler.getMusic();
		}
		return instance;
	}

	public void changeSong() throws SlickException {
		gameMusic.fade(2000, 0, true);
		gameMusic = ThemeHandler.getMusic();
		gameMusicLoop();
	}
	
	public void gameMusicPlay() {
		gameMusic.play();
	}

	public void gameMusicPause() {
		gameMusic.pause();
	}

	public void gameMusicLoop() {
		gameMusic.loop();
	}

	public void gameMusicStop() {
		gameMusic.stop();
	}
	
	public void gameMusicResume() {
		gameMusic.resume();
	}

	public void getGameMusicVolume() {
		gameMusic.getVolume();
	}

	public static void setGameMusicVolume(float value){
		gameMusic.setVolume(value);
	}
}
