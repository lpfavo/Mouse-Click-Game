/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to show the interface to let the player choice the level.
 */

package river;

import java.awt.*;
import javax.swing.*;

public class ChoicePanel {

	private JPanel p;//to store another two panels which are store a label,a textfield and five buttons
	private JPanel p1;//to store another two panels with a label and a textfield
	private JPanel p11;//to store a label
	private JPanel p12;//to store a textfield
	private JPanel p2;//to store five buttons
	private JLabel ChoiceLabel;//to show a title of the ChoicePanel
	private JLabel warning;//to show some warning about inputting a non-empty name 
	private JButton[] b=new JButton[4];//four levels button
	private JButton back;//to back to menu
	private boolean[] passed=new boolean[4];//record whether passed the corresponding level to decide whether have a chance to play the clicked level 
	
	/**
	 * This constructor is to initial the all components in this ChoicePanel without parameter
	 */
	public ChoicePanel()
	{
		p= new JPanel(new GridLayout(2,1));//set the panel's layout is GridLayout with 2 rows and 1 column to store p1 and p2
		p.setPreferredSize(new Dimension(700, 750));//set the ChoicePanel fix the size of frame
		
		//initial the p1 panel to store p11 and p12 Panel
		p1=new JPanel(new GridLayout(2,1));
		p1.setPreferredSize(new Dimension(700,350));//set the appropriate size for p1 panel
		
		//initial the p11 panel to store ChoiceLabel
		p11=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the p11's layout is FlowLayout to center
		p11.setPreferredSize(new Dimension(700,175));//set the appropriate size for p11 panel
		ChoiceLabel = new JLabel("Select a Level");
		ChoiceLabel.setPreferredSize(new Dimension(300,175));//set the appropriate size for the label
		ChoiceLabel.setFont(new Font("Arial",Font.BOLD,40));//set the label's font
		
		p12=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the p12's layout is FlowLayout to center
		p12.setPreferredSize(new Dimension(700,175));//set the appropriate size for p12 panel
		warning = new JLabel("You need to pass the fitst or lower levels so that you can play higher levels");
		warning.setFont(new Font("Arial",Font.BOLD,15));//set the label's font
		warning.setPreferredSize(new Dimension(600,175));//set the appropriate size for the label
		
		//initial the p2 panel to store levels buttons and back button. And also each level 's boolean value of passed 
		p2= new JPanel();
		p2.setPreferredSize(new Dimension(700,400));//set the appropriate size for p2 panel
		for(int i=0;i<b.length;i++)
		{
			b[i]=new JButton("Level"+(i+1));
			b[i].setPreferredSize(new Dimension(150,50));//set the appropriate size for buttons
			p2.add(b[i]);
			passed[i]=false;
		}
		//set the higher level's buttons be transparent to tell player it is useless to click is he passes the previous level
		b[1].setContentAreaFilled(false);
		b[2].setContentAreaFilled(false);
		b[3].setContentAreaFilled(false);
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(150,50));//set the appropriate size for the button
		
		p2.add(back);
		p11.add(ChoiceLabel);
		p12.add(warning);
		p1.add(p11);
		p1.add(p12);
		p2.add(p1);
		p.add(p1);
		p.add(p2);
	}
	
	/**
	 * this is to let bottom get the ChoicePanel
	 * @return JPanel ChoicePanel 
	 */	
	public JPanel getp()
	{
		return p;
	}
	
	/**
	 * this is to let bottom get the level buttons to let player to choose the level
	 * @return JButton[] b 
	 */
	public JButton[] getb()
	{
		return b;
	}
	
	/**
	 * this is to let bottom get the back button to back to the the menu
	 * @return JButton back 
	 */
	public JButton getBack()
	{
		return back;
	}
	
	/**
	 * this is to let bottom get the passed boolean value to decide the player can play the level
	 * @return boolean[] passed 
	 */
	public boolean[] getpassed()
	{
		return passed;
	}
	
	/**
	 * this is to set the passed boolean value 
	 */
	public void setPassed(int i,boolean p)
	{
		passed[i]=p;
	}
	public void setButtonContentAreaFilled(int i)
	{
		b[i].setContentAreaFilled(true);
	}

}
