package tetrix.core;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public class FileReader {
	public FileReader(String fileName) throws FileNotFoundException{


	}

	public List <String> getRows() throws FileNotFoundException{
		List <String> l = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream("highscore/highscore.dat"));
		while (scanner.hasNext()){
			String row = scanner.next();
			l.add(row);
		}
		return l;

	}

	public void writeRows(List<String> rows) throws IOException {
		Writer output = new BufferedWriter(new FileWriter("highscore/highscore2.dat"));

		try {
			for(String s:rows){
				output.write( s +"\n" );
			}
		}

		finally {
			output.close();
		}
	}
}
