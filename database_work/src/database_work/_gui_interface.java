package database_work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class _gui_interface {
	
	// Declare color for background color of frames
	public static Color background_color = new Color(228, 251, 255);
	
	// Function to create window in frame with title, dimension and close_operation
	public static void create_window(JFrame frame, String title, Dimension dimension, int operation) {
		// Sets frame defaultcloseoperation to operation
		frame.setDefaultCloseOperation(operation);
		// Sets title to title
		frame.setTitle(title);
		// Sets alwaysontop to true
		frame.setAlwaysOnTop(true);
		// Sets the size to the dimension
		frame.setSize(dimension);
		// Sets resizable to false
		frame.setResizable(false);
		// Sets the contentpane background to background_color
		frame.getContentPane().setBackground(background_color);
		// Sets locationrelativeto to null
		frame.setLocationRelativeTo(null);
		// Clears the layout of contentPane()
		frame.getContentPane().setLayout(null);
	}

	// Function to create label on a frame, on x and y coordinates, with Font and String which is the text
	public static void add_label(JFrame frame, int x, int y, Font font, String string) {
		// Create new JPanel, to add the string to it
		JPanel panel = new JPanel();
		// Set layout to LEFT, because we want all contents inside it to go left
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Create new label, and add String contents to it
		JLabel label = new JLabel(string);
		// Set the font of that label to Font font
		label.setFont(font);
		
		// Add the label to panel
		panel.add(label);
		// Put background_color of panel to the background_color that is declared at the top of this program
		panel.setBackground(background_color);
		// Set bounds of panel to x, y, string.length() * 8 pixels in width and 30 in height
		panel.setBounds(x, y, string.length() * 8, 30);
		// Add the panel to frame
		frame.getContentPane().add(panel);
	}

	// Function to create button on a frame, on x and y coordinates, with Font and String which is the content of the Button
	public static JButton add_button(JFrame frame, int x, int y, Font font, String string) {
		// Create new JPanel
		JPanel panel = new JPanel();
		// Set the layout of THIS panel to center, because we want the buttons to be centered
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// Create new Button, and add string to it
		JButton button = new JButton(string);
		// Set font of that button to Font font
		button.setFont(font);
		// Set size with PrefferedSize to button (dont use setSize, because it doesn't work with FlowLayout)
		button.setPreferredSize(new Dimension(100, 40));
		
		// Add button to panel
		panel.add(button);
		// Put background_color of panel to the background_color that is declared at the top of this program
		panel.setBackground(background_color);
		// Set bounds of panel to x, y, 100 in width and 30 in height
		panel.setBounds(x, y, 100, 45);
		// Add panel to contentpane
		frame.getContentPane().add(panel);
		
		// Return Button
		return button;
	}
	
	// Function to add mutebutton to frame on x and y coordinate, with path
	// This one needs to be declared differently considering it has an image on it
																				  // throws IOException because we declare an image
	public static JButton add_mutebutton(JFrame frame, int x, int y, String path) throws IOException {
		// Create a new JPanel
		JPanel panel = new JPanel();
		
		// Declare a BufferedImage and sets its path to File's path of the image
		BufferedImage image = ImageIO.read(new File(path));
		// Declare an Image object and SCALE it down to 35, 35
		Image img = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		// Declare a new Icon and set it to ImageIcon(Image img)
		Icon icon = new ImageIcon(img);
		
		// Finally, declare the button JButton with the icon
		JButton button = new JButton(icon);
		// Set size of that button to 35, 35
		button.setPreferredSize(new Dimension(35, 35));
		
		// Add button to panel
		panel.add(button);
		// Set background color the same as before, on top of this program
		panel.setBackground(background_color);
		// Set bounds of panel to 50, 50
		panel.setBounds(x, y, 50, 50);
		// Add panel to contentpane
		frame.getContentPane().add(panel);
		
		// return button 
		return button;
	}
	
	// Function to add text box to frame, on x and y coordinates, with Font that is added and String to be displayed
	public static JTextField add_textBox(JFrame frame, int x, int y, Font font, String string) {
		// Create new JPanel
		JPanel panel = new JPanel();
		
		// Create new textfield and add the string to it
		JTextField textfield = new JTextField(string);
		// set selectedtextcolor to blue, because we want the color of the selected text to be blue
		textfield.setSelectedTextColor(Color.blue);
		// Add horizontal alignment of it to center
		textfield.setHorizontalAlignment(JTextField.CENTER);
		// Set font of string to font
		textfield.setFont(font);
		// Add new size of it
		textfield.setPreferredSize(new Dimension(100, 40));
		
		// Add textfield to panel
		panel.add(textfield);
		// Put background_color of panel to the background_color that is declared at the top of this program
		panel.setBackground(background_color);
		// Set bounds of panel to x, y, 100 in width and 45 in height
		panel.setBounds(x, y, 100, 45);
		// Add panel to contentpane
		frame.getContentPane().add(panel);
		
		// Return textfield
		return textfield;
	}
	
	// Function to add list to frame, on coordinate x and y, with font and list that is DefaultListModel<String>
	public static void add_list(JFrame frame, int x, int y, Font font, DefaultListModel<String> stringlist) {
		// Create a new panel
		JPanel panel = new JPanel();
		
		// Declare new list with JList<String> type
		JList<String> list = new JList<String>(stringlist);
		// Set font of all to font
		list.setFont(font);
		
		// Create new ScrollPane on list, with vertical scrollbar and horizontal scrollbar if needed
		JScrollPane pane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Set preferredsize of pane to 450, 350
		pane.setPreferredSize(new Dimension(450, 350));
		
		// Set layout of panel to new BorderLayout()
		panel.setLayout(new BorderLayout());
		// Add pane to panel
		panel.add(pane);
		
		// Set background color of panel to background_color declared on top of this program
		panel.setBackground(background_color);
		// Set bounds of panel to 450, 350
		panel.setBounds(x, y, 450, 350);
		
		// Add panel to contentpane
		frame.getContentPane().add(panel);
	}
	
	// Function to remove a list from frame
	public static void remove_list(JFrame frame) {
		// For every component C in frame's contentpane's components
		for(Component c : frame.getContentPane().getComponents()) {
			// if the Component C is instanceof (class or subclass) JPanel
			if(c instanceof JPanel) {
				// Remove the component, so it doesnt stack up
				frame.getContentPane().remove(c);
			}
		}
	}
}
