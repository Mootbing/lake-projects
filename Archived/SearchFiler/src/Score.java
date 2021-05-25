import java.util.ArrayList;

public class Score {
	
	String ProblemNumber, Answer;
	
	Score(String ANumber, String AAnswer)
	{
		this.ProblemNumber = ANumber;
		this.Answer = AAnswer;
	}
	
	public String getAnswer()
	{
		return Answer;
	}
	
	public String getProblem() 
	{
		return ProblemNumber;
	}
	
	public boolean equals(Object Other) 
	{
		Score OtherScore = (Score)Other;
		
		return 
			(
				this.ProblemNumber.equals(OtherScore.getProblem())
				&&
				this.Answer.equals(OtherScore.getAnswer())
			);
	}
	
	public static void main(String args[])
	{
		Score S = new Score("Question 1", "A"); 
		Score Answer = new Score("Question 1", "A");
		
		System.out.println(S.equals(Answer));
	}
	
}
