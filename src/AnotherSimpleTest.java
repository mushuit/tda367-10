import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class AnotherSimpleTest implements Game{

	int yMax = 480;
	int xMax = 640;
	int xPos = xMax/2;
	int yPos = yMax/2;
	int movement = 1;
	int bossMovement = 1;
	int xBossPos = xMax/2;
	int yBossPos = yMax;
	int xBoss2Pos = xMax;
	int yBoss2Pos = yMax/2;
	int size = 10;
	int[] yPositions;
	int[] xPositions;
	int[] yBossPositions;
	int[] xBossPositions;
	int[] yBoss2Positions;
	int[] xBoss2Positions;
	String play;
	boolean dead = false;
	double poang = 0;
	boolean pause = false;

	public AnotherSimpleTest(){
		yPositions = new int[size*2];
		xPositions = new int[size*2];
		yBossPositions = new int[size*2];
		xBossPositions = new int[size*2];
		yBoss2Positions = new int[size*2];
		xBoss2Positions = new int[size*2];
		play = "You are still alive";
	}

	public void init(GameContainer container) throws SlickException {

	}

	public void update(GameContainer container, int delta)
			throws SlickException {
		Input input = container.getInput();
		if(input.isKeyPressed(input.KEY_P)){
			pause = !pause;
		}
		if(!pause){
			if(!dead)
				poang += 0.1;

			for(int i = 0; i < 20; i++){
				yPositions[i] = yPos+10-i;
				xPositions[i] = xPos+10-i;
				yBossPositions[i] = yBossPos+10-i;
				xBossPositions[i] = xBossPos+10-i;
				yBoss2Positions[i] = yBossPos+10-i;
				xBoss2Positions[i] = xBossPos+10-i;
			}
			if(input.isKeyDown(Input.KEY_DOWN)){
				yPos = yPos+movement;
			}
			if(input.isKeyDown(Input.KEY_UP)){
				yPos = yPos-movement;
			}
			if(input.isKeyDown(Input.KEY_RIGHT)){
				xPos = xPos+movement;
			}
			if(input.isKeyDown(Input.KEY_LEFT)){
				xPos = xPos-movement;
			}
			if(input.isKeyDown(Input.KEY_B)){
				xPos = 100;
				yPos = 100;
			}
			if(input.isKeyDown(Input.KEY_ENTER)){
				dead = false;
				play = "You are still alive";
				poang = 0;
			}
			yBossPos += bossMovement;
			xBoss2Pos += bossMovement;

			if(yBossPos > yMax){
				yBossPos = 0;
				xBossPos = (int)(Math.random()*xMax);
			}
			if(xBoss2Pos > xMax){
				xBoss2Pos = 0;
				yBoss2Pos = (int)(Math.random()*yMax);
			}
			if(yPos > yMax){
				yPos = 0;
			} else if(yPos < 0){
				yPos = yMax;
			}
			if(xPos > xMax){
				xPos = 0;
			} else if(xPos < 0){
				xPos = xMax;
			}
			for(int h = 0; h < 20; h++){
				for(int i = 0; i < 20; i++){
					if(yBossPositions[h] == yPositions[i]){
						for(int j = 0; h < 20; h++){
							for(int k = 0; i < 20; i++){
								if(xBossPositions[j] == xPositions[k]){
									play = "You died(Enter to restart)";
									dead = true;
								}
							}
						}
					}
				}
			}
			for(int h = 0; h < 20; h++){
				for(int i = 0; i < 20; i++){
					if(xBoss2Positions[h] == xPositions[i]){
						for(int j = 0; h < 20; h++){
							for(int k = 0; i < 20; i++){
								if(yBoss2Positions[j] == yPositions[k]){
									play = "You died(Enter to restart)";
									dead = true;
								}
							}
						}
					}
				}
			}
		}
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.green);
		g.drawString(play + ", poŠng: " + (int)poang, 0, 100);
		if(!dead)
			g.drawRect(xPos, yPos, size, size);
		g.setColor(Color.red);
		g.drawOval(xBossPos, yBossPos, 20, 20, 20);
		g.drawOval(xBossPos, yBossPos, 20, 20);
		g.drawOval(xBoss2Pos, yBoss2Pos, 20, 20);

	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new AnotherSimpleTest());
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return ".:|BAWS|:.";
	}

}
