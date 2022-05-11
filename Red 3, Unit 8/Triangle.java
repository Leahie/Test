import javax.swing.*;
import java.awt.*;
public class Triangle{
   private int[] x;
   private int[] y;
   private Color col;
   private int height;
   private int length;
   private int dx;
   private int dy;
   public Triangle(int r, int g, int b, int height, int width){
      col=new Color(r, g, b);
      x = new int[3];
      y = new int[3];
      x[0]=200-width/2;
      x[1]=200+width/2;
      y[0]=400;
      y[1]=400;
      x[2]=200;
      y[2]=400-height;
      dx=0;
      dy=0;
   }
   public void move(){
      for(int i=0;i<3;i++){
         x[i]+=dx;
      }
      for(int i=0;i<3;i++){
         y[i]+=dy;
      }
   }
   public void draw(Graphics graph){
      graph.setColor(col);
      graph.fillPolygon(x, y, 3);
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
   
}