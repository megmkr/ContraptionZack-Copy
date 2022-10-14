//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public class TWall extends Wall{
   private int delay; //ms delay based on what order the door is
   private String direction, copyDirection; //what direction the door is going to move
   private Timer t; //timer for door order
   private int copyX, copyY; //copy x and y for creating back file

   
   
   //constructor
   public TWall(String type, int x, int y, int delay, String direction){
      super(type, x, y, 20, 25, new Color(50, 131, 168));
      copyX = x;
      copyY = y;
      this.delay = delay;
      this.direction = direction;
      copyDirection = direction;
      
      t = new Timer(delay, new TimeListener());
      t.setDelay(4000); //set new delay to close door 4 sec after opening
      t.start();
      
   }
   //accessors
   public int getDelay(){
      return delay;
   }
   public String getDirection(){
      return direction;
   }
   public String getCopyDirection(){
      return copyDirection;
   }
   public int getCopyX(){
      return copyX;
   }
   public int getCopyY(){
      return copyY;
   }
   
   public class TimeListener implements ActionListener {
      private int doorPos; //where the door needs to go upon timer call
      private int counter = 0; // keep track of when we most recently open/closed this door
      private Timer doorTimer; //timer for individual door animation
      
      
      public void actionPerformed(ActionEvent e) {
         //only open and close doors twice, then wait a full round
         if (counter == 0 || counter == 1){
            //close/open doors
            if(direction.equals("UP")){
               doorTimer = new Timer(10, new DoorListener());
               doorTimer.start();
               doorPos = (getY()-25);
               direction = "DOWN";
            }else if(direction.equals("DOWN")){
               doorTimer = new Timer(10, new DoorListener());
               doorTimer.start();
               doorPos = (getY()+25);
               direction = "UP";
            }
         }   
         counter++;
         if(counter == 4) //keep track of when to move
            counter = 0;
      }
      
      public class DoorListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            //individual door animation
            if(getY() > doorPos){
               setY(getY()-1);
            }else if(getY() < doorPos){
               setY(getY()+1);
            }else if(getY() == doorPos){
               doorTimer.stop(); //stop timer when door is in desired position
            }
         }
      }
      
   }
   
   
}