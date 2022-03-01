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
        JButton bs, b1, b2, b3, b4;

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
        b4 = new JButton("Clear");
        

        bs.setBounds(50, 50, 95, 30);
        b1.setBounds(50, 400, 95, 30);
        b2.setBounds(160, 400, 95, 30);
        b3.setBounds(270, 400, 95, 30);
        b4.setBounds(50, 350, 95, 30);


        //SEARCH METHOD
        try{
        bs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tfs.getText();
                String[] result = Client.Search(productID);
                tf1.setText(result[0]);
                tf2.setText(result[1]);
                tf3.setText(result[2]);
                tf4.setText(result[3]);
                tf5.setText(result[4]);
                //print statement in client.
            }
        });
        }
        catch(Exception e)
        {
            System.out.println("Search Error! ");
            e.printStackTrace();
        }

        //CREATE METHOD
        try{
        b1.addActionListener(new ActionListener() {

            
            public void actionPerformed(ActionEvent e) {
                String productID = tf1.getText();
                String quantity = tf2.getText();
                String wholesaleCost = tf3.getText();
                String retailPrice = tf4.getText();
                String sellerID = tf5.getText();
                Client.Create(productID,quantity,wholesaleCost,retailPrice,sellerID);
                
                model.addRow(new Object[]{
                    productID,
                    quantity,
                    wholesaleCost,
                    retailPrice,
                    sellerID
                }); 

                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                
                System.out.println("Operation Success: New Item Added!");
            }
        });
        }
        catch(Exception e)
        {e.printStackTrace();}

        // UPDATE METHOD
        try{
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if  (tf1.getText().equals("")|| tf2.getText().equals("")||tf3.getText().equals("")
                    ||tf4.getText().equals("")||tf5.getText().equals("")){
                        System.out.println("Error: One or more fields is empty, cannot update");
                    }
                else{    
                int row = table.getSelectedRow();
                
                table.setValueAt(tf1.getText() ,row,0 );
                table.setValueAt(tf2.getText() ,row,1 );
                table.setValueAt(tf3.getText() ,row,2 );
                table.setValueAt(tf4.getText() ,row,3 );
                table.setValueAt(tf5.getText() ,row,4 );
                
                Client.Update(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText());

                
                System.out.println("Update Successful ");
                }
            }
        });
    }
    catch(Exception e){
        System.out.println("Update Error! ");
        e.printStackTrace();
    }


        try{
        // DELETE METHOD
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tf1.getText();
                Client.Delete(productID);
                int row = table.getSelectedRow();
                model.removeRow(row);
                table.updateUI();
                System.out.println("Delete Successful! ");
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
            }
        });
        }
        catch(Exception e){
            System.out.println("Delete Error! ");
            e.printStackTrace();
        }


        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
            }
        });

        // POPULATE TEXT FIELDS WITH SELECTED ROW

        try{
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
        }
        catch(Exception e)
        {
            System.out.println("Table Update Error ");
            //e.printStackTrace();
        }

        f.add(bs);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);

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

        f.add(pane);

        f.setSize(1200, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}