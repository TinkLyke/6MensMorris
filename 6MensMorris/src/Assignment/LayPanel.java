package Assignment;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LayPanel extends JLayeredPane implements MouseListener {

	private static int start = 0; // initialize all variables
	private static int setboard = 0;
	private static int setBlue = 0;
	private static int setRed = 0;
	private static int move = 0;
	private static String delcolor = "";

	private static int pick = 0;
	private static int put = 0;
	private static int delete = 0;
	private static JLabel temp;
	private static int track;
	private static int addcolor = 0;
	private static int redMills = 0;
	private static int blueMills = 0;

	private static BufferedWriter w;
	private static BufferedReader r;

	private static JPanel GPanel = new JPanel();
	private static Point position[] = new Point[16];

	private static final long serialVersionUID = 1L;
	private ImageIcon pic;
	private static ImageIcon BlueP;
	private static ImageIcon RedP;
	public static String color = randColor();
	////////////////////////////////
	private static int AImode = 0;
	private static int AImove;
	private static String AIcolor = "";
	private static String Usercolor = "";
	private static Point[] search;
	private static int wantLoad = 0;
	/////////////////////////////////

	public static String MoveColor = "";
	private static int countred = 6;
	private static int countblue = 6;
	private static JLabel legal = new JLabel("ilegal move! restart please");
	private static JLabel[] LabArray = new JLabel[12];
	private static String[] colors = new String[12];

	private static int index = 0;

	public LayPanel() {
		setPreferredSize(new Dimension(400, 400)); // set size
		setPoint(); // set point
		pic = createImageIcon("/bg-400.png"); // import image of the board
		BlueP = createImageIcon("/Blue-circle-90.png"); // import image of the
														// blue piece
		RedP = createImageIcon("/Red-circle-90.png"); // import image of the red
														// piece
		GPanel.add(new JLabel(pic)); // add the board into the GPanel
		GPanel.setBounds(0, 0, pic.getIconWidth(), pic.getIconHeight());
		GPanel.setVisible(true);
		GPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		GPanel.addMouseListener(this);
		add(GPanel, new Integer(50)); // add the GPanel into the LayPanel
		
//		try {
//			load();
//		} catch (IOException e) {
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { // once mouse is clicked
		if (start == 1 && setboard == 0 && move == 0 && pick == 0 && put == 0 && AImode == 0) { // we
																								// check
																								// if
																								// start
																								// =
																								// 1
																								// and
																								// setboard
																								// =
																								// 0,
																								// we
																								// knows
																								// game
																								// is
																								// started
			// int addcolor = 0;
			if (legalSpot(e.getX(), e.getY())) { // we check if mouse position
													// is in the board area
				int X = getX(e.getX()); // get mouse position
				int Y = getY(e.getY());
				if (color.equals("blue")) { // check which color of piece we
											// should put, if blue
					addDisk("blue", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2); // add
																								// piece
					colors[addcolor] = "blue";
					addcolor++;
					color = "red"; // change the color
				} else if (color.equals("red")) { // if red
					addDisk("red", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2); // add
																								// piece
					colors[addcolor] = "red";
					addcolor++;
					color = "blue"; // change the color
				}
			}

			moveallowed();

		} else if (start == 0 && setboard == 1 && move == 0 && pick == 0 && put == 0 && delete == 0 && AImode == 0) { // if
																														// setboard
																														// =
																														// 1
																														// and
																														// start
																														// =
																														// 0,
																														// we
																														// are
																														// in
																														// setup
																														// mode
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				if (color.equals("blue")) {
					addDisk("blue", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);
					colors[addcolor] = "blue";
					addcolor++;
				} else if (color.equals("red")) {
					addDisk("red", X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2);
					colors[addcolor] = "red";
					addcolor++;
				}
			}
		} else if (start == 1 && setboard == 0 && move == 1 && pick == 1 && put == 0 && delete == 0 && AImode == 0) { // pick
																														// a
																														// dish
																														// to
																														// move
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				pickDisk(X, Y);

			}

		} else if (start == 1 && setboard == 0 && move == 1 && pick == 0 && put == 1 && delete == 0 && AImode == 0) { // pick
																														// a
																														// position
																														// where
																														// the
																														// disk
																														// moves
																														// to
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				moveDisk(X, Y);

			}
		} else if (start == 1 && setboard == 0 && move == 1 && pick == 0 && put == 0 && delete == 1 && AImode == 0) { // have
																														// mills,
																														// take
																														// oppponent's
																														// disk
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				deleteDisk(X, Y);
			}
		}

		else if (start == 1 && setboard == 0 && move == 0 && pick == 0 && put == 0 && AImode == 1) { // we
																										// check
																										// if
																										// start
																										// =
																										// 1
																										// and
																										// AImode
																										// =
																										// 0,
																										// we
																										// knows
																										// game
																										// is
																										// started
																										// with
																										// AI

			if (legalSpot(e.getX(), e.getY())) { // we check if mouse position
													// is in the board area
				int X = getX(e.getX()); // get mouse position
				int Y = getY(e.getY());
				if (color.equals(Usercolor)) { // check which color of piece we
												// should put, if blue
					addDisk(Usercolor, X - pic.getIconWidth() / 2, Y - pic.getIconHeight() / 2); // add
																									// piece
					colors[addcolor] = Usercolor;
					addcolor++;
					color = AIcolor; // change the color
				}
			}
			if (color.equals(AIcolor)) { // if red
				AIaddDisk(); // add piece
				colors[addcolor] = AIcolor;
				addcolor++;
				color = Usercolor; // change the color
			}

			moveallowed();
		}

		else if (start == 1 && setboard == 0 && move == 1 && pick == 1 && put == 0 && delete == 0 && AImode == 1) { // pick
																													// a
																													// dish
																													// to
																													// move
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				pickDisk(X, Y);

			}

		} else if (start == 1 && setboard == 0 && move == 1 && pick == 0 && put == 1 && delete == 0 && AImode == 1) { // pick
																														// a
																														// position
																														// where
																														// the
																														// disk
																														// moves
																														// to
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				moveDisk(X, Y);
			}
		} else if (start == 1 && setboard == 0 && move == 1 && pick == 0 && put == 0 && delete == 1 && AImode == 1) { // have
																														// mills,
																														// take
																														// oppponent's
																														// disk
			if (legalSpot(e.getX(), e.getY())) { // we will do the same except
													// we don't change the color
				int X = getX(e.getX());
				int Y = getY(e.getY());
				deleteDisk(X, Y);
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

	private void setPoint() { // the method contains all positions of board
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

	private boolean legalSpot(int x, int y) { // check if the position moused
												// clicked within the range of
												// one of position on the board
		for (Point p : position) {
			if (x + 30 >= p.x && x - 30 <= p.x) {
				if (y + 30 >= p.y && y - 30 <= p.y) {
					return true; // if we find one, return true
				}
			}
		}
		return false; // else return false
	}

	private int getX(int x) { // get x position of the board
		for (Point p : position) {
			if (x + 30 >= p.x && x - 30 <= p.x) {
				return p.x;
			}
		}
		return 0;
	}

	private int getY(int y) { // get y position of the board
		for (Point p : position) {
			if (y + 30 >= p.y && y - 30 <= p.y) {
				return p.y;
			}
		}
		return 0;
	}

	private void addDisk(String color, int x, int y) { // add pieces on the
														// board
		System.out.println("x is " + x + " and y is " + y);
		if (color.equals("blue") && countblue > 0) { // if we have blue piece to
														// put, as long as
														// countblue is not 0
														// means we still can
														// put it on the board
			JLabel j = new JLabel(BlueP);
			LabArray[index] = j; // put the piece also into the JLabel array
			index++; // the index of the array + 1
			add(j, new Integer(100));
			j.setBounds(x + 155, y + 165, BlueP.getIconWidth(), BlueP.getIconHeight()); // set
																						// bounds
			countblue--; // countblue - 1
			LeftPanel.incrementcounter(); // go to method decrementcounter in
											// LeftPanel

		} else if (color.equals("red") && countred > 0) { // if we have red, we
															// will do the same
															// thing
			JLabel j = new JLabel(RedP);
			LabArray[index] = j;
			index++;
			add(j, new Integer(100));
			j.setBounds(x + 155, y + 165, RedP.getIconWidth(), RedP.getIconHeight());
			countred--; // this time countred - 1
			RightPanel.incrementcounter(); // go to method decrementcounter in
											// RightPanel
		}
	}

	public static void pickDisk(int x, int y) {				// method that allows player to pick a disk of his color
		x = x - 45;
		y = y - 35;
		for (int i = 0; i < LabArray.length; i++) {			// loop through the JLabel array
			if (LabArray[i] != null) {						// check only when Jlabel is not null
				if (LabArray[i].getX() <= x + 10 && LabArray[i].getX() >= x - 10 && LabArray[i].getY() <= y + 10
						&& LabArray[i].getY() >= y - 10) {	// if we find one Label on that point
					if (colors[i].equals(MoveColor)) {		// if it matches with the color of piece we want to move 
						track = i;							// go to put mode
						pick = 0;
						put = 1;
					}
				}
			}
		}

	}

	/**
	 * Enable to move disks around, also check if there is a Mills on the board
	 * 
	 * @param x
	 *            - horizontal position of mouse click
	 * @param y
	 *            - vertical position of mouse click
	 */
	public void moveDisk(int x, int y) {					// the method to put the disk we picked to the new spot
		x = x - 45;
		y = y - 35;
		if (checkadj(x, y)) {								// check if the new spot is in the adjacent list of the old spot
			LabArray[track].setLocation(x, y);				// reposition the disk
			if (MoveColor.equals("red")) {					// we raise an message to remind another player to go next
				MoveColor = "blue";
				ErrorPanel.Bluemove();
			} else if (MoveColor.equals("blue")) {
				MoveColor = "red";
				ErrorPanel.Redmove();
			} else
				System.out.println("there is a disk already on the sopt");
		}
		// System.out.println("check adj is "+checkadj(x,y));
		if (AImode == 0 && checkMills()) {
			if (delcolor.equals("red") && blueMills == 1) { // if blue has
															// Mills, we delete
															// red disk, and
															// back to move
															// state
				ErrorPanel.delRed();
				delete = 1;
				pick = 0;
				put = 0;
			} else if (delcolor.equals("blue") && redMills == 1) { // if red has
																	// Mills, we
																	// delete
																	// blue disk
				ErrorPanel.delBlue();
				delete = 1;
				pick = 0;
				put = 0;
			} else {												// otherwise we go back to pick stage
				delete = 0;
				pick = 1;
				put = 0;
			}
		} else if (AImode == 1 && checkMills()) {					// if we are in the AImode and we check Mills now
			if (delcolor.equals(Usercolor)) {						// if we want AI to delete user's disk
				if (Usercolor.equals("blue") && redMills == 1) {	// check the color
					AIremove();										// call AIremove() method
				} else if (Usercolor.equals("red") && blueMills == 1) {
					AIremove();
				} else {											// otherwise just let AI move;
					AImoveDisk();							
				}

			} else if (delcolor.equals(AIcolor)) {					// if we want user to delete AI's disk
				if (AIcolor.equals("blue") && redMills == 1) {		// check the color, and raise a message to remind player
					ErrorPanel.delBlue();							// we assume one color only can have Mill once at the time.
					delete = 1;										// then we go to delete method
					pick = 0;
					put = 0;
				} else if (AIcolor.equals("red") && blueMills == 1) { 	// check color and Mills counter
					ErrorPanel.delRed();								// raise a message
					delete = 1;											// then we go to delete method
					pick = 0;
					put = 0;
				} else {												// otherwise we let AI move
					if (AImode == 1)
						AImoveDisk();
				}
			}
		}

		else {															// if we have no Mills on board 
			if (AImode == 1)											// we let AI move, if we are in AI mode
				AImoveDisk();
			else {														// we just switch player, if we are in PVP mode
				blueMills = 0;
				redMills = 0;
				pick = 1;
				put = 0;
			}
		}
	}

	/**
	 * Enable to delete opponent's disk once Mills occur
	 * 
	 * @param x
	 *            - horizontal position of mouse click
	 * @param y
	 *            - vertical position of mouse click
	 */
	public void deleteDisk(int x, int y) {								// method to delete a disk
		x = x - 45;
		y = y - 35;
		for (int i = 0; i < LabArray.length; i++) {						// Loop through all jLabel
			if (LabArray[i] != null) {									
				if (LabArray[i].getX() <= x + 20 && LabArray[i].getX() >= x - 20 && LabArray[i].getY() <= y + 20
						&& LabArray[i].getY() >= y - 20) {				// check if the position we clicked has any disk on it
					if (colors[i].equals(delcolor)) {					// if it matches the color of the disk we want to delete
						if (delcolor.equals("blue"))
							LeftPanel.decrementcounter();				// counter of blue - 1
						else if (delcolor.equals("red"))
							RightPanel.decrementcounter();				// counter of red - 1
						Container parent = LabArray[i].getParent();		// delete method
						parent.remove(LabArray[i]);
						parent.validate();
						parent.repaint();
						colors[i] = null;
						LabArray[i] = null;
						if (MoveColor.equals("blue")) {					// check which color is gonna move next
							ErrorPanel.Bluemove(); 						// raise a warning to player
						} else {
							ErrorPanel.Redmove();
						}
						if (AImode == 1) {								// if we are in AI mode
							if (MoveColor.equals(AIcolor)) {			// if AI should move next
								AImoveDisk();							// call AImoveDisk();
							} else if (MoveColor.equals(Usercolor)) {	// if user should move next
								if (MoveColor.equals("blue")) {
									ErrorPanel.Bluemove(); // raise a warning to
															// player
								} else {
									ErrorPanel.Redmove();
								}
								pick = 1;								// move to moveDisk stage
								put = 0;
								delete = 0;
							}

						} else {										// otherwise we back to move stage
							pick = 1;
							put = 0;
							delete = 0;
						}
						Win(); 											// we keep tracking if a player/AI win

					}
				}
			}
		}

	}

	/**
	 * Check if the disk is able to move to the position
	 * 
	 * @param x
	 *            - horizontal position of the mouse click
	 * @param y
	 *            - vertical position of the mouse click
	 * @return true if we can move
	 */
	public static boolean checkadj(int x, int y) {// check if it is adj, if
													// there is a disk
		boolean isAdj = false;				// initialize the isAdj to false
		boolean hadDisk = false;			// initialize the hadDisk to false
		boolean canMove = false;			// initialize the canMove to false
		for (int i = 0; i < position.length; i++) {				// check all positions
			if ((position[i].x - 45) == LabArray[track].getX() && (position[i].y - 35 == LabArray[track].getY())) { // for each position we check its adjacent position
				if (i == 0) {
					if ((x == position[1].x - 45 && y == position[1].y - 35)
							|| (x == position[3].x - 45 && y == position[3].y - 35))
						isAdj = true;														// if the spot we want to move is in adjacent list, then we set isAdj to true
				} else if (i == 1) {
					if ((x == position[0].x - 45 && y == position[0].y - 35)
							|| (x == position[2].x - 45 && y == position[2].y - 35)
							|| (x == position[6].x - 45 && y == position[6].y - 35))
						isAdj = true;
				} else if (i == 2) {
					if ((x == position[1].x - 45 && y == position[1].y - 35)
							|| (x == position[9].x - 45 && y == position[9].y - 35))
						isAdj = true;
				} else if (i == 3) {
					if ((x == position[0].x - 45 && y == position[0].y - 35)
							|| (x == position[4].x - 45 && y == position[4].y - 35)
							|| (x == position[13].x - 45 && y == position[13].y - 35))
						isAdj = true;
				} else if (i == 4) {
					if ((x == position[3].x - 45 && y == position[3].y - 35)
							|| (x == position[5].x - 45 && y == position[5].y - 35)
							|| (x == position[10].x - 45 && y == position[10].y - 35))
						isAdj = true;
				} else if (i == 5) {
					if ((x == position[4].x - 45 && y == position[4].y - 35)
							|| (x == position[6].x - 45 && y == position[6].y - 35))
						isAdj = true;
				} else if (i == 6) {
					if ((x == position[5].x - 45 && y == position[5].y - 35)
							|| (x == position[1].x - 45 && y == position[1].y - 35)
							|| (x == position[7].x - 45 && y == position[7].y - 35))
						isAdj = true;
				} else if (i == 7) {
					if ((x == position[6].x - 45 && y == position[6].y - 35)
							|| (x == position[8].x - 45 && y == position[8].y - 35))
						isAdj = true;
				} else if (i == 8) {
					if ((x == position[7].x - 45 && y == position[7].y - 35)
							|| (x == position[9].x - 45 && y == position[9].y - 35)
							|| (x == position[12].x - 45 && y == position[12].y - 35))
						isAdj = true;
				} else if (i == 9) {
					if ((x == position[2].x - 45 && y == position[2].y - 35)
							|| (x == position[8].x - 45 && y == position[8].y - 35)
							|| (x == position[15].x - 45 && y == position[15].y - 35))
						isAdj = true;
				} else if (i == 10) {
					if ((x == position[4].x - 45 && y == position[4].y - 35)
							|| (x == position[11].x - 45 && y == position[11].y - 35))
						isAdj = true;
				} else if (i == 11) {
					if ((x == position[10].x - 45 && y == position[10].y - 35)
							|| (x == position[14].x - 45 && y == position[14].y - 35)
							|| (x == position[12].x - 45 && y == position[12].y - 35))
						isAdj = true;
				} else if (i == 12) {
					if ((x == position[11].x - 45 && y == position[11].y - 35)
							|| (x == position[8].x - 45 && y == position[8].y - 35))
						isAdj = true;
				} else if (i == 13) {
					if ((x == position[3].x - 45 && y == position[3].y - 35)
							|| (x == position[14].x - 45 && y == position[14].y - 35))
						isAdj = true;
				} else if (i == 14) {
					if ((x == position[13].x - 45 && y == position[13].y - 35)
							|| (x == position[11].x - 45 && y == position[11].y - 35)
							|| (x == position[15].x - 45 && y == position[15].y - 35))
						isAdj = true;
				} else if (i == 15) {
					if ((x == position[14].x - 45 && y == position[14].y - 35)
							|| (x == position[9].x - 45 && y == position[9].y - 35))
						isAdj = true;
				} else {
					isAdj = false;
				}

			}
		}
		for (int j = 0; j < LabArray.length; j++) {						// then we check if there is a disk on that position
			if (LabArray[j] != null) {
				if (LabArray[j].getX() == x && LabArray[j].getY() == y) {
					hadDisk = true;										// we set hadDisk to true
					break;
				}
			}
		}
		if (isAdj == true && hadDisk == false) {						// if we are clicking the right spot and there is no any disk at that spot
			canMove = true;												// we set canMove to true, mean the player can move  
		}
		return canMove;													// and return canMove

	}

	/**
	 * method for Check Mills
	 * 
	 * @return true if there is a Mills
	 */
	public static boolean checkMills() { // we check all possible combinations
											// that can occur Mills
		boolean hasMills = false;			// initialize hasMills to false
		for (int i = 0; i < LabArray.length; i++) {					//every time we check 3 spots that are in a row or in a column
			for (int j = 0; j < LabArray.length; j++) {
				for (int k = 0; k < LabArray.length; k++) {
					if (LabArray[i] != null && LabArray[j] != null && LabArray[k] != null) {
						if (LabArray[i].getX() == position[0].x - 45 && LabArray[i].getY() == position[0].y - 35	// check first row
								&& LabArray[j].getX() == position[1].x - 45 && LabArray[j].getY() == position[1].y - 35
								&& LabArray[k].getX() == position[2].x - 45
								&& LabArray[k].getY() == position[2].y - 35) {
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) { //if these 3 spots has 3 disks with same color, means we have a Mills
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[5].x - 45 && LabArray[i].getY() == position[5].y - 35 // check 2nd row
								&& LabArray[j].getX() == position[6].x - 45 && LabArray[j].getY() == position[6].y - 35
								&& LabArray[k].getX() == position[7].x - 45
								&& LabArray[k].getY() == position[7].y - 35) {
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else
							if (LabArray[i].getX() == position[10].x - 45 && LabArray[i].getY() == position[10].y - 35	// check 3rd row
									&& LabArray[j].getX() == position[11].x - 45
									&& LabArray[j].getY() == position[11].y - 35
									&& LabArray[k].getX() == position[12].x - 45
									&& LabArray[k].getY() == position[12].y - 35) {
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[13].x - 45					
								&& LabArray[i].getY() == position[13].y - 35
								&& LabArray[j].getX() == position[14].x - 45
								&& LabArray[j].getY() == position[14].y - 35
								&& LabArray[k].getX() == position[15].x - 45
								&& LabArray[k].getY() == position[15].y - 35) {											// check 4th row
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[0].x - 45 && LabArray[i].getY() == position[0].y - 35
								&& LabArray[j].getX() == position[3].x - 45 && LabArray[j].getY() == position[3].y - 35
								&& LabArray[k].getX() == position[13].x - 45
								&& LabArray[k].getY() == position[13].y - 35) {											// check 1st column
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[5].x - 45 && LabArray[i].getY() == position[5].y - 35
								&& LabArray[j].getX() == position[4].x - 45 && LabArray[j].getY() == position[4].y - 35
								&& LabArray[k].getX() == position[10].x - 45
								&& LabArray[k].getY() == position[10].y - 35) {											// check 2nd column
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[7].x - 45 && LabArray[i].getY() == position[7].y - 35
								&& LabArray[j].getX() == position[8].x - 45 && LabArray[j].getY() == position[8].y - 35
								&& LabArray[k].getX() == position[12].x - 45
								&& LabArray[k].getY() == position[12].y - 35) {											// check 3rd column
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						} else if (LabArray[i].getX() == position[2].x - 45 && LabArray[i].getY() == position[2].y - 35
								&& LabArray[j].getX() == position[9].x - 45 && LabArray[j].getY() == position[9].y - 35
								&& LabArray[k].getX() == position[15].x - 45
								&& LabArray[k].getY() == position[15].y - 35) {											// check 4th column
							if (colors[i].equals(colors[j]) && colors[j].equals(colors[k])) {
								System.out.println("there is a mills");
								hasMills = true;
								if (colors[k].equals("red")) {
									redMills++;
									delcolor = "blue";
								} else {
									blueMills++;
									delcolor = "red";
								}
							}
						}
					}
				}
			}
		}
		return hasMills;					//if hasMills is true, means we have Mills
	}

	/**
	 * method to check if a player has won the game
	 */
	public static void Win() {
		int blue = 0;
		int red = 0;
		for (int i = 0; i < LabArray.length; i++) {			// count number of disks of each color
			if (LabArray[i] != null) {
				if (colors[i].equals("red")) {
					red++;
				} else {
					blue++;
				}
			}
		}
		if (red < 3 && blue >= 3) { // as long as the opponent only has 2 or
									// less pieces on board, the player wins (if red has less than 3 disks)
			move = 0;
			pick = 0;
			put = 0;
			start = 0;
			ErrorPanel.Bluewin(); // raise a message to notice player for
									// winning
		} else if (blue < 3 && red >= 3) {	// if blue has less than 3 disks, red wins
			move = 0;
			pick = 0;
			put = 0;
			start = 0;
			ErrorPanel.Redwin();
		}
	}

	/**
	 * method to reset the game
	 */
	public static void Restart() { // delete all pieces from the board
		try {
			for (int i = 0; i < LabArray.length; i++) { // loop through the
														// JLabel array, delete
														// every element
				if (LabArray[i] != null) {
					Container parent = LabArray[i].getParent();
					parent.remove(LabArray[i]);
					parent.validate();
					parent.repaint();
				}
			}
			LabArray = new JLabel[12]; // reset all variables here
			colors = new String[12];

			addcolor = 0;
			countblue = 6;
			countred = 6;
			index = 0;
			color = randColor();
			MoveColor = "";

			start = 0;
			setboard = 0;
			setBlue = 0;
			setRed = 0;
			move = 0;
			pick = 0;
			put = 0;
			delete = 0;

			AImode = 0;
			AIcolor = "";
			Usercolor = "";
			search = new Point[3];
			
			delcolor = "";
			blueMills = 0;
			redMills = 0;
			wantLoad = 0;

			color = randColor();
			LeftPanel.resetcounter(); // reset all error messages.
			RightPanel.resetcounter();
			ErrorPanel.resetError();
		} catch (NullPointerException nullpointerexception) {
		}
	}

	/**
	 * method to save the game
	 * 
	 * @throws IOException
	 */
	public static void Save() throws IOException { // basically we store all
													// changing variables in
													// data.txt
		File f = new File(System.getProperty("user.dir"), "data.txt");
		w = new BufferedWriter(new FileWriter(f));
		String positionIndex = "";
		String colorIndex = "";
		for (int i = 0; i < LabArray.length; i++) {
			for (int j = 0; j < position.length; j++) {
				if (LabArray[i] != null)
					if (LabArray[i].getX() == position[j].x - 45 && LabArray[i].getY() == position[j].y - 35) {	//we put position index in the string
						if (i == LabArray.length - 1 || LabArray[i + 1] == null) {								// and put color in another string
							positionIndex += j;
							colorIndex += colors[i];
						} else {
							positionIndex += j;
							positionIndex += ",";
							colorIndex += colors[i];
							colorIndex += ",";
						}
					}
			}
		}
		w.write(positionIndex + "\n");				// write all variables in the data.txt
		w.write(colorIndex + "\n");
		w.write(start + "\n");
		w.write(setboard + "\n");
		w.write(setBlue + "\n");
		w.write(setRed + "\n");
		w.write(move + "\n");
		w.write(delcolor + "\n");
		w.write(pick + "\n");
		w.write(put + "\n");
		w.write(delete + "\n");
		w.write(track + "\n");
		w.write(redMills + "\n");
		w.write(blueMills + "\n");
		w.write(color + "\n");
		w.write(MoveColor + "\n");
		w.close();

	}
	public static void wantLoad(){	// want to load
		wantLoad = 1;
	}
	/**
	 * method to load game
	 * 
	 * @throws IOException
	 */
	public void load() throws IOException { // we read in all variables to check
											// which state we are currently in
		File f = new File(System.getProperty("user.dir"), "data.txt");
		JLabel B = new JLabel(BlueP);
		JLabel R = new JLabel(RedP);
		int num = 0;
		r = new BufferedReader(new FileReader(f));
		String positionIndex = r.readLine();
		String colorIndex = r.readLine();
		start = Integer.parseInt(r.readLine());
		setboard = Integer.parseInt(r.readLine());
		setBlue = Integer.parseInt(r.readLine());
		setRed = Integer.parseInt(r.readLine());
		move = Integer.parseInt(r.readLine());
		delcolor = r.readLine();
		pick = Integer.parseInt(r.readLine());
		put = Integer.parseInt(r.readLine());
		delete = Integer.parseInt(r.readLine());
		track = Integer.parseInt(r.readLine());
		// addcolor = Integer.parseInt(r.readLine());
		redMills = Integer.parseInt(r.readLine());
		blueMills = Integer.parseInt(r.readLine());
		color = r.readLine();
		MoveColor = r.readLine();
		// System.out.println("MoveColor is " + MoveColor);
		String[] location = positionIndex.split(",");
		String[] diskColor = colorIndex.split(",");
		for (int i = 0; i < location.length; i++) {			//we load color and position, and put them back to position and color array
			if (!location[i].equals("")) {
				addDisk(diskColor[i], position[Integer.parseInt(location[i])].x - 200,
						position[Integer.parseInt(location[i])].y - 200);
				colors[addcolor] = diskColor[i];
				addcolor++;
			}
		}
		r.close();

	}

	public static void CheckLegal() { // check if there are 2 pieces at same
										// spot
		if (!LabArray[0].equals("")) {
			for (int i = 0; i < LabArray.length; i++) {
				for (int j = 0; j < LabArray.length; j++) {
					try {
						if (i != j && !LabArray[i].equals(null) && !LabArray[j].equals(null)) {
							if ((LabArray[i].getX() + 20 >= LabArray[j].getX()
									&& LabArray[i].getX() - 20 <= LabArray[j].getX())
									&& (LabArray[i].getY() + 20 >= LabArray[j].getY()
											&& LabArray[i].getY() - 20 <= LabArray[j].getY())) {

								ErrorPanel.raiseError(); // if we have, raise an
															// error
							}
						}
					} catch (NullPointerException nullpointerexception) {
					}
				}
			}
		}
	}

	public static String randColor() { // randColor will randomize the color of
										// the piece at the start
		String color;
		int lo = 0;
		int hi = 100;
		int random = lo + (int) (Math.random() * hi);
		if (random <= 50) {
			color = "blue";
		} else {
			System.out.println("here red");
			color = "red";

		}
		return color;
	}

	public static int randPosition() { // randColor will randomize the color of
										// the piece at the start
		int lo = 0;
		int hi = 15;
		return (int) (Math.floor(Math.random() * (hi - lo + 1)) + lo);
	}

	/////////////////////////////// NEW//////////////////////////////////////////////////////
	public static void AImode() { // get in AI mode
		System.out.println(color + "ccccc");
		AImode = 1;					// we set AImode to 1;
		Usercolor = color;			// we assume player will always go first
		if (Usercolor.equals("red"))	// AI will have opposite color.
			AIcolor = "blue";
		else if (Usercolor.equals("blue"))
			AIcolor = "red";
	}

	private void AIaddDisk() {					// method to let AI add disk
		if (AIcolor.equals("red")) {
			JLabel i = new JLabel(RedP);
			LabArray[index] = i;				// we add the JLabel in the array
			index++;
			add(i, new Integer(100));
			int spot = AIFindEmptySpot();		// first we call AIFindEmptySpot() to find a empty spot beside any AI's disk.
			i.setBounds((int) position[spot].getX() - 45, (int) position[spot].getY() - 35, RedP.getIconWidth(),	// then we add it to the empty spot
					RedP.getIconHeight());
			countred--; // countred - 1
			RightPanel.incrementcounter(); // go to method decrementcounter in
											// RightPanel
		} else if (AIcolor.equals("blue")) {		// do the same for different color.
			JLabel i = new JLabel(BlueP);
			LabArray[index] = i;
			index++;
			add(i, new Integer(100));
			int spot = AIFindEmptySpot();
			i.setBounds((int) position[spot].getX() - 45, (int) position[spot].getY() - 35, BlueP.getIconWidth(),
					BlueP.getIconHeight());
			countblue--; // countblue - 1
			LeftPanel.incrementcounter(); // go to method decrementcounter in
											// LeftPanel
		}
	}

	public static boolean repeatSpot(ArrayList<Integer> n, int num) {	// this method just makes sure we don't get same position from randomizing position
		for (int i = 0; i < n.size(); i++) {
			if (num == n.get(i)) {
				return true;					// we get repeated spot, return true
			}
		}
		return false;
	}

	private void AImoveDisk() {				// method that allows AI to move disk
		ArrayList<Integer> repeat = new ArrayList<Integer>();
		// randomly find a ai piece
		boolean AIpick = false;				// initialize AIpick to false
		int posit = -1;						// initialize posit to -1
		while (AIpick == false) {			// as long as AIpick is false, mean AI has not move a piece yet, let AI keeps searching
			posit = randPosition();			// first randomize a position
			while (repeatSpot(repeat, posit)) {
				posit = randPosition();
			}
			repeat.add(posit);
			for (int i = 0; i < LabArray.length; i++) {		// check if there is any disk of AI's color at that position
				if (LabArray[i] != null)
					if (colors[i].equals(AIcolor) && LabArray[i].getX() == position[posit].x - 45
							&& LabArray[i].getY() == position[posit].y - 35) {
						// AImove = i;
						track = i;
						// AI pick ith piece;
						int a = AIemptyspot(AIadj(posit));	// and we check which adjacent position of the spot is empty 
						if (a != -1) {
							AIpick = true;		// if we can find one, then set AIpick to true
							LabArray[track].setLocation((int) position[a].getX() - 45, (int) position[a].getY() - 35);	// reposition the disk AI moved
							MoveColor = Usercolor;				// then we let player move next
							if (MoveColor.equals("red")) {		// raise an warning to player
								ErrorPanel.Redmove();

							} else if (MoveColor.equals("blue")) {
								ErrorPanel.Bluemove();
							}

							if (checkMills()) {					// we also check Mills occur on the board
								if (delcolor.equals(Usercolor)) {	// if AI has a Mills, we call AIremove() to delete player's disk
									if (Usercolor.equals("blue") && redMills == 1) {	// also we assume AI only can have one Mills at a time
										AIremove();
									} else if (Usercolor.equals("red") && blueMills == 1) {
										AIremove();
									} else {						//otherwise we let player move
										delete = 0;
										pick = 1;
										put = 0;
									}

								} else if (delcolor.equals(AIcolor)) {	//if player has a Mills
									if (AIcolor.equals("blue") && redMills == 1) {	// assume only can have one Mills at a time
										System.out.println("player should del blue");
										ErrorPanel.delBlue();						//raise a warning, and go to delete stage
										delete = 1;
										pick = 0;
										put = 0;
									} else if (AIcolor.equals("red") && blueMills == 1) {
										System.out.println("player should del red");
										ErrorPanel.delRed();
										delete = 1;
										pick = 0;
										put = 0;
									} else {										// otherwise we keep moving pieces
										delete = 0;
										pick = 1;
										put = 0;
									}
								}
							} else {						// otherwise we keep moving pieces, and reset Mills
								blueMills = 0;
								redMills = 0;
								pick = 1;
								put = 0;
							}
							break;
						}
						// }
					}
			}

		}
	}

	public void AIremove() {					// method that allows AI to remove player's disk
		ArrayList<Integer> repeat = new ArrayList<Integer>();
		boolean deleted = false;				// initialize deleted is false
		int posit = -1;
		while (deleted == false) {				// as long as deleted is false, means AI has not deleted any piece yet
			posit = randPosition();				// random a position
			while (repeatSpot(repeat, posit)) {
				posit = randPosition();
			}
			repeat.add(posit);
			for (int i = 0; i < LabArray.length; i++) {	// check if there is a player's disk at the position
				if (LabArray[i] != null)
					if (LabArray[i].getX() == position[posit].x - 45 && LabArray[i].getY() == position[posit].y - 35) {
						if (colors[i].equals(Usercolor)) {
							deleteDisk(LabArray[i].getX() + 45, LabArray[i].getY() + 35);	// if yes, we call deleteDisk() to delete that piece
							deleted = true;			// set deleted to true
							break;
						}
					}
			}
		}

	}

	public int AIemptyspot(Point[] s) {					// method that helps AI to find empty spot
		boolean Notempty = false;
		for (int i = 0; i < s.length; i++) {			// we check all adjacent positions if any disk on those positions
			for (int j = 0; j < LabArray.length; j++) {
				if (s[i] != null && LabArray[j] != null) {
					if (LabArray[j].getX() == (int) (s[i].getX() - 45)
							&& LabArray[j].getY() == (int) (s[i].getY() - 35)) {
						Notempty = true;				// if we can find a disk, means it is not empty
						break;
					}
				}
			}
			if (Notempty == false) { 				// if it is empty
				for (int k = 0; k < position.length; k++) {
					if (s[i] != null)
						if (position[k].getX() == s[i].getX() && position[k].getY() == s[i].getY())
							return k;				// we return the position where is empty
				}
			}
			Notempty = false;
		}
		return -1;
	}

	public Point[] AIadj(int n) {					// this is all adjacent positions of each position
		search = new Point[3];
		if (n == 0) {								// position 1 and 3 are the adjacent positions of position 0. etc.
			search[0] = position[1];
			search[1] = position[3];
		} else if (n == 1) {
			search[0] = position[0];
			search[1] = position[2];
			search[2] = position[6];
		} else if (n == 2) {
			search[0] = position[1];
			search[1] = position[9];
		} else if (n == 3) {
			search[0] = position[0];
			search[1] = position[4];
			search[2] = position[13];
		} else if (n == 4) {
			search[0] = position[3];
			search[1] = position[5];
			search[2] = position[10];
		} else if (n == 5) {
			search[0] = position[4];
			search[1] = position[6];
		} else if (n == 6) {
			search[0] = position[5];
			search[1] = position[1];
			search[2] = position[7];
		} else if (n == 7) {
			search[0] = position[6];
			search[1] = position[8];
		} else if (n == 8) {
			search[0] = position[7];
			search[1] = position[9];
			search[2] = position[12];
		} else if (n == 9) {
			search[0] = position[2];
			search[1] = position[8];
			search[2] = position[15];
		} else if (n == 10) {
			search[0] = position[4];
			search[1] = position[11];
		} else if (n == 11) {
			search[0] = position[10];
			search[1] = position[14];
			search[2] = position[12];
		} else if (n == 12) {
			search[0] = position[11];
			search[1] = position[8];
		} else if (n == 13) {
			search[0] = position[3];
			search[1] = position[14];
		} else if (n == 14) {
			search[0] = position[13];
			search[1] = position[11];
			search[2] = position[15];
		} else if (n == 15) {
			search[0] = position[9];
			search[1] = position[14];
		}

		return search;
	}

	public int AIFindEmptySpot() {			// method to find empty spot for AI to put piece in AImoveDisk()
		int randPosition = randPosition();
		boolean check = AIcheckPosit(randPosition);
		while (check == true) {				// as long as AI has not found any empty spot.
			randPosition = randPosition();		// let AI keep check
			check = AIcheckPosit(randPosition);
		}
		return randPosition;				// return the position index if AI found one
	}

	public boolean AIcheckPosit(int rand) {	// method to check if the position is empty
		boolean hadDisk = false;
		for (int j = 0; j < LabArray.length; j++) {
			if (LabArray[j] != null) {
				if (LabArray[j].getX() == position[rand].getX() - 45
						&& LabArray[j].getY() == position[rand].getY() - 35) {
					hadDisk = true;			// if we can find one disk on the spot, hadDisk is true
				}
			}
		}
		return hadDisk;
	}

	////////////////////////////////////////// END//////////////////////////////////////////////////////////////////
	public static void moveallowed() {			// method to enable moving disk feature, after putting down all disks
		if (start == 1 && move == 0 && setboard == 0 && countblue == 0 && countred == 0) {
			MoveColor = colors[0];
			move = 1;
			pick = 1;
			put = 0;
			if (MoveColor.equals("red"))
				ErrorPanel.Redmove();
			else
				ErrorPanel.Bluemove();
		} else {

		}
	}

	public static void startgame() { // set start = 1, setboard = 0
		if (setboard == 1) { // shows we are starting the game, not in setup
								// mode
			MoveColor = colors[0];
			start = 1;
			move = 1;
			pick = 1;
			put = 0;
			setboard = 0;
			if (MoveColor.equals("red"))
				ErrorPanel.Redmove();
			else
				ErrorPanel.Bluemove();
			// System.out.println("you set the board, not move!");
		} 
		else if (wantLoad == 1) {
			try {
				new LayPanel().load();
			} catch (IOException e) {
			}
		} else {
			start = 1;
			move = 0;
		}

	}

	public static void setboard() { // set setboard = 1, start = 1
		setboard = 1; // shows we are setting up the board, not starting the
						// game
		start = 0;
		// System.out.println("start is " + start);
	}

	public static void setBlue() { // setBlue will be 1, so application knows we
									// want a blue piece
		if (setboard == 1) {
			setBlue = 1;
			setRed = 0;
			color = "blue";
			start = 0;
		}
	}

	public static void setRed() { // setRed will be 1, so application knows we
									// want a red piece
		if (setboard == 1) {
			setRed = 1;
			setBlue = 0;
			color = "red";
			start = 0;
		}
	}

	/**
	 * find image
	 * 
	 * @param path
	 *            - location of the image
	 * @return ImageIcon
	 */
	private ImageIcon createImageIcon(String path) { // get image
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
