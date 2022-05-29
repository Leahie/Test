import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class TicTac
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("TicTacToe");
      frame.setSize(800, 430);
      frame.setLocation(20,20);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new TicTacToe());
      frame.setVisible(true);
   }
}

class TicTacToe extends JPanel
{
   JLabel number;  //These are fields so that the action listeners can access them.
   JLabel number1; 
   double num;
   double num1;
   Panel graph;
   
   public TicTacToe ()
   {
      setLayout(new BorderLayout());
      
      //label in NORTH
      JLabel title = new JLabel("TicTacToe");
      title.setFont(new Font("Monospaced", Font.BOLD, 20));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      add(title, BorderLayout.NORTH);
         
      //TO DO: label in CENTER
      //this is where the grid goes, but it's empty for now
      graph = new Panel();          
      add(graph);
     
      //TO DO: subpanel in WEST
      JPanel westSubpanel = new JPanel();
      westSubpanel.setLayout(new GridLayout(2, 1));
      double num = 0;
      //Label 
      JLabel X = new JLabel(" X " );  //Text field will begin displaying the String "0" and be 10 characters wide
      X.setFont(new Font("Serif", Font.BOLD, 40));
      X.setHorizontalAlignment(SwingConstants.CENTER);  //Put the text centered in the text field
      westSubpanel.add(X);

      number = new JLabel("" + num);
      number.setFont(new Font("Serif", Font.BOLD, 40));
      number.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(number);  
              
      add(westSubpanel, BorderLayout.WEST);
         
         //TO DO: subpanel in EAST
      JPanel eastSubpanel = new JPanel();
      eastSubpanel.setLayout(new GridLayout(2, 1));
      double num1 = 0;
      //Label 
      JLabel O = new JLabel(" O " );  //Text field will begin displaying the String "0" and be 10 characters wide
      O.setHorizontalAlignment(SwingConstants.CENTER);  //Put the text centered in the text field
      O.setFont(new Font("Serif", Font.BOLD, 40)); 
      eastSubpanel.add(O);     

      JLabel number1 = new JLabel("" + num1);
      number1.setFont(new Font("Serif", Font.BOLD, 40));
      number1.setHorizontalAlignment(SwingConstants.CENTER);
      eastSubpanel.add(number1);  
      add(eastSubpanel, BorderLayout.EAST);
      
      
      
      JButton button = new JButton("Reset with a new game!");//this is the thing that like makes it show up 
      button.addActionListener(new ResetListener());
      add(button, BorderLayout.SOUTH);
          
   }
   
   
   public class ResetListener implements ActionListener//this is the listener 
      {
      public void actionPerformed (ActionEvent e)
         {
            graph.reset();
            graph.requestFocusInWindow();//Required 
            graph.begin();
         }
   
   }
   }   



class Panel extends JPanel{
   public static final int FRAME = 400;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private O oSymbol;   
   private X xSymbol;
   private Timer t;
   private int ColX;
   private int ColO;
   private boolean SpaceO;
   private boolean SpaceX;
   private boolean Up;
   private boolean Down;
   private boolean Left;
   private boolean Right;
   public static int[][] Board; //x=1 o=0
   public static int[] CentersX;
   public static int[] CentersY;
   
   //POWERUPS 
   public static PowerUps[] powerup; //ADD UR THING INTO THIS ARRAY
   
   
   
   public Panel(){
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      oSymbol = new O(325, 320);
      xSymbol = new X(50, 350);
      oSymbol.drawAll(myBuffer, 1);//if the number is odd it draws the neutral state
      xSymbol.drawAll(myBuffer, 2);
      Up=true;
      Down=true;
      Left=true;
      Right=true;
      SpaceO = false;
      SpaceX = true;//if it's X or O being activated  
      Board= new int[3][3];//Board
      CentersX= new int[3];  
      CentersY= new int[3];  
      CentersX[0]= 130;
      CentersX[1]=200;
      CentersX[2]=275;
      CentersY[0]=80;
      CentersY[1]=190;
      CentersY[2]=310;
      
      
      //THIS IS THE ARRAY 
      powerup = new PowerUps[7];
      powerup[0] = new Bounce(-20,-20);//First one set away from the display BOUNCE
      powerup[1] = new Shake(-20,-20);//Shake
      powerup[2] = new Wave(-20, -20)//Wave
      powerup[3] = new Blink(-20,-20);//Blink

      for(int x =160; x < 250; x+=80)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(x, 30, 10, 340);   
      }
      
