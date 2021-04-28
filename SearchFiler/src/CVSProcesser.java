import java.io.File;
import java.util.ArrayList;

public class CVSProcesser {
	
	double Total;
	double Score;
	ArrayList<Score> TestAnswer = new ArrayList<Score>();
	
	CVSProcesser (File AFileIn) 
	{
		//gets the file needed and read the contents
		ReadFile Reader = new ReadFile(AFileIn.getAbsolutePath());
		ArrayList<String> StrTestAnswer; //declare arraylist
		
		try {
			StrTestAnswer = Reader.getList();
			
			for (int i = 0; i < StrTestAnswer.size(); i++)  //add all the answers and questions into arraylist in a score format
			{
				String[] Str = StrTestAnswer.get(i).split(",");
				TestAnswer.add(new Score(Str[0], Str[1]));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Score> GetAnswer()
	{
		return TestAnswer;
	}
	
	public String GetScores(CVSProcesser Other) 
	{
		
		ArrayList<Integer> MessedUpSpots = new ArrayList<Integer>();
		ArrayList<String> MessedUpAnswers = new ArrayList<String>();
		ArrayList<String> RightAnswers = new ArrayList<String>();
		
		if (Other.GetAnswer().size() != TestAnswer.size()) 
		{
			System.out.println("The following student's answer key size is different from size of answer key, the student's key is " + TestAnswer.size() + " long, while the answerkey is " + Other.GetAnswer().size());
			return null;
		}
		
		boolean IsMisaligned = false;
		
		for (int i = 0; i < TestAnswer.size(); i++)
		{
			
			if (TestAnswer.get(i).equals(Other.GetAnswer().get(i)))
			{
				Score ++;
			}
			else
			{
				if (!TestAnswer.get(i).getProblem().equals(Other.GetAnswer().get(i).getProblem())) 
				{
					System.out.println("The questions are misaligned for " + TestAnswer.get(i).getProblem() + " which is matched to " + Other.GetAnswer().get(i).getProblem());
					IsMisaligned = true;
				}
				MessedUpSpots.add(i + 1);
				MessedUpAnswers.add(TestAnswer.get(i).getAnswer());
				RightAnswers.add(Other.GetAnswer().get(i).getAnswer());
			}
			
			Total ++;
		}
		
		if (IsMisaligned) //return after so you can see all errors first
			return null;
		
		String GeneratedText = "";
		
		if (Score / Total * 100 != 100) 
		{
			GeneratedText = "\nThe student messed up in: " + MessedUpSpots.toString() + "\nThey got: " + MessedUpAnswers.toString() + "\ninstead of:" + RightAnswers.toString() ;
		}
		
		return Score / Total * 100 + "%, which is " + Score + "/" + Total + " questions right."+ GeneratedText;
	}
}
