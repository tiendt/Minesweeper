
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;


//import java.awt.Color;


public class MineSweeperGUI extends JPanel{
	//private Square squares[][];
	  
	private JPanel gridBoard;
	private Board myBoard;

     //Initializes the grid.
 
	public MineSweeperGUI()
	{
		  //assign a new Board class to the myBoard variable
		  myBoard = new Board();
	      //set up the frame
		  setupFrame();     
	}
	
	 public static void main( String[] args )
	  {
		 // Create a new big grid instance.
	        MineSweeperGUI newBoard = new MineSweeperGUI();
	  }
     // Creates a frame that will display the grid of little grids.
	 private void setupFrame()
	 {
		// Create the frame and set its size.
	    JFrame mineSweeper = new JFrame( "MineSweeper" );
	    mineSweeper.setSize(new Dimension (500, 500 ));	  
	    mineSweeper.setLayout( new BorderLayout());
	    
	    // Exit the application when the user closes the frame.
	    mineSweeper.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // Create the grid panel and add it.
        mineSweeper.getContentPane().add( createBoard() );
        
	    // Display the frame.
	    mineSweeper.setVisible( true );
	  }
	 
	 private JPanel createBoard()
	 {
		 gridBoard = new JPanel();
		 
		 // Set the panel to be set like a grid of 8x8-- each thing we add below
	     // will be placed in a cell, starting with the upper left and moving
	     // left-to-right, top-to-bottom.
	 
		 gridBoard.setLayout( new GridLayout(8, 8) );
		 //set background to pink
		 gridBoard.setBackground(Color.pink);
		 
	        // Iterate through each row and column of the big grid.
	        for( int i = 0; i < 8; i++ ) {
	            for( int j = 0; j < 8; j++ )
	            {
	            	//add each square to frame
	                gridBoard.add( myBoard.grids[i][j] );  
	            }
	        }
	      
	        // Return the newly made board
	        return gridBoard;

	 
	 }
	 
}
	 	



