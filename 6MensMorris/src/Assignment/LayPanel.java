package Assignment;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LayPanel extends JLayeredPane implements MouseListener {

	private static int start = 0;												// initialize all variables
	private static int setboard = 0;
	private static int setBlue = 0;
	private static int setRed = 0;

	private JPanel GPanel = new JPanel();
	private Point position[] = new Point[16];
	private static final long serialVersionUID = 1L;
	private ImageIcon pic;
	private ImageIcon BlueP;
	private ImageIcon RedP;
	public static String color = randColor();
	private static int countred = 6;
	private static int countblue = 6;
	private static JLabel legal = new JLabel("ilegal move! restart please");
	private static JLabel[] LabArray = new JLabel[12];
	private static int index = 0;

	public LayPanel() {
		setPreferredSize(new Dimension(400, 400));								// set size
		setPoint();																// set point
		pic = createImageIcon("/bg-400.png");										// import image of the board
		BlueP = createImageIcon("/Blue-circle-90.png");							// import image of the blue piece
		RedP = createImageIcon("/Red-circle-90.png");								// import image of the red piece
		GPanel.add(new JLabel(pic));											// add the board into the GPanel
		GPanel.setBounds(0, 0, pic.getIconWidth(), pic.getIconHeight());
		GPanel.setVisible(true);
		GPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		GPanel.addMouseListener(this);
		add(GPanel, new Integer(50));											// add the GPanel into the LayPanel

	}

	@Override
	public void mouseClicked(MouseEvent e) {									// once mouse is clicked
		if (start == 1 && setboard == 0) {										// we check if start = 1 and setboard = 0, we knows game is started 
			if (legalSpot(e.getX(), e.getY())) {								// we check if mouse position is in the board area
				int X = getX(e.getX());											// get mouse position
				int Y = getY(e.getY());
				if (color.equals("blue")) {										// check which color of piece we should put, if blue
					addDisk("blue", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);			// add piece
					color = "red";																		// change the color
				} else if (color.equals("red")) {														// if red
					addDisk("red", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);			// add piece
					color = "blue";																		// change the color
				}
			}
		} else if (start == 0 && setboard == 1) {								// if setboard = 1 and start = 0, we are in setup mode
			if (legalSpot(e.getX(), e.getY())) {								// we will do the same except we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				if (color.equals("blue")) {
					addDisk("blue", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);
				} else if (color.equals("red")) {
					addDisk("red", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	private void setPoint() {											// the method contains all positions of board
		position[0] = new Point(40, 50);
		position[1] = new Point(200, 50);
		position[2] = new Point(360, 50);
		position[3] = new Point(40, 210);
		position[4] = new Point(120, 210);
		position[5] = new Point(120, 130);
		position[6] = new Point(200, 130);
		position[7] = new Point(280, 130);
		position[8] = new Point(280, 210);
		position[9] = new Point(360, 210);
		position[10] = new Point(120, 290);
		position[11] = new Point(200, 290);
		position[12] = new Point(280, 290);
		position[13] = new Point(40, 370);
		position[14] = new Point(200, 370);
		position[15] = new Point(360, 370);
	}

	private boolean legalSpot(int x, int y) {							// check if the position moused clicked within the range of one of position on the board
		for (Point p : position) {
			if (x + 20 >= p.x && x - 20 <= p.x) {
				if (y + 20 >= p.y && y - 20 <= p.y) {
					return true;										// if we find one, return true
				}
			}
		}
		return false;													// else return false
	}

	private int getX(int x) {											// get x position of the board
		for (Point p : position) {
			if (x + 20 >= p.x && x - 20 <= p.x) {
				return p.x;
			}
		}
		return 0;
	}

	private int getY(int y) {											// get y position of the board
		for (Point p : position) {
			if (y + 20 >= p.y && y - 20 <= p.y) {
				return p.y;
			}
		}
		return 0;
	}

	private void addDisk(String color, int x, int y) {					// add pieces on the board 
		if (color.equals("blue") && countblue > 0) {					// if we have blue piece to put, as long as countblue is not 0 means we still can put it on the board
			if (countblue > 1 && countred > 0) {						// if we only have a blue piece left to put
				RightPanel.warning();									// raised a warning on RightPanel that shows user there is no red piece left
			} else {
			}
			JLabel j = new JLabel(BlueP);								
			LabArray[index] = j;										// put the piece also into the JLabel array
			index++;													// the index of the array + 1
			add(j, new Integer(100));
			j.setBounds(x + 155, y + 165, BlueP.getIconWidth(), BlueP.getIconHeight()); // set bounds
			//System.out.println("countblue: " + countblue);
			countblue--;												// countblue - 1
			LeftPanel.decrementcounter();								// go to method decrementcounter in LeftPanel

		} else if (color.equals("red") && countred > 0) {				// if we have red, we will do the same thing 	
			if (countred > 1 && countblue > 0) {
				LeftPanel.warning();									// instead of RightPanel, raise an error in RgithPanel
			} else {
			}
			JLabel j = new JLabel(RedP);
			LabArray[index] = j;
			index++;
			add(j, new Integer(100));
			j.setBounds(x + 155, y + 165, RedP.getIconWidth(), RedP.getIconHeight());
			//System.out.println("countred: " + countred);
			countred--;													// this time countred - 1
			RightPanel.decrementcounter();								// go to method decrementcounter in RightPanel
		}
	}

	public static void deleteDisk() {									// delete all pieces from the board
		try {
			for (int i = 0; i < LabArray.length; i++) {					// loop through the JLabel array, delete every element
				if (LabArray[i] != null) {
					Container parent = LabArray[i].getParent();
					parent.remove(LabArray[i]);
					parent.validate();
					parent.repaint();
				}
			}
			LabArray = new JLabel[12];									// reset all variables here
			countblue = 6;
			countred = 6;
			index = 0;
			color = randColor();
			start = 0;
			setboard = 0;
			setBlue = 0;
			setRed = 0;
			color = randColor();
			LeftPanel.resetcounter();
			RightPanel.resetcounter();
			ErrorPanel.resetError();
		} catch (NullPointerException nullpointerexception) {
		}
	}

	public static void CheckLegal() {									// check if there are 2 pieces at same spot
		if (!LabArray[0].equals("")) {
			for (int i = 0; i < LabArray.length; i++) {
				for (int j = 0; j < LabArray.length; j++) {
					try {
						if (i != j && !LabArray[i].equals(null) && !LabArray[j].equals(null)) {
							if ((LabArray[i].getX() + 20 >= LabArray[j].getX()
									&& LabArray[i].getX() - 20 <= LabArray[j].getX())
									&& (LabArray[i].getY() + 20 >= LabArray[j].getY()
											&& LabArray[i].getY() - 20 <= LabArray[j].getY())) {

								ErrorPanel.raiseError();				// if we have, raise an error
							}
						}
					} catch (NullPointerException nullpointerexception) {
					}
				}
			}
		}
	}

	public static String randColor() {									// randColor will randomize the color of the piece at the start
		String color;
		int lo = 0;
		int hi = 100;
		int random = lo + (int) (Math.random() * hi);
		if (random <= 50) {
			color = "blue";
		} else {
			color = "red";

		}
		return color;
	}

	public static void startgame() {									// set start = 1, setboard = 0
		start = 1;														// shows we are starting the game, not in setup mode
		setboard = 0;
	}

	public static void setboard() {										// set setboard = 1, start = 1
		setboard = 1;													// shows we are setting up the board, not starting the game
		start = 0;
		//System.out.println("start is " + start);
	}

	public static void setBlue() {										// setBlue will be 1, so application knows we want a blue piece
		if (setboard == 1) {
			setBlue = 1;
			setRed = 0;
			color = "blue";
			start = 0;
		}
	}

	public static void setRed() {										// setRed will be 1, so application knows we want a red piece
		if (setboard == 1) {
			setRed = 1;
			setBlue = 0;
			color = "red";
			start = 0;

		}
	}
	/**
	 * find image
	 * @param path - location of the image
	 * @return ImageIcon
	 */
	private ImageIcon createImageIcon(String path) {					// get image 
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
