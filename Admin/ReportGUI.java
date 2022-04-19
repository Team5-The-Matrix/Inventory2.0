//ReportGUI.Java
// Author(s): Noah Pearson Kramer
// GUI For Database Analysis tools - Charts, Simulation, etc




package Admin;
import javax.swing.*;
import java.awt.Color;

public class ReportGUI {

public static void main(String[] args){

    JFrame page = new JFrame("Report Tools");
    JButton SimulateOrders = new JButton("Simulate Orders");
    JButton OrderHistory = new JButton("Order History");
    JButton FinancialProjection = new JButton("Financial Projections");
    JButton ProfitLoss = new JButton("Profit/ Loss Calculator");
    JPanel panel = new JPanel();

    panel.setBounds(400, 200,200,200);
    SimulateOrders.setBounds(20,20,200,30);
    OrderHistory.setBounds(20,60,200,30);
    FinancialProjection.setBounds(20,100, 200,30);
    ProfitLoss.setBounds(20,140,200,30);

    page.add(SimulateOrders);
    page.add(OrderHistory);
    page.add(FinancialProjection);
    page.add(ProfitLoss);
    panel.setBackground(Color.green);
    page.add(panel);
    page.setSize(1200,800);
    page.setVisible(true);
    page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
