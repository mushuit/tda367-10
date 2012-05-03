package tetrix.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import tetrix.core.FileReader;

public class TestFileReader {

	@Test
	public void test() throws FileNotFoundException {
		FileReader f = new FileReader("highscore/highscore.dat");
		String s =f.getRow();
		assertTrue(s.equals("Pelle:26"));
		
	}
	@Test
	public void testhasRow() throws FileNotFoundException {
		FileReader f = new FileReader("highscore/highscore.dat");
		boolean b =f.hasRow();
		assertTrue(b == true);
		
	}
	
}

