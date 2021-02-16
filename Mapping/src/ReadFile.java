import java.io.File;  
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class ReadFile {
	
  public ArrayList<Word> Read(String FileName) {
	ArrayList<Word> Return = new ArrayList<Word>();
    try {
      File f = new File(FileName);
      Scanner myReader = new Scanner(f);
      while (myReader.hasNextLine()) {
    	  String NextLine = myReader.nextLine();
    	  if (NextLine.split(":").length == 2) {
    		  Word temp = new Word(NextLine.split(":")[0].strip(), NextLine.split(":")[1].strip());
	    	  Return.add(temp);
    	  }else {
    		  System.out.println(NextLine.split(":").length);
    		  continue;
    	  }
      }
      myReader.close();
      return Return;
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
      return null;
    }
	
  }
}