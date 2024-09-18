package swing;
import javax.swing.*;

class Button2
{
	Button2()
	{
		JFrame f  = new JFrame();
		
		JButton b1 = new JButton("click");
		b1.setBounds(100,150, 100, 150);
		f.add(b1);
		
		JButton b2 = new JButton("submit");
		b2.setBounds(110,170, 150, 250);
		f.add(b2);
		
		JButton b3 = new JButton("cancel");
		b3.setBounds(130,200, 200, 350);
		f.add(b3);
		
		
		f.setSize(800, 900);
		f.setLayout(null);
		f.setVisible(true);
	}
}

public class multiButtons {

	public static void main(String[] args) 
	{
			new Button2();
	}

}
