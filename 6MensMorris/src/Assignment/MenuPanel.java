package Assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints c0 = new GridBagConstraints();
	private GridBagConstraints c1 = new GridBagConstraints();
	private GridBagConstraints c2 = new GridBagConstraints();
	private GridBagConstraints c3 = new GridBagConstraints();
	private GridBagConstraints c4 = new GridBagConstraints();
	private GridBagConstraints c5 = new GridBagConstraints();
	private GridBagConstraints c6 = new GridBagConstraints();
	
	

	public MenuPanel() {
		Insets inset = new Insets(1, 1, 1, 1);							// set insets
		setLayout(new GridBagLayout());										// set layout
		setSize(200, 400);													// set size
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));			// set border
		setVisible(true);													// set it to visible
		
		
		JButton b0 = new JButton("start");									// create JButton for starting the game
		c0.gridx = 0;
		c0.gridy = 50;
		c0.insets = inset;
		b0.setPreferredSize(new Dimension(65, 50));
		JButton b1 = new JButton("restart");								// create JButton for restarting the game
		c1.gridx = 1;
		c1.gridy = 50;
		c1.insets = inset;
		b1.setPreferredSize(new Dimension(80, 50));						
		JButton b2 = new JButton("analyze");								// create JButton for analyzing the moves
		c2.gridx = 2;
		c2.gridy = 50;
		c2.insets = inset;
		b2.setPreferredSize(new Dimension(90, 50));			
		JButton b3 = new JButton("set board");								// create JButton for setting up the board			
		c3.gridx = 3;
		c3.gridy = 50;
		c3.insets = inset;
		b3.setPreferredSize(new Dimension(90, 50));
		JButton b4 = new JButton("save");								// create JButton for saving			
		c4.gridx = 4;
		c4.gridy = 50;
		c4.insets = inset;
		b4.setPreferredSize(new Dimension(65, 50));
		
		
		JButton b6 = new JButton("load");								// create JButton for saving			
		c6.gridx = 5;
		c6.gridy = 50;
		c6.insets = inset;
		b6.setPreferredSize(new Dimension(65, 50));
		
		JButton b5 = new JButton("AI Enable");								// create JButton for saving			
		c5.gridx = 6;
		c5.gridy = 50;
		c5.insets = inset;
		b5.setPreferredSize(new Dimension(90, 50));
		
		

		
		add(b0, c0);														// add all buttons to the MenuPanel
		add(b1, c1);
		add(b2, c2);
		add(b3, c3);
		add(b4, c4);
		add(b6, c6);
		add(b5, c5);

		
		b0.addActionListener(new ActionListener() {							// once the start button is clicked, go to startgame method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.startgame();
			}
		});
		
		b1.addActionListener(new ActionListener() {							// once the restart button is clicked, go to deleteDisk method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.Restart();

			}
		});

		b2.addActionListener(new ActionListener() {							// once the analyze button is clicked, go to CheckLegal method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.CheckLegal();
			}
		});
		
		b3.addActionListener(new ActionListener() {							// once the setboard button is clicked, go to setboard method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.setboard();
			}
		});
		
		b4.addActionListener(new ActionListener() {							// once the setboard button is clicked, go to save method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LayPanel.Save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		b5.addActionListener(new ActionListener() {							// once the AI mode button is clicked, go to AImode method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.AImode();
			}
		});
		
		b6.addActionListener(new ActionListener() {							// once the load button is clicked, go to Load method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.wantLoad();
			}
		});
		
	}
}
