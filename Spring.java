//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
// Spring Class

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Spring extends CZGameObject {
   
   // member variables
   private String direction;
   private int buttonLink;
   private String toggleSetting;
   private Timer t;
   
   //extends CZgameobject constructor with location and color
   public Spring(String type, int x, int y, String direction_in, int buttonLink, String toggleSetting, Color col) {
      super(type, x, y, 40, 40, col);
      direction = direction_in;
      this.toggleSetting = toggleSetting;
      this.buttonLink = buttonLink;
   }
   
   //draw spring
   public void draw(Graphics g, int centerx, int centery) {
      g.setColor(getColor());
      g.fillRect(getX()+centerx,getY()+centery,40,40);
   }
   
   // returns direction the spring goes
   public String getDirection() {
      return direction;
   }
   
   // returns what button is linked with the spring
   public int getButtonLink(){
      return buttonLink;
   }
   
   // returns whether the spring is one use or multiple
   public String getToggleSetting(){
      return toggleSetting;
   }
   
   //changes color
   public void toggleColor(){
      t = new Timer(2000, new SpringListener());
      t.start();
   }
   
   // resets spring after 2000 ms
   public class SpringListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         setColor(Color.GRAY);
         t.stop();
      }
   }
}