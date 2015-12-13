
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.javafx.tk.Toolkit;


//mine
//reveal
//num adj

public class Square extends JButton {
	
	/**
	 * 
	 */
	
	private boolean isMine; //check if there is mine 
	private int adjacentMines; //how many mines around that square
	private boolean reveal; //if clicked or not
	
	public Square () {
		adjacentMines = 0; //set the num of adj mines = 0
		reveal = false; //at the beginning, reveal = false
		isMine = false; // at the beginning, there is no mine
	}

	public void setMine()
	{
		//setter to set isMine = true
		isMine = true;
	}
	
	public boolean getIsMine()
	{
		//getter to get isMine 
		return isMine;
	}

	public void setReveal()
	{
		
		//setter to set reveal = true
		reveal = true;
	}
	
	public boolean getReveal()
	{
		//getter to get reveal
		return reveal;
	}
	
	public void setNumMines (int mines)
	{
		//setter to set number of adjacentMines
		adjacentMines = mines;
	}
	
	public int getNumberOfMines()
	{
		//getter to get adjacentMines
		return adjacentMines;
	}
	
	//method to reveal the square that has been clicked
	public void revealSquare()
	{
		//when clicked, boolean reveal = true
		reveal = true;
		//and disable the square so that user cannot click on again
		setEnabled(false);
		
		//set * if bomb
		if (isMine == true){
			
			setText("*");
			
			//ImageIcon mineImage = new ImageIcon("mineImage.jpg");
			//setIcon(mineImage);
			//this = new JButton(mineImage);
			//System.out.println(mineImage==null);
			//System.out.println ("Image set " + mineImage);
		}
		
		//other than that
		else {
			//reveal the number of adjacentMines on the square
			setText( Integer.toString( adjacentMines  ) );
		}
	}
	


}
