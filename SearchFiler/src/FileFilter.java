
public enum FileFilter {
	
    File_Sort(0),
    AlphabeticalOrder_Acending(1),
    AlphabeticalOrder_Decending(2),
    Contains(3),
    StartsWith(4),
    EndsWith(5)
    ;
	
	private String Name = "";
	
	FileFilter(int Type){
	}
	
	public void SetFilterString(String S) {
		this.Name = S;
	}
	
	public String GetFilterString() {
		return this.Name;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
}
