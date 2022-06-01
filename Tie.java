import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class Tie implements PowerUps{//implementing the interface, thus u need to use the methods/functions in there i.d getX, getY etc
   private int X;//X, Y cords 
   private int Y;
   private int adX;
   private int adY;
   int[] xT;
   int[] yT;
   int[] xI;
   int[] yI;
   //this is for Bounce u might not need it
   private int length;
   private int width;
   private boolean check;
   
   
   public Tie(int xVal, int yVal){
      X=xVal;
      Y=yVal;//basically setable 
      adX=0;//using this to set whether to move the x or y cords up or down, because I'm bouncing I would leave adX the same, while adY would be positive since to go down it needs to go higher
      adY=0; //setting this u can change it to suit ur needs `
      check=true;
      xT = new int[8];
      yT = new int[8];
      xI = new int[4];
      yI = new int[4];
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
     

   }

   public void draw(Graphics myBuffer){ //Redraw during every animation frame
      myBuffer.setColor(new Color(238, 108, 77));
      myBuffer.fillRect(X, Y, width, 20);
      //T
      xT[0]=X-25;
      yT[0]=Y-8;
      xT[1]=X-10;
      yT[1]=Y-8;//close up right 
      xT[2]=X-10;
      yT[2]=Y-2;
      xT[3]=X-15;
      yT[3]=Y-2;
      xT[4]=X-15;
      yT[4]=Y+10;
      xT[5]=X-20;
      yT[5]=Y+10;
      xT[6]=X-20;
      yT[6]=Y-2;
      xT[7]=X-25;
      yT[7]=Y-2;
      //I   
      xI[0]=X+3;
      yI[0]=Y-8;
      xI[1]=X-3;
      yI[1]=Y-8;
      xI[2]=X-3;
      yI[2]=Y+8;
      xI[3]=X+3;
      yI[3]=Y+8; 
      //E
          
   }
   public void move(){//this is whether or not it should move
      X+=adX;
      Y+=adY;
      
   }
      

   
   public boolean checkMove(){//change this to suit ur situation 
      if(Y>400){
         return true;
      }
      return false;
      
   }
   
}