package database_work;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class _main {
	
	public static JFrame frame = new JFrame();
	public static JFrame input_frame = new JFrame();
	public static JFrame output_frame = new JFrame();
	public static Connection _connection;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		 // this activates part of what you want to do
		 // database_work function is required to open connection to database, as well as use the database
		  database_work();
		 // frame_work function is used for opening the GUIs that display the contents
		  frame_work();
		 // music isn't required, and it is purely added for the sake of it, why not.
		  music("music.wav");
		
		 // UNCOMMENT IF FIRST TIME USING
		 //    vvv
		 // _database_functions.database_create(_connection);
	}
	
	public static void database_work() throws ClassNotFoundException {
		// declare the Class for the driver that JDBC (java database connection) uses
		Class.forName("com.mysql.cj.jdbc.Driver");

		// try to initiate connection, if unsuccessful database isn't active, close the program
		try {
			// Instantiation of the connection to database
		_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "gvozdenacnenad");
		} catch(Exception e) {
			// Print error to console
			e.printStackTrace();
			// Print error to console
			System.out.println("Could not open program, database not active.\nTerminating program.");
			// Print error to window
			JOptionPane.showMessageDialog(new JFrame(), "Error in opening database", "ERROR", JOptionPane.ERROR_MESSAGE);
			// Exit the System function, close the program
			System.exit(0);
		}
	}
	
	public static void frame_work() throws IOException {
		// Function in _gui_interface used to create a window with title "Working with databases...", Boundaries Dimension(450, 400), and close operation of nothing
		_gui_interface.create_window(frame, "Working with databases...", new Dimension(450, 400), WindowConstants.DO_NOTHING_ON_CLOSE);
		
		// Icon-setting function to iconImage.PNG, that is there
		frame.setIconImage(new ImageIcon(new File(".//src//database_work//iconImage.PNG").getAbsolutePath()).getImage());
		
		// Function to add label to the frame, uses JFrame, Int Position, Int Position, Font, and String which is the desired string
		_gui_interface.add_label(frame, 60, 0, new Font("Times New Roman", Font.BOLD, 17), "Hello user. What would you like to do?");
		_gui_interface.add_label(frame, 5,  70, new Font("Times New Roman", Font.BOLD, 16), "Input person's data");
		_gui_interface.add_label(frame, 5, 140, new Font("Times New Roman", Font.BOLD, 16), "Read all people's data");
		_gui_interface.add_label(frame, 5, 210, new Font("Times New Roman", Font.BOLD, 16), "Clear all people's data");
		_gui_interface.add_label(frame, 5, 280, new Font("Times New Roman", Font.BOLD, 16), "Exit the program");
		
		// Function for adding buttons to frame, uses same as up there
		JButton input_button  = _gui_interface.add_button(frame, 300, 70, new Font("Times New Roman", Font.BOLD, 16), "INPUT");
		JButton output_button = _gui_interface.add_button(frame, 300, 140, new Font("Times New Roman", Font.BOLD, 16), "OUTPUT");
		JButton clear_button  = _gui_interface.add_button(frame, 300, 210, new Font("Times New Roman", Font.BOLD, 16), "CLEAR");
		JButton exit_button   = _gui_interface.add_button(frame, 300, 280, new Font("Times New Roman", Font.BOLD, 16), "EXIT");
		JButton mute_button   = _gui_interface.add_mutebutton(frame, 5, 310, ".//src//database_work//unmuteImage.jpg");
		
		// Function to add listener to button mute_button
		_buttons_functions.add_mute_listener(frame, mute_button, ".//src//database_work//muteImage.jpg" ,".//src//database_work//unmuteImage.jpg");
		
		// Function to add listener to other buttons
		_buttons_functions.pressed(frame, input_button, output_button, clear_button, exit_button);
		
		// Function to add WindowListener for exiting of Aplication
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        JFrame frame = (JFrame)e.getSource();
		 
		        int result = JOptionPane.showConfirmDialog(
		            frame,
		            "Are you sure you want to exit the application?",
		            "Exit Application",
		            JOptionPane.YES_NO_OPTION);
		 
		        if (result == JOptionPane.YES_OPTION) {
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    		try {
						_database_functions.database_close(_connection);
						JOptionPane.showMessageDialog(frame, "Thank you for using this program.\nMade by Nenad Gvozdenac, FTN 2021.\n", "THANK YOU", JOptionPane.PLAIN_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		    }
		});
		
		// Activating the frame
		frame.setVisible(true);
	}
	
	public static void input_frame() throws SQLException {
		
		// Creation of frame, just like above
		_gui_interface.create_window(input_frame, "Input into database...", new Dimension(380, 400), WindowConstants.DO_NOTHING_ON_CLOSE);
		
		// Adding icon, just like above
		input_frame.setIconImage(new ImageIcon(".//src//database_work//iconImage.PNG").getImage());
		
		// Adding labels, just like above
		_gui_interface.add_label(input_frame, 60, 0, new Font("Times New Roman", Font.BOLD, 16), "You pressed INPUT. Enter the data:");
		_gui_interface.add_label(input_frame, 5,  70, new Font("Times New Roman", Font.BOLD, 16), "Input person's NAME: ");
		_gui_interface.add_label(input_frame, 5, 140, new Font("Times New Roman", Font.BOLD, 16), "Input person's LAST NAME: ");
		_gui_interface.add_label(input_frame, 5, 210, new Font("Times New Roman", Font.BOLD, 16), "Input person's GRADE: ");
		_gui_interface.add_label(input_frame, 5, 280, new Font("Times New Roman", Font.BOLD, 16), "Input person's BIRTH DATE: ");
		
		// Getting information from textfields, but adding them first via add_textBox function that adds a textbox to a frame, at X, Y position, with desired font and String value
		JTextField input_name      = _gui_interface.add_textBox(input_frame, 250, 65, new Font("Times New Roman", Font.ITALIC, 14), "NAME");
		JTextField input_lastname  = _gui_interface.add_textBox(input_frame, 250, 135, new Font("Times New Roman", Font.ITALIC, 14), "LAST NAME");
		JTextField input_grade     = _gui_interface.add_textBox(input_frame, 250, 205, new Font("Times New Roman", Font.ITALIC, 14), "GRADE");
		JTextField input_birthyear = _gui_interface.add_textBox(input_frame, 250, 275, new Font("Times New Roman", Font.ITALIC, 14), "DD/MM/YYYY");
		
		// Adding button to frame via add_button function
		JButton input_button = _gui_interface.add_button(input_frame, 135, 310, new Font("Times New Roman", Font.BOLD, 16), "ADD");
		
		// Retrieving the strings from textfields
		_buttons_functions.input_pressed_IF(input_frame, input_button, input_name, input_lastname, input_grade, input_birthyear);
		// On click on button, frame resets every textfield
		_buttons_functions.select_on_click(input_frame, input_name, input_lastname, input_grade, input_birthyear);
		
		// Adding listener to input_frame, if user exited out of application, it disposes of it and sets main Frame to be visible again
		input_frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	input_frame.dispose();
		        frame.setVisible(true);
		    }
		});
		
		// Setting input_frames visible
		input_frame.setVisible(true);
	}
	
	public static void output_frame() throws SQLException {
		
		// Creation of frame, just like above
		_gui_interface.create_window(output_frame, "Database output", new Dimension(500, 450), WindowConstants.DO_NOTHING_ON_CLOSE);
		// Adding icon, just like above
		output_frame.setIconImage(new ImageIcon("C:\\Database_Work\\database_work\\src\\database_work\\iconImage.PNG").getImage());
		
		// Adding labels, just like above
		_gui_interface.add_label(output_frame, 80, 0, new Font("Times New Roman", Font.BOLD, 16), "You pressed OUTPUT. Data is being loaded...");
		
		// Declaration of listModel string, later to be used for JList
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		// Adding list via guiinterface.add_list to output_frame
		_gui_interface.add_list(output_frame, 20, 30, new Font("Times New Roman", Font.BOLD, 16), listModel);
		
		// ArrayList<String> used for dynamically adding things to dynamic array, with database_output function
		ArrayList<String> podaci = _database_functions.database_output(_connection);
		
		// Adding with .add() method 
		for(int i = 0; i < podaci.size(); i++) {
			listModel.add(i, podaci.get(i));
		}
		
		// Adding listener onClose of window, disposes of frame and clears the list in output_frame
		output_frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	output_frame.dispose();
		    	_gui_interface.remove_list(output_frame);
		        frame.setVisible(true);
		    }
		});
		
		// Sets the frame visible
		output_frame.setVisible(true);
	}
	
	// Declaration for Clip for music, sets to null at start
	public static Clip clip = null;
	
	@SuppressWarnings("deprecation")
	public static void music(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		// Declares new File with path
		File f = new File(path);
		// Declares the AudioInputStream
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);  
		// Sets clip to that clip
	    clip = AudioSystem.getClip();
	    // Opens the clip instance
	    clip.open(audioIn);
	    // Starts the loop of Clip to continuous, so it never ends
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	    // Starts the clip, plays the music
	    clip.start();
	}
}
