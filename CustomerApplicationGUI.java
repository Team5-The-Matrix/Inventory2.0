import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CustomerApplicationGUI {
    public static void main(String[] args) {

        JFrame f = new JFrame("Customer Application");
        DefaultTableModel model = Client.readDatabase();
        JTextField tfs, tf1, tf2, tf3, tf4, tf5;
        JButton bs, b1, b2, b3, b4;

        //CALL IN TABLE MODEL FROM CLIENT
        
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.green);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(500,500));
        table.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setViewportView(table);
        pane.setBounds(20, 50, 500, 500);


        //CUSTOMER CART

        JTable customerCart = new JTable(model);
        JTableHeader header1 = customerCart.getTableHeader();
        header1.setBackground(Color.green);
        customerCart.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        customerCart.setPreferredScrollableViewportSize(new Dimension(500,500));
        customerCart.setFillsViewportHeight(true);
        JScrollPane pane1 = new JScrollPane(customerCart);
        pane1.setViewportView(customerCart);
        pane1.setBounds(550, 50, 500, 500);


        tfs = new JTextField();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();

        tfs.setBounds(130, 10, 150, 30);

        bs = new JButton("Search");

        bs.setBounds(20, 10, 95, 30);


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

        f.add(tfs);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        f.add(tf4);
        f.add(tf5);

        f.add(pane);
        f.add(pane1);

        f.setSize(1200, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}