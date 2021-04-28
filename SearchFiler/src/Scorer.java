import java.io.File;
import java.util.ArrayList;

public class Scorer {

	
	Scorer(File F, CVSProcesser AnsKey)
	{
		CVSProcesser C = new CVSProcesser(F);
		
		System.out.println(F.getName() + " got " + C.GetScores(AnsKey));
	}

}
