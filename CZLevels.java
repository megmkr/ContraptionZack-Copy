//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
//builds the levels (areas) from a .txt file


import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.*;

public class CZLevels {
   //member variables
   private int [][] leveldata; //arrays for data
   private int size1, size2, objectListSize, centerx, centery, barriercount;
   private int playerx, playery;
   private ArrayList<CZGameObject> objects = new ArrayList<CZGameObject>();
   
   //constructor takes file name as a String
   public CZLevels(String fileName) {
      if (fileName.equals("WIN")) { // if player wins, display win message then close the program when they press the "ok" button
         winMessage();
         System.exit(1);
      }
      try { // if the next area is not "WIN" then load the next area
         //read from file to build area
         Scanner readFile = new Scanner(new File(fileName));
         size1 = readFile.nextInt();
         size2 = readFile.nextInt();
         leveldata = new int [size1][size2];
         playerx = readFile.nextInt(); // playerx and playery are the player starting position
         playery = readFile.nextInt();
         centerx = readFile.nextInt(); //centerx and centery put the level in the center of the window
         centery = readFile.nextInt();
         
         barriercount = 0;
         for (int i=0; i<size1; i++) {
            for (int j=0; j<size2; j++) {
               leveldata[i][j] = readFile.nextInt();
               if (leveldata[i][j]==9) { // all 9's from area .txt file are a barrier
                  objects.add(new Barrier("BARR", (j*50),(i*50), Color.BLACK));
                  barriercount++;
               }
              if (leveldata[i][j]==7) { // all 7's are a barrier of type river
                  objects.add(new River("RIV", (j*50)+5,(i*50), 40, 300, new Color(82, 142, 227)));
                  barriercount++;
               }
            }
         }

         objectListSize = readFile.nextInt();
         
         for(int i=0; i < objectListSize; i++) { // reads each object from list in area's .txt file
            String nextObject = readFile.next();
            if(nextObject.equals("JUK")){ // make new jukebox
               objects.add(new Jukebox(nextObject, readFile.nextInt(), readFile.nextInt()));
            }
            else if(nextObject.equals("ARR")) { // make new arrow
               objects.add(new Arrow(nextObject, readFile.nextInt(), readFile.nextInt(), readFile.next(), readFile.next(), readFile.nextInt()));
               //x, y, next file, direction arrow points in, if fake arrow does not go anywhere
            }
            else if(nextObject.equals("BTN")) { // make new button
               int temp1 = readFile.nextInt();
               int temp2 = readFile.nextInt();
               Color tempCol = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt()); // makes new color to pass into button constructor
               Color tempColChange = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt()); // darker color to signify when button is pressed
               objects.add(new Button(nextObject, temp1, temp2, tempCol, tempColChange, readFile.nextInt()));
               //x, y, initial color, color change when pressed, corresponding spike num
            }
            else if(nextObject.equals("TBTN")) { // make new timed button
               int temp1 = readFile.nextInt();
               int temp2 = readFile.nextInt();
               Color tempCol = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt());
               Color tempColChange = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt());
               objects.add(new TButton(nextObject, temp1, temp2, tempCol, tempColChange, readFile.nextInt()));
               //x, y, initial color, color change when pressed, corresponding spike num
            }
            else if(nextObject.equals("SPK")) { // make new spike
               int temp1 = readFile.nextInt();
               int temp2 = readFile.nextInt();
               Color tempCol = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt());
               objects.add(new Spikes(nextObject, temp1, temp2, tempCol, readFile.next(), readFile.nextInt(), readFile.nextInt()));
               //x, y, initial color, horizontal or vertical, corresponding button num, starting size of spike (spikes down or up)
            }
            else if(nextObject.equals("SPR")) { // make new spring
               int temp1 = readFile.nextInt();
               int temp2 = readFile.nextInt();
               String temp3 = readFile.next();
               int temp4 = readFile.nextInt();
               String temp5 = readFile.next();
               Color tempCol = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt());
               objects.add(new Spring(nextObject, temp1, temp2, temp3, temp4, temp5, tempCol));
               //x, y, direction
            }
            else if(nextObject.equals("WALL")) { // make new wall
               int x = readFile.nextInt();
               int y = readFile.nextInt();
               
               int sizeX = readFile.nextInt();
               int sizeY = readFile.nextInt();
               
               Color tempCol = new Color(readFile.nextInt(), readFile.nextInt(), readFile.nextInt());
               objects.add(new Wall(nextObject, x, y, sizeX, sizeY, tempCol));
            
               //x, y, sizex, sizey, color
            }
            else if(nextObject.equals("TWALL")) { // make new sliding wall
               objects.add(new TWall(nextObject, readFile.nextInt(), readFile.nextInt(), readFile.nextInt(), readFile.next()));
               //x, y, direction
            }
            else if(nextObject.equals("SPOON")) { // make new spoon
               objects.add(new Spoon(nextObject, readFile.nextInt(), readFile.nextInt(), readFile.next()));
               //x, y, if visible
            }
         }
      }
      catch (FileNotFoundException FNFE) {} //catch if error loading next level (program won't crash if next area doesn't exist)
   }
   
   // message that appears when player wins
   public void winMessage() {
      JFrame f = new JFrame();
      JOptionPane.showMessageDialog(f,"Congratulations! You win!" + "\n\nDr. Mood's Hot Chocolate Recipe\n\"Not the rubbish at the store\"" + "\n\n1c whole milk\n1-2 tbsp heavy cream\n" + 
      "1-2 tsp cocoa powder\n2 oz dark chocolate" + "\n\nHeat the first 3 ingredients in a saucepan, stop before boiling. Put chocolate \nin a cup " + 
      "and pour the hot mixture over the chocolate to melt it. Stir.");
   }
   
   // accessor returns size1 of 2D array
   public int getSize1() {
      return size1;
   }
   
   // returns size 2 of 2D array
   public int getSize2() {
      return size2;
   }
   
   // returns number of non-player objects in the area
   public int getObjectListSize() {
      return objects.size();
   }
   
   // returns number of objects as read from the .txt file (not including barriers or rivers)
   public int getObjectListSize1() {
      return objectListSize;
   }
   
   // returns number of barriers (including type river)
   public int getBarrierCount() {
      return barriercount;
   }
   
   // next 2 functions return center of board
   public int getcenterX() {
      return centerx;
   }
   public int getcenterY() {
      return centery;
   }
   
   // next 2 functions return player's starting position
   public int getPlayerX() {
      return playerx;
   }
   public int getPlayerY() {
      return playery;
   }
   
   //return arrays
   public int [][] boardaccessor() {
      return leveldata;
   }
   
   // returns the object from list at specified index
   public CZGameObject getObjects(int index){
      return objects.get(index);
   }
}