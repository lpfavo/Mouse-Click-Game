/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class displays the fourth level's game interface
 */

package river;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Level4 {

	private Operation op;//create a object of Operation class to control each button action
	private JTextField shortestTimeTF;//to show the shortest time in the history record
	private JTextField timingTF;//to time the player's game time
	private JTextField numPlanksTF;//to display the numbers of the planks which player gets
	private JTextArea warningTA;//to warn the player the action is useless
	private JScrollPane warningSP;//give the warningTA scroll bar
	
	//timing
	private long minutes=0;//record the minutes of time
	private long second=0;//record the second of time
	private long count = 0;//use to be a clock that plus one per second
	private long lasttime = 0;//record the finish time
	private long shortestTime = 0;//record the history shortest time
	private boolean stop=false;//to control when player click stop button and timer stop timing
	private boolean StartToPlay=false;//to set the play whether play this level
		
	//record time
	private String[] dataType= {"GameDate","PlayerName","Time"};//可以放到一个类中进行记录时间
	private Object[][] data= {};//to record each time the player's data
	private String PlayerName="";//record player's name
	private ArrayList<Long> gameTime1;//record the current player's time
	private JTable jtable1;//create a table to record and display the game time which created by the current player
	private JScrollPane tableSP;//let the table have scroll bar
	private DefaultTableModel tableModel;//set the the table into tableModel
	private int RecordNum=0;//record the number of data that the following data can be record on the next row next time 
	
	/**
	 * the constructor is to initialize the components in level4 without parameter
	 */
	public Level4()
	{
		//initialize the TextField to record the short time
		shortestTimeTF= new JTextField("The Shortest time:");
        shortestTimeTF.setPreferredSize(new Dimension(290,50));
        shortestTimeTF.setBackground(Color.GREEN);
        shortestTimeTF.setFont(new Font("Arial", Font.BOLD,15));
        shortestTimeTF.setBorder(null);
        
      //initialize the TextField timingTF to time the game
        timingTF= new JTextField("0:00");
        timingTF.setPreferredSize(new Dimension(290,50));
        timingTF.setFont(new Font("Arial", Font.BOLD,15));
        timingTF.setBackground(Color.GREEN);
        timingTF.setBorder(null);
        
      //initialize the textField to record the number of planks that the player gets
        numPlanksTF= new JTextField("You are carrying: ");
        numPlanksTF.setPreferredSize(new Dimension(160,200));
        numPlanksTF.setFont(new Font("Arial", Font.BOLD,15));
        numPlanksTF.setBackground(new Color(50,200,50));
        
      //initialize the TextArea warningTA to warn the player the action was useless
        warningTA= new JTextArea(20,20);
        warningTA.setPreferredSize(new Dimension(160,200));
        warningTA.setFont(new Font("Arial", Font.BOLD,15));
        warningSP= new JScrollPane(warningTA);//make TextArea warningTA has a scroll bar
    	warningSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//op=new Operation(jtf41,jta42);
    	
    	//initialize the operation and make the level's map
    	op=new Operation(numPlanksTF,warningTA);
		op.getBottom().setButtonPicture(op.getBottom().getButtons()[0][1],new ImageIcon("stump3.jpg"));
        op.getBottom().setButtonName(0, 1, "stump3");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[12][2],new ImageIcon("stump2_man.jpg"));
        op.getBottom().setButtonName(12,2,"stump2_man");
        op.setRmanPosition(12);
        op.setCmanPosition(2);
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][1],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[4][5],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[4][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][0],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][3],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][2],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][0],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][2],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonName(2,1,"stump1");
        op.getBottom().setButtonName(2,4,"stump1");
        op.getBottom().setButtonName(2,8,"stump1");
        op.getBottom().setButtonName(4,5,"stump1");
        op.getBottom().setButtonName(4,8,"stump1");
        op.getBottom().setButtonName(6,0,"stump1");
        op.getBottom().setButtonName(6,3,"stump1");
        op.getBottom().setButtonName(6,8,"stump1");
        op.getBottom().setButtonName(7,4,"stump1");
        op.getBottom().setButtonName(8,2,"stump1");
        op.getBottom().setButtonName(10,0,"stump1");
        op.getBottom().setButtonName(10,2,"stump1");
        op.getBottom().setButtonName(10,4,"stump1");
        op.getBottom().setButtonName(10,8,"stump1");
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[1][6],new ImageIcon("water2.jpg"));
        op.getBottom().setButtonName(1,6,"water2");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][6],new ImageIcon("water3.jpg"));
        op.getBottom().setButtonName(8,6,"water3");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[3][2],new ImageIcon("water4.jpg"));
        op.getBottom().setButtonName(3,2,"water4");
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][2],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[11][2],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonName(7,0,"plank2");
        op.getBottom().setButtonName(8,0,"plank2");
        op.getBottom().setButtonName(9,0,"plank2");
        op.getBottom().setButtonName(9,2,"plank2");
        op.getBottom().setButtonName(11,2,"plank2");
        op.getBottom().setButtonName(7,8,"plank2");
        op.getBottom().setButtonName(8,8,"plank2");
        op.getBottom().setButtonName(9,8,"plank2");
        
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][1],new ImageIcon("plank1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][2],new ImageIcon("plank1.jpg"));
        op.getBottom().setButtonName(6,1,"plank1");
        op.getBottom().setButtonName(6,2,"plank1");
        
      //initialize the table, tableModel to let table record the data
        tableModel= new DefaultTableModel(data,dataType);
        jtable1 = new JTable(tableModel);
    	tableSP= new JScrollPane(jtable1);
    	tableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	warningTA.setText("");
    	
    	//initialize the gameTime1 to record the lastTime
    	gameTime1= new ArrayList<Long> ();
	}
	
	/**
	 * when the player click the restart button, it will initialize the map as same as the initial version
	 */
	public void initial()
	{
		minutes=0;
		second=0;
		count = 0;
		stop=false;
		StartToPlay=false;
		this.op.setnumPlanks(0);
		this.op.setgetPlanks(false);
		this.op.setstumpMan(false);
		this.op.setstumpBlank(false);
		this.op.setclicked(false);
        this.op.getBottom().initial();
		op.getBottom().setButtonPicture(op.getBottom().getButtons()[0][1],new ImageIcon("stump3.jpg"));
        op.getBottom().setButtonName(0, 1, "stump3");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[12][2],new ImageIcon("stump2_man.jpg"));
        op.getBottom().setButtonName(12,2,"stump2_man");
        op.setRmanPosition(12);
        op.setCmanPosition(2);
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][1],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[2][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[4][5],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[4][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][0],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][3],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][2],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][0],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][2],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][4],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[10][8],new ImageIcon("stump1.jpg"));
        op.getBottom().setButtonName(2,1,"stump1");
        op.getBottom().setButtonName(2,4,"stump1");
        op.getBottom().setButtonName(2,8,"stump1");
        op.getBottom().setButtonName(4,5,"stump1");
        op.getBottom().setButtonName(4,8,"stump1");
        op.getBottom().setButtonName(6,0,"stump1");
        op.getBottom().setButtonName(6,3,"stump1");
        op.getBottom().setButtonName(6,8,"stump1");
        op.getBottom().setButtonName(7,4,"stump1");
        op.getBottom().setButtonName(8,2,"stump1");
        op.getBottom().setButtonName(10,0,"stump1");
        op.getBottom().setButtonName(10,2,"stump1");
        op.getBottom().setButtonName(10,4,"stump1");
        op.getBottom().setButtonName(10,8,"stump1");
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[1][6],new ImageIcon("water2.jpg"));
        op.getBottom().setButtonName(1,6,"water2");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][6],new ImageIcon("water3.jpg"));
        op.getBottom().setButtonName(8,6,"water3");
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[3][2],new ImageIcon("water4.jpg"));
        op.getBottom().setButtonName(3,2,"water4");
        
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][0],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][2],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[11][2],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[7][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[8][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[9][8],new ImageIcon("plank2.jpg"));
        op.getBottom().setButtonName(7,0,"plank2");
        op.getBottom().setButtonName(8,0,"plank2");
        op.getBottom().setButtonName(9,0,"plank2");
        op.getBottom().setButtonName(9,2,"plank2");
        op.getBottom().setButtonName(11,2,"plank2");
        op.getBottom().setButtonName(7,8,"plank2");
        op.getBottom().setButtonName(8,8,"plank2");
        op.getBottom().setButtonName(9,8,"plank2");
          
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][1],new ImageIcon("plank1.jpg"));
        op.getBottom().setButtonPicture(op.getBottom().getButtons()[6][2],new ImageIcon("plank1.jpg"));
        op.getBottom().setButtonName(6,1,"plank1");
        op.getBottom().setButtonName(6,2,"plank1");
        this.warningTA.setText("");
        op.initialWarn();
	}
	/**
	 * this is to let Bottom get this level1
	 * @return JPanel p 
	 */
	public JPanel getp()
	{
		return this.op.getp();
	}
	
	/**
	 * this is to let Bottom get this TextField shortestTimeTF to set in CardLayout
	 * @return JTextField shortestTimeTF
	 */
	public JTextField getShortestTimeTF()
	{
		return shortestTimeTF;
	}
	
	/**
	 * this is to let Bottom get this timingTF TextField to set in CardLayout
	 * @return JTextField getTimingTF
	 */
	public JTextField getTimingTF()
	{
		return timingTF;
	}
	
	/**
	 * this is to let Bottom get this TextField numPlanksTF  to set in CardLayout
	 * @return JTextField numPlanksTF
	 */
	public JTextField getNumPlanksTF()
	{
		return numPlanksTF;
	}
	
	/**
	 * this is to let Bottom get this TextArea warningTA to set in CardLayout
	 * @return JTextArea warningTA
	 */
	public JTextArea getWarningTA()
	{
		return warningTA;
	}
	
	/**
	 * this is to let Bottom get this ScrollPane warningSP to set in CardLayout
	 * @return JScrollPane warningSP
	 */
	public JScrollPane getWarningSP()
	{
		return warningSP;
	}
	
	/**
	 * this is to let Bottom get this ScrollPane getTableSP to set in CardLayout
	 * @return JScrollPane getTableSP
	 */
	public JScrollPane getTableSP()
	{
		return tableSP;
	}
	
	/**
	 * set the stop value to timer decider to continue to timing  or stop to time 
	 * @param boolean stop
	 */
	public void setStop(boolean stop)
	{
		this.stop=stop;
	}
	
	/**
	 * to decide the current play level. If true, it will show this level
	 * @param boolean startToPlay
	 */
	public void setStartToPlay(boolean startToPlay)
	{
		this.StartToPlay=startToPlay;
	}
	
	/**
	 * to judge the player whether reach to the final point
	 * @return boolean Operation.getIsWin()
	 */
	public boolean getIsWin()
	{
		return this.op.getIsWin();
	}
	
	/**
	 * let Bottom class know this level's final point
	 * @return JButton Operation .getButtons()[0][1]
	 */
	public JButton getwinButton()
	{
		return this.op.getButtons()[0][1];
	}
	
	/**
	 * let the Bottom class get the tableModel to display the current player's data 
	 * @return DefaultTableModel tableModel
	 */
	public DefaultTableModel gettableModel()
	{
		return this.tableModel;
	}
	
	/**
	 * to return the time the player finish the level
	 * @return String time
	 */
	public String getlasttime()
	{
		String time="";
    	minutes=this.lasttime/60;
    	second=this.lasttime%60;
    	if(minutes<10&&second<10)
    		time="0"+minutes+":0"+second;
    	else if(minutes<10&&second>=10)
    		time="0"+minutes+":"+second;
    	else if(minutes>10&&second<10)
    		time=minutes+":0"+second;
    	else time=minutes+":"+second;
    	return time;
	}
	
	/**
	 * to change the TextField timingTF's time let it like a timer
	 */
	public void changeTime()
	{
		//this.lasttime=count;
    	minutes=count/60;
    	second=count%60;
    	if(minutes<10&&second<10)
    		timingTF.setText("Time:"+"0"+minutes+":0"+second);
    	else if(minutes<10&&second>=10)
    		timingTF.setText("Time:"+"0"+minutes+":"+second);
    	else if(minutes>10&&second<10)
    		timingTF.setText("Time:"+minutes+":0"+second);
    	else timingTF.setText("Time:"+minutes+":"+second);
    	this.lasttime=count;
		count++;
	}
	

	/*public void recordShortestTime()
	{
		minutes=this.shortestTime/60;
    	second=this.shortestTime%60;
    	if(minutes<10&&second<10)
    		shortestTimeTF.setText("The shortest time is "+"0"+minutes+":0"+second);
    	else if(minutes<10&&second>10)
    		shortestTimeTF.setText("The shortest time is "+"0"+minutes+":"+second);
    	else if(minutes>10&&second<10)
    		shortestTimeTF.setText("The shortest time is "+minutes+":0"+second);
    	else shortestTimeTF.setText("The shortest time is "+minutes+":"+second);
	}*/
	
	/**
	 * every time enter the level it will find the history record time and the time maker
	 * @throws IOException
	 */
	public void compareHisRecord() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("level4.txt"));
		String shortTime="";
		String name="";
		String time="";
		String line =br.readLine();
		if(line!=null)
		{
			Object[] get=line.split(" ");
			name=String.valueOf(get[6]);
			time=String.valueOf(get[7]);
			shortTime=time;
		}
		line = br.readLine();
		while(line!=null)
		{
			Object[] get=line.split(" ");
			//name=String.valueOf(get[6]);
			time=String.valueOf(get[7]);
			if(shortTime.compareTo(time)>0)
			{
				shortTime=time;
				name=String.valueOf(get[6]);
			}
				
			line = br.readLine();
		}
		if(shortTime!="")
			shortestTimeTF.setText("The shortest time is:"+shortTime+"("+name+")");
		else shortestTimeTF.setText("The shortest time is:");
        br.close();
	}
	
	/**
	 * every time the player finish the level it will output and write the data into a file
	 * @throws IOException
	 */
	public void exportTable() throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("level4.txt",true),"UTF-8");
        for(int i=RecordNum; i< tableModel.getRowCount(); i++) {
            for(int j=0; j < tableModel.getColumnCount(); j++) {
                out.append(tableModel.getValueAt(i,j)+" ");
            }
            out.append("\n");
            RecordNum++;
        }
        out.close();
    }
	
	/**
	 * this is the timer which every time begin to time, it will do changeTime() per second. 
	 * If the stop button it will do nothing, the count will not count number.And if player finish or restart
	 * the game. It will cancel the timer task 
	 */
	public void timing()
	{
		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(stop)
				{
				}
				else if(!StartToPlay) 
				{
					count=0;
					timer1.cancel();
				}
				else 
				{
					changeTime();
				}
				
			}}, 0, 1000L);
	}
	
	/**
	 * to get the shorttime from gametime1
	 */
	public void findShortestTime()
	{
		if(!gameTime1.isEmpty())
		{
			shortestTime=lasttime;
			
			for(int i=0;i<gameTime1.size();i++)
			{
				if(gameTime1.get(i)<this.shortestTime)
					this.shortestTime=gameTime1.get(i);
			}
		}
		else System.out.println("There is no time.");
	}
	
	/**
	 * save the player name into file
	 * @param name
	 */
	public void setPlayerName(String name)
	{
		this.PlayerName=name;
	}
	
	/**
	 * record the player's data into the table when player finish the level
	 */
	public void recordlasttime()
	{
		gameTime1.add(this.lasttime);
    	long currentTime = System.currentTimeMillis();
    	Date date = new Date(currentTime);
    	Object[] nn={date.toString(),PlayerName,this.getlasttime()};
    	tableModel.addRow(nn);
	}
}
