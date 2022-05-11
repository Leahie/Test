import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Lab8RED3 
{
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("GUI + a graphics JFrame");
      frame.setSize(800, 430);
      frame.setLocation(20, 20);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GUIPanel());
      frame.setVisible(true);
   }
}
class GUIPanel extends JPanel {
   JTextField r;
   JTextField g;
   JTextField b;
   JTextField Height;
   JTextField Width;
   TrianglePanel graph;
   
   public GUIPanel(){
  
      //North
      setLayout(new BorderLayout());
      JLabel title = new JLabel("The Moving Triangle");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      add(title, BorderLayout.NORTH);  
      
      //Center Graph
      graph = new TrianglePanel();
      add(graph);
      
      //South
      JButton reset = new JButton("Reset With a New Triangle!");
      reset.addActionListener(new resetListener());
      add(reset, BorderLayout.SOUTH);
      
      //West
      JPanel westSubpanel = new JPanel();
      westSubpanel.setLayout(new GridLayout(5, 2));
      //Red
      JLabel desc1 = new JLabel("Red: ");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      desc1.setHorizontalAlignment(SwingConstants.RIGHT);
      westSubpanel.add(desc1);
      r = new JTextField("0", 10);
      r.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(r);
      //Green
      JLabel desc2 = new JLabel("Green: ");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      desc2.setHorizontalAlignment(SwingConstants.RIGHT);
      westSubpanel.add(desc2);
      g = new JTextField("0", 10);
      g.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(g);
      //Blue
      JLabel desc3 = new JLabel("Blue: ");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      desc3.setHorizontalAlignment(SwingConstants.RIGHT);
      westSubpanel.add(desc3);
      b = new JTextField("0", 10);
      b.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(b);
      //Height
      JLabel desc4 = new JLabel("Height: ");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      desc4.setHorizontalAlignment(SwingConstants.RIGHT);
      westSubpanel.add(desc4);
      Height = new JTextField("0", 10);
      Height.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(Height);
      //Width
      JLabel desc5 = new JLabel("Width: ");
      title.setFont(new Font("Serif", Font.BOLD, 20));
      desc5.setHorizontalAlignment(SwingConstants.RIGHT);
      westSubpanel.add(desc5);
      Width = new JTextField("0", 10);
      Width.setHorizontalAlignment(SwingConstants.CENTER);
      westSubpanel.add(Width);
      //end
      add(westSubpanel, BorderLayout.WEST);
      
      //Center Graph
      graph = new TrianglePanel();
      add(graph);
   }
   private class resetListener implements ActionListener{
      public void actionPerformed(ActionEvent e)
         {
            graph.reset(Integer.parseInt(r.getText()),Integer.parseInt(g.getText()),Integer.parseInt(b.getText()), Integer.parseInt(Height.getText()), Integer.parseInt(Width.getText()));
            graph.requestFocusInWindow();//Required 
            graph.begin();
         }

   }



}
class TrianglePanel extends JPanel{
   public static final int FRAME = 400;
   private static final Color BACKGROUND = new Color(204, 204, 204);
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Triangle myTriangle;
   private MovingSquare sq;
   private Enemy em;
   private Timer t;
   private boolean Up;
   private boolean Down;
   private boolean Left;
   private boolean Right;
   private boolean Space;
   
