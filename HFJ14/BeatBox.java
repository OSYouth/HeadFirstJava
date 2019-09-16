import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox{
	 JPanel mainPanel;
	 ArrayList<JCheckBox> checkboxList;		//store checkbox into ArrayList
	 Sequencer sequencer;
	 Sequence seq;
	 Track track;
	 JFrame theFrame;
	 private JFrame picture;
	 MusicDrawPanel drawMusic;
	 //the name of instruments by String array
	 String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat",
								"Acounstic Snare", "Crash Cymbal", " Hand Clap",
								"High Tom", "Hi Bongo", "Maracas", "Whistle", 
								"Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom",
								"High Agogo", "Open Hi Conga"};
	//key of instruments, e.g. 35 is bass, 42 is closed hi-hat
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

	public static void main(String[] args){
		BeatBox beatBox = new BeatBox();
		beatBox.buildGui();
	}

	public void buildGui(){
		int xcoordinate = 100;
		int ycoordinate = 100;
		int width = 500;
		int height = 500;

		theFrame = new JFrame("Cybere BeatBox");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		picture = new JFrame("BeatBox Picture");
		picture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawMusic = new MusicDrawPanel();
		// code with some bugs before:they will display with the mainpanel
		// drawMusic.setPreferredSize(new Dimension(300, 300));
		// background.add(BorderLayout.SOUTH, drawMusic);

		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		JButton serializelt = new JButton("Serializelt");
		serializelt.addActionListener(new MySendListener());
		buttonBox.add(serializelt);

		JButton restore = new JButton("Restore");
		restore.addActionListener(new MyReadInListener());
		buttonBox.add(restore);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i < 16; i++){
			nameBox.add(new Label(instrumentNames[i]));
		}

		background.add(BorderLayout.WEST, nameBox);
		background.add(BorderLayout.EAST, buttonBox);
				
		theFrame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);

		for(int i = 0; i < 256; i++){
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}

		
		setUpMidi();

		theFrame.setBounds(xcoordinate, ycoordinate, width, height);
		theFrame.pack();
		theFrame.setVisible(true);

		picture.getContentPane().add(drawMusic);
		picture.setBounds(xcoordinate + width, ycoordinate + height,width,height/2);
		picture.setVisible(true);

	}

	public void setUpMidi(){
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(drawMusic, new int[] {127});
			seq = new Sequence(Sequence.PPQ, 4);
			track = seq.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void buildTrackAndStart(){
		int[] trackList = null;

		seq.deleteTrack(track);
		track = seq.createTrack();

		for(int i = 0; i < 16; i++){
			trackList = new int[16];
			int key = instruments[i];
			for(int j = 0; j < 16; j++){
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
				if( jc.isSelected()){
					trackList[j] = key;
				}else{
					trackList[j] = 0;
				}
			}

			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
		}

		track.add(makeEvent(192,9,1,0,15));
		try{
			sequencer.setSequence(seq);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			buildTrackAndStart();
		}
	}

	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			sequencer.stop();
		}
	}

	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		}
	}

	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float)(tempoFactor * 0.97));
		}
	}

	public class MySendListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean[] checkboxState = new boolean[256];
			for(int i = 0; i < 256; i++){
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if(check.isSelected()){
					checkboxState[i] = true;
				}
			}
			try{
				FileOutputStream fileStream = new FileOutputStream(new File("CheckBox.ser"));
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(checkboxState);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	public class MyReadInListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			boolean[] checkboxState = new boolean[256];
			try{
				FileInputStream fileIn = new FileInputStream(new File("CheckBox.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				checkboxState = (boolean[]) is.readObject();
			}catch(Exception ex){
				ex.printStackTrace();
			}

			for(int i = 0; i < 256; i++){
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if(checkboxState[i]){
					check.setSelected(true);
				}else{
					check.setSelected(false);
				}
			}

			sequencer.stop();
			buildTrackAndStart();
		}
	}
	//adding by myself to draw some information
	class MusicDrawPanel extends JPanel implements ControllerEventListener{
		boolean msg = false ;

		public void controlChange(ShortMessage event){
			msg = true;
			repaint();
		}

		public void paintComponent(Graphics g){
			if(msg){

				Graphics2D g2 = (Graphics2D) g;

				int red = (int) (Math.random() * 255);
				int green = (int) (Math.random() * 255);
				int blue = (int) (Math.random() * 255);
				Color c = new Color(red, green, blue);
				g.setColor(c);

				int ht = (int)((Math.random() * 120) + 10);
				int width = (int)((Math.random() * 120) + 10);

				int x = (int)((Math.random() * 80) + 10);
				int y = (int)((Math.random() * 80) + 10);

				g.fillRect(x,y,ht,width);
				msg = false;
			}
		}
	}

	public void makeTracks(int[] list){
		for(int i = 0; i < 16; i++){
			int key = list[i];
			if(key!=0){
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(176,1,127,0,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		}catch(Exception e){
			e.printStackTrace();
		}
		return event;
	}
}