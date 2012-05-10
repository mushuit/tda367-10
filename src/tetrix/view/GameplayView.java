package tetrix.view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import tetrix.core.BlockBox;
import tetrix.core.Bullet;
import tetrix.core.Cannon;
import tetrix.core.CollisionHandler;
import tetrix.core.Player;
import tetrix.core.Position;
import tetrix.core.tetrominos.Square;
import tetrix.core.tetrominos.Tetromino;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for updating and rendering of the gameplay view.
 * @author Magnus Huttu, Linus Karlsson
 *
 */
public class GameplayView extends BasicGameState {

	private int stateID;

	private Image background;
	private Image cannonImage;
	private Image iBlock;
	private Image jBlock;
	private Image lBlock;
	private Image oBlock;
	private Image tBlock;
	private Image sBlock;
	private Image zBlock;
	private Image lockedBlock;
	private Image screenCapture;

	private Cannon cannon;
	private Player player;
	private Bullet bullet; 
	private BlockBox blockBox;
	private CollisionHandler ch;

	private List<Bullet> bulletList;
	private List<Image> blocks;

	private UnicodeFont scoreDisplay;
	private boolean isPaused;
	private long timerInterval;

	public GameplayView(int stateID) {
		this.stateID = stateID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background= new Image("img/game_background.png");
		cannonImage = new Image("img/cannon2.png");
		screenCapture = new Image(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT);

		iBlock = new Image("img/block/purple.png");
		jBlock = new Image("img/block/blue.png");
		lBlock = new Image("img/block/orange.png");
		oBlock = new Image("img/block/yellow.png");
		tBlock = new Image("img/block/green.png");
		sBlock = new Image("img/block/red.png");
		zBlock = new Image("img/block/turquoise.png");
		lockedBlock = new Image("img/block/locked.png");
		cannon = new Cannon();
		bulletList = new ArrayList<Bullet>();
		blocks = new ArrayList<Image>();
		player = new Player();
		blockBox = new BlockBox(player);
		ch = new CollisionHandler(blockBox);

		timerInterval = 2000;
		Font font = new Font("Verdana", Font.PLAIN,55);

		scoreDisplay = new UnicodeFont(font , 15, true, false);
		scoreDisplay.addAsciiGlyphs();
		scoreDisplay.addGlyphs(400, 600);
		scoreDisplay.getEffects().add(new ColorEffect(java.awt.Color.YELLOW));
		try {
			scoreDisplay.loadGlyphs();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		cannonImage.draw(cannon.getX(), cannon.getY());
		scoreDisplay.drawString(5, 0, player.getScore() + "");

		if(blockBox.isInUse()){
			int i = 0;


			blockBox.update();
			putImage();

			for(Position[] p : blockBox.getPos()){
				for(Position pe : p){
					blocks.get(i).draw(pe.getX(), pe.getY());
					i++;
				}
			}
		}

		g.setColor(Color.black);
		for(int i = 0; i < bulletList.size(); i++){
			g.fillRect(((Bullet) bulletList.get(i)).getX(), ((Bullet) bulletList.get(i)).getY(), 5, 5);

		}

		if(isPaused) {
			g.copyArea(screenCapture, 0, 0);
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		isPaused = false;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		checkInput(input, sbg);

		int size = bulletList.size();
		for(int i = 0; i < size; i++){
			if(!ch.checkCollision(bulletList.get(i))){
				bulletList.get(i).update();
			} else{
				bulletList.remove(i);
				size--;
			}
		}

		cannonImage.setRotation(cannon.getRotation());
	}

	public void checkInput(Input input, StateBasedGame sbg) {
		int updateSpeed = 500/Util.FPS;

		if(input.isKeyDown(Input.KEY_RIGHT)) {
			cannon.move(updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_LEFT)) {
			cannon.move(-updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_D)) {
			cannon.move(updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_A)) {
			cannon.move(-updateSpeed);
		}

		if(input.isKeyPressed(Input.KEY_SPACE)) {
			bullet = new Bullet(cannon.getPosition(), cannon.getValue());
			bulletList.add(bullet);
		}

		if(input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			isPaused = true;
			sbg.enterState(States.PAUSEDGAMEVIEW.getID(), new EmptyTransition(), new FadeInTransition());
		}
	}

	/**
	 * Repeatedly create a new block at a given speed
	 */
	public void startTimer(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if(!isPaused){
						blockBox.newBlock((int)(Math.random()*7+0.5));
					}
					Thread.sleep(timerInterval);
					startTimer();
				} catch (SlickException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void putImage() throws SlickException{
		Image block = null;
		blocks.clear();
		for(Tetromino t : blockBox.getTetroList()){
			for(Square s : t.getSquares()){
				if(!s.isMoving()){
					block = lockedBlock;
				} else{


					if(t.toString().equals("I")){
						block = iBlock;
					}else if(t.toString().equals("J")){
						block = jBlock;
					}else if(t.toString().equals("L")){
						block = lBlock;
					}else if(t.toString().equals("O")){
						block = oBlock;
					}else if(t.toString().equals("T")){
						block = tBlock;
					}else if(t.toString().equals("S")){
						block = sBlock;
					}else if(t.toString().equals("Z")){
						block = zBlock;
					}
				}

				//		for(int i = 0; i < blockBox.getTetroList().size(); i++){
				//			if(blockBox.getTetroList().get(i).toString().equals("I")){
				//				block = iBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("J")){
				//				block = jBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("L")){
				//				block = lBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("O")){
				//				block = oBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("T")){
				//				block = tBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("S")){
				//				block = sBlock;
				//			}else if(blockBox.getTetroList().get(i).toString().equals("Z")){
				//				block = zBlock;
				//			}

				if(!s.destroyed()){
					blocks.add(block);
				}
			}
		}
	}


	public Image getPausedScreen() {
		return screenCapture;
	}

	public void pause() {
		isPaused = true;
	}

	/**
	 * Resets the values
	 */
	public void newGame() {
		player.resetScore();
		timerInterval = 2000;
		blockBox.clearBoard();
		blocks.clear();
		bulletList.clear();
		cannon.reset();
	}

	public void increaseSpeed(int value) {
		timerInterval -= value; 
	}

	public void setLevel(int i){
		blockBox.setLevel(i);
	}

	@Override
	public int getID() {
		return stateID;
	}
}
