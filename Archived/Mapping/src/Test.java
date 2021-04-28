import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class Test extends JComponent implements Accessible
{

   public static void main(String[] args) {
	   
	   	JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("rtf", "png");
	    chooser.setFileFilter(filter);
	    chooser.showSaveDialog(chooser);
   }
}