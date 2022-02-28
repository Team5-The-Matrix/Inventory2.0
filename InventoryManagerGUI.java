import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class InventoryManagerGUI {
    public static void main(String[] args) {

        JFrame f = new JFrame("Inventory Manager");
        DefaultTableModel model = Client.readDatabase();
        JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10; // labels on top for table
        JTextField tfs, tf1, tf2, tf3, tf4, tf5;
        JButton bs, b1, b2, b3;



        //CALL IN TABLE MODEL FROM CLIENT
        
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.green);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(750,500));
        table.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setViewportView(table);
        pane.setBounds(400, 50, 750, 500);


        


        //table.setBounds(400, 50, 750, 500);
        //DefaultListModel<String> li1, li2, li3, li4, li5;
        //JList<String> list1, list2, list3, list4, list5;

        /* li1 = new DefaultListModel<>();
        li2 = new DefaultListModel<>();
        li3 = new DefaultListModel<>();
        li4 = new DefaultListModel<>();
        li5 = new DefaultListModel<>();
 */

        //THESE SHOULD BE LABELS
        /* li1.addElement("Product Id");
        li2.addElement("Quantity");
        li3.addElement("Wholesale Cost");
        li4.addElement("Retail Price");
        li5.addElement("Vendor Id"); */

        /* li1.addElement("Item1");
        li1.addElement("Item2");
        li1.addElement("Item3");

        li2.addElement("Item1");
        li2.addElement("Item2");
        li2.addElement("Item3");

        li3.addElement("Item1");
        li3.addElement("Item2");
        li3.addElement("Item3");

        li4.addElement("Item1");
        li4.addElement("Item2");
        li4.addElement("Item3");

        li5.addElement("Item1");
        li5.addElement("Item2");
        li5.addElement("Item3"); */






      /*   list1 = new JList<>(li1);
        list2 = new JList<>(li2);
        list3 = new JList<>(li3);
        list4 = new JList<>(li4);
        list5 = new JList<>(li5);

        list1.setBounds(400, 50, 145, 500);
        list2.setBounds(550, 50, 145, 500);
        list3.setBounds(700, 50, 145, 500);
        list4.setBounds(850, 50, 145, 500);
        list5.setBounds(1000, 50, 145, 500);
 */
        l1 = new JLabel("Product Id");
        l2 = new JLabel("Quantity");
        l3 = new JLabel("Wholesale Cost");
        l4 = new JLabel("Retail Price");
        l5 = new JLabel("Vendor Id");

        l1.setBounds(50, 100, 100, 30);
        l2.setBounds(50, 150, 100, 30);
        l3.setBounds(50, 200, 100, 30);
        l4.setBounds(50, 250, 100, 30);
        l5.setBounds(50, 300, 100, 30);

        tfs = new JTextField();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();

        tfs.setBounds(215, 50, 150, 30);
        tf1.setBounds(215, 100, 150, 30);
        tf2.setBounds(215, 150, 150, 30);
        tf3.setBounds(215, 200, 150, 30);
        tf4.setBounds(215, 250, 150, 30);
        tf5.setBounds(215, 300, 150, 30);

        bs = new JButton("Search");
        b1 = new JButton("Add New");
        b2 = new JButton("Update");
        b3 = new JButton("Delete");

        bs.setBounds(50, 50, 95, 30);
        b1.setBounds(50, 400, 95, 30);
        b2.setBounds(160, 400, 95, 30);
        b3.setBounds(270, 400, 95, 30);


        //SEARCH METHOD
        bs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tfs.getText();
                Client.Search(productID);
                tf1.setText(productID);
                /* tf2.setText(quanity);
                tf3.setText(wholesaleCost);
                tf4.setText(retailPrice);
                tf5.setText(sellerID); */
            }
        });
        //CREATE METHOD
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tf1.getText();
                String quanity = tf2.getText();
                String wholesaleCost = tf3.getText();
                String retailPrice = tf4.getText();
                String sellerID = tf5.getText();
                Client.Create(productID,quanity,wholesaleCost,retailPrice,sellerID);

            }
        });
        // UPDATE METHOD
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tf1.getText();
                String quanity = tf2.getText();
                String wholesaleCost = tf3.getText();
                String retailPrice = tf4.getText();
                String sellerID = tf5.getText();
                Client.Update(productID,quanity,wholesaleCost,retailPrice,sellerID);
            }
        });
        // DELETE METHOD
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tfs.getText();
                Client.Delete(productID);
            }
        });

        // POPULATE TEXT FIELDS WITH SELECTED ROW
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event)
            {
                tf1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                tf2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                tf3.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                tf4.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                tf5.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
            }
        });

        f.add(bs);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        f.add(tfs);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        f.add(tf4);
        f.add(tf5);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);

       // f.add(list1);
       // f.add(list2);
       // f.add(list3);
      //  f.add(list4);
      //  f.add(list5);
        //pane.setViewportView(table);
        //f.add(pane);
        f.add(pane);

        f.setSize(1200, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}