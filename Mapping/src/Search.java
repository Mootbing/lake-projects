import java.io.File;

public class Search 
{
   public static void search(String directoryName) throws Exception 
   {
      File dir = new File(directoryName);
      
      String[] children = dir.list();
      
      if (children == null) 
      {
         System.out.println("does not exist or is not a directory");
      } 
      else 
      {
         for (int i = 0; i < children.length; i++) 
         {
            String filename = children[i];
            System.out.println(filename);
         }
      }
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