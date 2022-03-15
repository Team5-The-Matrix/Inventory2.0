package Customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class CustomerApplicationGUI {
    public static void main(String[] args) {

        JFrame f = new JFrame("Customer Application");
        JLabel l1, l2, l3, l4, l5, l6;
        JTextField tfs, tf1, tf2, tf3, tf4, tf5, tf6;
        JButton bs, b1, b2, b3;
        
        l1 = new JLabel("Product Id");
        l2 = new JLabel("Quantity Available");
        l3 = new JLabel("Quantity");
        l4 = new JLabel("Sale Price");
        //l5 = new JLabel("Vendor Id");
        l6 = new JLabel("Cart Total:");

        l1.setBounds(50, 100, 120, 30);
        l2.setBounds(50, 150, 120, 30);
        l3.setBounds(280, 300, 60, 30);
        l4.setBounds(50, 200, 120, 30);
        //l5.setBounds(50, 250, 120, 30);
        l6.setBounds(850, 500, 120, 30);

        tfs = new JTextField();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        //tf5 = new JTextField();
        tf6 = new JTextField();

        tf1.setEditable(false);
        tf2.setEditable(false);
        tf4.setEditable(false);
        tf6.setEditable(false);

        tf6.setText("0.00");
        tf3.setText("0");

        tfs.setBounds(215, 50, 150, 30);
        tf1.setBounds(215, 100, 150, 30);
        tf2.setBounds(215, 150, 150, 30);
        tf3.setBounds(215, 300, 60, 30);
        tf4.setBounds(215, 200, 150, 30);
       // tf5.setBounds(215, 250, 150, 30);
        tf6.setBounds(930, 500, 190, 30);

        bs = new JButton("Search");
        b1 = new JButton("Add Product to Cart");
        b2 = new JButton("Check Out");
        b3 = new JButton("Delete Cart");

        bs.setBounds(50, 50, 95, 30);
        b1.setBounds(50, 300, 150, 30);
        b2.setBounds(50, 350, 300, 30);
        b3.setBounds(50, 400, 300, 30);

//////////////////////////////////////////////////////////    
        //CUSTOMER CART

        String data[][]={};//tf1, tf5, tf4, =tf4*tf5
        String column[] = {"Product Id", "Quantity", "Sale Price", "Total Price","Row"};
        
        DefaultTableModel cartModel = new DefaultTableModel(data,column);
        //Read database into table;
        DefaultTableModel model = CustomerClient.readDatabase();
        JTable inventoryList = new JTable(model);
        JTable customerCart = new JTable(cartModel);
        JScrollPane pane1 = new JScrollPane(inventoryList);
        JScrollPane pane2 = new JScrollPane(customerCart);
        pane1.setBounds(400, 50, 700, 220);
        pane2.setBounds(400,350,700,100);

/////////////////////////////////////////////////////////
//METHODS
/////////////////////////////////////////////////////////

        //SEARCH METHOD
        try{
        bs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = tfs.getText();
                String[] result = CustomerClient.Search(productID);
                tf1.setText(result[0]);
                tf2.setText(result[1]);
                tf4.setText(result[3]);
              //  tf5.setText(result[4]);
            }
        });
        }
        catch(Exception e)
        {
            System.out.println("Search Error! ");
            e.printStackTrace();
        }

        //Add to Cart 
        try{
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                String currentTotal=tf6.getText();
                String item = tf1.getText();
                String quantity = tf3.getText();
                String price = tf4.getText();
                String totalPrice = Double.toString(Double.parseDouble(quantity) * Double.parseDouble(price));
                String selectedRow = Integer.toString(inventoryList.getSelectedRow());
                String[] row = {item,quantity,price,totalPrice,selectedRow};
                CustomerClient.addCart(item, quantity);
                cartModel.addRow(row);
                String newTotal=Double.toString(  Double.parseDouble(totalPrice) + Double.parseDouble(currentTotal));
                tf6.setText(newTotal);
                }
            });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        //Checkout METHOD
        try{
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    for(int i = 0; i< cartModel.getRowCount(); i++)
                    {
                        int Quantity = Integer.parseInt(cartModel.getValueAt(i, 1).toString());
                        int oldQuant = Integer.parseInt(model.getValueAt(Integer.parseInt(cartModel.getValueAt(i, 4).toString()), 1).toString());
                        String newQuant = Integer.toString(oldQuant-Quantity);
                        model.setValueAt(newQuant, Integer.parseInt(cartModel.getValueAt(i, 4).toString()), 1);
                    }
                    
                    cartModel.setRowCount(0);
                    if(CustomerClient.Purchase()){
                    System.out.println("Purchase Success ");
                    }
                    else
                        System.out.println("Purchase Error ");
                }
            });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            // DELETE CART METHOD
        try{
            b3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    cartModel.setRowCount(0);
                }
            }
            );


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

            // POPULATE TEXT FIELDS WITH SELECTED ROW

        try{
            inventoryList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event)
                {
                    
                    tf1.setText(inventoryList.getValueAt(inventoryList.getSelectedRow(), 0).toString());
                    tf2.setText(inventoryList.getValueAt(inventoryList.getSelectedRow(), 1).toString());
                    tf4.setText(inventoryList.getValueAt(inventoryList.getSelectedRow(), 2).toString());
                    
                    
                }
            });
            }
            catch(Exception e)
            {
                System.out.println("Table Update Error ");
                //e.printStackTrace();
            }
/////////////////////////////////////////////////////////////////


    
        f.add(bs);
        f.add(b1);
        f.add(b2);
        f.add(b3);

        f.add(tfs);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        f.add(tf4);
        //f.add(tf5);
        f.add(tf6);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
       // f.add(l5);
        f.add(l6);

        f.add(pane1);
        f.add(pane2);
        f.setSize(1200, 600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}