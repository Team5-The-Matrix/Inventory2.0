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
            //update variable names in string to correspond with database
            PreparedStatement updateStmt = conn.prepareStatement
            ("UPDATE Inventory SET quantity = ?, wholesale_cost = ?, sale_price = ?, supplier_id = ? WHERE product_id = ?");
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
            //update variable names in string to correspond with database
            PreparedStatement createStmt = conn.prepareStatement
            ("INSERT INTO Inventory VALUES(?,?,?,?,?)");
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

}

