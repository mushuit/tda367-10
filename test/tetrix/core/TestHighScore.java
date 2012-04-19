package tetrix.core;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * Class testing HighScore's sort, add, clear and remove player functions.
 * @author Linus Karlsson
 *
 */
public class TestHighScore {

	@Test
	public void test() {

		HighScore hs = new HighScore();
		
		Player p1 = new Player(1000, "Erik");
		Player p2 = new Player(434, "Johan");
		Player p3 = new Player(4563, "Albin");
		Player p4 = new Player(6745, "Oskar");
		Player p5 = new Player(2342, "Tomas");
		Player p6 = new Player(6436, "Jesper");
		Player p7 = new Player(1231, "Marcus");
		Player p8 = new Player(2356, "Tobbe");
		Player p9 = new Player(2342, "Bernard");
		
		hs.addToHighScore(p1);
		hs.addToHighScore(p2);
		hs.addToHighScore(p3);
		hs.addToHighScore(p4);
		hs.addToHighScore(p5);
		hs.addToHighScore(p6);
		hs.addToHighScore(p7);
		hs.addToHighScore(p8);
		hs.addToHighScore(p9);
		
		System.out.println("Sort by score: ");
		hs.sortByScore();
		for(Player p : hs.getList()) {
			System.out.println(p.getName() + "\t" + p.getScore());
		}
		
		System.out.println("\nSort by name: ");
		hs.sortByName();
		for(Player p : hs.getList()) {
			System.out.println(p.getName() + "\t" + p.getScore());
		}
		
		hs.removeScore(p3);
		System.out.println("\nTesting remove function. p3 (Albin) should be missing:");
		for(Player p : hs.getList()) {
			System.out.println(p.getName() + "\t" + p.getScore());
		}
		
		hs.clearList();	
		if(hs.getList().size() != 0) {
			System.out.println("\nClear list doesn't work properly.");
		} else {
			System.out.println("\nClear list works perfectly!");
		}
	}
}
