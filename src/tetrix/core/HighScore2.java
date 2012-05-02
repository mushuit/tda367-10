package tetrix.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



 /**
  * 
  * @author Andreas Karlberg
  *
  */

public class HighScore2 {
	FileReader f;
	public HighScore2() throws FileNotFoundException {
	   f = new FileReader("highscore/highscore.dat");
		// TODO Auto-generated constructor stub
	}

	public List<Entry> getHighScore(){
		
		List<Entry> l = new ArrayList<Entry>();
												// gšra en loop
		String row = f.getRow();
		String[] np = row.split(":");
		String name = np[0];
		String points = np[1];
		Entry e = new Entry(name, Integer.valueOf(points));
		l.add(e);
		return l;
	}

}
