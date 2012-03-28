package tetrix.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TestBlockbox implements Game {
	private BlockBox bBox;
	
	public TestBlockbox(){
		//bBox = new BlockBox();
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Test Blockbox";
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestBlockbox());
			app.setDisplayMode(500, 600, false);
		      app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
