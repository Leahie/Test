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
   
   public Panel(){
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      oSymbol = new O(300, 350);
      xSymbol = new X(35, 320);
      oSymbol.draw(myBuffer);
      xSymbol.draw(myBuffer);
      
   }
  public void paintComponent(Graphics g){
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);  //Draw the buffered image we've stored as a field
   }
   
  public void reset(){
      oSymbol= new O(300, 350);
      xSymbol= new X(35, 320);
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      oSymbol.draw(myBuffer);
      xSymbol.draw(myBuffer);
      repaint();
   }
   
}