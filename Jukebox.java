//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Jukebox extends CZGameObject {
   private int circleStart;
   private int counter;
   private int changeRadius = 0;
   //extends CZgameobject constructor with location and color BLUE
   public Jukebox(String type, int x, int y) {
      super(type, x, y, 50, 50, Color.CYAN);
      circleStart = x-5;
      Timer t = new Timer(60, new TimeListener());
      t.start();
   }
   //draw block
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx+9,getY()+centery,50,50);
      g.fillOval(circleStart+centerx+9, getY()+centery, 15, 50);
   }
   
   //animation
   public class TimeListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         //oscillates between two ints for bounce effect
         if (circleStart >= (getX()-5)){
            changeRadius = -1;
         }
         if(circleStart < (getX()-9)){
            changeRadius = 1;
         }
         
         circleStart += changeRadius;
      }
   }
}


