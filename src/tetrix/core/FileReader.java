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
			/**   log("Reading from file.");
			    StringBuilder text = new StringBuilder();
			    String NL = System.getProperty("line.separator");
			    try {
			      while (scanner.hasNextLine()){
			        text.append(scanner.nextLine() + NL);
			      }
			    }
			    finally{
			      scanner.close();
			    }
			   log("Text read in: " + text);
			   */
		}
		public boolean hasRow(){
			return scanner.hasNext();
		}
}