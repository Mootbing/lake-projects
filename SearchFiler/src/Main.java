import java.io.File;

public class Main 
{
	
	public static void main(String[] args) {
		
		String Path = "/Users/lake/Desktop/APMCQ"; //edit this
		
		CVSProcesser AnsKey = new CVSProcesser(new File("/Users/lake/Desktop/APCompSciMCQAnswerSheet.csv"));
		
		for (File f : new File(Path).listFiles()) 
		{
			if (f.getName().contains("AnswerKey") || !f.getName().contains(".csv"))
			{
				continue;
			}
			
			new Scorer(f, AnsKey);
			
			System.out.println("");
		}
	}
}
