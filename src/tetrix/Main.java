package tetrix;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import tetrix.util.Util;
import tetrix.view.StateHandler;

/**
 * Tetrix, a game in which you control a cannon moving around a typical Tetris gameboard.
 * Instead of moving the actual tetrominos, the player's mission is to shoot and form them
 * in a way so that they are aligned at the bottom - resulting in points.
 * 
 * @author Andreas Karlberg, Jonathan Kara, Linus Karlsson, Magnus Huttu
 *
 */
public class Main {
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new StateHandler());
		app.setTargetFrameRate(Util.FPS);
		app.setShowFPS(false);
        app.setDisplayMode(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT, false);
        app.start();
	}	
}