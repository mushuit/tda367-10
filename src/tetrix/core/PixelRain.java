package tetrix.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Class simulating square shaped raindrops falling from the top of the window to the bottom.
 * @author Linus Karlsson
 *
 */
public class PixelRain {
	
	private Timer timer;
	private List<Shape> pixelList;
	private Random rand;
	
	public PixelRain() {
		this(5);
	}
	
	public PixelRain(final int pixelSize) {
		rand = new Random();
		pixelList = new ArrayList<Shape>();
		
		timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	        	pixelList.add(new Rectangle(rand.nextInt(395) + 1, 0, pixelSize, pixelSize));
	          }
	        }, 100, 100);
	}
	
	public void move(int rate) {
		for(int i = 0; i < pixelList.size(); i++) {
			Shape currentPixel = pixelList.get(i);
			
			currentPixel.setY(currentPixel.getY() + rate);
			if(currentPixel.getY() <= 0) {
				pixelList.remove(currentPixel);
			}
		}
	}
	
	public List<Shape> getList() {
		return pixelList;
	}
}
