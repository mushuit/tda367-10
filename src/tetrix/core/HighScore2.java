package tetrix.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



 /**
  * 
  * @author Andreas Karlberg
  *
  */

public class HighScore2 implements IHighScore {
	FileReader f;
	public HighScore2() throws FileNotFoundException {
		f = new FileReader("highscore/highscore.dat");
	}
	@Override
	public List<Entry> getHighScore(){
		List<Entry> l = new ArrayList<Entry>();
		while (f.hasRow()){
			String row = f.getRow();
			String[] np = row.split(":");
			String name = np[0];
			String points = np[1];
			Entry e = new Entry(name, Integer.valueOf(points));
			l.add(e);
		}
		return l;

	}


	@Override
	public void setHighScore(String playerName, int score) {
		List<Entry> ls = this.getHighScore();
		Entry e = ls.get(ls.size());
		if (e.getPoints()< score){
			ls.remove(e);
			ls.add(new Entry(playerName, score));
		}
		Collections.sort(ls);
		
<<<<<<< HEAD
		String row = f.getRow();
		
		for (int i=0; i<10; i++) {
		f.writeRow(row);
		
		}
		
=======
		//f.writeRow(row);
>>>>>>> 64ae216cdc783f9458f1d98286de7a382a07c213
	}

}
