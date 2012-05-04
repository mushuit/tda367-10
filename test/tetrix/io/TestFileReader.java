package tetrix.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tetrix.core.FileReader;

public class TestFileReader {

	@Test
	public void test() throws FileNotFoundException {
		FileReader f = new FileReader("highscore/highscore.dat");
		List<String> s =f.getRows();
		assertTrue(s.size()==3);

	}

	@Test
	public void testWrite() throws IOException{
		FileReader f = new FileReader("highscore/highscore2.dat");
		List<String> ls = new ArrayList<String>();
		ls.add("xxx");
		ls.add("yyy");
		ls.add("zzz");
		f.writeRows(ls);
	}

}

