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
      
      setColor();
      oSymbol.move();
      oSymbol.drawAll(myBuffer, ColO);
      xSymbol.move();
      xSymbol.drawAll(myBuffer, ColX);
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
  

  public void reset(){
      oSymbol= new O(325, 320);
      xSymbol= new X(50, 350);
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      oSymbol.drawAll(myBuffer, 1);
      xSymbol.drawAll(myBuffer, 2);
      repaint();
   }
   
}