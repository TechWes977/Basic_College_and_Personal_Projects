package windows;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;

public class ATM {
	//Instance Variables:
		//Natural IVs:
			private String FirstName;
			private String LastName;
			private String Username;
			private String Password;
			
			FileReader file;
			BufferedReader in;
	
		//GUI Component IVs:
			//Containers:
				private JFrame frame;
				private Container cp; //This will be the contentPane
				private JPanel signIn;
				private JPanel createNew;
			//Basic Components:
				private JLabel lblWelcome;
				private JLabel lblUser;
				private JLabel lblPass;
				private JLabel lblDontHave;
				private JLabel lblNotFound;
				private JLabel lblNew;
				private JLabel lblFirst;
				private JLabel lblLast;
				private JLabel lblNewUser;
				private JLabel lblNewPass;
				private JLabel lblConfirmPass;
				private JLabel lblError;
				private JLabel lblNewError;
				
				private JButton btnSignIn;
				private JButton btnCreateNew;
				private JButton btnCreateAcct;
				private JButton btnBack;
				
				private JTextField txtUser;
				private JTextField txtFirst;
				private JTextField txtLast;
				private JTextField txtNewUser;
				private JPasswordField txtSignPass;
				private JPasswordField txtNewPass;
				private JPasswordField txtConfirmPass;
				

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM window = new ATM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATM() {
		initFrame();
		createGUI();
		createEvents();
	}
       
