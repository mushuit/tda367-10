package Test;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TestAnimationBlowUp implements Game{
	Animation animation;
	Image[] images;
	
	public TestAnimationBlowUp(){
		animation = new Animation();
		
		
	}
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
		g.setBackground(Color.white);
		g.setColor(Color.blue);
		g.fillRect(320, 240, 20, 20);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		
	}
	
	public void animate(){
		
	}
	
	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestAnimationBlowUp());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