      for(int x =120; x < 280; x+=120)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(100, x, 210, 15);   
      }



      addKeyListener(new Key());
      setFocusable(true);//needed for keyboard input
      
      
      t = new Timer(5, new AnimationListener());

     
   }
  public void setColor(){
      if (SpaceO==true){
         ColO=2;
      }
      else ColO=1;
      if (SpaceX==true){
         ColX=2;
      }
      else ColX=1;
  }
   
  public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);  //Draw the buffered image we've stored as a field
   }
   
   
  
  public void begin()
   {
      t.start();  //Starts the timer!
   }

   public void animate()
   {
      //Start over with a blank background
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      
      //Move the square (when we add multiple objects, they'll all need to move and draw)
      redrawBoard();
      setColor();
      oSymbol.move();
      oSymbol.drawAll(myBuffer, ColO);
      xSymbol.move();
      xSymbol.drawAll(myBuffer, ColX);
      ActuallyDrawTheStuff();
      if (checkWin()==0){
         reset();
         //This is for bounce, replace powerup[0] with bounce in ur mind because that's what it is in the array
         powerup[0].changeXY(50,50);
         powerup[0].changeadY(5); //cause it to start to go down
         powerup[2].changeXY(50,50);
         powerup[2].changeadY(5);
      }
      if (checkWin()==1){
         reset();
         //SHAKE
         powerup[1].changeXY(50,50);
         powerup[1].changeadY(5); //cause it to start to go down
         powerup[1].changeadX(-5);
         
         //BLINK
         powerup[3].changeXY(50,50);
         powerup[3].changeadY(0); //cause it to start to go down
         powerup[3].changeadX(4);
         
         
      }
      if (checkWin()==2){
         reset();
         
      }
      //POWERUP SECTION 
      if(powerup[0].checkMove()){//This checks if Max height is too small thus if the thing is beginning to end
         powerup[0].changeXY(-20,-20);
         powerup[0].changeadY(0);  //setting it away and back to normal once everything is completed
         }       
         if(powerup[2].checkMove()){//This checks if Max height is too small thus if the thing is beginning to end
         powerup[2].changeXY(-20,-20);
         powerup[2].changeadY(0);    
      }
      powerup[0].move();
      powerup[0].draw(myBuffer);
      powerup[1].move();
      powerup[1].draw(myBuffer);
      powerup[2].move();
      powerup[2].draw(myBuffer);
      powerup[3].move();
      powerup[3].draw(myBuffer);
      
      //Call paintComponent
      repaint();
   }
   private class AnimationListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)  //Gets called over and over by the Timer
      {
         animate();  //...hence animation!
      }
   }
   
  private class Key extends KeyAdapter{
      public void keyPressed(KeyEvent e){
         if(e.getKeyCode() == KeyEvent.VK_DOWN && Down==true){
            if(SpaceO==true){
               oSymbol.dY(5);
            }
            if(SpaceX==true){
               xSymbol.dY(5);
            }
            
            Down= false;
            }
         //Check for 'g':
         if(e.getKeyCode() == KeyEvent.VK_LEFT  && Left==true){
            if(SpaceO==true){
               oSymbol.dX(-5);
            }
            if(SpaceX==true){
               xSymbol.dX(-5);
            }  
            Left= false;
           }
         //Check for 'b':
         if(e.getKeyCode() == KeyEvent.VK_RIGHT  && Right==true){
            if(SpaceO==true){
               oSymbol.dX(5);
            }
            if(SpaceX==true){
               xSymbol.dX(5);
            }
            Right= false;
            }
         if(e.getKeyCode() ==KeyEvent.VK_UP && Up==true){
            if(SpaceO==true){
               oSymbol.dY(-5);
            }
            if(SpaceX==true){
               xSymbol.dY(-5);
            }            
            Up= false;
           }
         if(e.getKeyCode() ==  KeyEvent.VK_SPACE){
            pressedSpace(); 
            if(SpaceO==true){
               SpaceO=false;
            }
            else if(SpaceO==false){
               SpaceO=true;
            }

            if(SpaceX==true){
               SpaceX=false;
            }
            else if(SpaceX==false){
               SpaceX=true;
            } 
               
         }
         }
          public void keyReleased(KeyEvent e)  //Note keyReleased is called when... a key is released!
      {
         if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(SpaceO==true){
               oSymbol.dY(0);
            }
            if(SpaceX==true){
               xSymbol.dY(0);
            }
            Down= true;
            }
         //Check for 'g':
         if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(SpaceO==true){
               oSymbol.dX(0);
            }
            if(SpaceX==true){
               xSymbol.dX(0);
            }  
 
            Left= true;
           }
         //Check for 'b':
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(SpaceO==true){
               oSymbol.dX(0);
            }
            if(SpaceX==true){
               xSymbol.dX(0);
            }
            Right= true;
            }
         if(e.getKeyCode() ==KeyEvent.VK_UP){
            if(SpaceO==true){
               oSymbol.dY(0);
            }
            if(SpaceX==true){
               xSymbol.dY(0);
            }    
            Up= true;
           }
           
      }
      }
  public void redrawBoard(){
   for(int x =160; x < 250; x+=80)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(x, 30, 10, 340);   
      }
      
      for(int x =120; x < 280; x+=120)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(100, x, 210, 15);   
      }
   }
   public void pressedSpace(){
      if (SpaceO==true){
         setOnArr(oSymbol.returnX(),oSymbol.returnY(),0);
      }
      
      if (SpaceX==true){
         setOnArr(xSymbol.returnX(),xSymbol.returnY(),1);
      } 
      oSymbol= new O(325, 320);
      xSymbol= new X(50, 350);
      
   }
   
   public void setOnArr(int xCord, int yCord, int zeroOrOne){
      //This will put the cords of the symbol and set it into the rightful spot of the array;
      int xVal=0;
      int yVal=0;
      
      for(int j = 0; j < 3; j++)
      {
         for(int k = 0; k < 3; k++)
         {
             if(xCord>=CentersX[j]-50 && xCord<=CentersX[j]+50){
                  xVal=j;
               }
             if(yCord>=CentersY[k]-70 && yCord<=CentersY[k]+70){
                  yVal=k;
               }           
            }            
         }
      if(Board[xVal][yVal]>1){
         Board[xVal][yVal]=zeroOrOne;
         
         }
         
       }
     

 
          

  
  public void ActuallyDrawTheStuff(){
       for(int j = 0; j < Board.length; j++)
      {
         for(int k = 0; k < Board[0].length; k++)
         {
             if(Board[j][k]==1){
                  drawX(CentersX[j],CentersY[k]);
               }
             if(Board[j][k]==0){
                  drawO(CentersX[j]-10,CentersY[k]-25);
            }            
         }
      }
      
      }
 
   
   public void drawX(int Xpoints, int Ypoints){
      int[] SquareX;
      int[] CircleX;
      SquareX = new int[12];
      CircleX = new int[12];
      myBuffer.setColor(new Color(238, 108, 77));
      SquareX[0]=Xpoints;
      CircleX[0]=Ypoints-15;
      SquareX[1]=Xpoints+7;
      CircleX[1]=Ypoints-30;
      SquareX[2]=Xpoints+15;
      CircleX[2]=Ypoints-15;
      SquareX[3]=Xpoints+7;
      CircleX[3]=Ypoints;
      SquareX[4]=Xpoints+15; 
      CircleX[4]=Ypoints+15;
      SquareX[5]=Xpoints+7;
      CircleX[5]=Ypoints+30;
      SquareX[6]=Xpoints;
      CircleX[6]=Ypoints+15;
      SquareX[7]=Xpoints-7;
      CircleX[7]=Ypoints+30;
      SquareX[8]=Xpoints-15;
      CircleX[8]=Ypoints+15;
      SquareX[9]=Xpoints-7;
      CircleX[9]=Ypoints;
      SquareX[10]=Xpoints-15;
      CircleX[10]=Ypoints-15;
      SquareX[11]=Xpoints-7;
      CircleX[11]=Ypoints-30;
      myBuffer.fillPolygon(SquareX, CircleX, 12);
   }
   
    public void drawO(int XFOpoints,int YFOpoints){
      myBuffer.setColor(new Color(21, 76, 121));
      myBuffer.fillOval(XFOpoints, YFOpoints, 25, 55);
      myBuffer.setColor(new Color(204,204,204));
      myBuffer.fillOval(XFOpoints+8, YFOpoints+16, 10, 22);
   }
   
   public boolean blankCanvas(){
   for(int j = 0; j < Board.length; j++)
      {
         for(int k = 0; k < Board[0].length; k++)
         {
             if(Board[j][k]==4){
             return true;
               }
            } 
                    
         }
         return false;
         }
         
         

   
   public int checkWin(){
      //Vertical 
      
      for(int j = 0; j < Board.length; j++)
      {
         int checker=0;
         for(int k = 0; k < Board[0].length; k++)
         {
            checker+=Board[j][k];        
         }
         if(checker==3){
            return 1;
         }
         if(checker==0){
            return 0;
         }
         
      }    
      for(int j = 0; j < Board.length; j++)
      {
         int checker=0;
         for(int k = 0; k < Board[0].length; k++)
         {
            checker+=Board[k][j];          
         }
         if(checker==3){
            return 1;
         }
         if(checker==0){
            return 0;
         }
      } 
      
      if(Board[0][0]+Board[1][1]+Board[2][2]==3||Board[2][0]+Board[1][1]+Board[0][2]==3){
         return 1;
      } 
      if(Board[0][0]+Board[1][1]+Board[2][2]==0||Board[2][0]+Board[1][1]+Board[0][2]==0){
         return 0;
      }
      if(!blankCanvas()){
         return 2;
      }
      return 3;
                 
   }

     
   



  public void reset(){
      SpaceO = false;
      SpaceX = true;
      oSymbol= new O(325, 320);
      xSymbol= new X(50, 350);
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      oSymbol.drawAll(myBuffer, 1);
      xSymbol.drawAll(myBuffer, 2);
      repaint();
      for(int x =160; x < 250; x+=80)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(x, 30, 10, 340);   
      }
      
      for(int x =120; x < 280; x+=120)  //Use a loop to draw equally spaced identical shapes; note x+=10 not x+=1.
      {
         myBuffer.setColor(new Color(93, 115, 126));  	
         myBuffer.fillRect(100, x, 210, 15);   
      }
      for(int j = 0; j < Board.length; j++)
      {
         for(int k = 0; k < Board[0].length; k++)
         {
             Board[j][k]=4;
               }
            }            
         }

   
   
}