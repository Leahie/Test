import javax.swing.*;
import java.awt.*;

public class O extends Symbol{
   Color colNorm;
   public O(int xPlace, int yPlace){
      super(xPlace,yPlace);
   }
   public void color(int c){//if number is even do colSelected if number is odd do other
      if (c%2==0){
       colNorm = new Color(21, 76, 121);
       }
      else if (c%2==1){
       colNorm = new Color(51, 32, 42);
       }
   }

   
   public void draw(Graphics g, int p, int x,int y){
      color(p);
      g.setColor(colNorm);
      g.fillOval(x, y, 25, 55);
      g.setColor(new Color(204,204,204));
      g.fillOval(x+8, y+16, 10, 22);
   }

}