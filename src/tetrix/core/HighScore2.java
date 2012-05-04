package tetrix.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * 
 * @author Andreas Karlberg
 *
 */

public class HighScore2 implements IHighScore {

	public HighScore2() throws FileNotFoundException {
	}
	@Override
	public List<Entry> getHighScore() throws FileNotFoundException{
		FileReader f = new FileReader("highscore/highscore.dat");
		List<String> rows = f.getRows();
		List<Entry> l = new ArrayList<Entry>();
		for (String row : rows){
			String[] np = row.split(":");
			String name = np[0];
			String points = np[1];
			Entry e = new Entry(name, Integer.valueOf(points));
			l.add(e);
		}
		return l;

	}


	@Override
	public void setHighScore(String playerName, int score) throws IOException {
		List<Entry> ls = this.getHighScore();
		Entry e = ls.get(ls.size()-1);
		if (e.getPoints()< score){
			ls.remove(e);
			ls.add(new Entry(playerName, score));
		}
		Collections.sort(ls);


		List<String> ss = new ArrayList<String>();
		for(Entry e1:ls){
			String row = e1.getName() + ":" + e1.getPoints();
			ss.add(row);
		}
		FileReader f = new FileReader("highscore/highscore2.dat");
		f.writeRows(ss);
	}
}


