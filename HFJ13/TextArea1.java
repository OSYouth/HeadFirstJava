import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextArea1{

	JTextArea text;

	public static void main(String[] args){
		TextArea1 gui = new TextArea1();
		gui.go();
	}

	public void go(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Just Click It");

		button.addActionListener(new ButtonListener());
		text = new JTextArea(10,20);
		text.setLineWrap(true);

		JScrollPane scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		panel.add(scroller);

		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.getContentPane().add(BorderLayout.SOUTH,button);

		frame.setSize(500,500);
		frame.setVisible(true);

	}

	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			text.append("button clicked \n");
		}
	}
}