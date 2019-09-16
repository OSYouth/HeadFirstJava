import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleAnimation{
	JFrame frame;
	int x = 70;
	int y = 70;
	int i = 0;

	public static void main(String[] args){
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go(){
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton button = new JButton("uhum");
		button.addActionListener(new ButtonListener());

		JButton restart = new JButton("restart");
		restart.addActionListener(new RestartListener());

		MyDrawPanel drawPanel = new MyDrawPanel();

		frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.NORTH, restart);
		frame.setSize(800,800);
		frame.setVisible(true);

		for(i = 0; i < 130; i++){
			x= ( x + 1 ) % 800;
			y= ( y + 1 ) % 800;
			drawPanel.repaint();

			try{
				Thread.sleep(50);
			}catch(Exception ex){}
		}
	}

	class MyDrawPanel extends JPanel{
		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0,0,this.getWidth(), this.getHeight());

			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			Color c = new Color(red, green, blue);
			g.setColor(c);
			g.fillOval(x,y,40,40);
		}
	}

	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			x = (int) (Math.random() * 800);
			y = (int) (Math.random() * 800);
			i = 0;
			// frame.repaint();
		}
	}

	class RestartListener implements ActionListener{	//can't work
		public void actionPerformed(ActionEvent event){
			i = 0;
			SimpleAnimation g = new SimpleAnimation();
			// gui.go();
		}
	}
}