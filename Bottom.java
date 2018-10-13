/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to manage whole game interface panelby using the CardLayout to let each area can change to the correct panel
 */

package river;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Bottom extends JPanel implements MouseListener{

	private JFrame bottom;//the frame is to store the panel hold 
	private JPanel hold;//the hold JPanel is to store 7 panels:menupanel, namepanel, choicename, game panel, stop panel, winpanel, finalwinpanel. They arrange by CardLayout
	private CardLayout CardHold;//set the hold JPanel's layout is CardLayout
	
	private JPanel P; //to store the game area include shortest time ,timer, stop button, number of planks,warning, current player data
	
	private JPanel p1;//to store p11,p12,p13 panel, and put in the North area
	private JPanel p11;//to show each level's shortest time by using CardLayout
	private JPanel p12;//to show each level's timer by using CardLayout
	private JPanel p13;//to store stop button
	private JButton StopButton;// create a stop button using when player wants to stop game
	private ImageIcon StopImage;//put the image into StopButton
	private CardLayout cl11;//set p11's layout is CardLayout
	private CardLayout cl12;//set p12's layout is CardLayout
	
	private JPanel p2;//to store p11,p12,p13 panel, and put in the center area
	private CardLayout cl2;//set p2's layout is CardLayout

	private MenuPanel menuPanel;//create a menuPanel and it displays when open the game
	private NamePanel namePanel;//create a namepanel and it display swhen click the play button in menuPanel
	private ChoicePanel choicePanel;//create a choicepanel and it displays after input the player's name
	private WinPanel winPanel;//create a winPanel and it displays when finish each level
	private StopPanel stopPanel;//create a winPanel and it displays when click stop button 
	private FinalWinPanel finalWinPanel;//create a finalwinpanel and it displays finish the last level
	
	private Level1 level1;//create first level
	private Level2 level2;//create second level
	private Level3 level3;//create third level
	private Level4 level4;//create fourth level

	private int currentPlay=0;//to judge what level is going on
	
	private JPanel p3;//to store hintTF TextField and put it in the south area
	private JTextField hintTF;//show some hint about how to play the game
	
	private JPanel p4;//to store three panels p41,p42,p43 and put it in the east area
	private JPanel p41;//to store each level's numplanks TextField
	private JPanel p42;//to store each level's warningTA TextArea
	private JPanel p43;//to store each level's data table
	private CardLayout cl41;//set p41's layout is CardLayout
	private CardLayout cl42;//set p42's layout is CardLayout
	private CardLayout cl43;//set p43's layout is CardLayout
	
	/**
	 * the constructor is to initialize the components in Bottom
	 */
	public Bottom()
	{
		bottom= new JFrame();//initialize the frame to store hold panel
		CardHold=new CardLayout();//create a CardLayout
		hold = new JPanel(CardHold);//set hold panel a CardLayout
		
		//initialize each panel about menu,win,final win,stop,choice,name,each level
		menuPanel = new MenuPanel();
		level1= new Level1();
		level2=new Level2();
		level3=new Level3();
		level4= new Level4();
		winPanel = new WinPanel();
		stopPanel= new StopPanel();
		choicePanel= new ChoicePanel();
		namePanel=new NamePanel();
		finalWinPanel=new FinalWinPanel();
		
		//initialize the P panel to store the game components
		P=new JPanel(new BorderLayout());
		P.setPreferredSize(new Dimension(700,750));
		
		//p1 to store shortest time, timer and stop button
		p1= new JPanel(new BorderLayout());
		p1.setPreferredSize(new Dimension(700,50));
		cl11=new CardLayout();
		cl12=new CardLayout();
		
		p11= new JPanel(cl11);
		p11.setPreferredSize(new Dimension(290,50));
		p11.setBackground(Color.GREEN);
		p12= new JPanel(cl12);
		p13= new JPanel();
		p12.setPreferredSize(new Dimension(290,50));
		p12.setBackground(Color.green);
		
		p11.add(level1.getShortestTimeTF(), "level1");
		p12.add(level1.getTimingTF(), "level1");
		p11.add(level2.getShortestTimeTF(),"level2");
		p12.add(level2.getTimingTF(), "level2");
		p11.add(level3.getShortestTimeTF(),"level3");
		p12.add(level3.getTimingTF(), "level3");
		p11.add(level4.getShortestTimeTF(),"level4");
		p12.add(level4.getTimingTF(), "level4");
		
		p13.setPreferredSize(new Dimension(120,50));
		p13.setBackground(Color.green);
		StopImage = new ImageIcon("stop.jpg");// ±≥æ∞Õº∆¨  
		Image temp =StopImage.getImage().getScaledInstance(160,50,StopImage.getImage().SCALE_AREA_AVERAGING);
		StopImage = new ImageIcon(temp);
		StopButton= new JButton(StopImage);
		StopButton.setPreferredSize(new Dimension(160,50));

		p13.add(StopButton);
		p1.add("West",p11);
		p1.add("Center",p12);
		p1.add("East",p13);
		
		//initialize the p2 to store each level's panel 
		cl2=new CardLayout();
		p2= new JPanel(cl2);
		p2.setPreferredSize(new Dimension(540,650));
		p2.setBorder(null);

		p2.add(level1.getp(), "level1");
		p2.add(level2.getp(),"level2");
		p2.add(level3.getp(),"level3");
		p2.add(level4.getp(),"level4");
		
		//initialize the p3 to show the hint to play the game 
		p3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.setBackground(Color.green);
		p3.setPreferredSize(new Dimension(700,50));
		hintTF= new JTextField("Click LEFT to move. Click RIGHT to collect and put the planks");
		hintTF.setFont(new Font("Arial", Font.BOLD,22));
		hintTF.setBorder(null);
		hintTF.setBackground(Color.GREEN);
		p3.add(hintTF);
		
		//p4 to store p41,p42,p43 panels which show number of planks, warning, current player's data
		cl41=new CardLayout();
		cl42=new CardLayout();
		cl43=new CardLayout();
		p41= new JPanel(cl41);
		p42= new JPanel(cl42);
		p43= new JPanel(cl43);
		p4= new JPanel(new GridLayout(3,1));
		//p4= new JPanel(new BorderLayout());
		p4.setPreferredSize(new Dimension(160,650));
		p41.setPreferredSize(new Dimension(160,216));
		p41.setBackground(Color.RED);
		p42.setPreferredSize(new Dimension(160,217));
		p42.setBackground(Color.BLUE);
		p43.setPreferredSize(new Dimension(160,217));
		p41.add(level1.getNumPlanksTF(), "level1");
		p42.add(level1.getWarningSP(), "level1");
		p43.add(level1.getTableSP(), "level1");
		p41.add(level2.getNumPlanksTF(), "level2");
		p42.add(level2.getWarningSP(), "level2");
		p43.add(level2.getTableSP(), "level2");
		p41.add(level3.getNumPlanksTF(), "level3");
		p42.add(level3.getWarningSP(), "level3");
		p43.add(level3.getTableSP(), "level3");
		p41.add(level4.getNumPlanksTF(), "level4");
		p42.add(level4.getWarningSP(), "level4");
		p43.add(level4.getTableSP(), "level4");
		p4.add("North",p41);
		p4.add("Center",p42);
		p4.add("South",p43);
		P.add("North",p1);
		P.add("Center",p2);
		P.add("South",p3);
		P.add("East",p4);
		
		//add components into hold panel
		hold.add(menuPanel.getMenuPanel(),"menupanel");
		hold.add(namePanel.getNamePanel(),"namepanel");
		hold.add(choicePanel.getp(),"choicepanel");
		hold.add(P,"level");
		hold.add(stopPanel.getp(),"stoppanel");
		hold.add(winPanel.getp(),"win");
		hold.add(finalWinPanel.getp(),"finalwinpanel");
		
		bottom.setContentPane(hold);
		
		//set the bottom frame some parameters
		bottom.setTitle("Crossing river");
		bottom.setSize(700, 750);
		bottom.setResizable(false);
		bottom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bottom.setLocationRelativeTo(null);//æ”÷–
		bottom.setVisible(true);
		
		//add mouselistener to some buttons 
		StopButton.addMouseListener(this);
		menuPanel.getPlay().addMouseListener(this);
		for(JButton b:choicePanel.getb())
		{
			b.addMouseListener(this);
		}
		stopPanel.getContinueButton().addMouseListener(this);
		stopPanel.getRestart().addMouseListener(this);
		winPanel.getNextLevel().addMouseListener(this);
		winPanel.getRestart().addMouseListener(this);
		level1.getwinButton().addMouseListener(this);
		level2.getwinButton().addMouseListener(this);
		level3.getwinButton().addMouseListener(this);
		level4.getwinButton().addMouseListener(this);
		namePanel.getBack().addMouseListener(this);
		namePanel.getSure().addMouseListener(this);
		choicePanel.getBack().addMouseListener(this);
		stopPanel.getBackToStartMenu().addMouseListener(this);
		winPanel.getBackToStartMenu().addMouseListener(this);
		finalWinPanel.getRestart().addMouseListener(this);
		finalWinPanel.getBackToStartMenu().addMouseListener(this);
		
	}


	/**
	 * to stop program let the timer have enough to cancel 
	 */
	public void pause()
	{
		try { Thread.sleep(1001); }
		catch (Exception e) {};
	}
	
	/**
	 * to control the buttons after being clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==e.BUTTON1)
		{
			//click menupanel's play button or choicePanel's back button, it will go to namepanel
			if(e.getSource()==menuPanel.getPlay()||e.getSource()==choicePanel.getBack())
			{
				//CardHold.next(hold);
				CardHold.show(hold, "namepanel");
			}
			else if(e.getSource()==StopButton)//click stop button, it will go to stoppanel and set stop to responding level record the responding current time
			{
				switch(this.currentPlay)
				{
				case 1:
					level1.setStop(true);
					stopPanel.getCurrentTime().setText("Level1's Current Time: "+level1.getlasttime());
					break;
				case 2:
					stopPanel.getCurrentTime().setText("Level2's Current Time: "+level2.getlasttime());
					level2.setStop(true);
					break;
				case 3:
					stopPanel.getCurrentTime().setText("Level3's Current Time: "+level3.getlasttime());
					level3.setStop(true);
					break;
				case 4:
					stopPanel.getCurrentTime().setText("Level4's Current Time: "+level4.getlasttime());
					level4.setStop(true);
					break;
				}
				CardHold.show(hold,"stoppanel");
				
			}
			else if(e.getSource()==stopPanel.getContinueButton())//click stopPanel continue button it will back to previous level and initialize the level
			{
				switch(this.currentPlay)
				{
					case 1:
						level1.setStop(false);
						CardHold.show(hold,"level");
						cl11.show(p11, "level1");
						cl12.show(p12, "level1");
						cl2.show(p2, "level1");
						cl41.show(p41, "level1");
						cl42.show(p42, "level1");
						cl43.show(p43, "level1");
						break;
					case 2:
						level2.setStop(false);
						CardHold.show(hold,"level");
						cl11.show(p11, "level2");
						cl12.show(p12, "level2");
						cl2.show(p2, "level2");
						cl41.show(p41, "level2");
						cl42.show(p42, "level2");
						cl43.show(p43, "level2");
						break;
					case 3:
						level3.setStop(false);
						CardHold.show(hold,"level");
						cl11.show(p11, "level3");
						cl12.show(p12, "level3");
						cl2.show(p2, "level3");
						cl41.show(p41, "level3");
						cl42.show(p42, "level3");
						cl43.show(p43, "level3");
						break;
					case 4:
						level4.setStop(false);
						CardHold.show(hold,"level");
						cl11.show(p11, "level4");
						cl12.show(p12, "level4");
						cl2.show(p2, "level4");
						cl41.show(p41, "level4");
						cl42.show(p42, "level4");
						cl43.show(p43, "level4");
						break;
				}
			}
			//click back button, it will back to menupanel
			else if(e.getSource()==namePanel.getBack()||e.getSource()==winPanel.getBackToStartMenu()||e.getSource()==finalWinPanel.getBackToStartMenu())
			{
				CardHold.show(hold, "menupanel");
			}
			else if(e.getSource()==stopPanel.getBackToStartMenu())//click back button from stoppanel, it need to initialize the level and set stop to cancel the timer
			{
				CardHold.show(hold, "menupanel");
				level1.initial();
				level1.setStop(false);
				level2.initial();
				level2.setStop(false);
				level3.initial();
				level3.setStop(false);
				level4.initial();
				level4.setStop(false);
			}
			else if(e.getSource()==namePanel.getSure())
			{
				if(namePanel.getName()=="")//empty name ban to go to choicepanel
				{
					
				}
				else//go to choicepanel and record the name
				{
					CardHold.show(hold, "choicepanel");
					menuPanel.setPlayerName(namePanel.getName());
					level1.setPlayerName(namePanel.getName());
					level2.setPlayerName(namePanel.getName());
					level3.setPlayerName(namePanel.getName());
					level4.setPlayerName(namePanel.getName());
				}
			}
			else if(e.getSource()==winPanel.getNextLevel())//click next level, it will initialize the previous level and go to next level and begin to time next level
			{
				switch(currentPlay)
				{
				case 1:
					level1.initial();
					this.currentPlay=2;
					CardHold.show(hold,"level");
					cl11.show(p11, "level2");
					cl12.show(p12, "level2");
					cl2.show(p2, "level2");
					cl41.show(p41, "level2");
					cl42.show(p42, "level2");
					cl43.show(p43, "level2");
					try {
						level2.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level2.setStartToPlay(true);
					level2.setStop(false);
					level2.timing();
					break;
				case 2:
					level2.initial();
					this.currentPlay=3;
					CardHold.show(hold,"level");
					cl11.show(p11, "level3");
					cl12.show(p12, "level3");
					cl2.show(p2, "level3");
					cl41.show(p41, "level3");
					cl42.show(p42, "level3");
					cl43.show(p43, "level3");
					try {
						level3.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level3.setStartToPlay(true);
					level3.setStop(false);
					level3.timing();
					break;
				case 3:
					level3.initial();
					this.currentPlay=4;
					CardHold.show(hold,"level");
					cl11.show(p11, "level4");
					cl12.show(p12, "level4");
					cl2.show(p2, "level4");
					cl41.show(p41, "level4");
					cl42.show(p42, "level4");
					cl43.show(p43, "level4");
					try {
						level4.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level4.setStartToPlay(true);
					level4.setStop(false);
					level4.timing();
					break;
				}
			}
			else if(e.getSource()==level1.getwinButton())//win the level, set cancel the timer and record the data and go to winpanel or finalwinpanel
			{
				if(level1.getIsWin())
				{
					choicePanel.setPassed(0, true);
					choicePanel.setPassed(1, true);
					level1.setStartToPlay(false);

					CardHold.show(hold,"win");
					winPanel.getFinalTime().setText("The level's final time: "+level1.getlasttime());
					level1.recordlasttime();
					//level1.findShortestTime();
					//level1.recordShortestTime();
					choicePanel.setButtonContentAreaFilled(1);
					try {
						level1.exportTable();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						level1.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level1.initial();
				}
			}
			else if(e.getSource()==level2.getwinButton())
			{
				if(level2.getIsWin())
				{
					choicePanel.setPassed(1, true);
					choicePanel.setPassed(2, true);
					level2.setStartToPlay(false);
					CardHold.show(hold,"win");
					winPanel.getFinalTime().setText("The level's final time: "+level2.getlasttime());
					level2.recordlasttime();
					//level2.findShortestTime();
					//level2.recordShortestTime();
					choicePanel.setButtonContentAreaFilled(2);
					try {
						level2.exportTable();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						level2.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level2.initial();
				}
			}
			else if(e.getSource()==level3.getwinButton())
			{
				if(level3.getIsWin())
				{
					choicePanel.setPassed(2, true);
					choicePanel.setPassed(3, true);
					level3.setStartToPlay(false);
					CardHold.show(hold,"win");
					winPanel.getFinalTime().setText("The level's final time: "+level3.getlasttime());
					level3.recordlasttime();
					//level3.findShortestTime();
					//level3.recordShortestTime();
					choicePanel.setButtonContentAreaFilled(3);
					try {
						level3.exportTable();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						level3.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level3.initial();
				}
			}
			else if(e.getSource()==level4.getwinButton())
			{
				if(level4.getIsWin())
				{
					choicePanel.setPassed(3, true);
					level4.setStartToPlay(false);
					CardHold.show(hold,"finalwinpanel");
					finalWinPanel.getFinalTime().setText("The level's final time: "+level4.getlasttime());
					level4.recordlasttime();
					//level4.findShortestTime();
					//level4.recordShortestTime();
					try {
						level4.exportTable();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						level4.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level4.initial();
				}
			}
			//if restart button clicked, it will initial the current level,restart the timer go to the corresponding level panel
			else if(e.getSource()==stopPanel.getRestart()||e.getSource()==winPanel.getRestart()||e.getSource()==finalWinPanel.getRestart())
			{
				switch(currentPlay)
				{
					case 1:
						level1.initial();
						level1.setStartToPlay(false);
						pause();pause();
						CardHold.show(hold,"level");
						cl11.show(p11, "level1");
						cl12.show(p12, "level1");
						cl2.show(p2, "level1");
						cl41.show(p41, "level1");
						cl42.show(p42, "level1");
						cl43.show(p43, "level1");
						level1.setStartToPlay(true);
						level1.setStop(false);
						level1.timing();
						break;
					case 2:
						level2.initial();
						level2.setStartToPlay(false);
						pause();pause();
						CardHold.show(hold,"level");
						cl11.show(p11, "level2");
						cl12.show(p12, "level2");
						cl2.show(p2, "level2");
						cl41.show(p41, "level2");
						cl42.show(p42, "level2");
						cl43.show(p43, "level2");
						
						//this.currentPlay=1;
						level2.setStartToPlay(true);
						level2.setStop(false);
						level2.timing();
						break;
					case 3:
						level3.initial();
						level3.setStartToPlay(false);
						pause();pause();
						CardHold.show(hold,"level");
						cl11.show(p11, "level3");
						cl12.show(p12, "level3");
						cl2.show(p2, "level3");
						cl41.show(p41, "level3");
						cl42.show(p42, "level3");
						cl43.show(p43, "level3");
						level3.setStartToPlay(true);
						level3.setStop(false);
						level3.timing();
						break;
					case 4:
						level4.initial();
						level4.setStartToPlay(false);
						pause();pause();
						CardHold.show(hold,"level");
						cl11.show(p11, "level4");
						cl12.show(p12, "level4");
						cl2.show(p2, "level4");
						cl41.show(p41, "level4");
						cl42.show(p42, "level4");
						cl43.show(p43, "level4");
						
						level4.setStartToPlay(true);
						level4.setStop(false);
						level4.timing();
						break;
				}
				
			}
			else 
			{	//when choose the level, it should first the previous or lower level taht can play the level. If did,it will go to the corresponding level and start timer
				if(e.getSource()==choicePanel.getb()[0])
				{
					try {
						level1.compareHisRecord();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					level1.setStartToPlay(true);
					level1.timing();
					CardHold.show(hold,"level");
					cl11.show(p11, "level1");
						cl12.show(p12, "level1");
						cl2.show(p2, "level1");
						cl41.show(p41, "level1");
						cl42.show(p42, "level1");
						cl43.show(p43, "level1");
					this.currentPlay=1;
					
				}
				else if(e.getSource()==choicePanel.getb()[1])
				{
					if(choicePanel.getpassed()[1])
					{
						this.currentPlay=2;
						try {
							level2.compareHisRecord();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						choicePanel.getb()[1].setContentAreaFilled(true);
						level2.setStartToPlay(true);
						level2.timing();
						CardHold.show(hold,"level");
						cl11.show(p11, "level2");
						cl12.show(p12, "level2");
						cl2.show(p2, "level2");
						cl41.show(p41, "level2");
						cl42.show(p42, "level2");
						cl43.show(p43, "level2");
						cl2.show(p2, "level2");
					}		
					
				}
				else if(e.getSource()==choicePanel.getb()[2])
				{
					if(choicePanel.getpassed()[2])
					{
						this.currentPlay=3;
						try {
							level3.compareHisRecord();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						choicePanel.getb()[2].setContentAreaFilled(true);
						level3.setStartToPlay(true);
						level3.timing();
						CardHold.show(hold,"level");
						cl11.show(p11, "level3");
						cl12.show(p12, "level3");
						cl2.show(p2, "level3");
						cl41.show(p41, "level3");
						cl42.show(p42, "level3");
						cl43.show(p43, "level3");
						cl2.show(p2, "level3");
					}
				}
				else if(e.getSource()==choicePanel.getb()[3])
				{
					if(choicePanel.getpassed()[3])
					{
						this.currentPlay=4;
						try {
							level3.compareHisRecord();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						level4.setStartToPlay(true);
						level4.timing();
						CardHold.show(hold,"level");
						cl11.show(p11, "level4");
						cl12.show(p12, "level4");
						cl2.show(p2, "level4");
						cl41.show(p41, "level4");
						cl42.show(p42, "level4");
						cl43.show(p43, "level4");
						cl2.show(p2, "level4");
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
