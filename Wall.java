//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.awt.*;

public class Wall extends CZGameObject {
   //extends CZgameobject constructor with location and color
   public Wall (String type, int x, int y, int size1, int size2, Color color) {
      super(type, x, y, size1, size2, color);
    
   }
   //draw block
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx,getY()+centery,getSizeX(),getSizeY());
   }
}