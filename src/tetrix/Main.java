package tetrix;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import tetrix.util.Util;
import tetrix.view.StateHandler;

public class Main {
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new StateHandler());
		app.setTargetFrameRate(Util.FPS);
		app.setShowFPS(false);
        app.setDisplayMode(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT, false);
        app.start();
	}	
}