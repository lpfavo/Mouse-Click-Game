/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to show the menu interface when open the game.
 */

package river;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuPanel implements MouseListener{
	
	private JPanel menuPanel;//to store another four panels about welcome interface, helppanel, detailpanel and recordpanel
	private CardLayout pCl;//to store four panels by using CardLayout to be easy to change to another panel
	
	private JPanel helpPanel;//The helpPanelis to store hintPanel and back1 Panel to give some hints about the game
	private JPanel hintPanel;//It is to store hint label and helpTA which written some hint
	private JPanel hintLabelPanel;//It is to store label hint
	private JLabel hint;//It is the title of the panel
	private JTextArea helpTA;//write some hint in it
	private JScrollPane jsp1;//store helpTA which can see the scroll bar
	private JPanel back1Panel;//to store the back1 button
	private JButton back1;
	
	private JPanel recordPanel;//to store four levels history game time record
	private JPanel r1;//to store four labels about four levels' titles
	private JPanel r11;//to store the label about the first level's title
	private JPanel r12;//to store the label about the second level's title
	private JPanel r13;//to store the label about the third level's title
	private JPanel r14;//to store the label about the fourth level's title
	private JPanel r2;//to store four levels' tables with scroll bars
	private JPanel r3;//to store buttons which can let players to select the which level record he wants to see
	
	private JLabel recordLabel1;//the first level's title
	private JLabel recordLabel2;//the second level's title
	private JLabel recordLabel3;//the third level's title
	private JLabel recordLabel4;//the fourth level's title
	
	private CardLayout recordCl1;//let each level's title can be seen by clicking the corresponding button
	private CardLayout recordCl2;//let each level's record can be seen by clicking the corresponding button
	
	private Object[][] datatime= {};//the array is to store three data types: game date ,player's name and game time
	private String playerName;//to record player' name
	private String[] cloumnName={"GameDate","PlayerName","Time"};//to name the table header
	
	private JTable table1;//the first level's table
	private JScrollPane recordjsp1;//the first level's table's scroll bar
	private DefaultTableModel tableModel1;//the first level's table model which store these "GameDate","PlayerName","Time" three data
	private int RecordNum1=0;//record the number of rows which input into the first level's table,to avoid input the data repeatedly 
	
	private JTable table2;//the second level's table
	private JScrollPane recordjsp2;//the second level's table's scroll bar
	private DefaultTableModel tableModel2;//the second level's table model which store these "GameDate","PlayerName","Time" three data
	private int RecordNum2=0;//the record the number of rows which input into the second level's table,to avoid input the data repeatedly 
	
	private JTable table3;//the third level's table
	private JScrollPane recordjsp3;//the third level's table's scroll bar
	private DefaultTableModel tableModel3;//the third level's table model which store these "GameDate","PlayerName","Time" three data
	private int RecordNum3=0;//record the number of rows which input into the third level's table,to avoid input the data repeatedly 
	
	private JTable table4;//the fourth level's table
	private JScrollPane recordjsp4;//the fourth level's table's scroll bar
	private DefaultTableModel tableModel4;//the fourth level's table model which store these "GameDate","PlayerName","Time" three data
	private int RecordNum4=0;//record the number of rows which input into the fourth level's table,to avoid input the data repeatedly 
	
	private JButton recordLevel1;//the button is to let the record panel show the first level
	private boolean clickRecord1=true;//to avoid player input the button repeatedly that the data be input many times
	private JButton recordLevel2;//the button is to let the record panel show the second level
	private boolean clickRecord2=true;//to avoid player input the button repeatedly that the data be input many times
	private JButton recordLevel3;//the button is to let the record panel show the third level
	private boolean clickRecord3=true;//to avoid player input the button repeatedly that the data be input many times
	private JButton recordLevel4;//the button is to let the record panel show the fourth level
	private boolean clickRecord4=true;//to avoid player input the button repeatedly that the data be input many times
	private JButton recordBack;//back to menu
	
	private JPanel detailPanel;//show some introduction about this game
	private JPanel introPanel;//to show the title of this panel
	private JPanel introLabelPanel;//to store intro label
	private JLabel intro;//show the title
	private JTextArea detailTA;//some introduction written in this TextArea which support to write more than one row
	private JScrollPane jsp2;// let the TextArea has scroll bar
	private JPanel back2Panel;//to store back button
	private JButton back2;//back button to back to menu
	
	
	private JPanel startPanel;//the interface is shown firstly when open this game and store six panels which easy to set the size of each components
	private JPanel s1;
	private JPanel s2;
	private JPanel s3;
	private JPanel s4;
	private JPanel s5;
	private JPanel s6;
	private JLabel titleLabel;//to display the title of this game
	private JButton play;//begin to play and go to the namePanel
	private JButton recordButton;//to go to the recordPanel
	private JButton help;//to go to the helpPanel
	private JButton detail;//to go to the detailPanel
	private JButton quit;//exit the game
	
	/**
	 * This constructor is to initial the all components in this MenuPanel without parameter
	 */
	public MenuPanel()
	{
		//initial the menuPanel by using CardLayout to change among menuPanel,startPanel,recordPanel, helpPanel,detailPanel
		pCl= new CardLayout();
		menuPanel= new JPanel(pCl);
		menuPanel.setPreferredSize(new Dimension(700, 750));//set the menuPanel fix the size of frame
		
		//set six rows to store six panels with a label and five buttons
		startPanel= new JPanel(new GridLayout(6,1));
		
		//initial the panel to store the title label
		s1= new JPanel();
		s1.setPreferredSize(new Dimension(700,125));//set size
		titleLabel = new JLabel("River Crossing");
		//jlabel.setPreferredSize(new Dimension(100,100));
		titleLabel.setFont(new Font("Arial",Font.BOLD,40));
		s1.add(titleLabel);
		
		//initial the panel to store the play button
		s2= new JPanel();
		s2.setPreferredSize(new Dimension(700,125));
		play=new JButton("PLAY");
		play.setPreferredSize(new Dimension(150,50));
		s2.add(play);
		
		//initial the panel to store the record button
		s3= new JPanel();
		s3.setPreferredSize(new Dimension(700,125));
		recordButton= new JButton("record");
		recordButton.setPreferredSize(new Dimension(150,50));
		s3.add(recordButton);
		
		//initial the panel to store the help button
		s4= new JPanel();
		s4.setPreferredSize(new Dimension(700,125));
		help= new JButton("Help");
		help.setPreferredSize(new Dimension(150,50));
		s4.add(help);
		
		//initial the panel to store the detail button
		s5= new JPanel();
		s5.setPreferredSize(new Dimension(700,125));
		detail=new JButton("Detail");
		detail.setPreferredSize(new Dimension(150,50));
		s5.add(detail);
		
		//initial the panel to store the quit button
		s6= new JPanel();
		s6.setPreferredSize(new Dimension(700,125));
		quit= new JButton("Quit");
		quit.setPreferredSize(new Dimension(150,50));
		s6.add(quit);
		
		//add all components into menuPanel
		startPanel.add(s1);
		startPanel.add(s2);
		startPanel.add(s3);
		startPanel.add(s4);
		startPanel.add(s5);
		startPanel.add(s6);
		menuPanel.add(startPanel,"start");
		
		//initial the recordPanel to store the four levels' titles, tables and buttons
		recordPanel = new JPanel(new GridLayout(3,1));
		recordPanel.setPreferredSize(new Dimension(700, 750));
		
		//r1 panel is to store four levels' title by using cardLayoutout to change to each level
		recordCl1=new CardLayout();
		r1 = new JPanel(recordCl1);
		r1.setPreferredSize(new Dimension(700, 150));
		
		r11 = new JPanel();
		recordLabel1 = new JLabel("History Record:Level1");
		recordLabel1.setFont(new Font("Arial",Font.BOLD,30));
		r11.add(recordLabel1);
		r1.add(r11,"label1");
		
		r12 = new JPanel();
		recordLabel2 = new JLabel("History Record:Level2");
		recordLabel2.setFont(new Font("Arial",Font.BOLD,30));
		r12.add(recordLabel2);
		r1.add(r12,"label2");
		
		r13 = new JPanel();
		recordLabel3 = new JLabel("History Record:Level3");
		recordLabel3.setFont(new Font("Arial",Font.BOLD,30));
		r13.add(recordLabel3);
		r1.add(r13,"label3");
		
		r14 = new JPanel();
		recordLabel4 = new JLabel("History Record:Level4");
		recordLabel4.setFont(new Font("Arial",Font.BOLD,30));
		r14.add(recordLabel4);
		r1.add(r14,"label4");
		recordPanel.add(r1);
		
		//r2 panel is to store four levels' record by using cardLayoutout to change to each level
		recordCl2=new CardLayout();
		r2 = new JPanel(recordCl2);
		r2.setPreferredSize(new Dimension(700, 450));
		
		//the first level's record
		tableModel1=new DefaultTableModel(datatime,cloumnName); //data is store the 2d array datattime, the table header name is in cloumnName array
		table1= new JTable(tableModel1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer render1 = new DefaultTableCellRenderer();     
		render1.setHorizontalAlignment(JLabel.CENTER);// to let the data display in the center
	    table1.setDefaultRenderer(Object.class, render1);
		recordjsp1=new JScrollPane(table1);//to set scroll car
		recordjsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		r2.add(recordjsp1,"level1");
		
		//the second level's record
		tableModel2=new DefaultTableModel(datatime,cloumnName); //data is store the 2d array datattime, the table header name is in cloumnName array
		table2= new JTable(tableModel2);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table2.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer render2 = new DefaultTableCellRenderer();     
		render2.setHorizontalAlignment(JLabel.CENTER);// to let the data display in the center  
	    table2.setDefaultRenderer(Object.class, render2);
		recordjsp2=new JScrollPane(table2);//to set scroll car
		recordjsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		r2.add(recordjsp2,"level2");
		
		//the third level's record
		tableModel3=new DefaultTableModel(datatime,cloumnName); //data is store the 2d array datattime, the table header name is in cloumnName array
		table3= new JTable(tableModel3);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table3.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer render3 = new DefaultTableCellRenderer();     
		render3.setHorizontalAlignment(JLabel.CENTER);// to let the data display in the center   
	    table3.setDefaultRenderer(Object.class, render3);
		recordjsp3=new JScrollPane(table3);//to set scroll car
		recordjsp3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		r2.add(recordjsp3,"level3");
		
		//the fourth level's record
		tableModel4=new DefaultTableModel(datatime,cloumnName); //data is store the 2d array datattime, the table header name is in cloumnName array
		table4= new JTable(tableModel4);
		table4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table4.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer render4 = new DefaultTableCellRenderer();     
		render4.setHorizontalAlignment(JLabel.CENTER);// to let the data display in the center   
	    table4.setDefaultRenderer(Object.class, render3);
		recordjsp4=new JScrollPane(table4);//to set scroll car
		recordjsp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		r2.add(recordjsp4,"level4");
		recordPanel.add(r2);
		
		//r3 panel is to store four levels' button by clicking to change to each level and also a back butotn
		r3 = new JPanel();
		r3.setPreferredSize(new Dimension(700, 150));
		recordLevel1 =new JButton("Level1");
		recordLevel2 =new JButton("Level2");
		recordLevel3 =new JButton("Level3");
		recordLevel4 =new JButton("Level4");
		recordBack = new JButton("Back");
		r3.add(recordLevel1);
		r3.add(recordLevel2);
		r3.add(recordLevel3);
		r3.add(recordLevel4);
		r3.add(recordBack);
		recordPanel.add(r3);
		menuPanel.add(recordPanel,"record");
		
		//initial the helpPanel to show the introduction content of the game
		helpPanel=new JPanel(new GridLayout(2,1));//to store hintPanel and back1Panel
		
		hintPanel=new JPanel(new GridLayout(2,1));//to store hint label and JTextArea to store the introduction
		hintLabelPanel=new JPanel();
		hint=new JLabel("Hints");
		hint.setFont(new Font("Arial",Font.BOLD,30));
		hintLabelPanel.add(hint);
		hintPanel.add(hintLabelPanel);
		
		//initial the TextArea to write some hint content
		helpTA= new JTextArea();
		helpTA.setFont(new Font("Arial",Font.BOLD,15));
		helpTA.setLineWrap(true);
		helpTA.setWrapStyleWord(true);
		jsp1= new JScrollPane(helpTA);
		jsp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		back1Panel= new JPanel();
		back1Panel.setBorder(null);
		back1=new JButton("Back");
		back1.setPreferredSize(new Dimension(150,50));
		back1Panel.add(back1);
		hintPanel.add(jsp1);
		helpPanel.add(hintPanel);
		helpPanel.add(back1Panel);
		menuPanel.add(helpPanel,"help");
		
		//initial the recordPanel to store the four levels' titles, tables and buttons
		detailPanel=new JPanel(new GridLayout(2,1));
		introPanel=new JPanel(new GridLayout(2,1));
		
		intro=new JLabel("Introduction");//detailPanel's title
		intro.setFont(new Font("Arial",Font.BOLD,30));
		introLabelPanel=new JPanel();
		introLabelPanel.add(intro);
		introPanel.add(introLabelPanel);
		
		//to write some introduction in TextArea
		detailTA= new JTextArea();
		detailTA.setFont(new Font("Arial",Font.BOLD,15));
		detailTA.setLineWrap(true);
		detailTA.setWrapStyleWord(true);
		jsp2= new JScrollPane(detailTA);
		jsp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		back2Panel= new JPanel();
		back2Panel.setBorder(null);
		back2=new JButton("Back");
		back2.setPreferredSize(new Dimension(150,50));
		back2Panel.add(back2);
		introPanel.add(jsp2);
		detailPanel.add(introPanel);
		detailPanel.add(back2Panel);
		menuPanel.add(detailPanel,"detail");
		
		//add some buttons mouse listener in the menu panel
		recordBack.addMouseListener(this);
		recordLevel1.addMouseListener(this);
		recordLevel2.addMouseListener(this);
		recordLevel3.addMouseListener(this);
		recordLevel4.addMouseListener(this);
		recordButton.addMouseListener(this);
		help.addMouseListener(this);
		detail.addMouseListener(this);
		quit.addMouseListener(this);
		back1.addMouseListener(this);
		back2.addMouseListener(this);
	}
	
	/**
	 * this is to let bottom get the MenuPanel
	 * @return JPanel menuPanel 
	 */
	public JPanel getMenuPanel()
	{
		return menuPanel;
	}
	

	/*public CardLayout getpCl()
	{
		return pCl;
	}*/
	
	/**
	 * this is to let bottom get the play button to go to the namePanel
	 * @return JButton play 
	 */
	public JButton getPlay()
	{
		return play;
	}
	/*public JButton getBack1()
	{
		return back1;
	}
	public JButton getBack2()
	{
		return back2;
	}*/
	/**
	 * record the player' name
	 * @param playerName
	 */
	public void setPlayerName(String playerName)
	{
		this.playerName=playerName;
	}
	
	/**
	 * get Player Name
	 * @return String playerName
	 */
	public String getPlayerName()
	{
		return this.playerName;
	}
	/**
	 * record the numbers of each level's table's row
	 * @param i
	 */
	public void setRecordNum(int i)
	{
		switch(i) {
		case 1:
			this.RecordNum1++;
			break;
		case 2:
			this.RecordNum2++;
			break;
		case 3:
			this.RecordNum3++;
			break;
		case 4:
			this.RecordNum3++;
			break;
		}
	}
	/*public int getRecordNum1()
	{
		return this.RecordNum1;
	}
	public int getRecordNum2()
	{
		return this.RecordNum2;
	}
	public int getRecordNum3()
	{
		return this.RecordNum3;
	}
	public int getRecordNum4()
	{
		return this.RecordNum4;
	}
	public DefaultTableModel gettablemodel1()
	{
		return this.tableModel1;
	}
	public DefaultTableModel gettablemodel2()
	{
		return this.tableModel2;
	}
	public DefaultTableModel gettablemodel3()
	{
		return this.tableModel3;
	}
	public DefaultTableModel gettablemodel4()
	{
		return this.tableModel4;
	}*/
	
	/**
	 * to import detail file into detailTA TextArea
	 * @throws IOException
	 */
	public void importDetailFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("introduction.txt"));
		String line =br.readLine();
		while(line!=null)
		{
			detailTA.append(line);
			line = br.readLine();
		}
		br.close();
	}
	
	/**
	 * to import help file into helpTA TextArea
	 * @throws IOException
	 */
	public void importHelpFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("help.txt"));
		String line =br.readLine();
		while(line!=null)
		{
			helpTA.append(line);
			line = br.readLine();
		}
		br.close();
	}
	
	/**
	 * get data from each level's file
	 * @param file
	 * @param model
	 * @throws IOException
	 */
	public void importTable(String file,DefaultTableModel model) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String date="";
		String name="";
		String time="";
		String line =br.readLine();
		while(line!=null)
		{
			Object[] get=line.split(" ");
			date=String.valueOf(get[0]+" "+get[1]+" "+get[2]+" "+get[3]+" "+get[4]+" "+get[5]);
			name=String.valueOf(get[6]);
			time=String.valueOf(get[7]);
			Object[] aa = {date,name,time};
			model.addRow(aa);
			line = br.readLine();
		}
        br.close();
	}
	
	/**
	 * to clear or remove all record when back to menu from recordPanel
	 */
	public void setrecordclick()
	{
		clickRecord1=true;
		clickRecord2=true;
		clickRecord3=true;
		clickRecord4=true;
		//table1.clearSelection();
		for (int index = table1.getModel().getRowCount() - 1; index >= 0; index--) {
			tableModel1.removeRow(index);
        }
		for (int index = table2.getModel().getRowCount() - 1; index >= 0; index--) {
			tableModel2.removeRow(index);
        }
		for (int index = table3.getModel().getRowCount() - 1; index >= 0; index--) {
			tableModel3.removeRow(index);
        }
		for (int index = table4.getModel().getRowCount() - 1; index >= 0; index--) {
			tableModel4.removeRow(index);
        }
	}
	
	/**
	 * add mouse listener to control each button's action
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==e.BUTTON1)
		{
			//exit the game
			if(e.getSource()==quit)
			{
				System.exit(0);
			}
			else if(e.getSource()==help)//to import help file and show in the helpPanel
			{
				try {
					importHelpFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pCl.show(menuPanel,"help");
			}
			else if(e.getSource()==detail)//to import detail file and show in the detailPanel
			{
				try {
					importDetailFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				detailTA.append("\n\nGame Designer:Yongdi Liu, from Lancaster University at BJTU Weihai Campus");
				pCl.show(menuPanel, "detail");
			}
			else if(e.getSource()==recordButton)//go to recordPanel
			{
				pCl.show(menuPanel, "record");
			}
			else if(e.getSource()==back1||e.getSource()==back2||e.getSource()==recordBack)//back to startPanel
			{
				pCl.show(menuPanel, "start");
				this.setrecordclick();
				helpTA.setText("");
				detailTA.setText("");
				recordCl1.show(r1, "label1");
				recordCl2.show(r2, "level1");
				
			}
			else if(e.getSource()==recordLevel1)//display the level1's record 
			{
				if(clickRecord1)//to avoid being clicked more than once  
				{
					try {
						this.importTable("level1.txt", tableModel1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				clickRecord1=false;
				recordCl1.show(r1, "label1");
				recordCl2.show(r2, "level1");
			}
			else if(e.getSource()==recordLevel2)//display the level2's record 
			{
				if(clickRecord2)//to avoid being clicked more than once 
				{
					try {
					this.importTable("level2.txt", tableModel2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				clickRecord2=false;
				recordCl1.show(r1, "label2");
				recordCl2.show(r2, "level2");
			}
			else if(e.getSource()==recordLevel3)//display the level3's record 
			{
				if(clickRecord3)//to avoid being clicked more than once 
				{
					try {
					this.importTable("level3.txt", tableModel3);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				clickRecord3=false;
				recordCl1.show(r1, "label3");
				recordCl2.show(r2, "level3");
			}
			else if(e.getSource()==recordLevel4)//display the level4's record 
			{
				if(clickRecord4)//to avoid being clicked more than once 
				{
					try {
						this.importTable("level4.txt", tableModel4);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				clickRecord4=false;
				recordCl1.show(r1, "label4");
				recordCl2.show(r2, "level4");
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