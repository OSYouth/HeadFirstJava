import javax.swing.*;

public class SimpleGui1{

	public static void main(String [] args){

		JFrame frame = new JFrame();
		JButton button = new JButton("clike me");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(button);

		frame.setSize(500, 300);

		frame.setVisible(true);

	}
}
