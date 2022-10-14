//This was coded by Megan Mkrtchyan, Grant Solomon, and Joella Wu-Cardona for CSC 3023
//Title screen - main method

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TitleScreen{
   public static void main(String [] args){
      
      //create frame
      JFrame frame = new JFrame("Starting Menu");
      frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
      //title
      ImageIcon imageIcon = new ImageIcon("titleCard.png");
      JLabel title = new JLabel(imageIcon);
      title.setAlignmentX(Component.CENTER_ALIGNMENT);
      frame.add(title);

      //Create buttons
      JButton start = new JButton("Start");
      start.setAlignmentX(Component.CENTER_ALIGNMENT);
      frame.add(start);
      JButton load = new JButton("Load");
      load.setAlignmentX(Component.CENTER_ALIGNMENT);
      frame.add(load);
      
      //Start button functionality
      start.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
               frame.setVisible(false);
               JFrame frame2 = new JFrame("Contraption Zack, BUT!");
               frame2.add(new ContraptionZack("CZtext1.txt"));
               frame2.setSize(820, 640);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
         });
         
      //Load button functionality
      load.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) {
            File f1 = new File("CZtextSAVE.txt");
            if (f1.length() != 0) {
               frame.setVisible(false);
               JFrame frame2 = new JFrame("Contraption Zack, BUT!");
               frame2.add(new ContraptionZack("CZtextSAVE.txt"));
               frame2.setSize(820, 640);
               frame2.setVisible(true);
               frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
         }
      });
      frame.setSize(1000, 600);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   }
}