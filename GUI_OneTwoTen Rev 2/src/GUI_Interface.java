import acm.program.*;
import acm.graphics.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class GUI_Interface extends Program{
	
	/**
	 * Variable Declarations comprised of JLabel, JTextField
	 * JButton types.  Two objects created as well, one
	 * of type OneTwoTen designed to handle user interaction.
	 * The Second object of type GImage used to handle Winning
	 * image for display. 
	 */
	
	JLabel gameTitle, tokenImage, pileLabel, playerLabel, orLabel, chooseLabel, winLabel, noteLabel;

	JTextField amtField, playerField;

	JButton goButton, addOne, addTwo;

	OneTwoTen game;
	GImage youWon;
	ImageIcon token;
	
	int playedAmount;
	
	/**
	 * The "GUI_Interface" Constructor initiates new
	 * OneTwoTen object-game. 
	 */
	
	public GUI_Interface(){
		
		game =  new OneTwoTen();
		
	}// End of Constructor
	
	
	/**
	 * The "init" method will be used to handle the 
	 * application canvas display.  Will comprise of 
	 * setting canvas color, size and player's text 
	 * field.  
	 */
	
	public void init(){
		
		// Opening gaming console
		GCanvas console = new GCanvas();
		this.add(console);
		console.setLayout(null);
		
		// Setting console size color and label
		setSize(450,450);
		console.setBackground(Color.BLACK);
		gameTitle = new JLabel("ONE two TEN- FIRST!    ",JLabel.CENTER); 
		Font textFont = new Font("Comic Sans MS", Font.ITALIC,25 + Font.BOLD);
		gameTitle.setForeground(Color.red);
		gameTitle.setFont(textFont);
		console.add(gameTitle,80,0);
		
		// Setting console pile-total token field text
		ImageIcon token = new ImageIcon("silver-icon-45779.png");
		tokenImage = new JLabel(token);
		console.add(tokenImage,100, 70);
		pileLabel = new JLabel("PILE = 0 'TOKENS' ",JLabel.CENTER);      
		Font pileText = new Font("Comic Sans MS", Font.ITALIC,15 + Font.BOLD);
		pileLabel.setForeground(Color.BLUE);
		pileLabel.setFont(pileText);
		console.add(pileLabel, 140, 70); 
		
		// Setting player's field which will run hidden to keep track of players
		playerField = new JTextField("1"); 
		playerField.setVisible(false);
		console.add(playerField, 10, 200);
		
		// Setting Who's Turn Label
		playerLabel = new JLabel("YOU START, PLAYER 1  ");
		Font bigText = new Font("Comic Sans MS", Font.BOLD, 24);
		playerLabel.setForeground(Color.white);
		playerLabel.setFont(bigText);
		console.add(playerLabel, 75, 130);
		
		// Setting up Choose Label
		chooseLabel = new JLabel("CHOOSE "); 
		chooseLabel.setForeground(Color.ORANGE);
		chooseLabel.setFont(pileText);
		console.add(chooseLabel,175,190);
		
		// Setting up "goButton" and replacing with image   
		goButton = new JButton("");
		goButton.setActionCommand("go");
		ImageIcon Gogo = new ImageIcon("sketchplay.png"); 
		goButton.setIcon(Gogo);
		console.add(goButton, 150, 300);
		
		// Setting up "addOne" button 
		int one = 1;
		addOne = new JButton(String.valueOf(one)+ " Token");
		addOne.setSize(80,50);
		Font buttonText = new Font("Comic Sans MS", Font.BOLD, 13);
		addOne.setFont(buttonText);
		addOne.setForeground(Color.ORANGE);
		addOne.setBackground(Color.GREEN);
		addOne.setActionCommand("One");
		console.add(addOne, 110, 220);
		
		// Setting up "addTwo" button
		int two =2;
		addTwo = new JButton(String.valueOf(two)+" Tokens");
		addTwo.setSize(80,50);
		addTwo.setFont(buttonText);
		addTwo.setForeground(Color.ORANGE);
		addTwo.setBackground(Color.GREEN);
		addTwo.setActionCommand("Two");
		console.add(addTwo,230,220);
		
		// Setting up "Or" label to complete choice
		orLabel = new JLabel("OR "); 
		orLabel.setForeground(Color.BLUE);
		orLabel.setFont(pileText);
		console.add(orLabel,198,230);
		
		// Setting and keeping dormant winner's banner
		winLabel = new JLabel("You Won,             ");         
		Font bigText2 = new Font("Comic Sans MS", Font.BOLD, 34);
		winLabel.setFont(bigText2);
		winLabel.setVisible(false); 
		console.add(winLabel, 25, 100);
		
		// Setting and keeping dormant winning image
		youWon = new GImage("winner2.jpg");                 
		youWon.setVisible(false);
		console.add(youWon,-20, -70);
		
		// Setting note label 
		noteLabel = new JLabel("**** Note: First to get Total 10 Tokens in Pile Wins. ****");
		noteLabel.setForeground(Color.red);
		noteLabel.setVisible(true);
		console.add(noteLabel, 50, 430);
		 
		// Setting Buttons to actively listen for an event
		addActionListeners();   
		
		console.revalidate();
	}
	
	/**
	 * Action Performed Method is taking events from go button
	 * and making script updates where needed to keep track of
	 * players turn and summing pile tokens.   
	 */
	
	public void actionPerformed(ActionEvent event){
		
		String Cmd = event.getActionCommand();
		
		// Listening for add one/two button event
		if(Cmd.equals("One")){
			playedAmount = 1;	
		}
		if (Cmd.equals("Two")){
			playedAmount = 2;
		}
		
		// Listening for go button event
		if(Cmd.equals("go")){
			
			// Calling upon move method to track player and pile sum
			int p = Integer.parseInt(playerField.getText());       
			game.move(p, playedAmount);
			
			// Updating Labels accordingly
			pileLabel.setText("PILE = " + game.getPile() + " 'TOKENS' ");                   
			playerLabel.setText("YOUR TURN, PLAYER "+game.whoseTurn());
			playerField.setText(""+game.whoseTurn());
			
			// Testing for a game over condition
			if(game.isGameOver()){
				int winner = game.whoWon();
				winLabel.setText("You won Player "+winner+"!");   // Printing Winner on Screen
				winLabel.setVisible(true);
				
				// Setting dormant console game buttons and field labels
				playerLabel.setVisible(false);                   
				pileLabel.setVisible(false);                      
				goButton.setVisible(false);                       
				addOne.setVisible(false);
				addTwo.setVisible(false);
				orLabel.setVisible(false);
				chooseLabel.setVisible(false);
				noteLabel.setVisible(false);
				tokenImage.setVisible(false);
				
				// Displaying winning banner
				youWon.setVisible(true);                          
			}
		}
	}

}// end of GUI class
