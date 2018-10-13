/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to show the winning interface after passed all levels.
 */

package river;

import java.awt.*;
import javax.swing.*;

public class FinalWinPanel {

	private JPanel p;//to store another two panels which are store two labels and two buttons
	private JPanel f1;//to store f11 and f12 panels
	private JPanel f11;//to store finalText label
	private JPanel f12;//to store finalTime label
	private JPanel f2;//to store f21 and f22 panels
	private JPanel f21;//to store restart button
	private JPanel f22;//to store BackToStartMenu button
	private JLabel finalText;//to show the congratulations words
	private JLabel finalTime;//to show the final level's time
	private JButton restart;//to restart the current game
	private JButton BackToStartMenu;//back to the menu

	/**
	 * This constructor is to initial the all components in this FinalWinPanel without parameter
	 */
	public FinalWinPanel()
	{
		p= new JPanel(new GridLayout(2,1));//set the panel's layout is GridLayout with 2 rows and 1 column 
		p.setPreferredSize(new Dimension(700,750));//set the WinPanel fix the size of frame
		
		f1 =new JPanel(new GridLayout(2,1));//set the panel's layout is GridLayout with 2 rows and 1 column 
		f1.setPreferredSize(new Dimension(700, 375));//set the appropriate size for f1 panel
		
		f11=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the f11's layout is FlowLayout to center
		f11.setPreferredSize(new Dimension(700, 188));//set the appropriate size for f11 panel
		finalText=new JLabel("Congratulation! You passed all levels");
		finalText.setFont(new Font("Arial",Font.BOLD,35));//set the label's font
		f11.add(finalText);
		
		f12=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the f11's layout is FlowLayout to center
		f12.setPreferredSize(new Dimension(700, 187));//set the appropriate size for f12 panel
		finalTime = new JLabel("final");
		finalTime.setFont(new Font("Arial",Font.BOLD,20));//set the label's font
		f12.add(finalTime);
		f1.add(f11);
		f1.add(f12);
		p.add(f1);
		
		f2 =new JPanel(new GridLayout(2,1));//set the panel's layout is GridLayout with 2 rows and 1 column 
		f2.setPreferredSize(new Dimension(700, 375));//set the appropriate size for f2 panel
		f21 =new JPanel(new FlowLayout(FlowLayout.CENTER));//set the f21's layout is FlowLayout to center
		f21.setPreferredSize(new Dimension(700, 178));//set the appropriate size for f21 panel
		f22 =new JPanel(new FlowLayout(FlowLayout.CENTER));//set the f21's layout is FlowLayout to center
		f22.setPreferredSize(new Dimension(700, 188));//set the appropriate size for f22 panel
		
		restart = new JButton("Restart");
		restart.setPreferredSize(new Dimension(150,50));//set the appropriate size for restart button
		f21.add(restart);
		
		BackToStartMenu = new JButton("Back to Menu");
		BackToStartMenu.setPreferredSize(new Dimension(150,50));//set the appropriate size for BackToStartMenu button
		f22.add(BackToStartMenu);
		f2.add(f21);
		f2.add(f22);
		p.add(f2);
	}
	
	/**
	 * this is to let bottom get the FinalWinPanel when passed the final level
	 * @return JPanel FinalWinPanel 
	 */
	public JPanel getp()
	{
		return p;
	}
	
	/**
	 * this is to let bottom get the finalTime label
	 * @return JLabel finalTime 
	 */
	public JLabel getFinalTime()
	{
		return this.finalTime;
	}
	
	/**
	 * this is to let bottom get the restart button to restart the current level
	 * @return restart button
	 */
	public JButton getRestart()
	{
		return this.restart;
	}
	
	/**
	 * this is to let bottom get the BackToStartMenu button to back to the the menu
	 * @return JButton BackToStartMenu 
	 */
	public JButton getBackToStartMenu()
	{
		return this.BackToStartMenu;
	}
}
