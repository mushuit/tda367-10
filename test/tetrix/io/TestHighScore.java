package tetrix.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import tetrix.core.Entry;
import tetrix.core.HighScore2;

public class TestHighScore {

	@Test
	public void test() throws FileNotFoundException {
		HighScore2 h = new HighScore2();
		List<Entry> es = h.getHighScore();
		Entry first = es.get(0);
		Entry second = es.get(1);
		Entry third = es.get(2);
		String name = first.getName();
		String name2 = second.getName(); 
		String name3 = third.getName();
		Integer points = first.getPoints();
		Integer points2 = second.getPoints();
		Integer points3 = third.getPoints();
		assertTrue(name.equals("Pelle"));
		assertTrue(name2.equals("Olle"));
		assertTrue(name3.equals("Johan"));
		assertTrue(points.equals(26));
		assertTrue(points2.equals(23));
		assertTrue(points3.equals(12));
	}
	@Test
	public void testUpdate() throws IOException{
		HighScore2 h = new HighScore2();
		h.setHighScore("kalle", 9);
		List<Entry> es = h.getHighScore();
		Entry first = es.get(0);
		String name = first.getName();
		Integer points = first.getPoints();
		assertTrue(name.equals("kalle"));
		assertTrue(points.equals(250));
		
	}

}
