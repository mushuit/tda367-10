package tetrix;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class TestProject {
	
	
	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new GamePlayState());
			app.start();
			app.setShowFPS(false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
