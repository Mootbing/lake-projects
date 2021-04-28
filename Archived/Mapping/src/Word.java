
public class Word {
	
	String Text;
	String Def;
	
	Word(String AText, String ADef){
		SetText(AText);
		SetDef(ADef);
	}
	
	public String GetText(){
		return Text;
	}
	public String GetDef(){
		return Def;
	}
	public void SetText(String AText){
		Text = AText;
	}
	public void SetDef(String ADef){
		Def = ADef;
	}
	
	public String toString() {
		return Text + " : " + Def;
	}
}
