import javax.swing.*;
import java.awt.event.*;


public class HomePageGUI {

    public static void main(String[] args) {

        JFrame f = new JFrame("Home Page");
        JButton b1, b2;


        b1 = new JButton("Inventory Manager");
        b2 = new JButton("Customer Application");
        

        b1.setBounds(200, 100, 200, 100);
        b2.setBounds(200, 300, 200, 100);


        try{
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InventoryManagerGUI();
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


        f.add(b1);
        f.add(b2);


        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}