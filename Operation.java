/**
 * Author:Yongdi Liu  
 * Student ID:16723023
 * Date:May 30th,2018
 * Description:This class is to control the button's action man moving and change image.
 */

package river;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Operation implements MouseListener{
	
	private GameBottom gameBottom;//get the initial GameBottom
	
	private int RselectPlank;//record the mouse click position when click a plank by right button
    private int CselectPlank;
    
    private int numPlanks;//record the planks which player get
    
    private boolean getPlanks;//record whether planks are gotten
    
    private boolean stumpMan;//to judge whether the man stand on the stump
    private boolean stumpBlank;//to judge whether the stump without the man
    
    private int CwalkPosition;//record the mouse click position when click any position by left button
    private int RwalkPosition;
    
    private int CmanPosition;//record the man's position
    private int RmanPosition;
    
    private boolean clicked;//judge whether it had already finish traversal of which button is clicked
    
    private boolean go=true;//judge whether the man can go
    private boolean isWin=false;//judge whether the man reach the final pioint
	private int warn=0;//count the number of warning
	private JTextField jtf41;//receive the TextField from level panel to write number of planks
    private JTextArea jta42;//receive the TextArea from level panel to write warning
    private String PlankNumstring="";//use to write number of planks
    
    /**
     * This constructor is to initial the all components in this Operation class with parameter TextField and TextArea
     * @param jtf41
     * @param jta42
     */
	public Operation(JTextField jtf41,JTextArea jta42)
	{
		gameBottom= new GameBottom();//initialize the game bottom
		this.jtf41=jtf41;//get the numplanks TextField
		this.jta42=jta42;//get the warning TextArea
		numPlanks=0;//initialize the parameters
        getPlanks=false;
        stumpMan=false;
        stumpBlank=false;
        clicked=false;
        
        //let the game buttons add MouseListener to control to change image and move
        for(int i=0;i<13;i++)
        {
            for(int j=0;j<9;j++)
            {
                gameBottom.getButtons()[i][j].addMouseListener(this);
            }
        }
	}
	/*
	 *let each level can change the image
	 *@return GameBottom gameBottom 
	 */
	public GameBottom getBottom()
	{
		return gameBottom;
	}
	
	/**
	 * let each level get the panel about the game bottom
	 * @return JPanel gameBottom.getp()
	 */
	public JPanel getp()
	{
		return gameBottom.getp();
	}
	
	/**
	 * let each level get the game buttons
	 * @return JButton[][] gameBottom.getButtons()
	 */
	public JButton[][] getButtons()
	{
		return gameBottom.getButtons();
	}
	
	/**
	 * set the man's row position
	 * @param RmanPosition
	 */
	public void setRmanPosition(int RmanPosition)
	{
		this.RmanPosition=RmanPosition;
	}
	
	/**
	 * get the the man's row position
	 * @return int RmanPosition
	 */
	public int getRmanPosition()
	{
		return this.RmanPosition;
	}
	/**
	 * set the man's column position
	 * @param CmanPosition
	 */
	public void setCmanPosition(int CmanPosition)
	{
		this.CmanPosition=CmanPosition;
	}
	
	/**
	 * get the the man's column position
	 * @return int CmanPosition
	 */
	public int getCmanPosition()
	{
		return this.CmanPosition;
	}
	
	/**
	 * set the number of planks
	 * @param numPlanks
	 */
	public void setnumPlanks(int numPlanks)
	{
		this.numPlanks=numPlanks;
	}
	
	/**
	 * get the number of planks
	 * @return int numPlanks
	 */
	public int getnumPlanks()
	{
		return this.numPlanks;
	}
	
	/**
	 * set whether the planks are picked
	 * @param getPlanks
	 */
	public void setgetPlanks(boolean getPlanks)
	{
		this.getPlanks=getPlanks;
	}
	
	/**
	 * get the value of getPlanks
	 * @return boolean getPlanks
	 */
	public boolean getgetPlanks()
	{
		return this.getPlanks;
	}
	
	/**
	 * set the whether the man stand the stump
	 * @param stumpMan
	 */
    public void setstumpMan(boolean stumpMan)
    {
    	this.stumpMan=stumpMan;
    }
    
    /**
     * get the value of stumpMan
     * @return boolean stumpMan
     */
    public boolean getstumpMan()
    {
    	return this.stumpMan;
    }
    
    /**
     * set the whether the stump is blank
     * @param stumpBlank
     */
    public void setstumpBlank(boolean stumpBlank)
    {
    	this.stumpBlank=stumpBlank;
    }
    
    /**
     * get the value of stumpBlank
     * @return boolean stumpBlank
     */
    public boolean getstumpBlank()
    {
    	return stumpBlank;
    }
    
    /**
     * set the button whether is clicked
     * @param clicked
     */
    public void setclicked(boolean clicked)
    {
    	this.clicked=clicked;
    }
    
    /**
     * get the value of clicked
     * @return boolean clicked
     */
    public boolean getclicked()
    {
    	return this.clicked;
    }
    
    /**
     * get the value of isWin
     * @return boolean isWin
     */
    public boolean getIsWin()
    {
    	return this.isWin;
    }
    
    /**
     * change the image of the corresponding button
     * @param RwalkPosition
     * @param CwalkPosition
     * @param picture
     * @param name
     */
	public void changePictureAndName(int RwalkPosition,int CwalkPosition,String picture,String name)
    {
    	gameBottom.setButtonPicture(gameBottom.getButtons()[RwalkPosition][CwalkPosition],new ImageIcon(picture));
    	gameBottom.setButtonName(RwalkPosition,CwalkPosition,name);
    }
	
	//initialize the parameters
	public void parabottom()
    {
    	numPlanks=0;
        getPlanks=false;
        stumpMan=false;
        stumpBlank=false;
        
    }
	
	/**
	 * when initialize level, clear the jta42
	 */
	public void clearJTA()
    {
    	if(warn%20==0)
    	{
    		this.jta42.setText("");
    	}
    }
	/**
	 *initialize warn variable
	 */
	public void initialWarn()
	{
		this.warn=0;
	}
	
	
	
	
	//case 1:
	//Horizontal
	/**
	When carrying one plank, if the click Position is water1 and there are a man 
	and a blank stump between it, it can put the plank in the horizontal direction
	*/
	public void putOnePlankHorizontal(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"
		&&((CselectPlank-1==CmanPosition&&RselectPlank==RmanPosition&&(gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="stump1"||gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="stump2"))
		||(CselectPlank+1==CmanPosition&&RselectPlank==RmanPosition&&(gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="stump1"||gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="stump3")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//vertical
	/**
	When carrying two planks, if the click Position is water1 and there are a man 
	and a blank stump between it, it can put the plank in the vertical direction
	*/
	public void putOnePlankVertical(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&
		((RselectPlank-1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump2"))
		||(RselectPlank+1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump3")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}

	//case 2:
	//Horizontal Left one
	/**
	When carrying two planks, if the click position is water1, the right one is water1
	and there are a man and a blank stump between them, it can put planks in the horizontal direction from left to right 
	*/
	public void putTwoPlanksHorizontalFromLeftToRight(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="water1"
		&&((CselectPlank-1==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank+2)=="stump1")
		||(CselectPlank+2==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="stump1"))){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank+1,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//Horizontal right one
	/**
	When carrying two planks, if the click position is water1, the left one is water1
	and there a the man and a blank stump between them,it can put planks in the horizontal direction from right to left
	*/
	public void putTwoPlanksHorizontalFromRightToLeft(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="water1"
		&&((CselectPlank+1==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank-2)=="stump1")
		||(CselectPlank-2==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="stump1"))){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank-1,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//vertical top
	/**
	When carrying two planks, if the click position is water1, the bottom one is water1
	and there a the man and a blank stump between them,it can put planks in the vertical direction down from the top
	*/
	public void putTwoPlanksVerticalDownFromTop(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="water1"
		&&((RselectPlank-1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+2,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+2,CselectPlank)=="stump2"))
		||(RselectPlank+2==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump3")))){

			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank+1,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}

	//vertical bottom
	/**
	When carrying two planks, if the click position is water1, the top one is water1
	and there a the man and a blank stump between them,it can put planks in the vertical direction from the bottom to the top
	*/
	public void putTwoPlanksVerticalFromBottomToTop(){

		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="water1"
		&&((RselectPlank+1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-2,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-2,CselectPlank)=="stump3"))
		||(RselectPlank-2==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump2")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank-1,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}

	//case 3:
	//Horizontal left one
	/**
	When carrying three planks, if the click position is water1, the right two are water1
	and there are a man and a blank stump among them,it can put planks in the horizontal direction from left to right
	*/
	public void putThreePlanksHorizontalFromLeftToRight(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank+2)=="water1"
		&&(CselectPlank-1==CmanPosition&&RselectPlank==RmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank+3)=="stump1")
		||(CselectPlank+3==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="stump1")){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank+1,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank+2,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//Horizontal middle
	/**
	When carrying three planks, if the click position is water1, the two sides are water1
	and there are a man and a blank stump among them,it can put planks in the horizontal direction from middle to sides
	*/
	public void putThreePlanksHorizontalFromMiddleToSides(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="water1"
		&&(CselectPlank-2==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank+2)=="stump1")
		||(CselectPlank+2==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank-2)=="stump1")){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank+1,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank-1,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//Horizontal right one
	/**
	When carrying three planks, if the click position is water1, the left two are water1
	and there are a man and a blank stump among them,it can put planks in the horizontal direction from right to left
	*/
	public void putThreePlanksHorizontalFromRightToLeft(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank-1)=="water1"&&gameBottom.getButtonName(RselectPlank,CselectPlank-2)=="water1"
		&&(CselectPlank+1==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank-3)=="stump1")
		||(CselectPlank-3==CmanPosition&&RselectPlank==RmanPosition&&gameBottom.getButtonName(RselectPlank,CselectPlank+1)=="stump1")){
			changePictureAndName(RselectPlank,CselectPlank,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank-1,"plank1.jpg","plank1");
			changePictureAndName(RselectPlank,CselectPlank-2,"plank1.jpg","plank1");
			parabottom();
		}
	}

	//vertical top
	/**
	When carrying three planks, if the click position is water1, the down two are water1
	and there are a man and a blank stump among them,it can put planks in the vertical direction down from the top
	*/
	public void putThreePlanksVerticalDownFromTop(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank+2,CselectPlank)=="water1"
		&&((RselectPlank+3==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="stump3"))
		||(RselectPlank-1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+3,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+3,CselectPlank)=="stump2")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank+1,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank+2,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}

	//vertical middle
	/**
	When carrying three planks, if the click position is water1, the  two sides are water1
	and there are a man and a blank stump among them,it can put planks in the vertical direction from middle to sides
	*/
	public void putThreePlanksVerticalFromMiddleToSides(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="water1"
		&&((RselectPlank+2==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-2,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-2,CselectPlank)=="stump3"))
		||(RselectPlank-2==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+2,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+2,CselectPlank)=="stump2")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank+1,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank-1,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}
	//vertical bottom
	/**
	When carrying three planks, if the click position is water1, the top two are water1
	and there are a man and a blank stump among them,it can put planks in the vertical direction the bottom to the top
	*/
	public void putThreePlanksVerticalFromBottomToTop(){
		if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank-1,CselectPlank)=="water1"&&gameBottom.getButtonName(RselectPlank-2,CselectPlank)=="water1"
		&&((RselectPlank+1==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank-3,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank-3,CselectPlank)=="stump3"))
		||(RselectPlank-3==RmanPosition&&CselectPlank==CmanPosition&&(gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump1"||gameBottom.getButtonName(RselectPlank+1,CselectPlank)=="stump2")))){
			changePictureAndName(RselectPlank,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank-2,CselectPlank,"plank2.jpg","plank2");
			changePictureAndName(RselectPlank-1,CselectPlank,"plank2.jpg","plank2");
			parabottom();
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==e.BUTTON1)//when click left mouse
        {   
            //walk
        	clicked =false;
        	//find the mouse click position and send to RwalkPosition and CwalkPosition
            for(int i =0 ;i<13;i++)
            {
                for(int k=0;k<9;k++)
                {
                    if(e.getSource()==gameBottom.getButtons()[i][k])
                    {
                        RwalkPosition=i;CwalkPosition=k;
                        clicked=true;
                        break;
                        
                    }  
                }
                if(clicked)break;
            }
            
            //useless position or position
            if(gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1_man"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2_man"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1_man"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump2_man"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="water1"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="water2"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="water3"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="water4")
            {
            	warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            	
            }
            //click the useful image and if man follows the rule, he can move
            else if(gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump2"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump3"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1"
    		||gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
            {
            	go=true;
            	if(RmanPosition>RwalkPosition&&CmanPosition==CwalkPosition)//只向上走
	            {
	            	for(int i=RmanPosition-1;i>=RwalkPosition;i--)
	            	{
	            		if(gameBottom.getButtonName(i,CwalkPosition)!="plank2"&&gameBottom.getButtonName(i,CwalkPosition)!="stump1"&&gameBottom.getButtonName(i,CwalkPosition)!="stump3")
	            		{
	            			// the path just only can be plank2, stump1, or stump3 when the man go upward
	            			go=false;
	            			break;
	            		}
	            	}
	            	if(go)
	            	{
	            		//if the images are the combination between two sets which choose one from each of them
	            		//one set consists of stump2_man, plank2_man, stump1_man
	            		//another set consists of stump1, plank2, stump3
	            		//if stump3 changes to stump3_man, it will win
	            		if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump2.jpg","stump2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump2.jpg","stump2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
                			
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump3")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump2.jpg","stump2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump3_man.jpg","stump3_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
                			this.isWin=true;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump3")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump3_man.jpg","stump3_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
                			isWin=true;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if (gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump3")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump3_man.jpg","stump3_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            			isWin=true;
	            		}
	            		else if (gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            	}
	            }
	            else if(RmanPosition<RwalkPosition&&CmanPosition==CwalkPosition)//只向下走
	            {
	            	for(int i=RmanPosition+1;i<=RwalkPosition;i++)
	            	{
	            		// the path just only can be plank2, stump1, or stump2 when the man go downward
	            		if(gameBottom.getButtonName(i,CwalkPosition)!="plank2"&&gameBottom.getButtonName(i,CwalkPosition)!="stump1"&&gameBottom.getButtonName(i,CwalkPosition)!="stump2")
	            		{
	            			go=false;
	            			break;
	            		}
	            	}
	            	if(go)
	            	{
	            		//if the images are the combination between two sets which choose one from each of them
	            		//one set consists of plank2_man, stump1_man
	            		//another set consists of stump1, plank2, stump2
	            		if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump2_man.jpg","stump2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"stump2_man.jpg","stump2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank2_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			RmanPosition=RwalkPosition;
                			CmanPosition=CwalkPosition;
	            		}
	            	}
	            }
	            else if(CmanPosition>CwalkPosition&&RmanPosition==RwalkPosition)//只向左走
	            {
	            	for(int i=CmanPosition-1;i>=CwalkPosition;i--)
	            	{
	            		if(gameBottom.getButtonName(RwalkPosition,i)!="plank1"&&gameBottom.getButtonName(RwalkPosition,i)!="stump1")
	            		{
	            			// the path just only can be plank1, stump1 when the man go left
	            			go=false;
	            			break;
	            		}
	            	}
	            	if(go){
	            		//if the images are the combination between two sets which choose one from each of them
	            		//one set consists of plank1_man, stump1_man
	            		//another set consists of stump1, plank1
	            		if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank1.jpg","plank1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank1.jpg","plank1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            	}
	            }
	            else if(CmanPosition<CwalkPosition&&RmanPosition==RwalkPosition)//只向右走
	            {
	            	for(int i=CmanPosition+1;i<=CwalkPosition;i++)
	            	{
	            		// the path just only can be plank1, stump1 when the man go right
	            		if(gameBottom.getButtonName(RwalkPosition,i)!="plank1"&&gameBottom.getButtonName(RwalkPosition,i)!="stump1")
	            		{
	            			
	            			go=false;
	            			break;
	            		}
	            	}
	            	if(go){
	            		//if the images are the combination between two sets which choose one from each of them
	            		//one set consists of plank1_man, stump1_man
	            		//another set consists of stump1, plank1
	            		if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="stump1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"stump1.jpg","stump1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank1.jpg","plank1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            		else if(gameBottom.getButtonName(RmanPosition,CmanPosition)=="plank1_man"&&gameBottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
	            		{
	            			changePictureAndName(RmanPosition,CmanPosition,"plank1.jpg","plank1");
	            			changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
	            			RmanPosition=RwalkPosition;
	            			CmanPosition=CwalkPosition;
	            		}
	            	}
	            }
	            else {
	            	//if it does not obey the above rule, it will get a warning 
	            	warn++;
            		jta42.append(warn+" This is useless!"+"\r\n");
            		clearJTA();
	            }
            	if(!go){
            		warn++;
            		jta42.append(warn+" This is useless!"+"\r\n");
            		clearJTA();
            	}
	            
            }
            /*else if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="water1") 
            {
            	warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            }*/
            
            /*//分步走
            //向上走
            if(RmanPosition>RwalkPosition)
            {
            	if(RwalkPosition!=0)
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
                	{
                		if(bottom.getButtonName(RwalkPosition+1,CwalkPosition)=="plank2_man")
                		{
                			changePictureAndName(RwalkPosition+1,CwalkPosition,"plank2.jpg","plank2");
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");

                            RmanPosition--;
                		}
                		else if(bottom.getButtonName(RwalkPosition+1,CwalkPosition)=="stump1_man")
                		{
                		changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");

                          changePictureAndName(RwalkPosition+1,CwalkPosition,"stump1.jpg","stump1");

                            RmanPosition--;
                		}
                		else if(bottom.getButtonName(RwalkPosition+1,CwalkPosition)=="stump2_man")
                		{
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");

                          changePictureAndName(RwalkPosition+1,CwalkPosition,"stump2.jpg","stump2");

                            RmanPosition--;
                		}
                		else
                    	{
                    		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                    	}
                	}
                	else if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
    	            {
                		if(bottom.getButtonName(RwalkPosition+1,CwalkPosition)=="plank2_man")
                		{
                			changePictureAndName(RwalkPosition+1,CwalkPosition,"plank2.jpg","plank2");

                          changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                            
                            RmanPosition--;
                		}
                		else
                    	{
                    		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                    	}
    	            }
                }
                else if(RwalkPosition==0)
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump3"&&bottom.getButtonName(RwalkPosition+1,CwalkPosition)=="plank2_man")
                	{	
                		 changePictureAndName(RwalkPosition+1,CwalkPosition,"plank2.jpg","plank2");
            			
                        changePictureAndName(RwalkPosition,CwalkPosition,"stump3_man.jpg","stump3_man");
                		isWin=true;
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}
                }
            }
            
            //向下走
            if(RmanPosition<RwalkPosition)
            {
            	if(RwalkPosition!=12)
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="plank2")
                	{
                		if(bottom.getButtonName(RwalkPosition-1,CwalkPosition)=="plank2_man")
                		{
                			changePictureAndName(RwalkPosition-1,CwalkPosition,"plank2.jpg","plank2");
                			
                          changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                           
                            RmanPosition++;
                		}
                		else if(bottom.getButtonName(RwalkPosition-1,CwalkPosition)=="stump1_man")
                		{
                			changePictureAndName(RwalkPosition,CwalkPosition,"plank2_man.jpg","plank2_man");
                			
                          changePictureAndName(RwalkPosition-1,CwalkPosition,"stump1.jpg","stump1");
                            
                            RmanPosition++;
                		}
                		else
                    	{
                    		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                    	}
                	}
                	else if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
    	            {
                		if(bottom.getButtonName(RwalkPosition-1,CwalkPosition)=="plank2_man")
                		{
                			changePictureAndName(RwalkPosition-1,CwalkPosition,"plank2.jpg","plank2");
                			
                          changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                            
                            RmanPosition++;
                		}
                		else
                    	{
                    		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                    	}
    	            }
                }
                else if(RwalkPosition==12)
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump2"&&bottom.getButtonName(RwalkPosition-1,CwalkPosition)=="plank2_man")
                	{	
                		changePictureAndName(RwalkPosition-1,CwalkPosition,"plank2.jpg","plank2");
            			
                      changePictureAndName(RwalkPosition,CwalkPosition,"stump2_man.jpg","stump2_man");
                        
                        RmanPosition++;
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}
                }
            }
            
            //向左走
            if(CmanPosition>CwalkPosition)
            {
            	
            	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
                {
            		
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition+1)=="plank1_man")
                	{
                		changePictureAndName(RwalkPosition,CwalkPosition+1,"plank1.jpg","plank1");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
                        
                        CmanPosition--;
                	}
                	else if(bottom.getButtonName(RwalkPosition,CwalkPosition+1)=="stump1_man")
                	{
                		
                		changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition+1,"stump1.jpg","stump1");
                        
                        CmanPosition--;
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}	
                }
                else if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition+1)=="plank1_man")
                	{
                		changePictureAndName(RwalkPosition,CwalkPosition+1,"plank1.jpg","plank1");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                        
                        CmanPosition--;
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}
                }
                else
            	{
            		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            	}
            }
            
            //向右走
            if(CmanPosition<CwalkPosition)
            {
            	if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="plank1")
                {
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition-1)=="plank1_man")
                	{
                		changePictureAndName(RwalkPosition,CwalkPosition-1,"plank1.jpg","plank1");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
                      CmanPosition++;
                	}
                	else if(bottom.getButtonName(RwalkPosition,CwalkPosition-1)=="stump1_man")
                	{
                		changePictureAndName(RwalkPosition,CwalkPosition,"plank1_man.jpg","plank1_man");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition-1,"stump1.jpg","stump1");
                        
                        CmanPosition++;
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}
                	
                }
                else if(bottom.getButtonName(RwalkPosition,CwalkPosition)=="stump1")
                {
                	
                	if(bottom.getButtonName(RwalkPosition,CwalkPosition-1)=="plank1_man")
                	{
                		
                		changePictureAndName(RwalkPosition,CwalkPosition-1,"plank1.jpg","plank1");
                		
                      changePictureAndName(RwalkPosition,CwalkPosition,"stump1_man.jpg","stump1_man");
                        
                        CmanPosition++;
                        
                	}
                	else
                	{
                		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
                	}
                }
                else
            	{
            		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            	}
            }*/
            
            
        }
		//it is to get and put the planks
        if(e.getButton()==e.BUTTON3)
        {
        	
        	clicked =false;
            for(int i =0 ;i<13;i++)
            {
                for(int k=0;k<9;k++)
                {
                    if(e.getSource()==gameBottom.getButtons()[i][k])
                    {
                    	//record the click position when click the right mouse
                        RselectPlank=i;CselectPlank=k;
                        clicked =true;
                        break;
                        
                    }    
                }
                if(clicked)break;
            }
            
            //get the planks
            //judge the horizontal direction
            if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="plank1" && !getPlanks)
            {
            	//it need that there are a man and blank stump between click position (it need to be plank),and the distance between man and the stump is lessa or equal to 3 units
        		for(int a=CselectPlank;a>=CselectPlank-3;a--)
                {
                    if(a<0||a>8)break;
                    else if(gameBottom.getButtonName(RselectPlank,a)=="stump1_man")
                    {
                    	stumpMan=true;break;
                    	
                    }
                    else if(gameBottom.getButtonName(RselectPlank,a)=="stump1")
                    {
                    	stumpBlank=true;break;	
                    }                       
                }
        		for(int a=CselectPlank;a<=CselectPlank+3;a++)
                {
            		if(a<0||a>8)break;
                    else if(gameBottom.getButtonName(RselectPlank,a)=="stump1_man")
                    {
                    	stumpMan=true;break;
                    }
                    else if(gameBottom.getButtonName(RselectPlank,a)=="stump1")
                    {
                    	stumpBlank=true;break;
                    }
                }
            	
            	if(stumpMan&&stumpBlank)
            	{
            		//if there are a man and blank stump between click position, it will get the planks
            		numPlanks++;
            		changePictureAndName(RselectPlank,CselectPlank,"water1.jpg","water1");
        			for(int a=CselectPlank-1;a>=CselectPlank-2;a--)
	                {
	                    if(a<0||a>8)break;
	                    else if(gameBottom.getButtonName(RselectPlank,a)=="stump1_man"||gameBottom.getButtonName(RselectPlank,a)=="stump1")
	                        break;
	                    else if(gameBottom.getButtonName(RselectPlank,a).equals("plank1"))
	                    {
	                        numPlanks++;
	                        changePictureAndName(RselectPlank,a,"water1.jpg","water1");
	                        
	                    }  
	                }
        			for(int a=CselectPlank+1;a<=CselectPlank+2;a++)
	                {
	                    if(a<0||a>8)break;
	                    else if(gameBottom.getButtonName(RselectPlank,a).equals("stump1_man")||gameBottom.getButtonName(RselectPlank,a).equals("stump1"))
	                        break;
	                    else if(gameBottom.getButtonName(RselectPlank,a).equals("plank1"))
	                    {
	                        numPlanks++;
	                        changePictureAndName(RselectPlank,a,"water1.jpg","water1");  
	                    }  
	                }
	                getPlanks=true;
            	}
            	else
            	{	
            		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            	}
            	PlankNumstring="You are carrying: "+numPlanks;
            	jtf41.setText(PlankNumstring); 
            }
            else if(gameBottom.getButtonName(RselectPlank,CselectPlank)=="plank2" && !getPlanks)
            {
            	////judge the vertical direction
        		for(int a=RselectPlank;a>=RselectPlank-3;a--)
                {
        			//it need that there are a man and blank stump between click position (it need to be plank),and the distance between man and the stump is lessa or equal to 3 units
                    if(a<0||a>12)break;
                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump2_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump3_man"))
                    {

                    	stumpMan=true;
                    	break;
                    }
                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1")||gameBottom.getButtonName(a,CselectPlank).equals("stump2")||gameBottom.getButtonName(a,CselectPlank).equals("stump3"))
                    {

                    	stumpBlank=true;
                    	break;
                    }
                }
        		for(int a=RselectPlank;a<=RselectPlank+3;a++) {

                    if(a<0||a>12)break;
                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump3_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump2_man"))
                    {
                    	stumpMan=true;
                    	break;
                    }
                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1")||gameBottom.getButtonName(a,CselectPlank).equals("stump3")||gameBottom.getButtonName(a,CselectPlank).equals("stump2"))
                    {
                    	stumpBlank=true;
                    	break;
                    }  
                }
            	
            	if(stumpMan&&stumpBlank)
            	{
            		//if there are a man and blank stump between click position, it will get the planks
            		numPlanks++;
            		changePictureAndName(RselectPlank,CselectPlank,"water1.jpg","water1");
        			for(int a=RselectPlank-1;a>=RselectPlank-2;a--)
	                {
	                    if(a<0||a>12)break;
	                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump1")||gameBottom.getButtonName(a,CselectPlank).equals("stump3_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump3"))
	                        break;
	                    else if(gameBottom.getButtonName(a,CselectPlank).equals("plank2"))
	                    {
	                        numPlanks++;
	                        changePictureAndName(a,CselectPlank,"water1.jpg","water1");
	                        
	                    }  
	                }
        			for(int a=RselectPlank+1;a<=RselectPlank+2;a++)
	                {
	                    if(a<0||a>12)break;
	                    else if(gameBottom.getButtonName(a,CselectPlank).equals("stump1_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump1")||gameBottom.getButtonName(a,CselectPlank).equals("stump2_man")||gameBottom.getButtonName(a,CselectPlank).equals("stump2"))
	                        break;
	                    else if(gameBottom.getButtonName(a,CselectPlank).equals("plank2"))
	                    {
	                        numPlanks++;
	                        changePictureAndName(a,CselectPlank,"water1.jpg","water1");
	                    }
	                }
	                
	                getPlanks=true;
            	}
            	else
            	{
            		warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
            	}

            	PlankNumstring="You are carrying: "+numPlanks;
            	jtf41.setText(PlankNumstring);  
            }
            
            //put the planks
            else if(numPlanks!=0){

            	switch(numPlanks){
            		case 1:
            			//row = 0 and row = 12 cannot put any plank
            			//It can only put a plank in vertical direction when column = 0 and column = 8
            			if((CselectPlank==0||CselectPlank==8)&&(RselectPlank!=0||RselectPlank!=12))
            				putOnePlankVertical();
            			//In other position it can put a plank in horizontal and vertical direction
            			else if(CselectPlank>0&&CselectPlank<8&&RselectPlank>0&&RselectPlank<12){
            				putOnePlankVertical();
            				putOnePlankHorizontal();
            			}
            			break;
            		case 2:
            			//CselectPlank==0||CselectPlank==8
            			//it just can put from bottom to top or down from top
            			if(CselectPlank==0||CselectPlank==8){
            				if(RselectPlank==1)
            					putTwoPlanksVerticalDownFromTop();
            				else if(RselectPlank==11)
            					putTwoPlanksVerticalFromBottomToTop();
            				else if(RselectPlank>1&&RselectPlank<11){
            					putTwoPlanksVerticalDownFromTop();
            					putTwoPlanksVerticalFromBottomToTop();
            				}
            			}
            			//CselectPlank==1
            			//it just can put from bottom to top, down from top,from left to right
            			else if(CselectPlank==1){
            				if(RselectPlank==1){
            					putTwoPlanksVerticalDownFromTop();
            					putTwoPlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank==11){
            					putTwoPlanksVerticalFromBottomToTop();
            					putTwoPlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank>1&&RselectPlank<11){
            					putTwoPlanksHorizontalFromLeftToRight();
            					putTwoPlanksVerticalDownFromTop();
            					putTwoPlanksVerticalFromBottomToTop();
            				}
            			}
            			//CselectPlank==7
            			//it just can put from bottom to top, down from top,from right to left
            			else if(CselectPlank==7){
            				if(RselectPlank==1){
            					putTwoPlanksVerticalDownFromTop();
            					putTwoPlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank==11){
            					putTwoPlanksVerticalFromBottomToTop();
            					putTwoPlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank>1&&RselectPlank<11){
            					putTwoPlanksHorizontalFromRightToLeft();
            					putTwoPlanksVerticalDownFromTop();
            					putTwoPlanksVerticalFromBottomToTop();
            				}
            			}
            			//it just can put down from top,from right to left or from left to right
            			else if(RselectPlank==1&&CselectPlank>1&&CselectPlank<7){
            				putTwoPlanksVerticalDownFromTop();
            				putTwoPlanksHorizontalFromLeftToRight();
            				putTwoPlanksHorizontalFromRightToLeft();
            			}
            			//it just can put from bottom to top,from right to left or from left to right
            			else if(RselectPlank==11&&CselectPlank>1&&CselectPlank<7){
            				putTwoPlanksVerticalFromBottomToTop();
            				putTwoPlanksHorizontalFromLeftToRight();
            				putTwoPlanksHorizontalFromRightToLeft();
            			}
            			//it just can put in all four ways
            			else if(RselectPlank>1&&RselectPlank<11&&CselectPlank>1&&CselectPlank<7){

            				putTwoPlanksHorizontalFromLeftToRight();
            				putTwoPlanksHorizontalFromRightToLeft();
            				putTwoPlanksVerticalDownFromTop();
            				putTwoPlanksVerticalFromBottomToTop();
            			}
            			break;
            		case 3:
            			//put three planks it need to obey the rules:
            			//1 CselectPlank==0||CselectPlank==8 
            			   //when RselectPlank==1, it can put them down from top
            			   //when RselectPlank==2, it can put them down from top or from middle to vertical two sides
            			   //when RselectPlank==11, it can put them from bottom to top
            			   //when RselectPlank==10, it can put them from bottom to top or from middle to vertical two sides
            			   //when RselectPlank between 2 and 10, it can put them from bottom to top, down from top or from middle to vertical two sides
            			if(CselectPlank==0||CselectPlank==8)
            			{
            				if(RselectPlank==1)
            					putThreePlanksVerticalDownFromTop();
            				else if(RselectPlank==2){
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksVerticalDownFromTop();
            				}
            				else if(RselectPlank==11)
            					putThreePlanksVerticalFromBottomToTop();
            				else if(RselectPlank==10){
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksVerticalFromBottomToTop();
            				}
            				else if(RselectPlank>2&&RselectPlank<10){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            				}
            			}
            			//2 CselectPlank==1
	            		   //when RselectPlank==1, it can put them down from top and from left to right
	         			   //when RselectPlank==2, it can put them down from top, from middle to vertical two sides or from left to right
	         			   //when RselectPlank==11, it can put them from bottom to top or from left to right
	         			   //when RselectPlank==10, it can put them from bottom to top from middle to vertical two sides or from left to right
	         			   //when RselectPlank between 2 and 10, it can put them from bottom to top, down from top, from middle to vertical two sides or from left to right
            			else if(CselectPlank==1){
            				if(RselectPlank==1){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank==2){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank==10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank==11){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksHorizontalFromLeftToRight();
            				}
            				else if(RselectPlank>2&&RselectPlank<10){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromLeftToRight();
            				}
            			}
            			//3 CselectPlank==2
	            		   //when RselectPlank==1, it can put them down from top, from left to right or from middle to horizontal two sides
	         			   //when RselectPlank==2, it can put them down from top, from middle to vertical two sides, left to right or from middle to horizontal two sides
	         			   //when RselectPlank==11, it can put them from bottom to top, from left to right or from middle to horizontal two sides
	         			   //when RselectPlank==10, it can put them from bottom to top from middle to vertical two sides, from left to right or from middle to horizontal two sides
	         			   //when RselectPlank between 2 and 10, it can put them from bottom to top, down from top, from middle to vertical two sides, from left to right or from middle to horizontal two sides
	            			else if(CselectPlank==2){
            				if(RselectPlank==1){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromLeftToRight();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==2){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromLeftToRight();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromLeftToRight();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==11){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksHorizontalFromLeftToRight();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank>2&&RselectPlank<10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromLeftToRight();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            					
            			}
            			//4  CselectPlank==6
	            		   //when RselectPlank==1, it can put them down from top, from right to left or from middle to horizontal two sides
	         			   //when RselectPlank==2, it can put them down from top, from middle to vertical two sides, from right to left or from middle to horizontal two sides
	         			   //when RselectPlank==11, it can put them from bottom to top, from right to left or from middle to horizontal two sides
	         			   //when RselectPlank==10, it can put them from bottom to top from middle to vertical two sides, from right to left or from middle to horizontal two sides
	         			   //when RselectPlank between 2 and 10, it can put them from bottom to top, down from top, from middle to vertical two sides, from right to left or from middle to horizontal two sides
            			else if(CselectPlank==6){
            				if(RselectPlank==1){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromRightToLeft();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==2){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromRightToLeft();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromRightToLeft();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank==11){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksHorizontalFromRightToLeft();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            				else if(RselectPlank>2&&RselectPlank<10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromRightToLeft();
            					putThreePlanksHorizontalFromMiddleToSides();
            				}
            			}
            			//5 CselectPlank==7
	            		   //when RselectPlank==1, it can put them down from top and from right to left
	         			   //when RselectPlank==2, it can put them down from top, from middle to vertical two sides or from right to left
	         			   //when RselectPlank==11, it can put them from bottom to top or from right to left
	         			   //when RselectPlank==10, it can put them from bottom to top from middle to vertical two sides or from right to left
	         			   //when RselectPlank between 2 and 10, it can put them from bottom to top, down from top, from middle to vertical two sides or from right to left
            			else if(CselectPlank==7){
            				if(RselectPlank==1){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank==2){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank==10){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank==11){
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksHorizontalFromRightToLeft();
            				}
            				else if(RselectPlank>2&&RselectPlank<10){
            					putThreePlanksVerticalDownFromTop();
            					putThreePlanksVerticalFromBottomToTop();
            					putThreePlanksVerticalFromMiddleToSides();
            					putThreePlanksHorizontalFromRightToLeft();
            				}
            			}
            			//6 RselectPlank==1&&CselectPlank>2&&CselectPlank<6
            				//it can put them down from top, from right to left,from left to right or from middle to horizontal two sides
            			else if(RselectPlank==1&&CselectPlank>2&&CselectPlank<6){
            				putThreePlanksHorizontalFromLeftToRight();
            				putThreePlanksHorizontalFromMiddleToSides();
            				putThreePlanksHorizontalFromRightToLeft();
            				putThreePlanksVerticalDownFromTop();
            			}
            			//7 RselectPlank==11&&CselectPlank>2&&CselectPlank<6
        				//it can put them from bottom to top, from right to left,from left to right or from middle to horizontal two sides
            			else if(RselectPlank==11&&CselectPlank>2&&CselectPlank<6){
            				putThreePlanksHorizontalFromLeftToRight();
            				putThreePlanksHorizontalFromMiddleToSides();
            				putThreePlanksHorizontalFromRightToLeft();
            				putThreePlanksVerticalFromBottomToTop();
            			}
            			//8 RselectPlank==2&&CselectPlank>2&&CselectPlank<6
        				//it can put them from bottom to top, down from top, from right to left,from left to right or from middle to horizontal two sides
            			else if(RselectPlank==2&&CselectPlank>2&&CselectPlank<6){
            				putThreePlanksHorizontalFromLeftToRight();
            				putThreePlanksHorizontalFromMiddleToSides();
            				putThreePlanksHorizontalFromRightToLeft();
            				putThreePlanksVerticalDownFromTop();
            				putThreePlanksVerticalFromMiddleToSides();
            			}
            			//9 RselectPlank==10&&CselectPlank>2&&CselectPlank<6
        				//it can put them from bottom to top, down from top, from right to left,from left to right or from middle to horizontal two sides
            			else if(RselectPlank==10&&CselectPlank>2&&CselectPlank<6){
            				putThreePlanksHorizontalFromLeftToRight();
            				putThreePlanksHorizontalFromMiddleToSides();
            				putThreePlanksHorizontalFromRightToLeft();
            				putThreePlanksVerticalFromBottomToTop();
            				putThreePlanksVerticalFromMiddleToSides();
            			}
            			//RselectPlank>2&&RselectPlank<10&&CselectPlank>2&&CselectPlank<6
            			//it can put all six ways
            			else if(RselectPlank>2&&RselectPlank<10&&CselectPlank>2&&CselectPlank<6){
            				putThreePlanksHorizontalFromLeftToRight();
            				putThreePlanksHorizontalFromMiddleToSides();
            				putThreePlanksHorizontalFromRightToLeft();
            				putThreePlanksVerticalDownFromTop();
            				putThreePlanksVerticalFromBottomToTop();
            				putThreePlanksVerticalFromMiddleToSides();
            			}
            			break;   	
            	}

            	jtf41.setText("You are carrying: "+numPlanks);
            	if(getPlanks){
            		warn++;
            		jta42.append(warn+" This is useless!"+"\r\n");
            		clearJTA();
            	}
            }
            else
            {
                warn++;jta42.append(warn+" This is useless!"+"\r\n");clearJTA();
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
