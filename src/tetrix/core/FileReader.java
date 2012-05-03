package tetrix.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Andreas Karlberg
 *
 */

public class FileReader {
	  private Scanner scanner;
	
	public FileReader(String fileName) throws FileNotFoundException{
	    scanner = new Scanner(new FileInputStream(fileName));

	}

		public String getRow(){
			return scanner.nextLine();

		}
		public boolean hasRow(){
			return scanner.hasNext();
		}
		
		public void writeRow(String row) {
			
		}
}