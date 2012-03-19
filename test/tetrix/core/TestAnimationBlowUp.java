package tetrix.core;

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
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestAnimationBlowUp implements GameState, Game{
	private Animation animation;
	private Image[] images;
	private int xBullet;
	private int yBullet;
	private int xCannon;
	private int yCannon;
	private boolean shoot;
	private boolean start;
	private FadeOutTransition fadeOut;
	private boolean gameOver;
	
	public TestAnimationBlowUp(){
		animation = new Animation();
		images = new Image[5];
		xCannon = 317;
		yCannon = 360;
		yBullet = 370;
		xBullet = 334;
		shoot = false;
		start = false;
		gameOver = false;
		fadeOut = new FadeOutTransition();
	}
	public boolean closeRequested() {
		return true;
	}

	public String getTitle() {
		return "Blow-up animation";
	}

	public void init(GameContainer container) throws SlickException {
		mergeImages();
		animation = new Animation(images, 10);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setBackground(Color.black);
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
		
		if(gameOver){
			fadeOut.init(this, this);
			fadeOut.update(this, container, delta);
		}
	}
	
	public void animate(){
		if(shoot)
			animation.restart();
		
		animation.stopAt(5);
		animation.setLooping(false);
		gameOver = true;
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
	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
