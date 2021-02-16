import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteFile {
  public void Write(String FileName, Word AWord) {
    try {
      FileWriter w = new FileWriter(FileName);
      w.write(AWord.toString());
      w.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}