import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public interface PowerUps
{
   public int getX();
   public int getY();
   public void changeXY(int xVal, int yVal);
   public void changeadX(int newX);
   public void changeadY(int newY);
   public void draw(Graphics myBuffer);
   public void move();
   public boolean checkMove();
   //8) Why doesn't this interface need toString() defined? Because it already has status report and none of the plants use toString
}