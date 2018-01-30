package windows;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Account {

	//Instance Variables:
			//Natural IVs:
				private String FirstName;
				private String LastName;
				private String Username;
				private String Password;
				private double Balance;
				
				private FileReader file;
				private BufferedReader in;
				private FileWriter newFile;
				private PrintWriter out;
				
				String pattern = "###.##";
				public DecimalFormat format = new DecimalFormat(pattern);
		
			//GUI Component IVs:
				//Containers:
					private JFrame frame;
					private Container cp; //This will be the contentPane
					private JPanel homeScreen;
				//Basic Components:
					private JLabel lblGreeting;
					private JLabel lblBalance;
					private JLabel lblQuestion;
					private JLabel lblError;
					
					private JButton btnWithdraw;
					private JButton btnDeposit;
					private JButton btnChange;
					private JButton btnSignOut;
					
					private JSeparator separator;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public void main(String arg) throws IOException {
		if(arg.equals("signIn")){
		EventQueue.invokeLater(new Runnable() {
			Account window = new Account(Username);
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		}
		if(arg.equals("new")){
			EventQueue.invokeLater(new Runnable() {
				Account window = new Account(Username);
				public void run() {
					try {
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	//The Constructor for when an existing user Signs In
	public Account(String user) throws IOException {
		this.Username = user;
	
		this.file = new FileReader(user+ ".txt");
		this.in = new BufferedReader(file);
		
		this.Password = in.readLine();
		this.FirstName = in.readLine();
		this.LastName = in.readLine();
		this.Balance = Double.parseDouble(in.readLine());
		
		in.close();
		file.close();
		
		createGUI();
	}
	
	//The Constructor for when a user creates a new account
	public Account(String user, String first, String last, String pass) throws IOException{
		this.Username = user;
		this.FirstName = first;
		this.LastName = last;
		this.Password = pass;
		this.Balance = 0;
		
		this.newFile = new FileWriter(Username+".txt");
		this.out = new PrintWriter(newFile);
		
		out.println(Password);
		out.println(FirstName);
		out.println(LastName);
		out.println(Balance);
		out.println(Username);
		
		out.close();
		newFile.close();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void createGUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		cp = frame.getContentPane();
		cp.setLayout(null);
		
		homeScreen = new JPanel();
		homeScreen.setBounds(10, 5, 415, 250);
		homeScreen.setLayout(null);
		cp.add(homeScreen);
		
		createComponents();
		 createEvents();
	}

	private void createComponents(){
		
		lblGreeting = new JLabel("Welcome, " + FirstName + " " + LastName + "!");
		lblGreeting.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGreeting.setBounds(10,0,200,45);
		homeScreen.add(lblGreeting);
		
		lblBalance = new JLabel("Your current Balance is: $" + format.format(Balance));
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setFont(new Font("Tahom", Font.ITALIC, 14));
		lblBalance.setBounds(10,35,395,45);
		homeScreen.add(lblBalance);
		
		separator = new JSeparator();
		separator.setBounds(120, 75, 175, 5);
		homeScreen.add(separator);
		
		lblQuestion = new JLabel("What would you like to do?");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblQuestion.setBounds(10,80,395,30);
		homeScreen.add(lblQuestion);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(150,110,115,30);
		homeScreen.add(btnWithdraw);
		
		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(150,145,115,30);
		homeScreen.add(btnDeposit);
		
		btnChange = new JButton("Change Password");
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnChange.setBounds(150,180,115,30);
		homeScreen.add(btnChange);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.setBounds(150,215,115,30);
		homeScreen.add(btnSignOut);
		
		lblError = new JLabel("");
		lblError.setText("Error");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(160,180,115,30);
		lblError.setVisible(true);
		homeScreen.add(lblError);
		
	}
	
	public void createEvents(){
		//This Event is when the 'withdraw' button is pressed, will allow user
		//to enter the amount he would like to withdraw
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amount = JOptionPane.showInputDialog("How much would you like to withdraw?");
				double amnt = -(Double.parseDouble(amount));
				try {
					changeBal(amnt);
					lblError.setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		//This event is when the 'deposit' button is pressed, and will allow user
		//to enter amount he/she would like to withdraw
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String amount = JOptionPane.showInputDialog("How much would you like to Deposit?");
				double amnt = (Double.parseDouble(amount));
				try {
					changeBal(amnt);
					lblError.setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
                
                //This even is when the 'changePassword' button is pressed, and will allow user 
                //to change their password
                btnChange.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        String check = JOptionPane.showInputDialog("Enter your old password");
                        if(check.equals(Password)){
                            String new1 = JOptionPane.showInputDialog("Enter your desired password");
                            String new2 = JOptionPane.showInputDialog("Confirm your new passowrd");
                            if(new1.equals(new2))
                            	try {
                					changePass(new1);
                					lblError.setVisible(false);
                				} catch (IOException e) {
                					// TODO Auto-generated catch block
                					e.printStackTrace();
                				}
                            else{
                                System.out.println("Passwords Don't Match");
                                lblError.setBounds(160,180,175,30);
                                lblError.setVisible(true);
                            }
                        }
                    }
                });
                
                //This event is for the 'signOut' button, and will close the current account, and
                //return to the main menu/ sign in page
                btnSignOut.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        ATM newWin = new ATM();
                        frame.dispose();
                    }
                });
	}
	
	private void changeBal(double num) throws IOException{
		
		this.file = new FileReader(Username+".txt");
		this.in = new BufferedReader(file);
		
		String pass = in.readLine();
		String first = in.readLine();
		String last = in.readLine();
		String bal = in.readLine();
		String user = in.readLine();
		
		this.newFile = new FileWriter(Username+ ".txt");
		this.out = new PrintWriter(newFile);
		
		out.println(Password);
		out.println(FirstName);
		out.println(LastName);
		this.Balance = Balance + num;
		out.println(Balance);
		out.println(Username);
		
		this.lblBalance.setText("Your current Balance is: $" + format.format(Balance));
		
		
		out.close();
		newFile.close();
	}
	
private void changePass(String newPass) throws IOException{
		
		this.file = new FileReader(Username+".txt");
		this.in = new BufferedReader(file);
		
		String pass = in.readLine();
		String first = in.readLine();
		String last = in.readLine();
		String bal = in.readLine();
		String user = in.readLine();
		
		this.newFile = new FileWriter(Username+ ".txt");
		this.out = new PrintWriter(newFile);
		
		out.println(newPass);
		out.println(first);
		out.println(last);
		out.println(bal);
		out.println(user);
		
		
		out.close();
		newFile.close();
	}
}
