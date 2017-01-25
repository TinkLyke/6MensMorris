package Assignment;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.*;

public class SixMensMorris extends JFrame {

	private static final long serialVersionUID = 1L;
	private final MenuPanel MPanel = new MenuPanel();					// create all panels 
	private final LeftPanel LPanel = new LeftPanel();
	private final RightPanel RPanel = new RightPanel();
	private final LayPanel LayPanel = new LayPanel();
	private final ErrorPanel EPanel = new ErrorPanel();

	public SixMensMorris() {

		setTitle("SixMensMorris");										// set up JFrame 
		setSize(600, 600);
		add(LayPanel, BorderLayout.CENTER);								// add all JPanels on the JFrame
		add(MPanel, BorderLayout.NORTH);
		add(LPanel, BorderLayout.WEST);
		add(RPanel, BorderLayout.EAST);
		add(EPanel, BorderLayout.SOUTH);
		pack();															// organize all components
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String args[]) { 							// In main, we call the method in order to create the game
		SixMensMorris game = new SixMensMorris();
		
	}

}
