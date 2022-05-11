import javax.swing.*;
import java.awt.*;
public class Enemy{
   private int[] x;
   private int[] y;
   private Color col;
   private int height;
   private int length;
   private int dx;
   private int dy;
   public Enemy(int r, int g, int b, int hi, int width){
      col=new Color(r, g, b);
      x = new int[4];
      y = new int[4];
      x[0]=20-width/2;
      x[1]=20+width/2;
      y[0]=50;
      y[1]=50;
      x[2]=20+width/2;
      y[2]=50-hi;
      x[3]=20-width/2;
      y[3]=50-hi;
      dx=2;
      dy=0;
      length=width;
      height=hi;
      
   }
   public void move(){
      if(getX()>=390){
         x[2]=10+length;
         x[1]=10+length;
         x[3]=10;
         x[0]=10;
      }
      for(int i=0;i<4;i++){
         x[i]+=dx;
      }
      for(int i=0;i<4;i++){
         y[i]+=dy;
      }
   }
   public void draw(Graphics graph){
      graph.setColor(col);
      graph.fillPolygon(x, y, 4);
   }
   public void chngX(int dix){
      dx=dix;
   }
   public void chngY(int diy){
      dy=diy;
   }
   public int getX(){//for rocket
      return x[2];
     }
   public int getY(){
      return y[2];
   }
   
   public void setXY(int X, int Y){
      x[0]= X-length/2;
      x[1]=X+length/2;
      y[0]=Y;
      y[1]=Y;
      x[2]=X+length/2;
      y[2]=Y-height;
      x[3]=X-length/2;
      y[3]=Y-height;
   }
}