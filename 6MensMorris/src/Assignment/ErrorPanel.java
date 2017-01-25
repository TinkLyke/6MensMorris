package Assignment;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorPanel extends JPanel {
	private static JLabel errorMessage; 										// initialize errorMessage 
	private GridBagConstraints c1 = new GridBagConstraints();
	public ErrorPanel(){
		Insets inset = new Insets(10, 10, 10, 10);								// set insets 
		setLayout(new GridBagLayout());											// set layout
		setSize(200, 200);														// set size of the panel
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));				// set border 
		setVisible(true);														// set it to visible
		errorMessage = new JLabel("all good!");									// default text of errorMessage
		c1.gridx = 0;															// set position of errorMessage
		c1.gridy = 50;
		c1.insets = inset;
		add(errorMessage, c1);													// add errorMessage to the panel
	}
	
	public static void raiseError() {											// if we made an illegal move, raised an error  
		errorMessage.setText("Not a legal move, please restart!");
	}
	
	public static void resetError() {											// reset the text to default
		errorMessage.setText("all good!");
	}

	public static void Redmove(){												// notice red player to move
		errorMessage.setText("Red can move now!");

	}
	public static void Bluemove(){												// notice blue player to move
		errorMessage.setText("Blue can move now!");
	}
	public static void delRed(){												// notice blue player to delete a red disk
		errorMessage.setText("Remove a red disk");
	}
	public static void delBlue(){												// notice red player to delete a blue disk
		errorMessage.setText("Remove a blue disk");
	}	
	public static void Bluewin(){												// notice blue player win
		errorMessage.setText("Blue player wins!");
	}
	public static void Redwin(){												// notice red player win
		errorMessage.setText("Red player wins!");
	}
	public static void InProgress(){											// show game is in progress

		errorMessage.setText("Game in progress");

	}
}
