package tetrix.core;

/**
 * 
 * @author Andreas karlberg
 * 
 */

import java.awt.List;

public class getHighScore {

	List<Entry> getHigScore(String fileName){
		List<Entry> l = new ArrayList<Entry>();
		
		String row = getRow();
		String[] np = row.split(":");
		String name = np[0];
		String points = np[1];
		Entry e = new Entry(name, Integer.valueOf(points));
		l.add(e);
		return l;
	}
}
