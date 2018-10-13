/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to build the bottom of game stage just like Game arena.
 *  		   The game uses buttons to change the image when any button clicked.
 */

package river;

import javax.swing.*;
import java.awt.*;

public class GameBottom {

	private JPanel p;//just like game arena ,to store the buttons
	private JButton[][] button = new JButton[13][9];//the game buttons are put in 13 rows * 9 columns
	private String[][] string = new String[13][9];
	ImageIcon water1;//get the water1 image
	ImageIcon bank2;//get the bank2 image
	ImageIcon bank1;//get the bank1 image
	
	public GameBottom()
	{
		p= new JPanel(new GridLayout(13,9));//set the panel to store button
		p.setPreferredSize(new Dimension(540,650));
		p.setBorder(null);//set panel without border
		//ImageIcon water1 = new ImageIcon("water1.jpg");
		//get the water1 image and let the image fix the button
		water1 = new ImageIcon("water1.jpg");
	    Image temp1 =water1.getImage().getScaledInstance(60, 50, water1.getImage().SCALE_AREA_AVERAGING);
	    water1 = new ImageIcon(temp1);
	    
	    //ImageIcon bank2 = new ImageIcon("bank2.jpg");
	    //get the bank2 image and let the image fix the button
	    bank2 = new ImageIcon("bank2.jpg");
		Image temp3 =bank2.getImage().getScaledInstance(60, 50, bank2.getImage().SCALE_AREA_AVERAGING);
		bank2 = new ImageIcon(temp3);
		
		//ImageIcon bank1 = new ImageIcon("bank1.jpg");
		//get the bank1 image and let the image fix the button
		bank1 = new ImageIcon("bank1.jpg");
		Image temp2 =bank1.getImage().getScaledInstance(60, 50, bank1.getImage().SCALE_AREA_AVERAGING);
		bank1 = new ImageIcon(temp2);
		
		//initial the buttons and  name each button
	    for(int i =0 ;i<13;i++)
	    {
	        for(int k=0;k<9;k++)
	        {
	            if(i==0)
	            {
	                button[i][k]= new JButton(bank2);
	                string[i][k]="bank2";
	            }
	            else if(i==12)
	            {
	                button[i][k]= new JButton(bank1);
	                string[i][k]="bank1";
	            }
	            else
	            {
	                button[i][k]= new JButton(water1);
		            string[i][k]="water1";
	            }
	            //button[i][k].setPreferredSize(new Dimension(60,50));
	            //set the buttons without border and remove the gap between each button
	            button[i][k].setBorderPainted(false);
	        	button[i][k].setBorder(null);
	        	button[i][k].setMargin(new Insets(0,0,0,0));
	        }
	    }
	    //add buttons into panel
	    for(int i =0 ;i<13;i++)
	    {
	        for(int k=0;k<9;k++)
	        {
	        	p.add(button[i][k]);
	        }
	    }
	}
	
	/**
	 * initial the gameBottom by setting all buttons's images and names into middle is water1, top is bank2 and bottom is bank1
	 */
	public void initial()
	{
		/*
	    Image temp1 =water1.getImage().getScaledInstance(60, 50, water1.getImage().SCALE_AREA_AVERAGING);
	    water1 = new ImageIcon(temp1);
	    
	    
		Image temp3 =bank2.getImage().getScaledInstance(60, 50, bank2.getImage().SCALE_AREA_AVERAGING);
		bank2 = new ImageIcon(temp3);
		
		
		Image temp2 =bank1.getImage().getScaledInstance(60, 50, bank1.getImage().SCALE_AREA_AVERAGING);
		bank1 = new ImageIcon(temp2);*/
		/*ImageIcon water1 = new ImageIcon("water1.jpg");
		ImageIcon bank2 = new ImageIcon("bank2.jpg");
		ImageIcon bank1 = new ImageIcon("bank1.jpg");*/
	    for(int i =0 ;i<13;i++)
	    {
	        for(int k=0;k<9;k++)
	        {
	            if(i==0)
	            {
	                this.setButtonPicture(button[i][k], bank2);
	                string[i][k]="bank2";
	            }
	            else if(i==12)
	            {
	            	this.setButtonPicture(button[i][k], bank1);
	                string[i][k]="bank1";
	            }
	            else
	            {
	            	this.setButtonPicture(button[i][k], water1);
		            string[i][k]="water1";
	            }
	        }
	    }
	}
	
	/**
	 * this is to let bottom get the p to show the game bottom
	 * @return JPanel p
	 */
	public JPanel getp()
	{
		return p;
	}
	
	/**
	 * this is to let bottom get the button button to back to the the menu
	 * @return JButton[][] BackToStartMenu 
	 */
	public JButton getButton(int m,int n)
	{
	    return button[m][n];
	}
	
	/**
	 * set the button's image
	 * @param JButton b
	 * @param ImageIcon ii
	 */
	public void setButtonPicture(JButton b,ImageIcon ii)
	{
	    Image temp =ii.getImage().getScaledInstance(60,50,ii.getImage().SCALE_AREA_AVERAGING);
	    ii = new ImageIcon(temp);
	    b.setIcon(ii);
	    b.setBorderPainted(false);
	}
	
	/**
	 * set the button's name
	 * @param m
	 * @param n
	 * @param name
	 */
	public void setButtonName(int m,int n,String name)
	{
	    string[m][n]=name;
	}
	
	/**
	 * get the button's name
	 * @param m
	 * @param n
	 * @return
	 */
	public String getButtonName(int m,int n)
	{
	    return string[m][n];
	}
	
	/**
	 * get the button's button
	 * @return
	 */
	public JButton[][] getButtons() {
		// TODO Auto-generated method stub
		return button;
	}	
}
