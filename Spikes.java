//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// Spikes Class

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;


public class Spikes extends CZGameObject {
   
   // member variables
   private String direction;
   private int buttonNum;
   private int sizeCorrect;
   private Timer t;
   
   //extends CZgameobject constructor with location and color
   public Spikes(String type, int x, int y, Color col, String direction_in, int buttonNum1, int size1) {
      super(type, x, y, size1, size1, col);
      direction = direction_in;
      buttonNum = buttonNum1;
   }
   
   //if corresponding to button color
   public int getButtonNum() { 
      return buttonNum;
   }
   
   // returns direction of spikes (VERT or HORZ)
   public String getDirection() {
      return direction;
   }
   
   //draw spikes
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      int tempx = getX()+centerx;
      int tempy = getY()+centery;
      if (direction.equals("VERT")) { //vertical orientation spikes
         g.fillOval(tempx,tempy,getSizeX(),getSizeY());
         g.fillOval(tempx,tempy+15,getSizeX(),getSizeY());
         g.fillOval(tempx,tempy-15,getSizeX(),getSizeY());
      }
      if (direction.equals("HORZ")) { //horizontal orientation spikes
         g.fillOval(tempx,tempy,getSizeX(),getSizeY());
         g.fillOval(tempx+15,tempy,getSizeX(),getSizeY());
         g.fillOval(tempx-15,tempy,getSizeX(),getSizeY());
      }
   }
   
   // animates spikes changing states
   public void toggleSize(int size){
      t = new Timer(20, new SpikeListener()); 
      sizeCorrect = size;
      t.start();
   }
   
   // every 20 ms (set when defining timer) animate spikes
   public class SpikeListener implements ActionListener { 
      public void actionPerformed(ActionEvent e) {;
         if(sizeCorrect == 15 && getSizeX()<15){
            setSizeX(getSizeX()+1);
            setSizeY(getSizeY()+1);
         }else if(sizeCorrect == 10 && getSizeX() > 10){
            setSizeX(getSizeX()-1);
            setSizeY(getSizeY()-1);
         }else{
            t.stop();
         }
      }
   }
}