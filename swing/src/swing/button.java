package swing;
//import java.util.*;
import javax.swing.*;

public class button 
{
			public static void main(String args[])
			{
				JFrame jf = new JFrame();
				
				JButton jb = new JButton("Click me");
				
				jb.setBounds(150, 200, 220, 50);
				
				jf.add(jb);
				
				jf.setSize(500, 600);
				
				jf.setLayout(null);
				
				jf.setVisible(true);
			}
}
