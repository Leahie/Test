import javax.swing.*;
import java.awt.*;

public class X extends Symbol{
   private int x; //center -->
   private int y;
   int[] xC;
   int[] yC;
   public X(int xPlace, int yPlace){
      super(xPlace,yPlace);
      x=xPlace;
      y=yPlace;
      xC = new int[12];
      yC = new int[12];
   }
   
   public void draw(Graphics g, int xV,int yV){
      g.setColor(new Color(238, 108, 77));
      xC[0]=x;
      yC[0]=y-15;
      xC[1]=x+15;
      yC[1]=y-30;
      xC[2]=x+30;
      yC[2]=y-15;
      xC[3]=x+15;
      yC[3]=y;
      xC[4]=x+30; 
      yC[4]=y+15;
      xC[5]=x+15;
      yC[5]=y+30;
      xC[6]=x;
      yC[6]=y+15;
      xC[7]=x-15;
      yC[7]=y+30;
      xC[8]=x-30;
      yC[8]=y+15;
      xC[9]=x-15;
      yC[9]=y;
      xC[10]=x-30;
      yC[10]=y-15;
      xC[11]=x-15;
      yC[11]=y-30;
      g.fillPolygon(xC, yC, 12);
   }

}