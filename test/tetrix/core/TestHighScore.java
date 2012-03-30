package tetrix.core;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestHighScore {

	@Test
	public void test() {
		HighScore hs = new HighScore();
		
		// Create players
		Player p1 = new Player(1000, "Erik");
		Player p2 = new Player(434, "Johan");
		Player p3 = new Player(4563, "Albin");
		Player p4 = new Player(6745, "Oskar");
		Player p5 = new Player(2342, "Tomas");
		Player p6 = new Player(6436, "Jesper");
		Player p7 = new Player(1231, "Marcus");
		Player p8 = new Player(2356, "Tobbe");
		Player p9 = new Player(2342, "Bernard");
		
		// Add to High Score list
		hs.addToHighScore(p1);
		hs.addToHighScore(p2);
		hs.addToHighScore(p3);
		hs.addToHighScore(p4);
		hs.addToHighScore(p5);
		hs.addToHighScore(p6);
		hs.addToHighScore(p7);
		hs.addToHighScore(p8);
		hs.addToHighScore(p9);
		
		
		
		Iterator<String> iter = hs.iterator();
		
		
		
	}

}
