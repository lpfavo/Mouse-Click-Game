/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to show the name inputing interface to input the player name 
 * 				to record the time mapping the responding name.
 */

package river;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class NamePanel implements DocumentListener{

	private JPanel namePanel;//to store another three panels which are store two labels and two buttons
	private JPanel p1;//to shore p11 and p12 panel to store title label and warnning label
	private JPanel p11;//to store title label
	private JPanel p12;//to store warnning label
	private JPanel p2;//to store input textfield
	private JPanel p3;//to store two buttons

	private JLabel label;//show the title of the NamePanel
	private JLabel warning;//to show some warning when input the name
	private JTextField text;//the area to input the name
	private JButton sure;//make sure the player had already input the name
	private JButton back;//back to menu
	private String name="";//initial the name and record the name
	
	/**
	 * This constructor is to initial the all components in this NamePanel without parameter
	 */
	public NamePanel()
	{
		namePanel=new JPanel(new GridLayout(3,1));//set the panel's layout is GridLayout with 3 rows and 1 column to store p1,p2 and p3 panels 
		namePanel.setPreferredSize(new Dimension(700,750));//set the NamePanel fix the size of frame
		
		p1= new JPanel(new GridLayout(2,1));//set the panel's layout is GridLayout with 2 rows and 1 column to store p11 and p22 panels
		p1.setPreferredSize(new Dimension(700,250));//set the appropriate size for p1 panel
		
		p11=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the p11's layout is FlowLayout to center
		p11.setPreferredSize(new Dimension(700,125));//set the appropriate size for p11 panel
		label=new JLabel("Please input your name:");
		label.setFont(new Font("Arial",Font.BOLD,30));//set the label's font
		p11.add(label);
		
		p12=new JPanel(new FlowLayout(FlowLayout.CENTER));//set the p12's layout is FlowLayout to center
		p12.setPreferredSize(new Dimension(700,125));//set the appropriate size for p12 panel
		warning=new JLabel("You cannot input an empty name,or you can not enter the game!");
		warning.setFont(new Font("Arial",Font.BOLD,20));//set the label's font
		p12.add(warning);
		
		p1.add(p11);
		p1.add(p12);
		namePanel.add(p1);
		
		p2=new JPanel();//initial the p2 panel
		p2.setPreferredSize(new Dimension(700,250));//set the appropriate size for p2 panel
		text=new JTextField();
		text.setFont(new Font("Arial",Font.BOLD,25));//set the textfield's font
		text.setPreferredSize(new Dimension(600,40));//set the appropriate size for textfield
		p2.add(text);
		namePanel.add(p2);

		p3= new JPanel(new FlowLayout(FlowLayout.CENTER));//set the p3's layout is FlowLayout to center
		p3.setPreferredSize(new Dimension(700,250));//set the appropriate size for p3 panel
		sure=new JButton("Sure");
		sure.setPreferredSize(new Dimension(150,50));//set the appropriate size for sure button
		back=new JButton("Back");
		back.setPreferredSize(new Dimension(150,50));//set the appropriate size for back button
		p2.add(sure);
		p2.add(back);
		namePanel.add(p2);
		text.getDocument().addDocumentListener(this);//set DocumentListener to text to get the name
		
	}
	
	/**
	 * this is to let bottom get the namePanel
	 * @return JPanel namePanel 
	 */
	public JPanel getNamePanel()
	{
		return namePanel;
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
	 * this is to let bottom get the sure button to get the name and go to the ChoicePanel
	 * @return JButton sure 
	 */
	public JButton getSure()
	{
		return sure;
	}
	
	/**
	 * this is to  get the warning label
	 * @return JLabel warning
	 */
	public JLabel getwarn()
	{
		return warning;
	}
	
	/**
	 * this is to get the current player name
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * this is to get the player name and store into the name variable
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		try{
				String s=e.getDocument().getText(e.getOffset(),e.getLength());	
				name=name+s;
			}catch(BadLocationException xe){
				System.out.println(xe.getMessage());
			}
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
