import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Board {

	//declare a multi dimensional array of square objects

	private static final int SIZE = 8;
	
	//private boolean mines = false;
	//declare an array of type Square named grids
	
	Square [][] grids;
	
	
	public Board ()
	{
		//add squares to the array 
		grids = new Square [SIZE][SIZE];
		
		for (int i =0; i < grids.length; i ++) {
			for (int j =0; j < grids[0].length; j ++) {
				grids[i][j] = new Square();
				
				
				// add listeners to "listen" for button events
				
				grids[i][j].addActionListener(
				        
				        new ActionListener()
				        {
				          /**
				           * Invoked when associated action is performed.
				           **/
				          public void actionPerformed( ActionEvent e )
				          {
				            // executed when the button is pressed
				            // here, we call a method to update the display
				        	  
				        	  
				        	  for( int row = 0 ; row < SIZE ; row ++ )
				        	  {
				        		  for( int col = 0 ; col < SIZE ; col ++ )
				        		  {
				        			  
				        			  // found which square is clicked on
				        			  if( e.getSource().equals(grids[row][col]) )
				        			  {
				        				  recurse (row, col);
				        				  // if mine, game over
				        				  if(grids[row][col].getIsMine() ==true){
				        					  System.out.println ("mine!");
				        					  gameLose () ;
				        					  return;
				        				  }
				        				  //else, game win
				        				  gameWin();
				        				  System.out.println ("test!");
				        				  //grids[row][col].setReveal();
				        			  }
				        		  }
				        	  }
				          } 
				        }
				);
			}
		}
		//set mines 
		setMines();
		for(int i =0; i < grids.length; i ++)
			{
				for(int j =0; j < grids[0].length; j ++)
				{  
			    //check num of adjacent mines	
				checkAdjMines(i,j);
				}
			}

	}
	
	//method to disable the game
	public void disable(){
		for( int i = 0 ; i < SIZE ; i++ )
  	  {
			System.out.println("\ndisabling column " + i + "\n");
  		  for( int j = 0 ; j < SIZE ; j ++ )
  		  {
  			  System.out.print(" " + j);
  			  //reveal all squares
  			  grids[i][j].revealSquare();
  			  
  			
  		  }
  	  }
	}
	
	//method to recurse
	public void recurse (int x, int y)
	{

		//don't recurse if square clicked is out of bound
		if (nOOB(x,y) ==false){
			return;
		}
		
		//don't recurse if square clicked is already revealed
		if (grids[x][y].getReveal() == true) {
			//System.out.println ("isrevealed");

			return;
		}
		
		//set reveal to true and reveal the square
		grids[x][y].setReveal() ;
		grids[x][y].revealSquare();
		

		//don't recurse if there are mines around the square clicked
		if (grids[x][y].getNumberOfMines() !=0) {
			return;
		}
		//recurse if there is 0 mine at and around the square clicked
		if (grids[x][y].getNumberOfMines() ==0  && !grids[x][y].getIsMine()) {
			for (int i=x-1; i<=x+1; i ++){
				for (int j=y-1; j<=y+1; j++){
					//System.out.println("rcalling recurse for " + x + "," + y);
					recurse(i,j) ;
					
					//System.out.println ("recurse works");
					
				}
			}	
			return;
		} 
		return;
		
	}
	
	//check when game loses
	public boolean gameLose() {
		
		for (int i = 0; i < SIZE; i ++) {
			for (int j = 0; j < SIZE; j++) {
				//game loses when a mine is revealed
				if ( grids[i][j].getReveal() == true && grids[i][j].getIsMine() == true ) {
					System.out.println("game lose is true");
					//show a dialog when game loses
		   	    	JOptionPane.showMessageDialog(null, "Sorry, you hit a bomb :(");
		   	    	//disable the game 
		   	    	disable();

					return true;
				}
			}
		}
		return false;
				
		}
	
	//check when user wins
	public boolean gameWin() {
		for (int i = 0; i < SIZE; i ++) {
			for (int j = 0; j < SIZE; j++) {
				//game win when all the mines is not revealed
				if (grids[i][j].getIsMine() == false && grids[i][j].getReveal() == false  )
					
					return false;
			}
		}
		//show a dialog when game win
		JOptionPane.showMessageDialog(null, "Congrats, you won!");
		//and disable the game
		disable();
		return true;
	
	}
	
	//setMinesto random places
	public void setMines()
	{
		//set 8 or less than 8 mines
		for (int i = 0 ; i<SIZE; i++) {
			//pick 2 random number to be the row and column number
			int row = (int) Math.floor(Math.random()* SIZE);
			int column = (int) Math.floor(Math.random()* SIZE);
			//assign mines to those random squares
			grids[row][column].setMine();
		}
	}

	//check whether the coordinate is not out of bound
		
	public boolean nOOB (int x, int y) {
		//check all the positions around the board: 
		//> x, > y, < x, < y -> out of bound
		if (x>=grids.length) {
			return false;
		}
		else if (y>= grids[0].length){
			return false;
		}
		else if (x<0){
			return false;
		}
		else if (y<0){
			return false;
		}
		//other than that, it's not out of bound
		return true;
	}
	
	//check number of adjacent mines	
	public int checkAdjMines(int x, int y)
	{
		//declare a variable named numMines of type int and assign the value 0
		int numMines = 0;
		
		//if there is mine, return -1
		if( grids[x][y].getIsMine() == true )
		{
			return -1 ;
		}
		
		//check through 8 positions around clicked mine
		for (int i = x-1; i <= x+1; i ++){
			for (int j = y-1; j <= y+1; j++) {
				//check only if it's not the mine clicked
				if( ! (i == 0 && j == 0) )
				{
					//and only if those 8 positions are not out of bound
					if ( nOOB(i,j) == true) {
						//if there are mines, update numMines
						if (grids[i][j].getIsMine() == true ){
							numMines ++;
						}	
					}
				}
			}
		}
		
		//use setter to set num of mines to the square clicked
		grids[x][y].setNumMines (numMines);
		return numMines ;
	}
	

}