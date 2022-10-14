//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public class TButton extends Button{
   //class members
   private Spikes thisSpike;
   private Color orgColor;
   private int delay;
   
   //extends constructor with color
   public TButton(String type, int x, int y, Color color, Color changeCol, int spikeNum){
      super(type, x, y, color, changeCol, spikeNum);
      orgColor = color;
      
   }
   //draw timed button circle
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillOval(getX()+centerx,getY()+centery,40,40);
   }
   //start timer when button is pressed
   public void startTimer(Spikes otherSpike){
      Timer t = new Timer(30000, new TimeListener()); //delay for 30 seconds before going back to original color
      thisSpike = otherSpike;
      t.start();
   }
   //accessor for orgColor for filewriting
   public Color getOrgColor(){
      return orgColor;
   }
   public class TimeListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         //change spike size
         if(thisSpike.getSizeX() == 10){
            thisSpike.setSizeX(15); 
            thisSpike.setSizeY(15);
         }else{
            thisSpike.setSizeX(10);
            thisSpike.setSizeY(10);
         }
         //set back to original color
         setColor(orgColor);
         
      }
   }   
}