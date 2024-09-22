package main.view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.pojo.Match;


public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public JFrame frame = null;
	
	private ArrayList<JPanel> pannels = null;
	private ArrayList<Match> matches = null;
	public void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		pannels = new ArrayList<JPanel>();
		matches = new ArrayList<Match>();
		
		MainPanel mainPanel = new MainPanel(pannels, matches);
		JPanel panel1 = mainPanel.getPanel();
		panel1.setVisible(true);
		pannels.add(panel1);
		frame.getContentPane().add(panel1);
		
		frame.setVisible(true);
	}
}
