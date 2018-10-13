/**
 * Author:Yongdi Liu  
 * Student ID: 16723023
 * Date:May 30th,2018
 * Description:This class is to show the stop interface after Click the stop button when playing the game.
 */

package river;
import java.awt.*;
import javax.swing.*;

public class StopPanel {

	private JPanel p;//to store another four panels which are store a label and three buttons
	private JLabel currentTime;//to show the current time about the passed level  
	private JButton restart;//to restart the current level
	private JButton continueButton;//to continue the current level
	private JButton BackToStartMenu;//back to the main menu
	private JPanel s1;//to shore label currentTime
	private JPanel s2;//to shore continueButton button
	private JPanel s3;//to shore restart button
	private JPanel s4;//to shore BackToStartMenu button
	
	/**
	 * This constructor is to initial the all components in this StopPanel without parameter
	 */
	public StopPanel()
	{
		p= new JPanel(new GridLayout(4,1));//set the panel's layout is GridLayout with 4 rows and 1 column
		p.setPreferredSize(new Dimension(700, 750));//set the StopPanel fix the size of frame
		
		//initial the s1 panel and currentTime label
		s1 =new JPanel();
		s1.setPreferredSize(new Dimension(700,187));//set the appropriate size for s1 panel
		currentTime = new JLabel("Current Time");
		currentTime.setFont(new Font("Arial",Font.BOLD,20));//set the label's font
		s1.add(currentTime);
		
		//initial the s2 panel and continueButton button
		s2 =new JPanel();
		s2.setPreferredSize(new Dimension(700,187));//set the  appropriate size for s2 panel
		continueButton= new JButton("Continue");
		continueButton.setPreferredSize(new Dimension(150,50));//set the appropriate size for continueButton button
		s2.add(continueButton);
		
		//initial the s3 panel and restart button
		s3 =new JPanel();
		s3.setPreferredSize(new Dimension(700,188));//set the appropriate size for s3 panel
		restart =new JButton("Restart");
		restart.setPreferredSize(new Dimension(150,50));//set the appropriate size for restart button
		s3.add(restart);
		
		
		//initial the s4 panel and BackToStartMenu button
		s4 =new JPanel();
		s4.setPreferredSize(new Dimension(700,188));//set the appropriate size for s4 panel
		BackToStartMenu= new JButton("Back To Start Menu");
		BackToStartMenu.setPreferredSize(new Dimension(150,50));//set the appropriate size for BackToStartMenu button
		s4.add(BackToStartMenu);
		
		p.add(s1);
		p.add(s2);
		p.add(s3);
		p.add(s4);
		
		
	}
	
	/**
	 * this is to let bottom get the StopPanel
	 * @return JPanel StopPanel 
	 */
	public JPanel getp()
	{
		return p;
	}
	
	/**
	 * this is to let bottom get the BackToStartMenu button to restart the current level
	 * @return JButton BackToStartMenu 
	 */
	public JButton getRestart()
	{
		return this.restart;
	}
	
	/**
	 * this is to let bottom get the continueButton button to continue the current level
	 * @return JButton continueButton 
	 */
	public JButton getContinueButton()
	{
		return this.continueButton;
	}
	
	/**
	 * this is to let bottom get the continueButton button to show the current level's time
	 * @return JLabel currentTime 
	 */
	public JLabel getCurrentTime()
	{
		return this.currentTime;
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
