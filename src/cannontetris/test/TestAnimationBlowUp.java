package cannontetris.test;

import javax.swing.ImageIcon;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TestAnimationBlowUp implements Game{
	private Animation animation;
	private Image[] images;
	private int xBullet;
	private int yBullet;
	private int xCannon;
	private int yCannon;
	private boolean shoot;
	private boolean start;
	
	public TestAnimationBlowUp(){
		animation = new Animation();
		images = new Image[5];
		xCannon = 317;
		yCannon = 360;
		yBullet = 370;
		xBullet = 334;
		shoot = false;
		start = false;
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
		mergeImages();
		animation = new Animation(images, 10);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		g.setColor(Color.blue);
		if(!start)
		g.fillRect(xCannon+10, 180, 20, 20);
		if(start)
		g.drawAnimation(animation, xCannon-30, 140);

		
		g.setColor(Color.black);
		if(shoot)
		g.fillRect(xBullet, yBullet, 6, 6);

		
		g.setColor(Color.red);
		g.fillRect(xCannon, yCannon, 40, 10);
		g.fillRect(xCannon+15, yCannon-10, 10, 10);
		g.setColor(Color.black);
		if(shoot)
		g.fillRect(xBullet, yBullet, 6, 6);
		
		g.setColor(Color.red);
		g.fillRect(xCannon, yCannon, 40, 10);
		g.fillRect(xCannon+15, yCannon-10, 10, 10);
		
		g.setColor(Color.red);
		g.fillRect(xCannon, yCannon, 40, 10);
		g.fillRect(xCannon+15, yCannon-10, 10, 10);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_SPACE)){
			shoot = true;
		}
		if(shoot){
			yBullet -= 1;
		}
		if(yBullet == 210){
			animate();
			shoot = false;
			start = true;
			yBullet = 360;
		}

		if(animation.isStopped())
			start = false;
	}
	
	public void animate(){
		if(shoot)
			animation.restart();
		
		animation.stopAt(5);
		animation.setLooping(false);
		
	}
	
	public void mergeImages(){
		try {
			images[0] = new Image("img/blowUp0.jpg");
			images[1] = new Image("img/blowUp1.jpg");
			images[2] = new Image("img/blowUp2.jpg");
			images[3] = new Image("img/blowUp3.jpg");
			images[4] = new Image("img/blowUp4.jpg");
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("failed to load image");
		}
	}
	
	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestAnimationBlowUp());
			app.start();
			app.setShowFPS(false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
