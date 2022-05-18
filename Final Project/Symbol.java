import javax.swing.*;
import java.awt.*;
public abstract class Symbol{
   private int x; //center -->
   private int y;

   public Symbol(int xPlace, int yPlace){
      x=xPlace;
      y=yPlace;

   }  
   /*************************************/ 
   public abstract void draw(Graphics g); //Draws the correct shape 
   /*************************************/
   public void move(int dx,int dy){
      x+=dx;
      y+=dy;
   }
} 