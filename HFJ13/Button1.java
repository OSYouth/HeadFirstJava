import javax.swing.*;
import java.awt.*;

public class Button1{

	public static void main(String[] args){
		Button1 gui = new Button1();
		gui.go();
		gui.go406();
		gui.go407();
	}

	public void go(){
		JFrame frame = new JFrame();
		JButton button = new JButton("Click like you mean it (more longer)");
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setSize(200,200);
		frame.setVisible(true);
	}

	public void go406(){
		JFrame frame = new JFrame();
		JButton button = new JButton("Click this");
		Font bigFont = new Font("serif", Font.BOLD, 28);
		button.setFont(bigFont);

		frame.getContentPane().add(BorderLayout.NORTH, button);
		frame.setSize(200,200);
		frame.setVisible(true);
	}

	public void go407(){
		JFrame frame = new JFrame();

		JButton east = new JButton("east");
		JButton west = new JButton("west");
		JButton south = new JButton("south");
		JButton north = new JButton("north");
		JButton center = new JButton("center");

		frame.getContentPane().add(BorderLayout.EAST, east);
		frame.getContentPane().add(BorderLayout.WEST, west);
		frame.getContentPane().add(BorderLayout.SOUTH, south);
		frame.getContentPane().add(BorderLayout.NORTH, north);
		frame.getContentPane().add(BorderLayout.CENTER, center);

		frame.setSize(500,500);
		frame.setVisible(true);
	}
}