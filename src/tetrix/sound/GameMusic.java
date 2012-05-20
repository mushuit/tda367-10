package tetrix.sound;

import java.io.FileNotFoundException;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import tetrix.core.HighScore;

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
			gameMusic = new Music("Sound/background-music.wav");
		}
		return instance;
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

	public void setGameMusicVolume(float value) {
		gameMusic.setVolume(value);
	}
}
