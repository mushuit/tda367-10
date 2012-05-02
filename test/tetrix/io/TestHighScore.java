package tetrix.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import tetrix.core.Entry;
import tetrix.core.FileReader;
import tetrix.core.HighScore2;

public class TestHighScore {

	@Test
	public void test() throws FileNotFoundException {
		HighScore2 h = new HighScore2();
		List<Entry> es = h.getHighScore();
		Entry first = es.get(0);
		String name = first.getName();
		assertTrue(name.equals("Pelle"));
	}

}
