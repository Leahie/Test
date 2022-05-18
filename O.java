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
   
   public void draw(Graphics g){
      g.setColor(new Color(190, 229, 181));
      g.drawOval(x, y, 60  , 60);
      g.setColor(new Color(190, 229, 181, 1));
      g.drawOval(x, y, 10, 10);
   }

}
