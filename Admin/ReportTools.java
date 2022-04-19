//ReportTools.java
//Author(s): Noah Pearson Kramer
//Backend for reporting tools - calls in ProfitCalculation.java


package Admin;

import java.util.Scanner;

public class ReportTools {

    public static void main(String[] args){}



    // TAKES CSV OF SAMPLE ORDERS AND RUNS THEM ON DATABASE 
    public void SimulateOrders(){

        Scanner sc = new Scanner(System.in);
        //TODO : READ CSV, RUN SIMULATED ORDERS ON DATABASE, CREATE NEW TABLE TO HOLD ORDER HISTORY

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
