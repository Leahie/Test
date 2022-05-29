import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class Wave implements PowerUps{//implementing the interface, thus u need to use the methods/functions in there i.d getX, getY etc
   private int X;//X, Y cords 
   private int Y;
   private int adX;
   private int adY;
   private int maxHeight;
   private boolean sign; 
   
   
   public Wave(int xVal, int yVal){
      X=xVal;
      Y=yVal;//basically setable 
      adX=0;//using this to set whether to move the x or y cords up or down, because I'm bouncing I would leave adX the same, while adY would be positive since to go down it needs to go higher
      adY=0; //setting this u can change it to suit ur needs 
      maxHeight=400;
      sign = true; 
   }
   public int getX(){//Get X values, test the range
      return X;
   }
   public int getY(){ 
      return Y;
   }
   public void changeadX(int newX){//Change the shiftable X and Y vals 
      adX=newX;
   }
   public void changeadY(int newY){
      adY=newY;
   }
   public void changeXY(int xVal, int yVal){
      X=xVal;
      Y=yVal;
      maxHeight=400;
   
   }

   public void draw(Graphics myBuffer){ //Redraw during every animation frame
      myBuffer.fillRect(X, Y, 20, 20);
     
   }
   public void move(){//this is whether or not it should move
      X+=adX;
      Y+=adY;
      if(X%20==0){ 
         if(sign == true){ 
            adY= 5; 
         }
         else{
            adX=-5;
         }
      }
   
   }
   public boolean checkMove(){//change this to suit ur situation 
      if(maxHeight<10){
         return true;
      }
      return false;
      
   }

}