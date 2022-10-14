//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// Barrier class

import java.awt.*;

public class Barrier extends CZGameObject {
   //extends CZgameobject constructor with location and color
   public Barrier(String type, int x, int y, Color color) {
      super(type, x, y, 50,50, color);
   }
   
   //draw block
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx,getY()+centery,50,50);
   }
}