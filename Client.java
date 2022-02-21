import java.sql.*;

public class Client{


//TEST
    public static void main(String[] args){

        Connection conn = null;
        try{

            conn = DriverManager.getConnection("jdbc:mysql://SG-InventoryManagement-5756-mysql-master.servers.mongodirector.com:3306/Inventory");
            System.out.println("Connected to database! ");

            //ALL INTERACTIONS WITH DATABASE NEED TO BE IN A TRY/CATCH









        }
        catch(SQLException e){
            e.printStackTrace();

        }

        



    }
}

// WRITE CRUD METHODS HERE

