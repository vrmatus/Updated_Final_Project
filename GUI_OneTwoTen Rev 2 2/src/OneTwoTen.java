
public class OneTwoTen {
	
	private int pile;
	private int turn;
	
	public OneTwoTen(){
		pile = 0;
		turn = 1;
	}
	
	/**
	 * Player's turn.
	 * Shows player's number and amount to add to the pile.
	 * This will return boolean true if move was within game guidelines.
	 * Will return boolean false if followed.
	 */
	
	public boolean move(int player, int amount){
		if (player==turn){
			
			if(amount>=1 && amount <=2 && amount<=(10-pile)){
				
				pile = pile + amount;  // updating pile sum
				
				if(turn==1){           // updating player's turn
					turn=2;
				}                      
				else{
					turn=1;
				}
				return true;           // if no errors = true
				
			}// end of conditional amount if
			
			else{
				return false;          // if errors = false
			}// end of else
			
		}// end of player if
		
		else{
			return false;
		}
		
	}// end of move method
	
	/**
	 * Is game over.
	 * Will return true if game has been completed
	 */
	
	public boolean isGameOver(){
		return(pile ==10);
	}
	
	/**
	 * Who won.
	 * Will return 1 if player1 wins or 2 if player2 wins or 
	 * a zero if the game is still continuing. 
	 */
	
	public int whoWon(){
		if(!isGameOver()){
			return 0;
		}
		else if(turn == 1){
			return 2;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * Whose turn is it.
	 * Returns 1 or 2
	 */
	
	public int whoseTurn(){
		return turn;
	}
	
	/**
	 * Returns current amount in the pile
	 */
	
	public int getPile(){
		return pile;
	}
	
}// end of class



