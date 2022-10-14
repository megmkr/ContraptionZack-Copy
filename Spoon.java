//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// Spoon Class

import java.awt.*;

public class Spoon extends CZGameObject{
   
   // member variables
   private String ifVis;
   
   //extends CZgameobject constructor with location and color
   public Spoon (String type, int x, int y, String visible){
      super(type, x, y, 50, 10, new Color(191, 134, 29));
      ifVis = visible;
      if (ifVis.equals("NOTVIS")) { // make spoon invisible if already collected
         setColor(new Color(0,0,0,0));
      }
   }
   
   // returns whether spoon is visible
   public String getVis() {
      return ifVis;
   }
   
   //makes spoon in/visible
   public void setVis(String input) {
      ifVis = input;
   }
   
   // draw spoon
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx,getY()+centery,4,35);
      g.fillOval(getX()+centerx-3,getY()+centery-13,10,15);
   }

}