   public TrianglePanel(){
      myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
      myBuffer = myImage.getGraphics();
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      myTriangle = new Triangle(31, 122, 140, 10, 10);
      sq = new MovingSquare();
      em = new Enemy(31, 122, 140, 20, 20);
      myTriangle.draw(myBuffer);
      Up=false;
      Down=false;
      Left=false;
      Right=false;
      Space=false;
      
      
      addKeyListener(new Key());
      setFocusable(true);//needed for keyboard input
      
      t = new Timer(5, new AnimationListener());
      
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
      myTriangle.move();
      myTriangle.draw(myBuffer);
      sq.move();
      sq.draw(myBuffer);
      em.move();
      em.draw(myBuffer);
      
      //check if they interact
      //topleft
      if(sq.getX()>=em.getX()-20&&sq.getX()<=em.getX()&&sq.getY()<=em.getY()&&sq.getY()>=em.getY()-20){
         em.chngX(0);
         em.chngY(0);
         em.setXY(-200,-200);
      }
      //topright
      if(sq.getX()+10>=em.getX()-20&&sq.getX()+10<=em.getX()&&sq.getY()<=em.getY()&&sq.getY()>=em.getY()-20){
         em.chngX(0);
         em.chngY(0);
         em.setXY(-200,-200);    
      }

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
   
   public void reset(int r, int g, int b, int height, int width){
      myTriangle= new Triangle(r, g, b, height, width);
      em = new Enemy(31, 122, 140, 20, 20);
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,FRAME,FRAME);
      myTriangle.draw(myBuffer);
      em.draw(myBuffer);
      repaint();
   }
    private class Key extends KeyAdapter  //This subpanel must have focus for keyboard input to register (see above)
   {
      public void keyPressed(KeyEvent e)  //We override this method; "e" contains a lot of information...
      {
      	//...including a Key Code, which will let us see which key the user pressed.  Check for 'r':
         if(e.getKeyCode() == KeyEvent.VK_DOWN && Down==true){
            myTriangle.chngY(5);
            Down= false;
            }
         //Check for 'g':
         if(e.getKeyCode() == KeyEvent.VK_LEFT  && Left==true){
            myTriangle.chngX(-5);  
            Left= false;
           }
         //Check for 'b':
         if(e.getKeyCode() == KeyEvent.VK_RIGHT  && Right==true){
            myTriangle.chngX(5);
            Right= false;
            }
         if(e.getKeyCode() ==KeyEvent.VK_UP && Up==true){
            myTriangle.chngY(-5);
            Up= false;
           }
         if(e.getKeyCode() ==  KeyEvent.VK_SPACE && sq.getY()<=-10){
            sq.setXY(myTriangle.getX(),myTriangle.getY());
            sq.addy(-10);
            Space=true;
         }
         }
         public void keyReleased(KeyEvent e)  //Note keyReleased is called when... a key is released!
      {
         if(e.getKeyCode() == KeyEvent.VK_DOWN){
            myTriangle.chngY(0);
            Down= true;
            }
         //Check for 'g':
         if(e.getKeyCode() == KeyEvent.VK_LEFT){
            myTriangle.chngX(0);  
            Left= true;
           }
         //Check for 'b':
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            myTriangle.chngX(0);
            Right= true;
            }
         if(e.getKeyCode() ==KeyEvent.VK_UP){
            myTriangle.chngY(0);
            Up= true;
           }
           
      }
      
         
   }
}


class MovingSquare
{
   private int myX;
   private int myY;
   private int mySize;
   private Color myColor;
   private int mydy;
      
    // constructors
   public MovingSquare()
   {
      mySize = 10;
      myY = -10;
      myX = 200;
      myColor = new Color(10, 10, 10);
      mydy = 0;
   }
      
   //instance methods
   public void move()
   {
      myY += mydy;  //Move a certain distance upwards - this will happen every animation step
      if(myY < -10)   //If we reach the top of the screen, reset
      {
         mydy=0;
      }
   }
   
   public void addy(int dy)  //Allows the keyboard listener to modify animated behavior
   {
      mydy=dy;
   }
   
   public void setXY(int Tx, int Ty){
      myX = Tx;
      myY = Ty;
   }
        
   public void draw(Graphics myBuffer) 
   {
      myBuffer.setColor(myColor); 
      myBuffer.fillRect(myX-mySize/2, myY, mySize, mySize);
   }
   public int getY()
   {
      return myY;
   }
   public int getX(){
      return myX-mySize/2;
   }
}
