package view;

import controller.POSController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class POSPanel extends JPanel {

    private final POSController posController;
    private final POSOrderPanel orderPanel;

    public POSPanel() {
        this.posController = new POSController();
        this.orderPanel = new POSOrderPanel();

        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 247));

        add(createTopToolbar(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JComponent createTopToolbar() {
        POSToolbarPanel toolbar = new POSToolbarPanel();
        toolbar.addToolbarListener(action -> handleToolbarAction(action));
        return toolbar;
    }

    private JComponent createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        POSSearchPanel searchPanel = new POSSearchPanel();
        searchPanel.addSearchListener(query -> handleSearch(query));
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        mainPanel.add(createContentArea(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JComponent createContentArea() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        POSCategoryPanel categoryPanel = new POSCategoryPanel();
        categoryPanel.addCategoryListener(category -> handleCategorySelection(category));

        POSProductGridPanel productGrid = new POSProductGridPanel(posController);
        productGrid.addProductListener(product -> handleProductSelection(product));

        contentPanel.add(categoryPanel, BorderLayout.WEST);
        contentPanel.add(productGrid, BorderLayout.CENTER);
        contentPanel.add(orderPanel, BorderLayout.EAST);

        return contentPanel;
    }

    private JComponent createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 242, 247));
        bottomPanel.setBorder(new EmptyBorder(12, 16, 12, 16));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        buttonPanel.setOpaque(false);

        JButton checkOutBtn = new JButton("Check Out");
        checkOutBtn.setBackground(new Color(239, 109, 137));
        checkOutBtn.setForeground(Color.WHITE);
        checkOutBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        checkOutBtn.setFocusPainted(false);
        checkOutBtn.setPreferredSize(new Dimension(100, 36));
        checkOutBtn.addActionListener(e -> handleCheckout());

        JLabel totalLabel = new JLabel("Total : 000");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel.setForeground(new Color(50, 50, 50));

        buttonPanel.add(checkOutBtn);
        buttonPanel.add(totalLabel);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        return bottomPanel;
    }

    private void handleToolbarAction(String action) {
        switch (action) {
            case "New":
                posController.clearOrder();
                orderPanel.clearOrder();
                break;
            case "Hold":
                JOptionPane.showMessageDialog(this, "Order on Hold");
                break;
            case "Bill List":
                JOptionPane.showMessageDialog(this, "Bill List");
                break;
            case "KOT": // Kitchen Order Ticket - send order to kitchen
                handleKOT();
                break;
            case "Dine In":
                JOptionPane.showMessageDialog(this, "Dine In Mode");
                break;
        }
    }

    // KOT (Kitchen Order Ticket) - sends current order to the kitchen
    private void handleKOT() {
        POSController.KitchenTicket ticket = posController.sendToKitchen();
        if (ticket != null) {
            JOptionPane.showMessageDialog(this, "Order sent to Kitchen\nTicket: " + ticket.getTicketId());
            orderPanel.clearOrder();
        } else {
            JOptionPane.showMessageDialog(this, "Order is empty. Add items before sending to kitchen.");
        }
    }

    private void handleCategorySelection(String category) {
        // Filter products by category
        System.out.println("Category selected: " + category);
    }

    private void handleProductSelection(String product) {
        // Add product to order
        POSController.Product prod = posController.getProduct(product);
        if (prod != null) {
            posController.addToOrder(product, 1);
            orderPanel.addItem(product, 1, prod.getPrice());
            updateTotal();
        }
    }

    private void handleSearch(String query) {
        // Search for products
        System.out.println("Search query: " + query);
    }

    private void handleCheckout() {
        double total = posController.calculateTotal();
        if (total > 0) {
            JOptionPane.showMessageDialog(this, "Checkout Total: " + total);
        } else {
            JOptionPane.showMessageDialog(this, "Order is empty");
        }
    }

    private void updateTotal() {
        // Update total label dynamically (requires passing reference to totalLabel)
    }
}
