//Admin Client Program - connects to MySQL database, CRUD methods
//Author(s): Noah Pearson Kramer, June Luke, Aria Comeau 

package Admin;
import java.sql.*;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;


public class Client{

static Connection conn = null;


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


//TEST
    public static void main(String[] args){
    }

    
    //updates entry in database
    public static void Update(String productID, String quantity, String wholesaleCost, String salePrice, String sellerID){ //is this data passed in correct?
        try{
            PreparedStatement updateStmt = conn.prepareStatement
            ("UPDATE items SET quantity = ?, wholesale_cost = ?, sale_price = ?, supplier_id = ? WHERE product_id = ?");
            updateStmt.setString(1, productID);
            updateStmt.setString(2, quantity);
            updateStmt.setString(3, wholesaleCost);
            updateStmt.setString(4, salePrice);
            updateStmt.setString(5, sellerID);
            updateStmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    //create new entry in database
    public static void Create(String productID, String quantity, String wholesaleCost, String salePrice, String sellerID){
        try{
            PreparedStatement createStmt = conn.prepareStatement
            ("INSERT INTO items VALUES(?,?,?,?,?)");
            createStmt.setString(1, productID);
            createStmt.setString(2, quantity);
            createStmt.setString(3,wholesaleCost);
            createStmt.setString(4,salePrice);
            createStmt.setString(5,sellerID);
            createStmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    //deletes entry from database
    public static void Delete(String productID){
        try{
            PreparedStatement deleteStmt = conn.prepareStatement
            ("DELETE FROM items WHERE product_id = ?");
            deleteStmt.setString(1, productID);
            deleteStmt.executeUpdate();
            
        }
        catch(Exception e){
            System.out.println("Client Delete Error! ");
            e.printStackTrace();
        }
    }

    //READS DATABASE INTO TABLEMODEL FOR GUI
    public static DefaultTableModel readDatabase(){
    
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Product ID");
        table.addColumn("Quantity");
        table.addColumn("Wholesale Cost");
        table.addColumn("Sale Price");
        table.addColumn("Supplier ID");
        try{
        String sql = "SELECT product_id, quantity, wholesale_cost, sale_price, supplier_id "+
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
                    rs.getString("wholesale_cost"),
                    rs.getString("sale_price"),
                    rs.getString("supplier_id")
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

}
