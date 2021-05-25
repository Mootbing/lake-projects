import java.io.File;
import java.util.ArrayList;

public class Scorer {

	
	Scorer(File F, CVSProcesser AnsKey)
	{
		CVSProcesser C = new CVSProcesser(F);
		
		String scores =  C.GetScores(AnsKey);
		if(scores == null)
		{
			System.out.println (F.getName());
		}
		else 
		{
			String report = F.getName() + " got " + C.GetScores(AnsKey);
			String name = F.getName().replaceAll(".csv", "");
			FileWrite writer = new FileWrite("/Users/lake/Desktop/APCompSciReports/" + name);
			writer.writeToFile(report);
	
		}
	}

}
