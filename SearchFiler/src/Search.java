import java.io.File;
import java.util.ArrayList;

public class Search 
{
   public static ArrayList<File> search(String directoryName) throws Exception 
   {
      File dir = new File(directoryName);
      
      File[] children = dir.listFiles();
      
      if (children == null) 
      {
         System.out.println("does not exist or is not a directory");
      } 
      else 
      {
    	  ArrayList<File> ReturnValue = new ArrayList<File>(); 
         for (int i = 0; i < children.length; i++) 
         {
            ReturnValue.add(children[i]);
         }
         return ReturnValue;
      }
      return null;
   }
   
   public static void main(String args[])
   {
	   try {
		Search.search("D:\\repo\\lake-projects\\Mapping");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}