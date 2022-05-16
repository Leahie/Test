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
   }
}