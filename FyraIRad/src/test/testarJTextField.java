package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class testarJTextField {

	public static void main(String[] args) {
		
		JFrame frame =new JFrame("Button Example"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//submit button
		JButton b=new JButton("Submit");    
		b.setBounds(100,100,140, 40);    
				//enter name label
		JLabel label = new JLabel();		
		label.setText("Enter Name :");
		label.setBounds(10, 10, 100, 100);
				//empty label which will show event after button clicked
		final JLabel label1 = new JLabel();
		label1.setBounds(10, 110, 200, 100);
				//textfield to enter name
		final JTextField textfield= new JTextField();
		textfield.setBounds(110, 50, 130, 30);
				//add to frame
		frame.add(label1);
		frame.add(textfield);
		frame.add(label);
		frame.add(b);    
		frame.setSize(300,300);    
		frame.setLayout(null);    
		frame.setVisible(true);
		frame.setVisible(true);
		
		b.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = textfield.getText();
					label1.setText("Ditt namn är " + text);				
			}
	     });   
	}

}
