/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to show the interface after passed the current level.
 */

package river;

import javax.swing.*;
import java.awt.*;

public class WinPanel {

	private JPanel p;//to store another four panels which are store a label and three buttons
	private JLabel finalTime;//to show the final time about the passed level
	private JButton nextLevel;//to go to the next level
	private JButton restart;//to restart the current level
	private JButton BackToStartMenu;//back to the main menu 
	private JPanel w1;//to shore label finalTime
	private JPanel w2;//to shore nextLevel button
	private JPanel w3;//to shore restart button
	private JPanel w4;//to shore BackToStartMenu button
	
	/**
	 * This constructor is to initial the all components in this WinPanel without parameter
	 */
	public WinPanel()
	{
		p= new JPanel(new GridLayout(4,1));//set the panel's layout is GridLayout with 4 rows and 1 column 
		p.setPreferredSize(new Dimension(700, 750));//set the WinPanel fix the size of frame
		
		//initial the w1 panel and finalTime label
		w1 =new JPanel();
		w1.setPreferredSize(new Dimension(700, 187));//set the  appropriate size for w1 panel
		finalTime = new JLabel("final");
		finalTime.setFont(new Font("Arial",Font.BOLD,20));//set the label's font
		w1.add(finalTime);
		
		////initial the w2 panel and nextLevel button
		w2 =new JPanel();
		w2.setPreferredSize(new Dimension(700, 187));//set the  appropriate size for w3 panel
		nextLevel = new JButton("Next Level");
		nextLevel.setPreferredSize(new Dimension(150,50));//set the  appropriate size for nextLevel button
		w2.add(nextLevel);
		
		//initial the w3 panel and restart button
		w3 =new JPanel();
		w3.setPreferredSize(new Dimension(700, 188));//set the  appropriate size for w3 panel
		restart = new JButton("Restart");
		restart.setPreferredSize(new Dimension(150,50));//set the appropriate size for restart button
		w3.add(restart);
		
		//initial the w4 panel and BackToStartMenu button
		w4 =new JPanel();
		w4.setPreferredSize(new Dimension(700, 188));//set the  appropriate size for w4 panel
		BackToStartMenu = new JButton("Quit");
		BackToStartMenu.setPreferredSize(new Dimension(150,50));//set the appropriate size for BackToStartMenu button
		w4.add(BackToStartMenu);
		p.add(w1);
		p.add(w2);
		p.add(w3);
		p.add(w4);
	}
	
	/**
	 * this is to let bottom get the WinPanel
	 * @return JPanel WinPanel 
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
	 * this is to let bottom get the nextLevel button to go to next level
	 * @return JButton nextLevel 
	 */
	public JButton getNextLevel()
	{
		return this.nextLevel;
	}
	
	/**
	 * this is to let bottom get the restart button to restart the current level
	 * @return JButton restart
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
		return BackToStartMenu;
	}
	
}
