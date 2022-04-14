//Analysis tool - Calculates company profits
//Author(s): June Luke

package Admin;
import java.sql.*;
import java.util.ArrayList;

public class ProfitCalculation{
    static Connection conn = null;


    //Calculates overall profit from all sales - all unit costs
    public static double OverallProfit(){
        double qty, wholesaleCost, salePrice;
        double totalGain = 0;
        double totalCost = 0;
        try{
            PreparedStatement profitStmt = conn.prepareStatement
            ("SELECT quantity, wholesale_cost, sale_price FROM customer_orders");//change customer_orders if needed
            ResultSet profitRS = profitStmt.executeQuery();
            while(profitRS.next()){
                qty = Double.parseDouble(profitRS.getString("quantity"));
                wholesaleCost = Double.parseDouble(profitRS.getString("wholesale_cost"));
                salePrice = Double.parseDouble(profitRS.getString("sale_price"));
                totalGain = totalGain + (qty * salePrice);
                totalCost = totalCost + (qty * wholesaleCost);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return totalGain - totalCost;
    }


    //Calculates the profit of item(s)
    public static double ItemProfit(ArrayList<String> itemIDs){
        double qty, wholesaleCost, salePrice;
        double totalGain = 0;
        double totalCost = 0;
        try{
            for(int i = 0; i < itemIDs.size(); i++){
                String[] str = Search(itemIDs.get(i));
                qty = Double.parseDouble(str[1]);
                wholesaleCost = Double.parseDouble(str[2]);
                salePrice = Double.parseDouble(str[3]);
                totalGain = totalGain + (qty * salePrice);
                totalCost = totalCost + (qty * wholesaleCost);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return totalGain - totalCost;
    }


    //searches for and reads a line in database
    public static String[] Search(String productID){
        String[] entry = new String[5];
        try{
            PreparedStatement selectStmt = conn.prepareStatement
            ("SELECT * FROM product WHERE product_id = "+ "'"+productID+"';");
            
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