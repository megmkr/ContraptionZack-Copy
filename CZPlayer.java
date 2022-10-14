//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;

public class CZPlayer extends CZGameObject {
   //class members 
   private int rows, columns, endPos;
   private boolean isSpringing = false;
   private boolean isMoving;
   private Timer t;
   private int counter = 0;
   
   //constructor
   public CZPlayer(String type, int x, int y) {
      super(type, x, y, 25, 25, Color.BLUE);
   }
   
   //draw method for player
   public void draw(Graphics g) {
      counter++;
      if(counter % 80 < 40 && isMoving){
         g.setColor(new Color(123, 224, 221));
      }else{
         g.setColor(getColor());
      }
      g.fillRect(getX(),getY(),25,25);
   }
   //
   public void spring(int endPos) {
      this.endPos = endPos;
      isSpringing = true;
      t = new Timer(5, new TimeListener());
      t.start();
   }
   //return is springing which will stop all player movement
   public boolean getIsSpringing(){
      return isSpringing;
   }
   //is moving boolean for player animation
   public void setIsMoving(boolean isMoving){
      this.isMoving = isMoving;
   }
   
   public class TimeListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
            //move right
            if(endPos > getX()) {
               setX(getX()+1);
            }
            //move left
            else if(endPos < getX()) {
               setX(getX()-1);
            }
            //stop moving if reached desired position
            else if(endPos == getX()){
               isSpringing = false;
               t.stop();
            }   
        
      }
   }
}