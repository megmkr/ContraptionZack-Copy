//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// river Class

import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public class River extends Wall{
   
   // member variables
   private int[] waves = new int[20];
   
   // extends constructor with location and color
   public River(String type, int x, int y, int sizeX, int sizeY, Color color){
      super(type, x, y, sizeX, sizeY, color);
      
      for(int i=0; i<waves.length; i++){
         waves[i] = (i*15)+50;
      }
      
      Timer t = new Timer(15, new RiverListener());
      t.start();
   }
   
   // draws river
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx,getY()+centery,getSizeX(),getSizeY());
      g.setColor(new Color(25, 35, 145));
      for(int i=0; i<waves.length; i++){
         g.drawLine(getX()+centerx, waves[i]+centery, getX()+40+centerx, waves[i]+centery);
      }
   }
   
   // animates river
   public class RiverListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
      
         for(int i = 0; i< waves.length;i++){
            waves[i] = (waves[i]+1);
            
            if(waves[i] > 350){
               waves[i] = 50;
            }
         }
      }
   }
}