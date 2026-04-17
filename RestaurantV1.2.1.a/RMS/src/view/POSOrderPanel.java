package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class POSOrderPanel extends JPanel {

    private final DefaultTableModel tableModel;
    private final JTable orderTable;

    public POSOrderPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(280, 400));
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(220, 224, 235)));

        String[] columnNames = {"SR#", "Product Name", "Qty", "Price", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);
        orderTable.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        orderTable.setRowHeight(24);
        orderTable.setBackground(Color.WHITE);

        JScrollPane tableScroll = new JScrollPane(orderTable);
        tableScroll.setBorder(new EmptyBorder(8, 8, 8, 8));
        add(tableScroll, BorderLayout.CENTER);
    }

    public void addItem(String productName, int quantity, double price) {
        double amount = quantity * price;
        Object[] row = {tableModel.getRowCount() + 1, productName, quantity, price, amount};
        tableModel.addRow(row);
    }

    public void clearOrder() {
        tableModel.setRowCount(0);
    }

    public double getTotalAmount() {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object amountObj = tableModel.getValueAt(i, 4);
            if (amountObj instanceof Number) {
                total += ((Number) amountObj).doubleValue();
            }
        }
        return total;
    }
}
