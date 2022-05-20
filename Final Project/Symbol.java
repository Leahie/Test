import javax.swing.*;
import java.awt.*;
public abstract class Symbol{
   private int x; //center -->
   private int y;
   private int dx;
   private int dy;

   public Symbol(int xPlace, int yPlace){
      x=xPlace;
      y=yPlace;
      dx=0;
      dy=0;

   }  
   /*************************************/ 
   public abstract void draw(Graphics g, int p, int r, int t); //Draws the correct shape 
   public abstract void color(int c);
   
   /*************************************/
   public void dX(int newX){
      dx=newX;
   }
   public void dY(int newY){
      dy=newY;
   }
   public void move(){
      x=x+dx;
      y=y+dy;
   }
   public void drawAll(Graphics f, int n){
      draw(f,n,x,y);
   }
} 