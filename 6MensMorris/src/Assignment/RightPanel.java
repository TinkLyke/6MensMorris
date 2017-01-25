package Assignment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int Red = 0;
	private static JLabel RR;
	private GridBagConstraints c1 = new GridBagConstraints();
	private GridBagConstraints c2 = new GridBagConstraints();
	private GridBagConstraints c3 = new GridBagConstraints();
	/**
	 * RightPanel
	 */
	public RightPanel() {
		ImageIcon RedP = createImageIcon("/Red-circle-90.png");					// import the image for red piece
		Insets inset = new Insets(10, 10, 10, 10);								// set insets
		setLayout(new GridBagLayout());											// set layout
		setSize(200, 200);														// set size
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));				// set border
		setVisible(true);														// set it to visible
		JLabel R = new JLabel(RedP);											// JLabel contains red piece
		c1.gridx = 0;
		c1.gridy = 50;
		c1.insets = inset;
		RR = new JLabel("remains: " + Integer.toString(Red));					// JLabel contains text that tells users how many red pieces he has left	
		c2.gridx = 0;
		c2.gridy = 150;
		c2.insets = inset;
		
		JButton pickred = new JButton("pick red");								// JButton allows user to put red piece on the board in setup mode
		c3.gridx = 0;
		c3.gridy = 250;
		c3.insets = inset;
		pickred.setPreferredSize(new Dimension(100, 50));						// set size of the JButton
		
		
		add(R, c1);																// add components into the RightPanel
		add(RR, c2);
		add(pickred, c3);
		
		pickred.addActionListener(new ActionListener() {						// once the JButton is clicked, we go to setRed method in LayPanel

			@Override
			public void actionPerformed(ActionEvent e) {
				LayPanel.setRed();
			}
		});

	}
	/**
	 * incrementcounter
	 */
	public static void incrementcounter() {										// whenever a red piece is dropped onto the board, remaining + 1
		RR.setText("remaining " + ++Red);
	}
	
	/**
	 * decrementcounter
	 */
	public static void decrementcounter() {										// whenever a blue piece is removed from the board, remains - 1
		RR.setText("remaining: " + --Red);
	}
	
	
	/**
	 * resetcounter
	 */
	public static void resetcounter() {											// reset the counter
		Red = 0;
		RR.setText("remaining " + Red);
	}
	/**
	 * warning
	 */
	public static void warning() {												// if it is red's move next turn, raise a warning
		RR.setText("Your turn now!");
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
