package database_work;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JOptionPane;

public class _buttons_functions {

	// Declare connection to the mainconnection in main program
	public static Connection database_connection = _main._connection;
	
	// Declare mute_active boolean which indicates that at start mute_active is true
	// When program starts, this goes to false
	public static boolean mute_active = true;
	
	// Function to add usability to buttons in Frame
	public static void pressed(JFrame frame, JButton... buttons) {
		
		// For all buttons in JButton[]
		for(int i = 0; i < buttons.length; i++) {
			// Switch the text of buttons[i]
			switch(buttons[i].getText()) {
				// if the text is "EXIT"
				case "EXIT":
					// Add listener that redirects you to exit_pressed(frame)
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								exit_pressed(frame);
								// If error, System.out the error
							} catch (SQLException e1) {
								System.out.print("Exiting could not be done. Error.");
								e1.printStackTrace();
							}
						}
					});
				break;
				// if the text is "INPUT"
				case "INPUT":
					// Add listener that redirects you to input_pressed(frame)
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								input_pressed(frame);
								// If error, System.out the error
							} catch (SQLException e1) {
								System.out.print("Could not input data. Error.");
								e1.printStackTrace();
							}
						}
					});
				break;
				// if the text is "OUTPUT"
				case "OUTPUT":
					// Add listener that redirects you to output_pressed(frame)
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								output_pressed(frame);
								// If error, System.out the error
							} catch (SQLException e1) {
								System.out.print("Could not output data. Error.");
								e1.printStackTrace();
							}
						}
					});
				break;
				// if the text is "CLEAR"
				case "CLEAR":
					// Add listener that redirects you to clear_pressed(frame)
					buttons[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								try {
									clear_pressed(frame);
									// If error, System.out the error
								} catch (SQLException e1) {
									System.out.print("Could not clear database. Error.");
									e1.printStackTrace();
								}
						}
					});
				break;
			}
		}
	}
	
	// Function to to check if input_button in output_frame is pressed
	public static void input_pressed_IF(JFrame frame, JButton button, JTextField... fields) throws SQLException {
		// If button.getText() is "ADD"
		if(button.getText() == "ADD") {
			// Add listener to that button
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// Try and chop datestring into text
						String[] datestring = fields[3].getText().split("/");
						// Create new Class Date
						// Constructor of Integer, Integer, Integer
						_classes.Date date = new _classes.Date(Integer.parseInt(datestring[0]), Integer.parseInt(datestring[1]), Integer.parseInt(datestring[2]));
						
						// See what the grade is, from fields[2].getText()
						Float grade = Float.parseFloat(fields[2].getText());
						
						// If grade > 5f(loat), throw exception that its not valid
						if(grade > 5f) throw new Exception("Grade not valid");
						
						// We input all the information via database_addmember functions, to databaseconnection
						_database_functions.database_addmember(database_connection, fields[0].getText(), fields[1].getText(), grade, date);
						
						// We output that data has been successfully added
						JOptionPane.showMessageDialog(frame, "Successfully inputed data into database!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
						
						// We set all textboxes to null
						reset_textboxes(frame, fields[0], fields[1], fields[2], fields[3]);
					} catch (Exception ex) {
						// If there is a error, output that there was an error
						JOptionPane.showMessageDialog(frame, "Wrong data to be inputed into database. \nTry again.", "ERROR", JOptionPane.ERROR_MESSAGE);
						reset_textboxes(frame, fields[0], fields[1], fields[2], fields[3]);
					}
				}
			});
		}
	}
	// Function to reset textboxes
	public static void reset_textboxes(JFrame frame, JTextField... text) {
		// for every textfield c in JTextField[] text
		for(JTextField c : text) {
			// set the text to null
			c.setText(null);
		}
	}
	
	// Function to select text on click
	public static void select_on_click(JFrame input_frame2, JTextField... fields) {
		// For every textfield in JTextField[] fields
 		for(JTextField c : fields) {
 			// Add mouse listener, if its been pressed, select all
			c.addMouseListener(new MouseInputAdapter() {
				public void mouseClicked(MouseEvent e) {
					// Select all text in that textfield
					c.selectAll();
				}
			});
		}
	}

	// Function to add mute listener to mutebutton
	public static void add_mute_listener(JFrame frame, JButton button, String muteimagepath, String unmuteimagepath) {
		// Add actionlistener to the mutebutton, if its been clicked, try mute_pressed
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						mute_pressed(frame, button, muteimagepath, unmuteimagepath);
						
						if(_main.clip == null)	throw new Exception();
						// If error, System.out the error
					} catch (IOException e1) {
						System.out.print("Error, doesn't work!");
						e1.printStackTrace();
					} catch (Exception e2) {
						System.out.println("Music isn't loaded.");
					}
			}
		});
	}
	
	// Function that was redirected from pressed, for exit
	public static void exit_pressed(JFrame frame) throws SQLException {
		
		// Result is for the extra JOptionPane window that has YES/NO. If yes clicked, it exists the application
		int result = JOptionPane.showConfirmDialog(
				frame,
				"Are you sure you want to exit the program?",
				"Exit Application",
				JOptionPane.YES_NO_OPTION);
		
		// If result is YES, exit
		if (result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(frame, "Thank you for using this program.\nMade by Nenad Gvozdenac, FTN 2021.\n", "THANK YOU", JOptionPane.PLAIN_MESSAGE);
			_database_functions.database_close(database_connection);
			System.exit(0);
		}
	}

	// Function that was redirected from pressed, for input
	public static void input_pressed(JFrame frame) throws SQLException {
		// Activate the input_frame again
		_main.input_frame();
		// Set it visible too
		frame.setVisible(false);
	}

	// Function that was redirected from pressed, for output
	public static void output_pressed(JFrame frame) throws SQLException {
		// If database is empty
		if(_database_functions.database_empty(database_connection)) 
			// Activate the message that database is empty
			JOptionPane.showMessageDialog(frame, "Database is empty.\nAdd some data!", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			// Else activate output_frame
			_main.output_frame();
			// Deactivate the frame
			frame.setVisible(false);
			// Also output all data to CMD
			_database_functions.database_cmdoutput(database_connection);
		}
	}

	// Function that was redirected from pressed, for clearing
	public static void clear_pressed(JFrame frame) throws SQLException {
		// If database is empty
		if(_database_functions.database_empty(database_connection))
			// Output that database is empty already
			JOptionPane.showMessageDialog(frame, "Database is already empty...", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			// Else clear it and output that it had been cleared
			_database_functions.database_clear(database_connection);
			JOptionPane.showMessageDialog(frame, "Successfully cleared the database of all data!", "CLEARED", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Function to indicate that mute was pressed
	public static void mute_pressed(JFrame frame, JButton button, String mutepath, String unmutepath) throws IOException {
		// If mute is active
		// Put it to be not active
		if(mute_active) {
			mute_active = false;
			// Get image and put the 'mute' image from mutepath
			BufferedImage image = ImageIO.read(new File(mutepath));
			Image img = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			Icon ikona = new ImageIcon(img);
			
			// Stop the clip
			_main.clip.stop();
			// Put icon as 'mute'
			button.setIcon(ikona);
		} else if(mute_active != true) {
			// if mute isnt active, put it to active
			mute_active = true;
			// Get image again
			BufferedImage image = ImageIO.read(new File(unmutepath));
			Image img = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
			Icon ikona = new ImageIcon(img);

			// Start the clip
			_main.clip.start();
			
			// Put icon as 'unmute'
			button.setIcon(ikona);
		}
		
	}
	
}


