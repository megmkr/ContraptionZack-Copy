//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// Button Class

import java.awt.*;

public class Button extends CZGameObject {
   
   // member variables
   Color changeCol;
   int spikeNum;
   
   // extends CZgameobject constructor with location and color
   public Button(String type, int x, int y, Color color, Color changeCol, int spikeNum) {
      super(type, x, y, 40, 40, color);
      this.changeCol = changeCol;
      this.spikeNum = spikeNum;
   }
   
   // returns changed color (after pressing button)
   public Color getColorChange() {
      return changeCol;
   }
   
   //if corresponding to spike color
   public int getSpikeNum() { 
      return spikeNum;
   }
   
   //draw button
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      if (spikeNum == 999) {
         g.fillOval(getX()+centerx,getY()+centery,40,40);
      }
      else {
         g.fillRect(getX()+centerx,getY()+centery,40,40);
      }
   }
}