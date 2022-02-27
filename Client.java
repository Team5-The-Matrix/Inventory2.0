import java.sql.*;

public class Client{


//TEST
static Connection conn = null;
    public static void main(String[] args){

        
        try{

            conn = DriverManager.getConnection("jdbc:mysql://SG-InventoryManagement-5756-mysql-master.servers.mongodirector.com:3306/Inventory"+ 
            "user=InventoryAdmin&password=");
            System.out.println("Connected to database! ");

            //ALL INTERACTIONS WITH DATABASE NEED TO BE IN A TRY/CATCH









        }
        catch(SQLException e){
            e.printStackTrace();

        }

        



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

