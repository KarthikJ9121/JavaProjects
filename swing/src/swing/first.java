package swing;
//import java.util.*;
import javax.swing.*;

public class first 
{
			public static void main(String args[])
			{
				JFrame jf = new JFrame();
				
				JLabel jl = new JLabel("Hello World");
				
				jf.add(jl);
				jf.setSize(400, 350);
				jf.setLayout(null);
				
				jf.setVisible(true);
			}
}
