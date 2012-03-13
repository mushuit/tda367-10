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
	int xBossPos = xMax/2;
	int yBossPos = yMax;
	int xBoss2Pos = xMax;
	int yBoss2Pos = yMax/2;
	int size = 10;
	int[] yPositions;
	int[] xPositions;
	String play;
	boolean dead = false;
	double poang = 0;
	
	public AnotherSimpleTest(){
		yPositions = new int[size*2];
		xPositions = new int[size*2];
		play = "You are still alive";
	}
	
    public void init(GameContainer container) throws SlickException {

    }
    
    public void update(GameContainer container, int delta)
            throws SlickException {
    	Input input = container.getInput();
    	if(!dead)
    	poang += 0.1;
    	
    	for(int i = 0; i < 20; i++){
    		yPositions[i] = yPos+10-i;
    		xPositions[i] = xPos+10-i;
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
    	}
    	yBossPos += movement;
    	xBoss2Pos += movement;
    	
    	if(yBossPos > yMax){
    		yBossPos = 0;
    		xBossPos = (int)(Math.random()*xMax);
    	}
    	if(xBoss2Pos > xMax){
    		xBoss2Pos = 0;
    		yBoss2Pos = (int)(Math.random()*yMax);
    	}
    	for(int i = 0; i < 20; i++){
    		if(yBossPos == yPositions[i]){
    			if(xBossPos == xPositions[i]){
    				play = "You died!";
    				dead = true;
    			}
    		
    		}
    	}
    	for(int i = 0; i < 20; i++){
    		if(xBoss2Pos == xPositions[i]){
    			if(yBoss2Pos == yPositions[i]){
    				play = "You died";
    				dead = true;
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
