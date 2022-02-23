
import javax.swing.*;
import javax.swing.event.*;

public class InventoryManagerGUI {
    public static void main(String[] a) {
        JFrame f = new JFrame("Inventory Manager");
        String data[][] = { { "1", "1", "1", "1", "1" },
                { "1", "1", "1", "1", "1" },
                { "1", "1", "1", "1", "1" } };
        String column[] = { "Product Id", "Quanity", "Wholesale Cost", " Retail Price", "Vendor Id" };
        final JTable jt = new JTable(data, column);
        jt.setCellSelectionEnabled(true);
        ListSelectionModel select = jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int[] row = jt.getSelectedRows();
                int[] columns = jt.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (String) jt.getValueAt(row[i], columns[j]);
                    }
                }
                System.out.println("Table element selected is: " + Data);
            }
        });
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(800, 800);
        f.setVisible(true);
    }
}