import javax.swing.*;
import java.awt.*;

public class X extends Symbol{
   int[] xC;
   int[] yC;
   Color colNorm;
   public X(int xPlace, int yPlace){
      super(xPlace,yPlace);
      xC = new int[12];
      yC = new int[12];
   }   
   
   public void color(int c){//if number is even do colSelected if number is odd do other
      if (c%2==0){
       colNorm = new Color(238, 108, 77);
       }
      else if (c%2==1){
       colNorm = new Color(51, 32, 42);
       }
   }
   public void draw(Graphics g, int p, int x, int y){
      color(p);
      g.setColor(colNorm);
      xC[0]=x;
      yC[0]=y-15;
      xC[1]=x+7;
      yC[1]=y-30;
      xC[2]=x+15;
      yC[2]=y-15;
      xC[3]=x+7;
      yC[3]=y;
      xC[4]=x+15; 
      yC[4]=y+15;
      xC[5]=x+7;
      yC[5]=y+30;
      xC[6]=x;
      yC[6]=y+15;
      xC[7]=x-7;
      yC[7]=y+30;
      xC[8]=x-15;
      yC[8]=y+15;
      xC[9]=x-7;
      yC[9]=y;
      xC[10]=x-15;
      yC[10]=y-15;
      xC[11]=x-7;
      yC[11]=y-30;
      g.fillPolygon(xC, yC, 12);
   }

}