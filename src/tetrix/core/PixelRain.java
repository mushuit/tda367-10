package tetrix.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import tetrix.util.Util;

/**
 * Class simulating square shaped raindrops falling from the top of the window
 * to the bottom.
 * 
 * @author Linus Karlsson
 * 
 */
public class PixelRain {

	private List<Shape> pixelList;
	private Random rand;
	private int pixelSize;
	private int startValue;

	public PixelRain() {
		this(5);
	}

	public PixelRain(final int pixelSize) {
		rand = new Random();
		pixelList = new ArrayList<Shape>();
		this.pixelSize = pixelSize;
		raining();
		startValue = 0;
	}

	public void raining() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pixelList.add(new Rectangle(rand.nextInt(Util.WINDOW_WIDTH
							- pixelSize) + 1, startValue, pixelSize, pixelSize));
					Thread.sleep(100);
					raining();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void movePixel(int rate) {
		for (int i = 0; i < pixelList.size(); i++) {
			Shape currentPixel = pixelList.get(i);

			currentPixel.setY(currentPixel.getY() + rate);
			if (currentPixel.getY() >= Util.WINDOW_HEIGHT) {
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
