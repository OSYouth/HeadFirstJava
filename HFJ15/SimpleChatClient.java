//some mistake occur in the scroll, can't move the text

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleChatClient{

	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args){
		new SimpleChatClient().go();
	}

	public void go(){
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		JPanel southPanel = new JPanel();
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(incoming);
		southPanel.add(outgoing);
		southPanel.add(sendButton);
		setUpNetWorking();

		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		// frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
		frame.setSize(800,500);
		frame.pack();
		frame.setVisible(true);
	}

	public void setUpNetWorking(){
		try{
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		}catch(IOException e){
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

	public class IncomingReader implements Runnable{
		public void run(){
			String message;
			try{
				while((message = reader.readLine())!=null){
					System.out.println("read " +message);
					incoming.append(message + "\n");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}