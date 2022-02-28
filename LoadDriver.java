import java.sql.*;

public class LoadDriver {
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
