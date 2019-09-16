import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleChatClientA{

	JTextField outgoing;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args){
		new SimpleChatClientA().go();
	}

	public void go(){
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		setUpNetWorking();
		frame.setSize(400,500);
		frame.pack();
		frame.setVisible(true);
	}

	public void setUpNetWorking(){
		try{
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public class SendButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			try{
				writer.println(outgoing.getText());
				writer.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
}