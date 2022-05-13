import javax.swing.*;
import java.awt.*;

public class O extends Symbol{
   private int x; //center -->
   private int y;
   public O(int xPlace, int yPlace){
      super(xPlace,yPlace);
      x=xPlace;
      y=yPlace;
   }
   
   public void draw(Graphics g, int xV,int yV){
      g.setColor(new Color(190, 229, 181));
      g.drawOval(xV, yV, 60, 60);
      g.setColor(new Color(190, 229, 181, 1));
      g.drawOval(xV, yV, 10, 10);
   }

}