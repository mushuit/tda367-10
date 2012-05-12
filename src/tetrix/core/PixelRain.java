package tetrix.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Class simulating square shaped raindrops falling from the top of the window to the bottom.
 * @author Linus Karlsson
 *
 */
public class PixelRain {

	private List<Shape> pixelList;
	private Random rand;
	private int pixelSize;
	
	public PixelRain() {
		this(5);
	}
	
	public PixelRain(final int pixelSize) {
		rand = new Random();
		pixelList = new ArrayList<Shape>();
		this.pixelSize = pixelSize;
		raining();
	}
	
	public void raining() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pixelList.add(new Rectangle(rand.nextInt(395) + 1, 0, pixelSize, pixelSize));
					Thread.sleep(100);
					raining();
	            } catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void movePixel(int rate) {
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
	
	public void setSize(int size) {
		pixelSize = size;
	}
}
