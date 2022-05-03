//Backend for Customer Application
// Author(s): Noah Pearson Kramer, June Luke, Aria Comeau


package Customer;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class CustomerClient{

static Connection conn = null;
static ArrayList<ArrayList<String>> userCart = new ArrayList();
static ArrayList<String> productList = new ArrayList();
static ArrayList<String> quantList = new ArrayList();


//Constructs login string
public static String login(String user, String pass){
String loginString = null;
loginString = "user="+user+"&password="+pass;
return loginString;
}

//Connect to database

public static boolean connect(String user,String password){
    boolean connected = false;
    //LoadDriver driver = new LoadDriver();
    String name,pass,url;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://SG-InvnetoryManager-5975-mysql-master.servers.mongodirector.com:3306/Inventory";
        name = user;
        pass = password;
        //String fullLoginString = "jdbc:mysql://SG-InventoryManagement-5756-mysql-master.servers.mongodirector.com:3306/Inventory"+ loginString;
        //System.out.println(fullLoginString);
        conn = DriverManager.getConnection(url,name,pass);
        System.out.println("Connected to database! ");
        connected = true;
        return connected;
    }

    catch(SQLException e){
        e.printStackTrace();
        connected = false;
        return connected;
    }

    catch(Exception f)
    {
        f.printStackTrace();
        connected = false;
        return connected;
    }
}
    
    //updates entry in database after CHECKOUT is called... only needs to update quantity.


    public static void Update(String productID, String quantity){ //is this data passed in correct?
        try{
            PreparedStatement updateStmt = conn.prepareStatement
            ("UPDATE items SET quantity = ? WHERE product_id = ?");
            updateStmt.setString(2, productID);
            updateStmt.setString(1, quantity);
            updateStmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    //READS DATABASE INTO TABLEMODEL FOR GUI
    public static DefaultTableModel readDatabase(){
    
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Product ID");
        table.addColumn("Quantity Available");
        table.addColumn("Price");
        try{
        String sql = "SELECT product_id, quantity, sale_price "+
                     "FROM items "+
                     "LIMIT 50000";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        int rowcount=0;
        while (rs.next())
            {
                table.addRow(new Object[]{
                    rs.getString("product_id"),
                    rs.getString("quantity"),
                    rs.getString("sale_price"),
                });  
                rowcount++;   
            }
    
        System.out.println("Lines stored: "+rowcount);

        System.out.println("Database stored in table! ");
        return table;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return table;
        }
    }


    //searches for and reads a line in database
    public static String[] Search(String productID){
        String[] entry = new String[5];
        try{
            PreparedStatement selectStmt = conn.prepareStatement
            ("SELECT * FROM items WHERE product_id = "+ "'"+productID+"';");
            
            ResultSet selectRS = selectStmt.executeQuery();
            selectRS.next();
            entry[0] =selectRS.getString("product_id");
            entry[1] =selectRS.getString("quantity");
            entry[2] =selectRS.getString("wholesale_cost");
            entry[3] =selectRS.getString("sale_price");
            entry[4] =selectRS.getString("supplier_id");       
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Search Failed, Item not in Database ");
            
        }
        return entry;
        
    }


    //updates database with purchases from cart; returns true if completed successfully,
    //returns false if the quantity purchased exceeds quantity availible of any item in cart
    public static boolean Purchase(){
        String[] itemSearch = null;
        String productID = null;
        int currentQty;
        int purchaseQty;
        int updatedQty;
        for(int i = 0; i < userCart.size(); i++){
            itemSearch = Search(userCart.get(0).get(i)); //searches for each item in database
            productID = itemSearch[0];
            currentQty = Integer.parseInt(itemSearch[1]); //quantity in database
            purchaseQty = Integer.parseInt(userCart.get(1).get(i)); //quantity to be purchased
            updatedQty = currentQty - purchaseQty;
            if (updatedQty >= 0)
                Update(productID, Integer.toString(updatedQty));
            else
                return false;
        }
        return true;
    }


   // Run this before doing any cart stuff and only once.
    static void newCart(){
        userCart.add(productList);
        userCart.add(quantList);
    }


   // Clears out the userCart
    static void clearCart(){
        productList.clear();
        quantList.clear();
    }
    

   // Adds a new product to userCart with quantity
    static void addCart(String productID, String quantity){
        productList.add(productID);
        quantList.add(quantity);
    }
    

   // Removes a product from userCart
    static void removeCart(String productID){
        int cartLocalIndex = productList.indexOf(productID);
        productList.remove(cartLocalIndex);
        quantList.remove(cartLocalIndex);
    }
    

    // Changes the quantity of desired product in cart
    static void changeCart(String productID, String quantity){
        int cartLocalIndex = productList.indexOf(productID);
        quantList.set(cartLocalIndex, quantity);
    }
        
      
}
