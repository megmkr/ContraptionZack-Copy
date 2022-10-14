//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
//Arrow class

import java.awt.*;

public class Arrow extends CZGameObject{
   
   //member variables
   private String direction;
   private String next;
   private boolean fake = false;
   
 //extends CZgameobject constructor with location and color GRAY
   public Arrow(String type, int x, int y, String next_in, String direction_in, int realArrowCheck) {
      super(type, x, y, 50, 50, Color.GRAY);
      
      next = next_in;
      direction = direction_in;
      
      if (realArrowCheck==0) {
         fake = true;
      }
   }
   
   //returns the next area
   public String getNext(){
      return next;
   }
   
   // returns whether the arrow points to another screen
   public boolean getFake() {
      return fake;
   }
   
   // returns the direction the arrow points
   public String getDirection() {
      return direction;
   }
   
   //draw block
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(Color.GRAY);
      g.fillRect(getX()+centerx,getY()+centery,50,50);
      
      // draw arrow on top of block
      g.setColor(Color.RED);
      int[] xPoints = new int[3]; // x-points
      int[] yPoints = new int[3]; // y-points
      // arrays are necessary for fillPolygon() method
      
      // each of these draws stem of arrow based on direction arrow points
      if(direction.equals("UP")) {
         xPoints[0] = getX()+centerx+25;
         xPoints[1] = getX()+centerx+3;
         xPoints[2] = getX()+centerx+47;
         yPoints[0] = getY()+centery+3;
         yPoints[1] = getY()+centery+25;
         yPoints[2] = getY()+centery+25;
         g.fillRect(getX()+centerx+16, getY()+centery+25, 18, 22);
         
      }
      else if(direction.equals("DOWN")) {
         xPoints[0] = getX()+centerx+25;
         xPoints[1] = getX()+centerx+3;
         xPoints[2] = getX()+centerx+47;
         yPoints[0] = getY()+centery+47;
         yPoints[1] = getY()+centery+25;
         yPoints[2] = getY()+centery+25;
         g.fillRect(getX()+centerx+16, getY()+centery+3, 18, 22);
      }
      else if(direction.equals("RIGHT")) {
         xPoints[0] = getX()+centerx+25;
         xPoints[1] = getX()+centerx+47;
         xPoints[2] = getX()+centerx+25;
         yPoints[0] = getY()+centery+3;
         yPoints[1] = getY()+centery+25;
         yPoints[2] = getY()+centery+47;
         g.fillRect(getX()+centerx+3, getY()+centery+16, 22, 18);
      }
      else if(direction.equals("LEFT")) {
         xPoints[0] = getX()+centerx+25;
         xPoints[1] = getX()+centerx+3;
         xPoints[2] = getX()+centerx+25;
         yPoints[0] = getY()+centery+3;
         yPoints[1] = getY()+centery+25;
         yPoints[2] = getY()+centery+47;
         g.fillRect(getX()+centerx+25, getY()+centery+16, 22, 18);
      }
      
      // draws head of arrow
      g.fillPolygon(xPoints, yPoints, 3);
    
   }
}