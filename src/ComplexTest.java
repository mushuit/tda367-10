



import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ComplexTest implements Game{

	public ComplexTest(){
		super();
			try {
				Image img = new Image("res/myimage.png");
			} catch (SlickException e) {
				System.out.println("Bild finns inte");
			}
	}

	public void init(GameContainer gc) throws SlickException {

	}

	public void update(GameContainer gc, int delta) throws SlickException{

			}

	public void render(GameContainer gc, Graphics g) throws SlickException{

			}

	public static void main(String[] args) throws SlickException{
		AppGameContainer app = 
				new AppGameContainer(new ComplexTest());

		app.setDisplayMode(800, 600, false);
		app.start();
			}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return ".:|BAWS 2|:.";
	}
}

