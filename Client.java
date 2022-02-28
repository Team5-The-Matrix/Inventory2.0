import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;
public class Client{

static Connection conn = null;



//Constructs login string

public static String login(String user, String pass){
String loginString = null;
loginString = "user="+user+"&password="+pass;
return loginString;
}


public static boolean connect(String user,String password){
    boolean connected = false;
    LoadDriver driver = new LoadDriver();
    String name,pass,url;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://SG-InventoryManagement-5756-mysql-master.servers.mongodirector.com:3306/Inventory";
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
    public void Update(String productID, String quantity, String wholesaleCost, String salePrice, String sellerID){ //is this data passed in correct?
        try{
            PreparedStatement updateStmt = conn.prepareStatement
            ("UPDATE inventory SET quantity = ?, wholesale_cost = ?, sale_price = ?, supplier_id = ? WHERE product_id = ?");
            updateStmt.setString(5, productID);
            updateStmt.setString(1, quantity);
            updateStmt.setString(2, wholesaleCost);
            updateStmt.setString(3, salePrice);
            updateStmt.setString(4, sellerID);
            updateStmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    //create new entry in database
    public void Create(String productID, String quantity, String wholesaleCost, String salePrice, String sellerID){
        try{
            PreparedStatement createStmt = conn.prepareStatement
            ("INSERT INTO inventory VALUES(?,?,?,?,?)");
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
    public void Delete(String productID){
        try{
            PreparedStatement deleteStmt = conn.prepareStatement
            ("DELETE FROM inventory WHERE product_id = ?");
            deleteStmt.setString(1, productID);
            deleteStmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    //searches for and reads a line in database
    public String Search(String productID){
        try{
            PreparedStatement selectStmt = conn.prepareStatement
            ("SELECT product_id, quantity, wholesale_cost, sale_price, supplier_id FROM inventory WHERE product_id = ?");
            selectStmt.setString(1, productID);
            ResultSet selectRS = selectStmt.executeQuery();
            return selectRS.getString("product_id") + "," + selectRS.getString("quantity") + "," 
            + selectRS.getString("wholesale_cost") + "," + selectRS.getString("sale_price") + "," + selectRS.getString("supplier_id") + ",";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "search failed";
    }

}

