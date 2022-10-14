//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
//contains the actual gameplay functionality

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class ContraptionZack extends JPanel {
   //Member Variables

   private String fileName;
   private CZLevels level;
   private JPanel resetMenu;
   private JButton resetAreaButton, resetLevelButton, loadButton, saveButton;
   private int x = 0, y = 0;
   private CZPlayer player = new CZPlayer("Player", x, y);
   private int [][] board;
   private Arrow endArrow, beginningArrow;
   
   // Constructor takes in the area .txt file name as a String
   
   public ContraptionZack(String fileName) {
      setBackground(Color.BLACK);
      level = new CZLevels(fileName);

      board = level.boardaccessor();

      player.setX(level.getPlayerX());
      player.setY(level.getPlayerY());
      
      //access keylistener
      addKeyListener(new CZKeyEvent());
      setFocusable(true);
      
      //timer makes animation smooth
      Timer t = new Timer(10, new TimeListener());
      t.start();
      
       //Menu when you press "esc"
      resetMenu = new JPanel();
 
      //create buttons
      resetAreaButton = new JButton("Reset Area");
      resetLevelButton = new JButton("Reset Level");
      loadButton = new JButton("Load");
      saveButton = new JButton("Save");
      ButtonListener buttonlisten = new ButtonListener();
      
      //add button listeners
      resetAreaButton.addActionListener(buttonlisten);
      resetLevelButton.addActionListener(buttonlisten);
      loadButton.addActionListener(buttonlisten);
      saveButton.addActionListener(buttonlisten);
      
      //add buttons to panel
      resetMenu.add(resetAreaButton);
      resetMenu.add(resetLevelButton);
      resetMenu.add(loadButton);
      resetMenu.add(saveButton);
      
      add(resetMenu);
      resetMenu.setVisible(false);
   }
   
   //button listener
   public class ButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == resetAreaButton) { //reset area the player is in
            if (fileName.equals("BACK3.txt")) {
               fileName = "CZtext3.txt";
            }
            else if (fileName.equals("BACK2.txt")) {
               fileName = "CZtext2.txt";
            }
            level = new CZLevels(fileName);
            board = level.boardaccessor();
      
            player.setX(level.getPlayerX());
            player.setY(level.getPlayerY());
            resetMenu.setVisible(false);
         }
         if (e.getSource() == resetLevelButton) { //reset level (go back to area 1)
            fileName = "CZtext1.txt";
            level = new CZLevels(fileName);
            board = level.boardaccessor();
      
            player.setX(level.getPlayerX());
            player.setY(level.getPlayerY());
            resetMenu.setVisible(false);
         }
         File f1 = new File("CZtextSAVE.txt");
         if (e.getSource() == loadButton) { //load saved game
            if (f1.length() != 0) { //make sure file isnt empty
               fileName = "CZtextSAVE.txt";
               level = new CZLevels(fileName);
               board = level.boardaccessor();
         
               player.setX(level.getPlayerX());
               player.setY(level.getPlayerY());
            }
         }
         if (e.getSource() == saveButton) { //save data in new file
            clearFile("CZtextSAVE.txt"); //clear data before writing new
            uploadData("CZtextSAVE.txt"); //upload new data to save file
         }
         resetMenu.setVisible(false);
      }
   }
   
   public void uploadData(String file) {
      String dataString = "";
      try {
         FileWriter fileWriter = new FileWriter(file,true);
         PrintWriter pw = new PrintWriter(fileWriter);
         
         dataString += String.valueOf(level.getSize1()); //size of board
         dataString += " ";
         dataString += String.valueOf(level.getSize2());
         dataString += "\n\n";
         
         dataString += String.valueOf(player.getX()); //player location
         dataString += " ";
         dataString += String.valueOf(player.getY());
         dataString += "\n\n";
         
         dataString += String.valueOf(level.getcenterX()); //center of board
         dataString += " ";
         dataString += String.valueOf(level.getcenterY());
         dataString += "\n\n\n";
         
         for (int i=0; i<level.getSize1(); i++) { //board number array
            for (int j=0; j<level.getSize2(); j++) {
               dataString += String.valueOf(board[i][j]);
               dataString += " ";
            }
            dataString += "\n";
         }
         dataString += "\n";
         
         dataString += String.valueOf(level.getObjectListSize1()); //amount of objects
         dataString += "\n\n";
         
         String temp = "";
         for (int i=0; i<level.getObjectListSize1(); i++) { //write in all data for objects
            temp = level.getObjects(i+(level.getBarrierCount())).getType();
            dataString += temp;
            dataString += " ";
            if (temp.equals("JUK")) { //jukebox data
               Jukebox tempobj = (Jukebox)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
            }
            if (temp.equals("ARR")) { //arrow data
               Arrow tempobj = (Arrow)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += tempobj.getNext();
               dataString += " ";
               dataString += tempobj.getDirection();
               dataString += " ";
               if (tempobj.getFake()) { //fake is type boolean but data in file is int, so read accordingly
                  dataString += "0 ";
               }
               else {
                  dataString += "1 ";
               }
               dataString += "\n";
            }
            if (temp.equals("BTN")) { //button data
               Button tempobj = (Button)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getBlue());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getBlue());
               dataString += " ";
               dataString += String.valueOf(tempobj.getSpikeNum());
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("TBTN")) { //timed button data
               TButton tempobj = (TButton)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getOrgColor().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getOrgColor().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getOrgColor().getBlue());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColorChange().getBlue());
               dataString += " ";
               dataString += String.valueOf(tempobj.getSpikeNum());
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("SPK")) { //spike data
               Spikes tempobj = (Spikes)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getBlue());
               dataString += " ";
               dataString += tempobj.getDirection();
               dataString += " ";
               dataString += String.valueOf(tempobj.getButtonNum());
               dataString += " ";
               dataString += String.valueOf(tempobj.getSizeX());
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("SPR")) { //spring data
               Spring tempobj = (Spring)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += tempobj.getDirection();
               dataString += " ";
               dataString += String.valueOf(tempobj.getButtonLink());
               dataString += " ";
               dataString += String.valueOf(tempobj.getToggleSetting());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getBlue());
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("WALL")) { //wall data
               Wall tempobj = (Wall)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getSizeX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getSizeY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getRed());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getGreen());
               dataString += " ";
               dataString += String.valueOf(tempobj.getColor().getBlue());
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("TWALL")) { //timed wall data
               TWall tempobj = (TWall)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getCopyX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getCopyY());
               dataString += " ";
               dataString += String.valueOf(tempobj.getDelay());
               dataString += " ";
               dataString += tempobj.getCopyDirection();
               dataString += " ";
               dataString += "\n";
            }
            if (temp.equals("SPOON")) { //spoon data
               Spoon tempobj = (Spoon)level.getObjects(i+(level.getBarrierCount()));
               dataString += String.valueOf(tempobj.getX());
               dataString += " ";
               dataString += String.valueOf(tempobj.getY());
               dataString += " ";
               dataString += tempobj.getVis();
               dataString += " ";
               dataString += "\n";
            }
         }
         pw.write(dataString);
         pw.close();
      } 
      catch(IOException x){
      }
   }
   
   public static void clearFile(String file) { //clear out everything in save file
      try {
         FileWriter fw = new FileWriter(file, false);
         PrintWriter pw = new PrintWriter(fw, false);
         pw.flush();
         pw.close();
         fw.close();
      }
      catch(Exception exception){
      }
   }
   
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      g.setColor(Color.BLACK);
      g.fillRect(0,0,820,620);
      
      //color background
      for (int i=0; i<level.getSize1(); i++) {
         for (int j=0; j<level.getSize2(); j++) {
            if (board[i][j]==0) {
               g.setColor(Color.WHITE);
            }
            else if (board[i][j]==1) {
               g.setColor(Color.DARK_GRAY);
            }
            else if (board[i][j]==2) {
               g.setColor(Color.BLACK);
            }
            else if (board[i][j]==5) {
               Color tempcol = new Color(204,204,204);
               g.setColor(tempcol);
            }
            else if (board[i][j]==4) {
               Color tempcol = new Color(255, 120, 87);
               g.setColor(tempcol);
            }
            else if (board[i][j]==3) {
               Color tempcol = new Color(69,69,69);
               g.setColor(tempcol);
            }
            g.fillRect((j*50)+level.getcenterX(),(i*50)+level.getcenterY(),50,50);
         }
      }
      
      for (int i=0; i<level.getObjectListSize(); i++) {
         if(level.getObjects(i) != null){
            level.getObjects(i).draw(g, level.getcenterX(), level.getcenterY());
         }   
      }
      
      //draw player
      player.draw(g);
   }
   
   boolean left, right, up, down;
   //key input, key must be pressed in order to move player
   public class CZKeyEvent implements KeyListener {
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
            player.setIsMoving(false);
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
            player.setIsMoving(false);
         }
         if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
            player.setIsMoving(false);
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
            player.setIsMoving(false);
         }
      }
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right=true;
         }
         if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
         }
         if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE){ //reset menu visibility
            if(resetMenu.isVisible())
               resetMenu.setVisible(false);
            else
               resetMenu.setVisible(true);   
         }
      }
   }
   //called every 10ms, makes movement smooth
   public class TimeListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         
         if(!player.getIsSpringing()){
         
            boolean canMove = true;
            //loop through object list to check for all possible collisions, move player if no collisions
            for(int i = 0; i< level.getObjectListSize(); i++){
               if(player.collides(level.getObjects(i), level.getcenterX(), level.getcenterY())){
                  canMove = false;
                  player.setIsMoving(false);
               }  
               if (!canMove && level.getObjects(i) instanceof Button) { //make sure player can move over buttons
                     canMove = true;
                  }
             }  
            //if can move, move player over one
            if(left&&canMove) {
               player.setX(player.getX() - 1);
               player.setIsMoving(true);
               
            }
            if(right&&canMove) {
               player.setX(player.getX() + 1);
               player.setIsMoving(true);
               
            }
            if(up&&canMove) {
               player.setY(player.getY() - 1);
               player.setIsMoving(true);
               
            }
            if(down&&canMove) {
               player.setY(player.getY() + 1);
               player.setIsMoving(true);
               
            }
         }
         
         for (int i=0; i<level.getObjectListSize(); i++) {
            if (level.getObjects(i) instanceof Arrow) {
            Arrow tempArr = (Arrow)level.getObjects(i);
               //switch screen if player collides with arrow  
               if(player.collides(tempArr, level.getcenterX(), level.getcenterY()) && !tempArr.getFake()){
                  fileName = tempArr.getNext();
                  if (fileName.equals("CZtext3.txt")) {
                     clearFile("BACK2.txt"); //clear data before writing new
                     uploadData("BACK2.txt"); //upload new data to save file
                  }
                  if (fileName.equals("CZtext4.txt")) {
                     clearFile("BACK3.txt"); //clear data before writing new
                     uploadData("BACK3.txt"); //upload new data to save file
                  }
                  level = new CZLevels(fileName);
                  board = level.boardaccessor();
            
                  player.setX(level.getPlayerX());
                  player.setY(level.getPlayerY());
               }
            }
            if (level.getObjects(i) instanceof Spring) {
               Spring tempSpr = (Spring)level.getObjects(i);
               //move player right or left depending on direction of spring
               
               if(player.collides(tempSpr, level.getcenterX(), level.getcenterY()) && tempSpr.getColor() != Color.BLACK){
                  if (fileName.equals("CZtext2.txt")) { //in area 2, stepping on one spring activates both
                     for (int j=0; j<level.getObjectListSize(); j++) {
                        if (level.getObjects(j) instanceof Spring) {
                           Spring tempSpr2 = (Spring)level.getObjects(j);
                           level.getObjects(j).setColor(Color.BLACK);
                        }
                     }
                  }
                  //spring player to right
                  if (tempSpr.getDirection().equals("RIGHT")) {
                     player.spring(player.getX() + 105);
                     level.getObjects(i).setColor(Color.BLACK);
                     
                  }
                  //spring player to left
                  if (tempSpr.getDirection().equals("LEFT")) {
                     player.spring(player.getX() - 105);
                     level.getObjects(i).setColor(Color.BLACK);
                  }
                  player.setY(tempSpr.getY() + level.getcenterY() + 8);
                  
                  //reset color if spring is timed
                  if(tempSpr.getToggleSetting().equals("toggleFalse")){
                     tempSpr.toggleColor();
                  }
                  //if spring is connected to button change button color
                  if (tempSpr.getButtonLink() == 999) {
                     level.getObjects(i+1).setColor(Color.WHITE);
                  }   
               }
            }
            //if colliding with button
            if (level.getObjects(i) instanceof Button) {
               Button tempBtn = (Button)level.getObjects(i);
               if(player.collides(tempBtn, level.getcenterX(), level.getcenterY())){
                        for (int j=0; j<level.getObjectListSize(); j++) { //loop to compare buttons and spikes
                           
                           if (level.getObjects(j) instanceof Spikes) {
                              Spikes tempSpk = (Spikes)level.getObjects(j);
                              if (tempSpk.getButtonNum() == tempBtn.getSpikeNum()) { //make sure button and spikes are color corresponded
                                    if (!(tempBtn.getColor().hashCode() == tempBtn.getColorChange().hashCode())) { //make sure button is only pressed once
                                       if (tempSpk.getSizeX() == 10) { //if down, put spikes up
                                          tempSpk.toggleSize(15);
                                       }
                                       else if (tempSpk.getSizeX() == 15) { //if up, put spikes down
                                          tempSpk.toggleSize(10);
                                       }
                                    if(level.getObjects(i) instanceof TButton){
                                       TButton tempTBtn = (TButton) level.getObjects(i);
                                       tempTBtn.startTimer((Spikes)level.getObjects(j));
                                    }   
                                 }
                              }
                           }
                        }
                        tempBtn.setColor(tempBtn.getColorChange()); //change color of button after pressed                    
               }
            }
         }
         repaint();
      }
   }
}