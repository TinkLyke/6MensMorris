package Assignment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int Blue = 0;
	private static JLabel BR;
	private GridBagConstraints c1 = new GridBagConstraints();
	private GridBagConstraints c2 = new GridBagConstraints();
	private GridBagConstraints c3 = new GridBagConstraints();
	/**
	 * LeftPanel
	 */
	public LeftPanel() {
		ImageIcon BlueP = createImageIcon("/Blue-circle-90.png");				// import the image for blue piece

		Insets inset = new Insets(5, 5, 5, 5);								// set insets
		setLayout(new GridBagLayout());											// set layout
		setSize(200, 200);														// set size
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));				// set border
		setVisible(true);														// set it to visible
		JLabel B = new JLabel(BlueP);											// Jlabel contains blue piece
		c1.gridx = 0;
		c1.gridy = 50;
		c1.insets = inset;
		BR = new JLabel("remains: " + Integer.toString(Blue));					// Jlabel contains text that tells user how many blue pieces he has left
		c2.gridx = 0;
		c2.gridy = 150;
		c2.insets = inset;
		
		JButton pickblue = new JButton("pick blue");							// JButton allows user to put blue piece on the board in setup mode
		c3.gridx = 0;
		c3.gridy = 250;
		c3.insets = inset;
		pickblue.setPreferredSize(new Dimension(100, 50));						// set size of the JButton
		
		
		add(B, c1);																// add components into the LeftPanel
		add(BR, c2);
		add(pickblue,c3);
		
		
		
		pickblue.addActionListener(new ActionListener() {						// once the JButton is clicked, we go to setBlue method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.setBlue();
			}
		});
		

	}
	/**
	 * incrementcounter
	 */
	public static void incrementcounter() {										// whenever a blue piece is dropped onto the board, remaining + 1
		BR.setText("remaining: " + ++Blue);
	}
	/**
	 * decrementcounter
	 */
	public static void decrementcounter() {										// whenever a blue piece is removed from the board, remaining - 1
		BR.setText("remaining: " + --Blue);
	}
	
	
	/**
	 * resetcounter
	 */
	public static void resetcounter() {											// reset the counter
		Blue = 0;
		BR.setText("remaining " + Blue);
	}
	/**
	 * warning
	 */
	public static void warning() {												// if it is blue's move next turn, raise a warning
		BR.setText("Your turn now!");
	}
	/**
	 * find image
	 * @param path - location of the image
	 * @return ImageIcon
	 */
	private ImageIcon createImageIcon(String path) {							// get image
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
