//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023

import java.awt.*;
import javax.swing.*;
import java.lang.Math;

public class CZGameObject {
   private Color color;
   private int x, y;
   private int sizeX, sizeY;
   private String type;
   
   public CZGameObject(String type, int x1, int y1, int sizeX, int sizeY, Color col) {
   //constructor initializes location and color
      x = x1;
      y = y1;
      color = col;
      this.sizeX = sizeX;
      this.sizeY = sizeY;
      this.type = type;
   }
   
   //variable accessors/setters
   public Color getColor() {
      return color;
   }
   public void setColor(Color col) {
      color = col;
   }
   
   public int getX() {
      return x;
   }
   
   public void setX(int x1) {
      x = x1;
   }
   
   public int getY() {
      return y;
   }
   
   public void setY(int y1) {
      y = y1;
   }
   public int getSizeX(){
      return sizeX;
   }
   
   public int getSizeY(){
      return sizeY;
   }
   public void setSizeX(int set){
      sizeX = set;
   }
   
   public void setSizeY(int set){
      sizeY = set;
   }
   
   public String getType() {
      return type;
   }

   //determines if two gameobjects collide
   public boolean collides(CZGameObject obj, int centerX, int centerY) {
      if (obj instanceof Arrow) {
         Arrow tempobj = (Arrow)obj;
         if (tempobj.getFake()) { //"fake" arrow does not change screens, does not collide
            return false;
         }
      }
      
      if (obj instanceof Spikes) {
         Spikes tempobj = (Spikes)obj;
         if (tempobj.getSizeX() == 10) { //if spikes are down, does not collide
            return false;
         }
      }
      
      if (obj == this || obj == null) {
         return false;
      }
      int topThis = y, topOther = obj.getY()+centerY, bottomThis = y+24, bottomOther = obj.getY()+obj.getSizeY()+centerY;
      int leftThis = x, leftOther = obj.getX()+centerX, rightThis = x+24, rightOther = obj.getX()+obj.getSizeX()+centerX;
      
      if (obj instanceof Spring && obj.getColor() != Color.BLACK) { //change collision center of spring
         topThis = y;
         bottomThis = y+25;
         leftThis = x+5;
         rightThis = x+20;
         
         topOther = obj.getY()+centerY + 15;
         bottomOther = obj.getY()+centerY + 25;
         leftOther = obj.getX()+centerX+ 15;
         rightOther = obj.getX()+centerX + 25;
         
      }
      
      if (obj instanceof Arrow) { //only collide at edge of arrow
         Arrow tempobj = (Arrow)obj;
         if (tempobj.getDirection().equals("UP")) {
            bottomThis = y+16;
            bottomOther = obj.getY()+2+centerY;
         }
         else if (tempobj.getDirection().equals("DOWN")) {
            topThis = y+6;
            topOther = obj.getY()+centerY+48;
         }
         else if (tempobj.getDirection().equals("LEFT")) {
            rightThis = x+16;
            rightOther = obj.getX()+2+centerX;
         }
         else if (tempobj.getDirection().equals("RIGHT")) {
            leftThis = x+6;
            leftOther = obj.getX()+centerX+48;
         }
      }
      
      if (!(obj instanceof Button)) { //only move player back if NOT a button, move freely on button
         if(!((bottomThis<topOther) || (topThis>bottomOther) || (leftThis>rightOther) || (rightThis<leftOther))){
            //pick up spoon if collides 
            if (obj instanceof Spoon) {
               obj.setColor(new Color(0,0,0,0));
               return false;
            }
            if (obj instanceof TWall) {
               //if collides left
               if((rightOther-leftThis)<15 && (bottomThis>topOther || rightThis>leftOther) ){
                  setX(rightOther+1); 
               }
               //if collides right
               if((rightThis-leftOther)<15 && (bottomThis>topOther || rightThis>leftOther)){
                  setX(leftOther - 25); 
               }
               return false;
            }
            //if collides down
            if((bottomThis-topOther)<5 && (leftThis<rightOther || rightThis>leftOther) ){
               setY(topOther - 25);
            }
            //if collides up
            if((bottomOther-topThis)<5 && (leftThis<rightOther || rightThis>leftOther) ){
               setY(bottomOther+1); 
            }
            //if collides left
            if((rightOther-leftThis)<5 && (bottomThis>topOther || rightThis>leftOther) ){
               setX(rightOther+1); 
            }
            //if collides right
            if((rightThis-leftOther)<5 && (bottomThis>topOther || rightThis>leftOther)){
               setX(leftOther - 25); 
            }
         }
      } 
      return !((bottomThis<topOther) || (topThis>bottomOther) || (leftThis>rightOther) || (rightThis<leftOther));
   }
   
   public void draw(Graphics g, int centerx, int centery) {}
}

