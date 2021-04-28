import java.io.File;

public class Main {
	
	public static void main(String[] args) {
		
		String Path = "D:\\downloads v2\\Scores"; //edit this
		
		CVSProcesser AnsKey = new CVSProcesser(new File(Path + "\\AnswerKey.csv"));
		
		for (File f : new File(Path).listFiles()) 
		{
			if (f.getName().contains("AnswerKey"))
			{
				continue;
			}
			
			new Scorer(f, AnsKey);
			
			System.out.println("");
		}
	}
}
