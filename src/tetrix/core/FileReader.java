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
 * The File reader reads and writes strings to the specific highscore file.
 * It also reads and writes the player name to the specific player name file.
 * @author Andreas Karlberg
 *
 */

public class FileReader {
	public FileReader(String fileName) throws FileNotFoundException{

	}

	public List <String> getFromHighScore() throws FileNotFoundException{
		List <String> l = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream("highscore/highscore.dat"));
		while (scanner.hasNext()){
			String row = scanner.next();
			l.add(row);
		}
		return l;

	}
	
	public static String getPlayerName() throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileInputStream("highscore/playername.dat"));
		return scanner.next();
	}
	
	public void writePlayerName(String player) throws IOException {
		Writer output = new BufferedWriter(new FileWriter("highscore/playername.dat"));
		output.write(player);
		output.close();
		
	}

	public void writeToHighScore(List<String> rows) throws IOException {
		Writer output = new BufferedWriter(new FileWriter("highscore/highscore.dat"));

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
