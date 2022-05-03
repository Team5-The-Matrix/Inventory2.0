//ReportGUI.Java
// Author(s): Noah Pearson Kramer
// GUI For Database Analysis tools - Charts, Simulation, etc




package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.date.DateChart01;
import org.knowm.xchart.internal.chartpart.Chart;

import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ReportGUI {

static List<Double> date = new ArrayList<Double>();
static List<Double> orders = new ArrayList<Double>();   
static DefaultTableModel orderTable;    
public static void setOrderTable(ReturnObject obj){
        orderTable = obj.table;
}

public static void simulateWindow(){

    
}


public static void main(String[] args){

    ////// Reporting Tools Page
    JFrame page = new JFrame("Report Tools");

    JButton SimulateOrders = new JButton("Simulate Orders");
    JButton OrderHistory = new JButton("Order History");
    JButton FinancialProjection = new JButton("Financial Projections");
    JButton ProfitLoss = new JButton("Profit/ Loss Calculator");
    JPanel panel = new JPanel();

    ////// Simulate Orders Page
    JFrame simulatePage = new JFrame("Simulate Orders");
    JTextField path = new JTextField();
    JLabel enterPath = new JLabel("Enter Path to Simulated Orders File:");
    JButton simulateLocal = new JButton("Simulate Orders Locally");
    String[][] output = {};
    String[] column = {"Output"};
    DefaultTableModel console = new DefaultTableModel(output,column);
    XChartPanel chart = null;
    JTable displayResults = new JTable(console);
    
    //SIMULATE ORDERS BUTTON
    try{
        SimulateOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulatePage.setLayout(null);
                path.setBounds(20, 100, 600, 40);
                enterPath.setBounds(20,50,400,20);
                simulateLocal.setBounds(40, 150, 200, 40);
                displayResults.setBounds(30, 300, 710, 100);  
                simulatePage.add(displayResults);
                simulatePage.add(path);
                simulatePage.add(enterPath);
                simulatePage.add(simulateLocal);
                simulatePage.setSize(800,750);
                simulatePage.setVisible(true);
                simulatePage.revalidate();
                simulatePage.repaint();
                
            }
        });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    // ORDER HISTORY GRAPH
        try{
            OrderHistory.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    XYChart chart = QuickChart.getChart("Order History", "Date", "Orders", "Orders", date, orders);
                    new SwingWrapper<>(chart).displayChart();
                }
            });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


    //SIMULATE ORDERS LOCALLY BUTTON
    try{
        simulateLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rowInput = "Simulating Orders Locally...";
                String[] row = {rowInput};
                console.addRow(row);
                String filePath = path.getText();
                ReturnObject obj = ReportTools.SimulateOrdersLocally(filePath);
                if (obj.success){
                     setOrderTable(obj);
                    rowInput = "Simulated Orders, exit this window to view reports";
                    row[0] = rowInput;
                    console.addRow(row); 
                    
                    for(int i = 0; i <obj.table.getRowCount(); i++){
                        orders.add(Double.parseDouble(obj.table.getValueAt(i, 4).toString()));
                        date.add(Double.parseDouble(obj.table.getValueAt(i, 0).toString().replace("-","")));
                    }
                    
                }
                else{
                    rowInput = "File Not Found! Try Again";
                    row[0] = rowInput;
                    console.addRow(row);

                }
                
            }
        });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    //SIMULATE ORDERS IN DATABASE BUTTON
    
    
    SimulateOrders.setBounds(20,20,200,30);
    OrderHistory.setBounds(20,60,200,30);
    FinancialProjection.setBounds(20,100, 200,30);
    ProfitLoss.setBounds(20,140,200,30);

    page.add(SimulateOrders);
    page.add(OrderHistory);
    page.add(FinancialProjection);
    page.add(ProfitLoss);
    
    page.setSize(200,200);
    page.setLayout(null);
    page.setVisible(true);
    page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

