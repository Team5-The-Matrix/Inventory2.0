//GUI for naviagating between applications
// Author(s): Matthew Mazzaccaro

package Admin;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import java.awt.event.*;

import Customer.CustomerApplicationGUI;

public class HomePageGUI extends JFrame { 

private static final int FONT_SIZE = 20;
private static final int NUMBER_OF_REPEATS = 5;
private static final String TEXT = new String("01");
private static JPanel panel = new JPanel(null);
private static Random random = new Random();
private static JLabel label[] = new JLabel[NUMBER_OF_REPEATS];
private static int loopTime = 0;



public HomePageGUI() {        
   this.add(panel);
   panel.setBackground(Color.BLACK);


}
public void scroll() {
    int[] random_x = new int[NUMBER_OF_REPEATS];
    while (true) {
        for (int i = 0; i < NUMBER_OF_REPEATS; i++) {
          int character_initial = random.nextInt(TEXT.length());
          random_x[i] = random.nextInt(panel.getWidth() / FONT_SIZE) - 1;
          label[i] = new JLabel("" + TEXT.charAt(character_initial));
          panel.add(label[i]);
          label[i].setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
          label[i].setForeground(new Color(0, 255, 0));
     }
    for (int j = 0; j < (panel.getHeight() / FONT_SIZE) * 2; j++) {
        int character = random.nextInt(TEXT.length());
        for (int i = 0; i < NUMBER_OF_REPEATS; i++) {
            label[i].setBounds(random_x[i] * FONT_SIZE, j * (FONT_SIZE / 2), FONT_SIZE, FONT_SIZE);
            label[i].setText("" + TEXT.charAt(character));
            label[i].setForeground(new Color(0, 255 - (j * 2), 0));     
            for (int k = 0; k < NUMBER_OF_REPEATS; k++) {
                int character_initial = random.nextInt(TEXT.length());
                random_x[k] = random.nextInt(panel.getWidth() / FONT_SIZE) - 1;
                label[k] = new JLabel("" + TEXT.charAt(character_initial));
                panel.add(label[k]);
                label[k].setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
                label[k].setForeground(new Color(0, 255, 0));
                Color colour = label[k].getForeground();
                if (colour.getGreen() <= 80) {
                    panel.remove(label[k]);
                    k = (panel.getHeight() / FONT_SIZE) * 2;
                }
            }
        }
        try {
            Thread.sleep(15);
        } catch (Exception e) {
        }
     }
  }
}
  public static void main(String[] args) {
    HomePageGUI frame = new HomePageGUI();

      JButton b1, b2;

      b1 = new JButton("Inventory Manager");
      b2 = new JButton("Customer Application");
      
   
      b1.setBounds(200, 100, 200, 100);
      b2.setBounds(200, 300, 200, 100);
   
      b1.setBackground(Color.red);
      b2.setBackground(Color.blue);
   
      try{
       b1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               InventoryManagerGUI.main(args);
           }
       });
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   
       try{
           b2.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   CustomerApplicationGUI.main(args);
               }
           });
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }

      panel.add(b1);
      panel.add(b2);
      
      frame.setVisible(true);
      frame.setSize(600, 600);
      frame.setResizable(false);
      frame.setMinimumSize(new Dimension(300, 200));
      frame.setLocationRelativeTo(null);
      frame.setTitle("Matrix");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
      frame.scroll();
  }
}