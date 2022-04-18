//Analysis tool - Calculates company profits
//Author(s): June Luke

package Admin;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ProfitCalculation{
    static Connection conn = null;


    //Calculates overall profit from all sales - all unit costs
    public static double OverallProfit(){
        double qty, wholesaleCost, salePrice;
        double totalGain = 0;
        double totalCost = 0;
        try{
            PreparedStatement profitStmt = conn.prepareStatement
            ("SELECT product_id FROM customer_orders");//change customer_orders if needed
            ResultSet profitRS = profitStmt.executeQuery();
            while(profitRS.next()){
                String[] str = Search(profitRS.getString("product_id"));
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


    //Calculate profit between date1 (less recent) and date2 (more recent)
    public static double ProfitBetweenDates(String date1, String date2) throws ParseException{ //dates should be passed as "MM/DD/YYYY"
        double qty, wholesaleCost, salePrice;
        double totalGain = 0;
        double totalCost = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = dateFormat.parse(date1);
        Date d2 = dateFormat.parse(date2);
        ArrayList<String> productIDs = new ArrayList<>();
        Iterator<String> itr = productIDs.iterator();
        try{
            PreparedStatement profitStmt = conn.prepareStatement
            ("SELECT product_id, date FROM customer_orders");//change customer_orders if needed
            ResultSet profitRS = profitStmt.executeQuery();
            while(profitRS.next()){
                if(d1.before(d2)){
                    productIDs.add(profitRS.getString("product_id"));
                }
            }
            while(itr.hasNext()){
                String[] str = Search(itr.next());
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