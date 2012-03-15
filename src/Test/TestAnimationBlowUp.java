package Test;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TestAnimationBlowUp implements Game{

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Blow-up animation";
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
	}
	private static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestAnimationBlowUp());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
