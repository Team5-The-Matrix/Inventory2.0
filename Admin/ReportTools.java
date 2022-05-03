//ReportTools.java
//Author(s): Noah Pearson Kramer
//Backend for reporting tools - calls in ProfitCalculation.java


package Admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class ReportTools {

    public static void main(String[] args){}



    
    public static ReturnObject SimulateOrdersLocally(String path){
        Boolean success = false;
        ReturnObject obj = new ReturnObject();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            DefaultTableModel SimulatedOrders = new DefaultTableModel();
            SimulatedOrders.addColumn("Date");
            SimulatedOrders.addColumn("Customer Email");
            SimulatedOrders.addColumn("Customer Location");
            SimulatedOrders.addColumn("Product ID");
            SimulatedOrders.addColumn("Product Quantity");
            String line;
            while((line = br.readLine())!= null){
                String[] lines = line.split(",");
                SimulatedOrders.addRow(lines);
            }
            obj.success = true;
            obj.table = SimulatedOrders;
        }
        catch(Exception e){
            obj.success = false;
            e.printStackTrace();
            return obj;
        }
        return obj;

        
        //TODO : READ CSV, RUN SIMULATED ORDERS LOCALLY, CREATE NEW TABLE TO HOLD ORDER HISTORY


    }

    public static Boolean SimulateOrdersInDatabase(File csv){
        Boolean success = false;
        return success;
    }

    // Pulls data from database and order history for charting
    public void ChartData (){

        //TODO: CALL ORDER HISTORY FROM DATABASE, PASS TO GUI

    }
    //Creates projection based on linear regression of sales
    public void FinancialProjection(){

        //TAKE PAST ORDER HISTORY, DO A LINEAR REGRESSION FIT TO PROJECT SALES
    }

    //calls profitcalculation.java, etc
    public void ProfitLoss(){

        // CALL IN JUNES PROFIT CALCULATOR

    }
    

}
class ReturnObject{
boolean success = false;
DefaultTableModel table;

}