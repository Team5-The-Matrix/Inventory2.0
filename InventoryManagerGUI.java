import javax.swing.*;

public class InventoryManagerGUI {
    public static void main(String[] args) {
        JFrame f = new JFrame("Inventory Manager");// creating instance of JFrame

        JButton b = new JButton("Create");// creating instance of JButton
        b.setBounds(130, 100, 100, 40);// x axis, y axis, width, height

        JButton c = new JButton("Read");
        c.setBounds(130, 160, 100, 40);

        JButton d = new JButton("Update");
        d.setBounds(130, 220, 100, 40);

        JButton e = new JButton("Delete");
        e.setBounds(130, 280, 100, 40);

        f.add(b);// adding button in JFrame
        f.add(c);
        f.add(d);
        f.add(e);

        f.setSize(400, 500);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
    }
}