	/**
	 * Initialize the contents of the frame.
	 */
	private void initFrame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		cp = frame.getContentPane();
		cp.setLayout(null);	
	}
	
	public void createGUI(){
		init_signIn_Panel();
		init_createNew_Panel();
	}
	
	private void init_signIn_Panel(){
		
		signIn = new JPanel();
		signIn.setLayout(null);
		signIn.setBounds(10,10,415,290);
		cp.add(signIn);
		
		lblWelcome = new JLabel("Welcome to our Bank!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setBounds(10, 25, 160, 30);
		signIn.add(lblWelcome);
		
		lblUser = new JLabel("Username:");
		lblUser.setBounds(130,115,70,25);
		lblUser.setFont(new Font("Tahoma",Font.PLAIN, 12));
		signIn.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(210, 118, 90, 20);
		signIn.add(txtUser);
		
		lblPass = new JLabel("Password:");
		lblPass.setBounds(130,146,70,25);
		lblPass.setFont(new Font("Tahoma",Font.PLAIN, 12));
		signIn.add(lblPass);
		
		txtSignPass = new JPasswordField();
		txtSignPass.setBounds(210,149,90,20);
		signIn.add(txtSignPass);
		
		lblNotFound = new JLabel("That Account could not be found.");
		lblNotFound.setVisible(false);
		lblNotFound.setForeground(Color.RED);
		lblNotFound.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNotFound.setBounds(140,95,160,10);
		signIn.add(lblNotFound);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSignIn.setBounds(230,180,70,25);
		signIn.add(btnSignIn);
		
		lblDontHave = new JLabel("Don't have an Account?");
		lblDontHave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDontHave.setBounds(10,223,120,20);
		signIn.add(lblDontHave);
		
		btnCreateNew = new JButton("New Account");
		btnCreateNew.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreateNew.setBounds(10,254,95,25);
		signIn.add(btnCreateNew);
		
	}

	private void init_createNew_Panel(){
		
		createNew = new JPanel();
		createNew.setVisible(false);
		createNew.setLayout(null);
		createNew.setBounds(10,10,415,290);
		cp.add(createNew);
		
		lblNew = new JLabel("New User Information:");
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNew.setBounds(55, 35, 160, 30);
		createNew.add(lblNew);
		
		lblFirst = new JLabel("First Name:");
		lblFirst.setBounds(100,75,75,25);
		createNew.add(lblFirst);
		
		txtFirst = new JTextField();
		txtFirst.setBounds(175,80,90,20);
		createNew.add(txtFirst);
		
		lblLast = new JLabel("Last Name:");
		lblLast.setBounds(100,110,75,25);
		createNew.add(lblLast);
		
		txtLast = new JTextField();
		txtLast.setBounds(175,115,90,20);
		createNew.add(txtLast);
		
		lblNewUser = new JLabel("Username:");
		lblNewUser.setBounds(100,145,75,25);
		createNew.add(lblNewUser);
		
		txtNewUser = new JTextField();
		txtNewUser.setBounds(175,150,90,20);
		createNew.add(txtNewUser);
		
		lblNewPass = new JLabel("Password:");
		lblNewPass.setBounds(100,180,75,25);
		createNew.add(lblNewPass);
		
		txtNewPass = new JPasswordField();
		txtNewPass.setBounds(175,185,90,20);
		createNew.add(txtNewPass);
		
		lblConfirmPass = new JLabel("Confirm Password:");
		lblConfirmPass.setBounds(55,210,120,25);
		createNew.add(lblConfirmPass);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(175,215,90,20);
		createNew.add(txtConfirmPass);
		
		btnCreateAcct = new JButton("Create Account");
		btnCreateAcct.setBounds(125,255,125,30);
		createNew.add(btnCreateAcct);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnBack.setBounds(0,0,55,20);
		createNew.add(btnBack);
		
		lblNewError = new JLabel("");
		lblNewError.setFont(new Font("Tahome", Font.PLAIN, 10));
		lblNewError.setBounds(125,240,175,15);
		lblNotFound.setForeground(Color.RED);
		lblNewError.setVisible(false);
		createNew.add(lblNewError);
		
	}

	private void createEvents(){
		//This event is for the 'Create New' button, and it takes the user to 
		//the screen that allows for the creation of a new user
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signIn.setVisible(false);
				createNew.setVisible(true);
			}
		});
		
		//This event is for the 'Back' button, and it takes the user
		//from the 'createNew' panel, to the 'signIn' panel
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNew.setVisible(false);
				signIn.setVisible(true);
			}
		});
	
		//This Event is for the 'Sign In' Button, and it checks the user's
		//credentials. If the credentials match an existing account, it opens
		//that account (class). If not, then it displays an error message
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sets this.Username equal to whatever is in the username text field
				Username = txtUser.getText();
				//set this.Password equal to whatever is in the password text field
				Password = txtSignPass.getText();
				
				try{
					//Tries to open the file who's name is that specific username.
					file = new FileReader(Username + ".txt");
					in = new BufferedReader(file);
					
					//sets variable 'line' equal to the first line of the file, which is where the password is stored.
					String line = in.readLine();
					
					//Checks if the password entered equals the stored password
					if(line.equals(Password)){
						//System.out.println("Sign In Successful!");
						lblNotFound.setVisible(false);
						Account user = new Account(Username);
						user.main("signIn");
						frame.dispose();
					}
					else
						lblNotFound.setVisible(true);
				}
				
				//If no file was found with that username, or if the password didn't match, then displays error message:
				catch(IOException fileName){
					lblNotFound.setVisible(true);
				}	
			}
		});
	
		//This Event is for the 'Create Account' Button, and will take all of relevant 
		//textfield's info, and create a new user
		btnCreateAcct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Username = txtNewUser.getText();
				FirstName = txtFirst.getText();
				LastName = txtLast.getText();
				
				if( ((Username.equals("")) || (FirstName.equals("")))  || (LastName.equals(""))){
					System.out.println("Cant be empty");
					lblNewError.setText("Fields cannot be left empty");
					lblNewError.setVisible(true);
					return;
				}
				
				//Checks to see if the password & confirm password are the same
				//If they aren't, then do this:
				if( (!txtNewPass.getText().equals(txtConfirmPass.getText()) || (txtNewPass.getText().equals("")) )) {
					System.out.println("Passwords Do not Match");
					lblNewError.setText("Passwords Do Not Match");
					lblNewError.setVisible(true);
				}
				
				//If they are the same, do this:
				else{
					Password = txtConfirmPass.getText();
					//System.out.println("you now have an account!");
					//System.out.println("\nFirstName: " + FirstName + "\nLastName: " + LastName + "\nPassword: " + Password);
					try {
						Account user = new Account(Username, FirstName, LastName, Password);
						user.main("new");
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	
	
	}
}

